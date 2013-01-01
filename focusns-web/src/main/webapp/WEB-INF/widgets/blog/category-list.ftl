<div class="widget">
    <div class="widget-hd">
        <h2>分类</h2>
    </div>
    <div class="widget-bd">
        <div class="blog-categories">
            <ul>
                <#list Request.blogCategories as blogCategory>
                <li>${blogCategory.label}</li>
                </#list>
            </ul>
        </div>
    </div>
</div>