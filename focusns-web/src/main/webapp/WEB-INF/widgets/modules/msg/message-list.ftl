<#import "/WEB-INF/libftl/html.ftl" as html>

<div class="widget">
    <div class="widget-hd">
        <h3>私信列表</h3>
    </div>
    <div class="widget-bd">
        <div class="messages">
            <#if Request.page.results?size gt 0 >
            <@html.ul items=Request.page.results ; message >
                ${message.title}
            </@html.ul>
            <#else>
            当前无私信！
            </#if>
        </div>
    </div>
</div>