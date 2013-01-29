<div class="widget">
    <div class="widget-hd">
        <h2>菜单编辑</h2>
    </div>
    <div class="widget-bd">
        <div class="menu-item">
            <form action="${Request.contextPath}/console/category" method="post">
                <table class="detail">
                    <tr>
                        <th>菜单编码</th>
                        <td><input name="code" value="${Request.category.code!''}" /></td>
                    </tr>
                    <tr>
                        <th>菜单名称</th>
                        <td><input name="label" value="${Request.category.label!''}" /></td>
                    </tr>
                    <tr>
                        <th>菜单顺序</th>
                        <td><input name="level" value="${Request.category.level!''}" /></td>
                    </tr>
                    <tr>
                        <th>是否启用</th>
                        <td><input name="enabled" value="${Request.category.enabled?string!''}" /></td>
                    </tr>
                </table>
                <div class="submit">
                    <input type="hidden" name="id" value="${Request.category.id!''}" />
                    <button type="submit" name="redirect" value="${Request.contextPath}/console/menu-items"">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>