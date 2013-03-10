<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="widget">
    <div class="widget-hd">
        <h2>栏目编辑</h2>
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
                    <form:hidden path="articleCategory.createById" />
                    <button type="submit" name="redirect" value='<c:url value="/console/cms?mode=category-list" />'>提交</button>
                </div>
            </form>
        </div>
    </div>
</div>