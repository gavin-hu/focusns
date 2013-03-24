<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="widget">
    <div class="widget-hd">
        <h2>关注</h2>
    </div>
    <div class="widget-bd">
        <div class="link-edit">
            <c:url value="${currentPath}" var="redirectTo" />
            <c:choose>
                <c:when test="${empty projectLink}">
                    <a href='<c:url value="/project/link/create?fromProjectId=${fromProject.id}&toProjectId=${toProject.id}&redirect=${redirectTo}" />'>添加关注</a>
                </c:when>
                <c:otherwise>
                    <a href='<c:url value="/project/link/remove?fromProjectId=${projectLink.fromProjectId}&toProjectId=${projectLink.toProjectId}&redirect=${redirectTo}" />'>取消关注</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>