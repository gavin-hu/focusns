<#import "/WEB-INF/libftl/html.ftl" as html>
<#import "/WEB-INF/libftl/form.ftl" as form>
<#import "/WEB-INF/libftl/utils.ftl" as utils>


<div class="widget">
    <div class="widget-bd">
        <div class="stream">
            <@html.ul items=Request.page.results ; histroy>
            <form action="${Request.contextPath}/project/histroy/create" method="post">
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
                    <#if histroy.children?? && histroy.children?size gt 0 >
                    <@html.ul ulClass="replies" items=histroy.children ; childHistroy>
                        <a href="${Request.contextPath}/">
                            <img class="thumbnail" src="${Request.contextPath}/project/user/${childHistroy.createById}/logo" />
                        </a>
                        <div class="reply-content">
                        ${childHistroy.content}
                        </div>
                        <div class="reply-info">
                            <abbr class="date" title="${histroy.createAt?datetime}">${histroy.createAt?string("yyyy-MM-dd HH:mm")}</abbr>
                        </div>
                    </@html.ul>
                    </#if>
                    <div class="reply">
                        <textarea name="content"></textarea>

                        <input type="hidden" name="targetType" value="project" />
                        <input type="hidden" name="targetId" value="${Request.project.id}" />
                        <input type="hidden" name="projectId" value="${Request.project.id}" />
                        <input type="hidden" name="createById" value="${Session.user.id}" />
                        <input type="hidden" name="parentId" value="${histroy.id}" />
                        <button class="button" type="submit" name="redirect" value="<@utils.redirect />">回复</button>
                    </div>
                </div>
            </form>
            </@html.ul>
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