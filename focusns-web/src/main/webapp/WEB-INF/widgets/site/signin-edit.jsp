<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-head>
        <h3>登录</h3>
    </ui:widget-head>
    <ui:widget-body>
        <c:if test="${not empty AuthenticationException}">
            <div class="alert alert-error">
                身份验证失败，请重新登录！
            </div>
        </c:if>
        <form class="valid-inline form-horizontal" action='<c:url value="/signin"/>' method="post">
            <div class="control-group">
                <label class="control-label" for="inputUsername">邮箱</label>
                <div class="controls">
                    <input type="text" id="inputUsername" name="username" placeholder="Email"
                           data-rule-required="true" data-msg-required="邮箱不能为空！"
                           data-rule-email="true" data-msg-email="邮箱格式错误！">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="inputUsername">密码</label>
                <div class="controls">
                    <input type="password" id="inputPassword" name="password" placeholder="Password"
                           data-rule-required="true" data-msg-required="密码不能为空！"
                           data-rule-minlength="6" data-msg-minlength="密码长度必须大于6个字符！">
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <label class="checkbox">
                        <input type="checkbox" name="rememberMe" > 记住我
                    </label>
                    <button type="submit" class="btn btn-primary">登录</button>
                    <a class="btn btn-link" href="<c:url value="/signup" />">注册</a>
                </div>
            </div>
        </form>
    </ui:widget-body>
</ui:widget>