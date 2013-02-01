<div class="console-login">
    <form action="${Request.contextPath}/console/login" method="post">
        <div>
            <label>账号</label>
            <input name="username" />
        </div>
        <div>
            <label>口令</label>
            <input type="password" name="password" />
        </div>
        <div class="submit">
            <button type="submit" name="redirect"
                    value="${Request.contextPath}/console/summary-db">登录</button>
        </div>
    </form>
</div>