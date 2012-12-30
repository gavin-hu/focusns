<#import "/WEB-INF/libftl/utils.ftl" as utils>

<div class="widget">
    <div class="widget-bd">
        <div class="project">
            <div class="project-logo">
                <img src="<@utils.urlPrefix />/project/logo-link.jpg" />
            </div>
            <div class="project-title">
                <h3>${Request.project.title}</h3>
            </div>
            <div class="project-description">
                ${Request.project.description}
            </div>
            <div class="project-attributes">
                <ul>
                    <#list Request.attributes as attribute>
                    <li>
                        ${attribute.name} : ${attribute.value} 
                    </li>
                    </#list>
                </ul>
            </div>
        </div>
    </div>
</div>