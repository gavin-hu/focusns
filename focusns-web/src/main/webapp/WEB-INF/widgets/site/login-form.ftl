<div class="login">
    <form action="${Request.contextPath}/login.action" method="post">
        <div>
            <label>用户名</label>
            <input name="username" />
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