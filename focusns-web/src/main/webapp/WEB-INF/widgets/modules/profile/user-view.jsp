<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<div class="widget">
    <div class="widget-bd">
        <div class="profile-user">
            <ui:avatar dimension="65" projectId="${project.id}" projectUserId="${projectUser.id}" />
            <ul class="actions">
                <li>
                    <a href='<c:url value="/${project.code}/profile?mode=edit" />'>编辑</a>
                </li>
                <c:if test="${user.projectId != project.id}">
                <li>
                    <c:url value="${currentPath}" var="redirectTo" />
                    <c:choose>
                        <c:when test="${empty projectLink}">
                            <a href='<c:url value="/project/link/create.action?fromProjectId=${fromProject.id}&toProjectId=${toProject.id}&redirect=${redirectTo}" />'>添加关注</a>
                        </c:when>
                        <c:otherwise>
                            <a href='<c:url value="/project/link/remove.action?fromProjectId=${projectLink.fromProjectId}&toProjectId=${projectLink.toProjectId}&redirect=${redirectTo}" />'>取消关注</a>
                        </c:otherwise>
                    </c:choose>
                </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>