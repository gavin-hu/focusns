<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ul class="nav">
    <li <c:if test="${'/index' eq param.path}">class="active"</c:if> >
        <a href='<c:url value="/index" />'>首页</a>
    </li>
    <c:forEach items="${requestScope.categories}" var="category">
    <li <c:if test="${'/'.concat(category.code) eq param.path}">class="active"</c:if> >
        <a href='<c:url value="/${category.code}"/> '>${category.label}</a>
    <li>
    </c:forEach>
</ul>