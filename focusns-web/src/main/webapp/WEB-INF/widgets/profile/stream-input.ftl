<#import "/WEB-INF/libftl/utils.ftl" as utils>

<div class="widget">
    <div class="widget-bd">
        <div class="share">
            <form action="<@utils.urlPrefix />/project/histroy-edit.action" method="post">
                <textarea name="content" rows="3" cols="50"></textarea>
                <input type="hidden" name="createById" value="${Session.user.id}" />
                <input type="hidden" name="targetId" value="${Request.project.id}" />
                <input type="hidden" name="targetType" value="${Request.project.class}" />
                <input type="hidden" name="projectId" value="${Request.project.id}" />

                <div class="weibo-actions">
                    <span class="action-share">
                        <button type="submit" name="action" value="create">分享</button>
                    </span>
                </div>
            </form>
        </div>
    </div>
</div>