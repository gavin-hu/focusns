<div class="widget">
    <div class="widget-hd">
        <h2>管理菜单</h2>
    </div>
    <div class="widget-bd">
        <div class="admin-menu">
            <#list Request.features as feature>
            <#if feature.code=='profile'>
                <h3>${feature.label}管理</h3>
                <ul>
                    <li><a href="${Request.contextPath}/${Request.project.code}/setting/profile/logo-edit">标志编辑</a></li>
                    <li><a href="${Request.contextPath}/${Request.project.code}/setting/profile/project-edit">空间编辑</a></li>
                    <li><a href="${Request.contextPath}/${Request.project.code}/setting/profile/attribute-edit">属性编辑</a></li>
                </ul>
            </#if>
            <#if feature.code=='blog'>
                <h3>${feature.label}管理</h3>
                <ul>
                    <li><a href="${Request.contextPath}/${Request.project.code}/setting/blog/post-edit">博文编辑</a></li>
                    <li><a href="${Request.contextPath}/${Request.project.code}/setting/blog/category-edit">分类编辑</a></li>
                </ul>
            </#if>
            </#list>
        </div>
    </div>
</div>