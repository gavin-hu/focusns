<#import "../common.ftl" as c>

<div id="stream">
    <form action="<@c.urlPrefix />/project/histroy-edit.action" method="post">
        <textarea name="content"></textarea>

        <div class="submit">
            <input type="hidden" name="createById" value="${Session.user.id}" />
            <input type="hidden" name="targetId" value="${Request.project.id}" />
            <input type="hidden" name="targetType" value="${Request.project.class}" />
            <input type="hidden" name="projectId" value="${Request.project.id}" />
            <button type="submit" name="action" value="create">分享</button>
        </div>
    </form>
    
</div>