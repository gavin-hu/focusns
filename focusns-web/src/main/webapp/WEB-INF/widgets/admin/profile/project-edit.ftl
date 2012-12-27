<div id="">
    <form action="${Request.contextPath}/${Request.project.code}/${Request.feature.code}/project-edit.action" method="post">
        <label>编码</label>
        <input name="code" value="${Request.project.code}" />
        <label>标题</label>
        <input name="title" value="${Request.project.title}" />
        <label>描述</label>
        <textarea name="description" rows="5" cols="5">
            ${Request.project.description}
        </textarea>
        <div class="submit">
            <input type="hidden" name="id" value="${Request.project.id}" />
            <button type="submi">提交</button>
        </div>
    </form>
    
</div>