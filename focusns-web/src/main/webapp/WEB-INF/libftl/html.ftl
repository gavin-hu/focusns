<#-- html ul tag wrapper -->
<#macro ul items ulClass="" liClass="">
<ul class="${ulClass}" >
<#list items as item>
    <li class="${liClass}
                <#if item_index==0 && !item_has_next>only<#elseif item_index==0>first<#elseif !item_has_next>last</#if>
                <#if (item_index +1) % 2 == 0 >even<#else>odd</#if>" >
        <#nested item>
    </li>
</#list>
</ul>
</#macro>