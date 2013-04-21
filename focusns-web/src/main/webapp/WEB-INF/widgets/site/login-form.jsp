<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-bd>
        <form action='<c:url value="/site/login.action"/>' method="post">
            <fieldset>
                <legend>登录</legend>
                <label>用户名</label>
                <input class="span2" type="text" name="username" placeholder="Email">
                <label>密码</label>
                <input class="span2" type="password" name="password" placeholder="Password">
                <label class="checkbox">
                    <input type="checkbox"> 记住我
                </label>
                <button type="submit" class="btn btn-primary">登录</button>
                <button type="reset" class="btn">重置</button>
            </fieldset>
        </form>
    </ui:widget-bd>
</ui:widget>