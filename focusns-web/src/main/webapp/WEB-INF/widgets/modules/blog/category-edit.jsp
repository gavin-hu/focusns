<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-body>
        <form class="form-inline" action="<widget:actionUrl value="/blog/category-create" />" method="post">
            <fieldset>
                <legend><h3>分类编辑</h3></legend>
                <input name="label" type="text" placeholder="分类名称" />
                <input type="hidden" name="projectId" value="${project.id}" />
                <input type="hidden" name="createdById" value="${projectUser.id}" />
                <button type="submit" class="btn btn-primary">添加</button>
            </fieldset>
        </form>

        <c:forEach items="${blogCategories}" var="blogCategory">
        <form class="form-inline" action="<widget:actionUrl value="/blog/category-modify" />" method="post">
            <input type="text" name="label" value="${blogCategory.label}" />
            <input type="hidden" name="id" value="${blogCategory.id}" />
            <input type="hidden" name="projectId" value="${project.id}" />
            <input type="hidden" name="createdById" value="${projectUser.id}" />
            <button type="submit" class="btn btn-primary">修改</button>
            <a class="btn" href="<widget:actionUrl value="/blog/category-remove?id=${blogCategory.id}" />">删除</a>
        </form>
        </c:forEach>
    </ui:widget-body>
</ui:widget>

