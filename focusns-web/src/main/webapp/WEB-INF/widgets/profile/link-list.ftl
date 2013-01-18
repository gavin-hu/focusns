<#import "/WEB-INF/libftl/html.ftl" as html>
<#import "/WEB-INF/libftl/form.ftl" as form>
<#import "/WEB-INF/libftl/utils.ftl" as utils>

<div class="widget">
    <div class="widget-hd">
        <h2>关注</h2>
    </div>
    <div class="widget-bd">
        <div class="project-links">
            <@html.ul items=Request.page.results ; item >
            <a href="#">
                <img class="thumbnail" src="${Request.contextPath}/project/${item.toProjectId}/logo" />
            </a>
            </@html.ul>
        </div>
    </div>
    <div class="widget-ft">
        <div class="more">
            <a href="/">更多 &gt;&gt;</a>
        </div>
    </div>
</div>