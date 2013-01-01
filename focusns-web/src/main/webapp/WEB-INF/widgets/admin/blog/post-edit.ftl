<div class="widget">
    <div class="widget-hd">
        <h2>博文编辑</h2>
    </div>
    <div class="widget-bd">
        <div class="admin-post">
            <form action="${Request.contextPath}/blog/post/edit" method="post">
                <div>
                    <label>标题</label>
                    <input name="title" value="" />
                </div>
                <div>
                    <label>分类</label>
                    <select name="categoryId">
                        <option value="-1">未分类</option>
                        <#list Request.blogCategories as blogCategory>
                        <option value="${blogCategory.id}">${blogCategory.label}</option>
                        </#list>
                    </select>
                </div>
                <div>
                    <label>内容</label>
                    <textarea id="content" name="content"></textarea>
                </div>
                <div class="submit">
                    <input type="hidden" name="createById" value="${Session.user.id}" />
                    <button type="submit" name="action" value="create">提交</button>
                <div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
$(function(){
    $('#content').xheditor({skin:'nostyle'});
});
</script>