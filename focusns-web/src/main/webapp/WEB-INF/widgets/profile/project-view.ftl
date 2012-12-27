<#import "../common.ftl" as c>
<div id="profile">
    <img class="logo" src="<@c.urlPrefix />/project/logo-link.jpg" width="80px" height="80px" />
   
    <h2>${Request.project.title}</h2>
    <p>${Request.project.description}</p>
    
    <ul>
        <#list Request.attributes as attribute>
        <li>
            ${attribute.name} : ${attribute.value} 
        </li>
        </#list>
    </ul>
</div>




