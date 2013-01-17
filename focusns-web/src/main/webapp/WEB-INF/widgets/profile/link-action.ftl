<div class="widget">
    <div class="widget-hd">
        <h2>关注</h2>
    </div>
    <div class="widget-bd">
        <#if Request.projectLink??>
        <a href="${Request.contextPath}/project/link/remove?fromProjectId=${Request.projectLink.fromProjectId}&toProjectId=${Request.projectLink.toProjectId}">取消关注</a>
        <#else>
        <a href="${Request.contextPath}/project/link/create?fromProjectId=${Request.fromProject.id}&toProjectId=${Request.toProject.id}">添加关注</a>
        </#if>
    </div>
</div>