<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-bd>
        <div class="thumbnail">
            <t:img-avatar dimension="260" projectId="${project.id}" projectUserId="${projectUser.id}" />
            <div class="caption">
                <h3>${projectUser.username}</h3>
                <p>
                    <c:if test="${not empty sessionScope.user}">
                    <a href='<c:url value="/${project.code}/${projectFeature.code}?mode=edit" />' class="btn">编辑</a>

                    <c:if test="${user.projectId != project.id}">
                        <c:url value="${currentPath}" var="redirectTo" />
                        <c:choose>
                            <c:when test="${empty projectLink}">
                                <a class="btn" href='<c:url value="/project/link/create.action?fromProjectId=${fromProject.id}&toProjectId=${toProject.id}&redirect=${redirectTo}" />'>添加关注</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn" href='<c:url value="/project/link/remove.action?fromProjectId=${projectLink.fromProjectId}&toProjectId=${projectLink.toProjectId}&redirect=${redirectTo}" />'>取消关注</a>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    </c:if>
                </p>
            </div>
        </div>
    </ui:widget-bd>
</ui:widget>