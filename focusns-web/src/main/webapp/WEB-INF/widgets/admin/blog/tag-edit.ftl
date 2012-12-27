<div id="">
    <form action="${Request.contextPath}/${Request.project.code}/${Request.feature.code}/blog/tag-edit.action" method="post">
        <input name="name" value="" />
        <input type="hidden" name="projectId" value="${Request.project.id}" />
        <button type="submit" name="action" value="create">添加</button>
    </form>
    <#list Request.blogTags as blogTag>
    <form action="${Request.contextPath}/${Request.project.code}/${Request.feature.code}/blog/tag-edit.action" method="post">
        <input name="name" value="${blogTag.name}" />
        <input type="hidden" name="id" value="${blogTag.id}" />
        <input type="hidden" name="projectId" value="${Request.project.id}" />
        <button type="submit" name="action" value="modify">修改</button>
        <button type="submit" name="action" value="remove">删除</button>
    </form>
    </#list>
</div>