
<div class="widget">
    <div class="widget-bd">
        <div class="project">
            <img class="thumbnail" alt="logo" src="${Request.contextPath}/project/${Request.project.id}/logo" />
            <div class="project-title">
                <h3>${Request.project.title}</h3>
            </div>
            <div class="project-description">
                ${Request.project.description}
            </div>
            <#if Request.attributes?? && Request.attributes?size gt 0>
            <div class="project-attributes">
                <ul>
                    <#list Request.attributes as attribute>
                    <li>
                        ${attribute.name} : ${attribute.value} 
                    </li>
                    </#list>
                </ul>
            </div>
            </#if>
        </div>
    </div>
</div>