<#import "/WEB-INF/libftl/utils.ftl" as utils>

<div class="widget">
    <div class="widget-bd">
        <div class="project-action">
            <ul>
                <li class="first">
                    <#if Request.projectLink??>
                    <a href="${Request.contextPath}/project/link/remove?fromProjectId=${Request.projectLink.fromProjectId}&toProjectId=${Request.projectLink.toProjectId}&redirect=<@utils.redirect />">取消关注</a>
                    <#else>
                    <a href="${Request.contextPath}/project/link/create?fromProjectId=${Request.fromProject.id}&toProjectId=${Request.toProject.id}&redirect=<@utils.redirect />">添加关注</a>
                    </#if>
                </li>
            </ul>
        </div>
    </div>
</div>