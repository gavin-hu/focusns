<div class="widget">
    <div class="widget-hd">
        <h2>分类编辑</h2>
    </div>
    <div class="widget-bd">
        <div class="admin-category">
            <form action="${Request.contextPath}/blog/category/edit" method="post">
                <input name="label" value="" />
                <input type="hidden" name="projectId" value="${Request.project.id}" />
                <input type="hidden" name="createById" value="${Session.user.id}" />
                <button type="submit" name="action" value="create">添加</button>
            </form>
            <#list Request.blogCategories as blogCategory>
            <form action="${Request.contextPath}/blog/category/edit" method="post">
                <input name="label" value="${blogCategory.label}" />
                <input type="hidden" name="id" value="${blogCategory.id}" />
                <input type="hidden" name="projectId" value="${Request.project.id}" />
                <input type="hidden" name="createById" value="${Session.user.id}" />
                <button type="submit" name="action" value="modify">修改</button>
                <button type="submit" name="action" value="remove">删除</button>
            </form>
            </#list>
        </div>
    </div>
</div>