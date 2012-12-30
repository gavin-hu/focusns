<#import "/WEB-INF/libftl/utils.ftl" as utils>

<div class="widget">
    <div class="widget-hd">
        <h2>分类编辑</h2>
    </div>
    <div class="widget-bd">
        <div class="admin-category">
            <form action="<@utils.urlPrefix />/blog/category-edit.action" method="post">
                <input name="label" value="" />
                <input type="hidden" name="projectId" value="${Request.project.id}" />
                <button type="submit" name="action" value="create">添加</button>
            </form>
            <#list Request.blogCategories as blogCategory>
            <form action="<@utils.urlPrefix />/blog/category-edit.action" method="post">
                <input name="name" value="${blogCategory.label}" />
                <input type="hidden" name="id" value="${blogCategory.id}" />
                <input type="hidden" name="projectId" value="${Request.project.id}" />
                <button type="submit" name="action" value="modify">修改</button>
                <button type="submit" name="action" value="remove">删除</button>
            </form>
            </#list>
        </div>
    </div>
</div>