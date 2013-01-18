<#if Session.user??>
<ul>
    <li>
        <a href="${Request.contextPath}/${Session.user.project.code}/profile">Gavin Hu</a>
    </li>
    <li>
        <a href="${Request.contextPath}/site/logout">退出</a>
    </li>
</ul>
<#else>
<ul>
    <li>
        <a href="${Request.contextPath}/login">登录</a>
    </li>
    <li>
        <a href="${Request.contextPath}/register">注册</a>
    </li>
</ul>
</#if>