<#import "/WEB-INF/libftl/utils.ftl" as utils>

<div class="widget">
    <div class="widget-hd">
        <h2>关注</h2>
    </div>
    <div class="widget-bd">
        <div class="project-links">
            <@utils.ul items=Request.page.results ; item >
            <a href="#">
                <img class="thumbnail" src="${Request.contextPath}/project/${item.toProjectId}/logo" />
            </a>
            </@utils.ul>
        </div>
    </div>
    <div class="widget-ft">
        <div class="more">
            <a href="/">更多 &gt;&gt;</a>
        </div>
    </div>
</div>