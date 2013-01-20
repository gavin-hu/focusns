<#import "/WEB-INF/libftl/html.ftl" as html>
<#import "/WEB-INF/libftl/form.ftl" as form>
<#import "/WEB-INF/libftl/utils.ftl" as utils>


<div class="widget">
    <div class="widget-bd">
        <div class="histories">
            <@html.ul items=Request.page.results ; history>
                <div class="history">
                    <a href="${Request.contextPath}">
                        <img class="thumbnail" src="${Request.contextPath}/project/${history.projectId}/logo" />
                    </a>
                    <div class="content">${history.content}</div>
                    <div class="status">
                        <abbr class="date" title="${history.createAt?datetime}">${history.createAt?string("yyyy-MM-dd HH:mm")}</abbr>
                    </div>
                    <#if history.children?? && history.children?size gt 0 >
                    <div class="history-children">
                        <@html.ul items=history.children ; childhistory>
                            <div class="history">
                                <img class="thumbnail" src="${Request.contextPath}/project/user/${childhistory.createById}/logo" />
                                <div class="content">${childhistory.content}</div>
                                <div class="status">
                                    <abbr class="date" title="${history.createAt?datetime}">${history.createAt?string("yyyy-MM-dd HH:mm")}</abbr>
                                </div>
                            </div>
                        </@html.ul>
                    </div>
                    </#if>
                    <div class="history-reply">
                        <#--
                        <@form.form name="reply" action="${Request.contextPath}/project/history/create">
                            <textarea name="content"></textarea>

                            <@form.textArea name="content" path="reply.content" />
                            <@form.hiddenInput name="targetType" path="reply.targetType" />
                            <@form.hiddenInput name="targetId" path="reply.targetId" />
                            <@form.hiddenInput name="projectId" path="reply.projectId" />
                            <@form.hiddenInput name="createById" path="reply.createById" />

                            <input type="hidden" name="parentId" value="${history.id}" />
                        </@form.form>
                        -->
                        <form action="${Request.contextPath}/project/history/create" method="post">
                            <textarea name="content"></textarea>

                            <input type="hidden" name="targetType" value="${Request.template.targetType}" />
                            <input type="hidden" name="targetId" value="${Request.template.targetId}" />
                            <input type="hidden" name="projectId" value="${Request.template.projectId}" />
                            <input type="hidden" name="createById" value="${Request.template.createById}" />
                            <input type="hidden" name="parentId" value="${history.id}" />

                            <div class="submit">
                                <button type="submit" name="redirect" value="<@utils.redirect />">回复</button>
                            </div>
                        </form>
                    </div>
                </div>
            </@html.ul>
        </div>
    </div>
</div>

<script type="text/javascript">
$(function(){
    // time ago
    $('abbr.date').timeago();
    //
    $('div.history-reply textarea').focus(function(){
        $(this).height(50);
    });
});
</script>