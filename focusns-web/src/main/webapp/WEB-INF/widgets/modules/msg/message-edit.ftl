<div class="widget">
    <div class="widget-hd">
        <h3>私信编辑</h3>
    </div>
    <div class="widget-bd">
        <div class="message-edit">
            <form action="" method="post">
                <div class="title">
                    <label>标题</label>
                    <input name="title"  />
                </div>
                <div class="content">
                    <label>内容</label>
                    <textarea id="content" name="content"></textarea>
                </div>
                <div class="submit">
                     <button type="submit">发送</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
$(function(){
    $('#content').xheditor({skin:'nostyle', tools:'simple'});
});
</script>