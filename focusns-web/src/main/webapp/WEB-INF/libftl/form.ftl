<!-- dynamic bean invoker @See DynamicInvoker -->
<#macro bind name>
${DynamicInvoker.create(name)}
</#macro>

<#-- enctype application/x-www-form-urlencoded or multipart/form-data -->
<#macro form name action id="" class="" method="post" target="" multipart=false accept="">
<@bind name=name />
<form id="${id}" class="${class}" name="${name}" action="${action}" method="method" target="${target}"
      enctype="<#if multipart>multipart/form-data<#else>application/x-www-form-urlencoded</#if>" accept="${accept}" >
    <#nested />
</form>
</#macro>

<#-- html input tag wrapper -->
<#macro textInput name path id="" class="">
<@input id="${id}" class="${class}" type="text" name=name path=path />
</#macro>

<#macro pwdInput name path id="" class="">
<@input id="${id}" class="${class}" type="password" name=name path=path />
</#macro>

<#macro hiddenInput name path Sid="" class="">
<@input id="${id}" class="${class}" type="hidden" name=name path=path />
</#macro>

<#macro textArea name path id="" class="">
<textarea id="${id}" class="${class}" name="${name}" value="${DynamicInvoker.invoke(path)}" ></textarea>
</#macro>

<#macro input type name path id="" class="">
<input id="${id}" class="${class}" type="${type}" name="${name}" value="${DynamicInvoker.invoke(path)!""}" />
</#macro>