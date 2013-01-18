<#import "/WEB-INF/libftl/utils.ftl" as utils>

<div class="widget">
    <div class="widget-bd">
        <div class="stream">
            <@utils.ul items=Request.page.results ; histroy>
            <form action="" method="post">
                <div>
                    <a href="${Request.contextPath}/">
                        <img class="thumbnail" src="${Request.contextPath}/project/${histroy.projectId}/logo" />
                    </a>
                </div>
                <div class="activity">
                    <div class="content">${histroy.content}</div>
                    <div class="info">
                        <abbr class="date" title="${histroy.createAt?datetime}">${histroy.createAt?string("yyyy-MM-dd HH:mm")}</abbr>
                    </div>
                    <div class="reply">
                        <textarea name="content"></textarea>
                    </div>
                </div>
            </form>
            </@utils.ul>
        </div>
    </div>
</div>

<script type="text/javascript">
$(function(){
    // time ago
    $('abbr.date').timeago();
    //
    $('div.reply textarea').focus(function(){
        $(this).height(50);
    });
});
</script>