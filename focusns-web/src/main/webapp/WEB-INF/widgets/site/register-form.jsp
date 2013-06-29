<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-head>
        <h3>注册</h3>
    </ui:widget-head>
    <ui:widget-body>
        <form class="form-horizontal" action="<c:url value="/site/register-user.action" />" method="post">
            <div class="control-group">
                <label class="control-label" for="inputEmail">邮箱</label>
                <div class="controls">
                    <input type="text" id="inputEmail" name="email" placeholder="Email">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="inputPassword">密码</label>
                <div class="controls">
                    <input type="password" id="inputPassword" name="password" placeholder="Password">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="inputNickname">昵称</label>
                <div class="controls">
                    <input type="password" id="inputNickname" name="nickname" placeholder="Nickname">
                </div>
            </div>

            <div class="control-group">
                <div class="controls">
                    <button type="submit" class="btn btn-primary">注册</button>
                    <a class="btn btn-link" href="<c:url value="/login" />">登录</a>
                </div>
            </div>
        </form>
    </ui:widget-body>
</ui:widget>