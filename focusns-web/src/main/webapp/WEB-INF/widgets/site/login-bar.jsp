<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<form class="navbar-form pull-right" action='<c:url value="/site/login.action"/>' method="post">
    <input class="span2" type="text" name="username" placeholder="Email">
    <input class="span2" type="password" name="password" placeholder="Password">
    <button type="submit" class="btn">登录</button>
</form>