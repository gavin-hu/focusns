<#import "/WEB-INF/libftl/utils.ftl" as utils>

<div class="widget">
    <div class="widget-hd">
        <h2>博文编辑</h2>
    </div>
    <div class="widget-bd">
        <div class="admin-post">
            <form action="<@utils.urlPrefix />/post/edit" method="post">
                <div>
                    <label>标题</label>
                    <input name="title" value="" />
                </div>
                <div>
                    <label>分类</label>
                    <select name="category">
                        <option >未分类</option>
                    </select>
                </div>
                <div>
                    <label>内容</label>
                    <textarea name="content"></textarea>
                </div>
            </form>
        </div>
    </div>
</div>