<#macro position name>
<#if Request?keys?seq_contains(name)>
<#nested />
</#if>
</#macro>

<#macro urlPrefix>
    ${Request.contextPath}/${Request.project.code}/${Request.feature.code}
</#macro>
