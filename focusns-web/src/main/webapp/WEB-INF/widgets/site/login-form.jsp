<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-head>
        <h3>登录</h3>
    </ui:widget-head>
    <ui:widget-body>
        <form class="form-horizontal" action='<c:url value="/login"/>' method="post">
            <div class="control-group">
                <label class="control-label" for="inputUsername">邮箱</label>
                <div class="controls">
                    <input type="text" id="inputUsername" name="username" placeholder="Email">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="inputUsername">密码</label>
                <div class="controls">
                    <input type="password" id="inputPassword" name="password" placeholder="Password">
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <label class="checkbox">
                        <input type="checkbox" name="rememberMe" > 记住我
                    </label>
                    <button type="submit" class="btn btn-primary">登录</button>
                    <a class="btn btn-link" href="<c:url value="/register" />">注册</a>
                </div>
            </div>
        </form>
    </ui:widget-body>
</ui:widget>