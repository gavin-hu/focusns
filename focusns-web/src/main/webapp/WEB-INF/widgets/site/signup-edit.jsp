<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-head>
        <h3>注册</h3>
    </ui:widget-head>
    <ui:widget-body>
        <form class="valid form-horizontal" action="<widget:actionUrl value="/site/signup" />" method="post">
            <div class="control-group">
                <label class="control-label" for="inputEmail">邮箱</label>
                <div class="controls">
                    <input type="text" id="inputEmail" name="email" placeholder="Email"
                            data-rule-required="true" data-msg-required="邮箱不能为空！"
                            data-rule-email="true" data-msg-email="邮箱格式错误！">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="inputPassword">密码</label>
                <div class="controls">
                    <input type="password" id="inputPassword" name="password" placeholder="Password"
                           data-rule-required="true" data-msg-required="密码不能为空！"
                           data-rule-minlength="6" data-msg-minlength="密码长度必须大于6个字符！">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="inputNickname">昵称</label>
                <div class="controls">
                    <input type="password" id="inputNickname" name="nickname" placeholder="Nickname"
                           data-rule-required="true" data-msg-required="昵称不能为空！">
                </div>
            </div>

            <div class="control-group">
                <div class="controls">
                    <button type="submit" class="btn btn-primary">注册</button>
                    <a class="btn btn-link" href="<c:url value="/signin" />">登录</a>
                </div>
            </div>
        </form>
    </ui:widget-body>
</ui:widget>