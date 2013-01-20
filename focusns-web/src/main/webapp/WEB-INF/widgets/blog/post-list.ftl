<#import "/WEB-INF/libftl/html.ftl" as html>

<div class="widget">
    <div class="widget-hd">
        <h2>日志列表</h2>
    </div>
    <div class="widget-bd">
        <div class="blog-posts">
            <#if Request.page.results?size gt 0>
            <@html.ul items=Request.page.results ; blogPost>
                <div class="post">
                    <a href="">
                        <img class="thumbnail" src="${Request.contextPath}/project/user/${blogPost.createById}/logo" />
                    </a>
                    <div class="title">
                        <a href="${Request.contextPath}/${Session.project.code}/blog/post?id=${blogPost.id}">
                        <h3>${blogPost.title}</h3>
                        </a>
                    </div>
                    <div class="summary">
                        ${blogPost.summary}
                    </div>
                    <div class="status">
                        ${blogPost.createAt?string('yyyy/MM/dd')}
                    </div>
                </div>
            </@html.ul>
            <#else>
            当前无日志
            </#if>
        </div>
    </div>
</div>