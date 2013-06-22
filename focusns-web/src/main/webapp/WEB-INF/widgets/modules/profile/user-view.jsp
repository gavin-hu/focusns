<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-body>
        <div class="thumbnail">
            <tool:img-avatar projectUserId="${projectUser.id}" width="260" height="260" />
            <div class="caption">
                <h3>${projectUser.username}</h3>
                <p>
                    <c:if test="${not empty sessionScope.projectUser}">
                        <a href='<c:url value="/${projectFeature.code};p=${project.code},m=edit" />' class="btn">编辑</a>

                        <c:if test="${user.projectId != project.id}">
                            <c:choose>
                                <c:when test="${empty projectLink}">
                                    <a class="btn" href='<widget:actionUrl value="/project/link-create?fromProjectId=${fromProject.id}&toProjectId=${toProject.id}" />'>添加关注</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="btn" href='<widget:actionUrl value="/project/link-remove?fromProjectId=${projectLink.fromProjectId}&toProjectId=${projectLink.toProjectId}" />'>取消关注</a>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </c:if>
                </p>
            </div>
        </div>
    </ui:widget-body>
</ui:widget>