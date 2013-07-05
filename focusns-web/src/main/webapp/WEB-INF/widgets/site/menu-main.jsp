<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ul class="nav">
    <li <c:if test="${fn:startsWith(pageConfig.path, '/index')}">class="active"</c:if> >
        <a href='<c:url value="/" />'>首页</a>
    </li>
    <c:forEach items="${categories}" var="category">
    <c:set var="categoryPath" value="/${category.code}"/>
    <li <c:if test="${fn:startsWith(pageConfig.path, categoryPath)}">class="active"</c:if>>
        <a href="<c:url value="/${category.code}"/>">${category.label}</a>
    <li>
    </c:forEach>
</ul>