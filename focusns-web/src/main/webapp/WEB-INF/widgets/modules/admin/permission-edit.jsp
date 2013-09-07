<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-head>
        <h3>权限编辑</h3>
    </ui:widget-head>
    <ui:widget-body>
        <tool:alert-status status="permissions-modified" statusMessage="编辑成功" />

        <form action="<widget:actionUrl value="/admin/permission-modify"/>"  method="post">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>角色</th>
                    <th>编码</th>
                    <th>描述</th>
                    <th>状态</th>
                </tr>
                </thead>
                <tbody>

                <c:set var="projectPermissionCounter" value="0"/>
                <c:forEach items="${projectRoles}" var="projectRole">

                    <c:forEach items="${projectRolePermissionMap}" var="entry">
                        <c:if test="${projectRole.id eq entry.key.id}">
                            <%-- set attributes --%>
                            <c:set var="projectRole" value="${entry.key}" scope="page"/>
                            <c:set var="projectPermissions" value="${entry.value}" scope="page"/>
                        </c:if>
                    </c:forEach>

                    <c:choose>
                        <c:when test="${not empty projectPermissions}">
                            <c:forEach items="${projectPermissions}" var="projectPermission" varStatus="status">
                                <tr>
                                    <c:if test="${status.first}">
                                        <td rowspan="${fn:length(projectPermissions)}">${projectPermission.projectRole.label}</td>
                                    </c:if>
                                    <td>${projectPermission.projectAuthority.code}</td>
                                    <td>${projectPermission.projectAuthority.description}</td>
                                    <td>
                                        <input type="checkbox" name="projectPermissions[${projectPermissionCounter}].enabled"
                                               value="${projectPermission.enabled}" <c:if test="${projectPermission.enabled}">checked="true" </c:if> />
                                        <input type="hidden" name="projectPermissions[${projectPermissionCounter}].id" value="${projectPermission.projectId}"/>
                                        <input type="hidden" name="projectPermissions[${projectPermissionCounter}].projectId" value="${projectPermission.projectId}"/>
                                        <input type="hidden" name="projectPermissions[${projectPermissionCounter}].projectRoleId" value="${projectPermission.projectRoleId}"/>
                                        <input type="hidden" name="projectPermissions[${projectPermissionCounter}].projectAuthorityId" value="${projectPermission.projectAuthorityId}"/>
                                    </td>
                                </tr>
                                <c:set var="projectPermissionCounter" value="${projectPermissionCounter+1}"/>
                            </c:forEach>
                            <%-- remove attributes --%>
                            <c:remove var="projectRole" scope="page"/>
                            <c:remove var="projectPermissions" scope="page"/>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${projectAuthorities}" var="projectAuthority" varStatus="status">
                                <tr>
                                    <c:if test="${status.first}">
                                        <td rowspan="${fn:length(projectAuthorities)}">${projectRole.label}</td>
                                    </c:if>
                                    <td>${projectAuthority.code}</td>
                                    <td>${projectAuthority.description}</td>
                                    <td>
                                        <input type="checkbox" name="projectPermissions[${projectPermissionCounter}].enabled"/>
                                        <input type="hidden" name="projectPermissions[${projectPermissionCounter}].projectId" value="${project.id}"/>
                                        <input type="hidden" name="projectPermissions[${projectPermissionCounter}].projectRoleId" value="${projectRole.id}"/>
                                        <input type="hidden" name="projectPermissions[${projectPermissionCounter}].projectAuthorityId" value="${projectAuthority.id}"/>
                                    </td>
                                </tr>
                                <c:set var="projectPermissionCounter" value="${projectPermissionCounter+1}"/>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                </tbody>
            </table>
            <button type="submit" class="btn btn-primary">提交</button>
            <input type="hidden" name="projectId" value="${project.id}">
        </form>
    </ui:widget-body>
</ui:widget>