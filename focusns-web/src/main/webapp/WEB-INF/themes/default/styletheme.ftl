<link rel="stylesheet" href="${Request.contextPath}/themes/default/css/style.css" type="text/css" />
<#if leftColumn?? && mainColumn?? && rightColumn??>
<style>
    #left-column {width:15%;}
    #main-column {width:55%;}
    #right-column {width:26%;}
</style>
<#elseif leftColumn?? && mainColumn??>
<style>
    #left-column {width:15%;}
    #main-column {width:83.9%; margin-left:10px;}
</style>
<#elseif mainColumn?? && rightColumn??>
<style>
    #main-column {width:70%;}
    #right-column {width:26%;}
</style>
<#elseif mainColumn??>
<style>
    #main-column {width:100%;}
</style>
</#if>