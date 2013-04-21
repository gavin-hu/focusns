<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<div class="widget">
    <div class="widget-hd">
        <h3>栏目编辑</h3>
    </div>
    <div class="widget-bd">
        <div class="article-category">
            <form action='<c:url value="/console/cms/category-modify.action" />' method="post">
                <table>
                    <tr>
                        <th>栏目名称</th>
                        <td><form:input path="articleCategory.label" /></td>
                    </tr>
                </table>
                <div class="submit">
                    <form:hidden path="articleCategory.id" />
                    <form:hidden path="articleCategory.createdById" />
                    <button type="submit" name="redirect" value="${currentPath}">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>