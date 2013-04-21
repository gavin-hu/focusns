<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-bd>
        <form class="form-inline" action='<c:url value="/blog/category-create.action" />' method="post">
            <fieldset>
                <legend><h3>分类编辑</h3></legend>
                <input name="label" type="text" placeholder="分类名称" />
                <input type="hidden" name="projectId" value="${project.id}" />
                <input type="hidden" name="createdById" value="${user.id}" />
                <input type="hidden" name="redirect" value="${currentPath}" />
                <button type="submit" class="btn btn-primary">添加</button>
            </fieldset>
        </form>

        <c:forEach items="${blogCategories}" var="blogCategory">
        <form class="form-inline" action='<c:url value="/blog/category-modify.action" />' method="post">
            <input type="text" name="label" value="${blogCategory.label}" />
            <input type="hidden" name="id" value="${blogCategory.id}" />
            <input type="hidden" name="projectId" value="${project.id}" />
            <input type="hidden" name="createdById" value="${user.id}" />
            <input type="hidden" name="redirect" value="${currentPath}">
            <button type="submit" class="btn btn-primary">修改</button>
            <a class="btn" href='<c:url value="/blog/category-remove.action?id=${blogCategory.id}&redirect=${currentPath}" />'>删除</a>
        </form>
        </c:forEach>
    </ui:widget-bd>
</ui:widget>

