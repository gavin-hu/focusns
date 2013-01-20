<div class="widget">
    <div class="widget-hd">
        <h2>空间编辑</h2>
    </div>
    <div class="widget-bd">
        <div class="setting-project">
            <form action="${Request.contextPath}/setting/project/edit" method="post">
                <div>
                    <label>编码</label>
                    <input name="code" value="${Request.project.code}" />
                </div>
                <div>
                    <label>标题</label>
                    <input name="title" value="${Request.project.title}" />
                </div>
                <div>
                    <label>描述</label>
                    <textarea name="description">
                        ${Request.project.description}
                    </textarea>
                </div>
                <div class="submit">
                    <input type="hidden" name="id" value="${Request.project.id}" />
                    <button type="submit">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>