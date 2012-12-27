<#list Request.features as feature>
<#if feature.code=='profile'>
<h3>${feature.label}管理</h3>
<ul class="sidemenu">
    <li><a href="${Request.contextPath}/${Request.project.code}/admin/profile/logo-edit">LOGO编辑</a></li>
    <li><a href="${Request.contextPath}/${Request.project.code}/admin/profile/project-edit">空间编辑</a></li>
    <li><a href="${Request.contextPath}/${Request.project.code}/admin/profile/attribute-edit">属性编辑</a></li>
</ul>
</#if>
<#if feature.code=='blog'>
<h3>${feature.label}管理</h3>
<ul class="sidemenu">
    <li><a href="${Request.contextPath}/${Request.project.code}/admin/blog/post-edit">博文编辑</a></li>
    <li><a href="${Request.contextPath}/${Request.project.code}/admin/blog/tag-edit">分类编辑</a></li>
</ul>
</#if>
</#list>