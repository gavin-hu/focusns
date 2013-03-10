<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
    <c:when test="${sessionScope.user!=null}">
    <ul>
        <li>
            <a href='<c:url value="/${sessionScope.user.project.code}/profile" /> '>
                    ${sessionScope.user.username}
            </a>
        </li>
        <li>
            <a href='<c:url value="/site/logout.action" /> '>退出</a>
        </li>
    </ul>
    </c:when>
    <c:otherwise>
    <ul>
        <li>
            <c:set value="redirectPath=/console/cms" var="queryString" />
            <c:forEach items="${param}" var="paramKV" varStatus="status">
                <c:choose>
                    <c:when test="${status.first}">
                        <c:set value="?" var="queryString" />
                    </c:when>
                    <c:otherwise>
                        <c:set value="${queryString}&" var="queryString" />
                    </c:otherwise>
                </c:choose>
                <c:set value="${queryString}redirect${paramKV.key}=${paramKV.value}" var="queryString" />
            </c:forEach>
            <a href='<c:url value="/login${queryString}" />'>登录</a>
        </li>
        <li>
            <a href='<c:url value="/register" />'>注册</a>
        </li>
    </ul>
    </c:otherwise>
</c:choose>