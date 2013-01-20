<#import "/WEB-INF/libftl/html.ftl" as html>

<div class="widget">
    <div class="widget-hd">
        <h2>分类</h2>
    </div>
    <div class="widget-bd">
        <div class="blog-categories">
            <@html.ul items=Request.blogCategories ; blogCategory>
                <a href="${Request.contextPath}/${Request.project.code}/${Request.feature.code}<#if blogCategory.id gt 0>?categoryId=${blogCategory.id}</#if>">
                    <h3>${blogCategory.label}</h3>
                </a>
            </@html.ul>
        </div>
    </div>
</div>