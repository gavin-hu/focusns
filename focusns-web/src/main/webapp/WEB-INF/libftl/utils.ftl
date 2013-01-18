<#macro position name>
<#if Request?keys?seq_contains(name)>
<#nested />
</#if>
</#macro>

<#macro redirect>
    <#t>${Request.contextPath}/${Request.project.code}/${Request.feature.code}
</#macro>
