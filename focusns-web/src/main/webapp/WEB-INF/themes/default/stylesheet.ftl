<!-- YUI Stylesheet -->
<link rel="stylesheet" type="text/css" href="${Request.contextPath}/themes/default/css/cssreset.css" />
<link rel="stylesheet" type="text/css" href="${Request.contextPath}/themes/default/css/cssfonts.css" />
<link rel="stylesheet" type="text/css" href="${Request.contextPath}/themes/default/css/cssgrids.css" />

<link rel="stylesheet" type="text/css" href="${Request.contextPath}/libjs/jcrop/css/jquery.Jcrop.min.css" />

<!-- Theme Stylesheet -->
<link rel="stylesheet" href="${Request.contextPath}/themes/default/css/style.css" type="text/css" />
<#if leftColumn?? && mainColumn?? && rightColumn??>
<style>
    #left-column {width:15%; padding-right:10px;}
    #main-column {width:56.92%;}
    #right-column {width:26%; padding-left:10px;}
</style>
<#elseif leftColumn?? && mainColumn??>
<style>
    #left-column {width:15%;}
    #main-column {width:83.9%; margin-left:10px;}
</style>
<#elseif mainColumn?? && rightColumn??>
<style>
    #main-column {width:70%;}
    #right-column {width:26%; margin-left:10px;}
</style>
<#elseif mainColumn??>
<style>
    #main-column {width:100%;}
</style>
</#if>