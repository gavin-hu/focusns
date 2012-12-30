
        <ul class="menu">
            <li><a href="${Request.contextPath}/index">首页</a></li>
            <#list Request.categories as category>
            <li><a href="${Request.contextPath}/${category.code}">${category.label}</a><li>
            </#list>
        </ul>

        <div class="search">                        	
            <form class="ajax" action="#">
                <select name="category">
                    <option value="">全部</option>
                    <#list Request.categories as category>
                    <option value="${category.code}">${category.label}</option>
                    </#list>
                </select>
                <input type="text" name="keywords" />
                <input type="submit" value="搜索" />
            </form>
        </div>
    </div>
</div>