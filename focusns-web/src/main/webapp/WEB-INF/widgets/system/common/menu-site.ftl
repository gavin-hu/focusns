<ul>
    <li><a href="${Request.contextPath}/index">首页</a></li>
    <#list Request.categories as category>
    <li><a href="${Request.contextPath}/${category.code}">${category.label}</a><li>
    </#list>
</ul>