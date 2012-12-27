<#import "../common.ftl" as c>

<div id="stream">
    <#list Request.page.results as histroy>
    <div class="histroy">
        ${histroy.content}
    </div>
    </#list>
</div>