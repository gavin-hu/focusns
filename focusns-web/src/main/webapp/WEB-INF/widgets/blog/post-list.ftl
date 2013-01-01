<div class="widget">
    <div class="widget-bd">
        <div class="blog-posts">
            <ul>
                <#list Request.page.results as blogPost>
                <li>
                    <div class=".thumnail">
                        <img src="${Request.contextPath}/project/logo/link" />
                    </div>
                    <div class="post-title">
                        <h3>${blogPost.title}</h3>
                    </div>
                    <div class="post-content">
                        <p>${blogPost.content}</p>
                    </div>
                </li>
                </#list>
            </ul>
        </div>
    </div>
</div>