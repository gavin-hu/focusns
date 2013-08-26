<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget styleClass="blog-category-edit">
    <ui:widget-head>
        <h3>分类编辑</h3>
    </ui:widget-head>
    <ui:widget-body>
        <form class="valid-inline form-inline" action="<widget:actionUrl value="/blog/category-create" />"
              method="post">
            <div class="control-group">
                <label class="control-label">分类名称</label>

                <div class="controls">
                    <form:input path="blogCategory.label" placeholder="分类名称"
                                data-rule-required="true" data-msg-required="分类名称不能为空！"/>
                </div>
            </div>

            <form:hidden path="blogCategory.projectId"/>
            <form:hidden path="blogCategory.createdById"/>
            <button type="submit" class="btn btn-primary">添加</button>
        </form>

        <c:forEach items="${blogCategories}" var="blogCategory" varStatus="status">
            <c:set value="${blogCategory}" var="category" scope="request"/>
            <form class="valid-inline form-inline" action="<widget:actionUrl value="/blog/category-modify" />"
                  method="post">
                <div class="control-group">
                    <div class="controls">
                        <form:input path="category.label" placeholder="分类名称"
                                    data-rule-required="true" data-msg-required="分类名称不能为空！"/>
                        <button type="submit" class="btn btn-primary">修改</button>
                        <a class="btn" href="<widget:actionUrl value="/blog/category-remove?id=${blogCategory.id}" />">删除</a>
                    </div>
                </div>
                <form:hidden path="category.id"/>
                <form:hidden path="category.projectId"/>
                <form:hidden path="category.createdById"/>
            </form>
        </c:forEach>
    </ui:widget-body>
</ui:widget>

