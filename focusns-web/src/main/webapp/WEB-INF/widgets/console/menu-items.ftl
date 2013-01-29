<div class="widget">
    <div class="widget-hd">
        <h2>菜单管理</h2>
    </div>
    <div class="widget-bd">
        <div class="menu-items">
            <table>
                <tr>
                    <th>菜单编码</th>
                    <th>菜单名称</th>
                    <th>菜单顺序</th>
                    <th>操作</th>
                </tr>
            <#list Request.categories as category>
                <tr>
                    <td>${category.code}</td>
                    <td>${category.label}</td>
                    <td>${category.level}</td>
                    <td>
                        <a href="${Request.contextPath}/console/menu-item">新增</a>
                        <a href="${Request.contextPath}/console/menu-item?id=${category.id}">修改</a>
                    </td>
                </tr>
            </#list>
            </table>
        </div>
    </div>
</div>