<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="login">
    <form action='<c:url value="/widget/site/login-action"/>' method="post">
        <div>
            <label>用户名</label>
            <input name="username" value="" />
        </div>
        <div>
            <label>密码</label>
            <input type="password" name="password" />
        </div>
        <div class="submit">
            <button type="submit">登录</button>
        </div>
    </form>
</div>