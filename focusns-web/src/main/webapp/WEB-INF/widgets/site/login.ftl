<#import "/WEB-INF/libftl/form.ftl" as form>

<div class="login">
    <@form.form name="login" action="${Request.contextPath}/site/login">
        <div>
            <label>用户名</label>
            <@form.textInput name="username" path="login.username" />
        </div>
        <div>
            <label>密码</label>
            <@form.pwdInput name="password" path="login.password" />
        </div>
        <div class="submit">
            <button type="submit">登录</button>
        </div>
    </@form.form>
</div>