<#import "/WEB-INF/libftl/html.ftl" as html>
<#import "/WEB-INF/libftl/form.ftl" as form>
<#import "/WEB-INF/libftl/utils.ftl" as utils>

<div class="widget">
    <div class="widget-hd">
        <h2>关注</h2>
    </div>
    <div class="widget-bd">
        <div class="project-links">
            <@html.ul items=Request.page.results ; link >
            <div class="project-link">
                <a href="${Request.contextPath}/${link.toProject.code}/profile">
                    <img class="thumbnail" src="${Request.contextPath}/project/${link.toProjectId}/logo" />
                </a>
            </div>
            </@html.ul>
        </div>
    </div>
    <#if Request.page.hasNext()>
    <div class="widget-ft">
        <div class="more">
            <a href="/">更多 &gt;&gt;</a>
        </div>
    </div>
    </#if>
</div>