<div class="widget">
    <div class="widget-bd">
        <div class="stream">
            <ul>
                <#list Request.page.results as histroy>
                <li>
                    <div class="thumbnail">
                        <a href="${Request.contextPath}/">
                            <img src="${Request.contextPath}/project/logo" />
                        </a>
                    </div>
                    <div class="activity">
                        <div class="content">${histroy.content}</div>
                        <div class="info">
                            <span class="date">${histroy.createAt?string("yyyy-MM-dd HH:mm")}</span>
                        </div>
                        <div class="reply">
                            <form action="" post="method">
                                <textarea name="content"></textarea>
                            </form>
                        </div>
                    </div>
                </li>
                </#list>
            </ul>
        </div>
    </div>
</div>