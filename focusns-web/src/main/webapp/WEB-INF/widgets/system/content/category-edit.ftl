<h2>菜单编辑</h2>
<form action="${Request.contextPath}/system/content/category.action" method="post">
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
		<input type="hidden" name="redirect" value="${Request.contextPath}/system/content/category-list">
		<button type="submit">提交</button>
	</div>
</form>