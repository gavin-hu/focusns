<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<div class="widget">
    <div class="widget-hd">
        <h3>菜单编辑</h3>
    </div>
    <div class="widget-bd">
        <div class="menu-item">

            <form action='<c:url value="/console/site/menu-modify.action" />' method="post">
                <table class="detail">
                    <tr>
                        <th>菜单编码</th>
                        <td><form:input path="category.code"/></td>
                    </tr>
                    <tr>
                        <th>菜单名称</th>
                        <td><form:input path="category.label"/></td>
                    </tr>
                    <tr>
                        <th>菜单顺序</th>
                        <td><form:input path="category.level"/></td>
                    </tr>
                    <tr>
                        <th>是否启用</th>
                        <td><form:checkbox path="category.enabled" /></td>
                    </tr>
                </table>
                <div class="submit">
                    <form:hidden path="category.id" />
                    <button type="submit" name="redirect" value='<c:url value="/console/site?mode=menu-list" />'>提交</button>
                </div>
            </form>
        </div>
    </div>
</div>