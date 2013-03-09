<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${sessionScope.user!=null}">
    <ul>
        <li>
            <a href='<c:url value="/${sessionScope.user.project.code}/profile" /> '>
                    ${sessionScope.user.username}
            </a>
        </li>
        <li>
            <a href='<c:url value="/widget/site/logout-action" /> '>退出</a>
        </li>
    </ul>
    </c:when>
    <c:otherwise>
    <ul>
        <li>
            <a href='<c:url value="/login" />'>登录</a>
        </li>
        <li>
            <a href='<c:url value="/register" />'>注册</a>
        </li>
    </ul>
    </c:otherwise>
</c:choose>