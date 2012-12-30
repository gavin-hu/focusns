<ul class="menu">
    <#list Request.features as feature>
    <li><a href="${Request.contextPath}/${Request.project.code}/${feature.code}">${feature.label}</a></li>
    </#list>
</ul>