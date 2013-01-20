<#import "/WEB-INF/libftl/utils.ftl" as utils>

<div class="widget">
    <div class="widget-bd">
        <div class="share">
            <form action="${Request.contextPath}/project/history/create" method="post">
                <textarea name="content"></textarea>
                <input type="hidden" name="createById" value="${Request.template.createById}" />
                <input type="hidden" name="targetId" value="${Request.template.targetId}" />
                <input type="hidden" name="targetType" value="${Request.template.targetType}" />
                <input type="hidden" name="projectId" value="${Request.template.projectId}" />

                <div class="submit">
                    <button type="submit" name="redirect" value="<@utils.redirect />">回复</button>
                </div>
            </form>
        </div>
    </div>
</div>