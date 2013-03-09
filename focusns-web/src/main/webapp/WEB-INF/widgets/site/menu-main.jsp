<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<ul class="menu">
    <li><a href='<c:url value="/index"/>' >首页</a></li>
    <c:forEach items="${requestScope.categories}" var="category">
    <li><a href='<c:url value="/${category.code}"/> '>${category.label}</a><li>
    </c:forEach>
</ul>

<div class="search">
    <form class="ajax" action="#">
        <select name="category">
            <option value="">全部</option>
            <c:forEach items="${requestScope.categories}" var="category">
            <option value="${category.code}">${category.label}</option>
            </c:forEach>
        </select>
        <input type="text" name="keywords" />
        <input type="submit" value="搜索" />
    </form>
</div>
</div>
</div>