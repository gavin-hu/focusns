<div id="login">
    <form action="${Request.contextPath}/login.action" method="post">
        <table>
            <tr>
                <th>用户名</th>
                <td><input name="username" /></td>
            </tr>
            <tr>
                <th>密码</th>
                <td><input type="password" name="password" /></td>
            </tr>
        </table>
        <div class="submit">
            <button type="submit">登录</button>
        </div>
    </form>
</div>