<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget styleId="blog-category-list">
    <ui:widget-head>
        <h3>分类</h3>
    </ui:widget-head>
    <ui:widget-body>
        <ul>
            <li>
                <i class="icon-folder-close"></i>
                <a href="<c:url value="/blog;p=${project.code}" />">所有分类</a>
            </li>
            <c:forEach items="${blogCategories}" var="blogCategory">
                <li>
                    <i class="icon-folder-close"></i>
                    <a href="<c:url value="/blog;p=${project.code},categoryId=${blogCategory.id}" />">
                        ${blogCategory.label}
                    </a>
                </li>
            </c:forEach>
            <li>
                <i class="icon-folder-close"></i>
                <a href="<c:url value="/blog;p=${project.code},categoryId=-1" />">未分类</a>
            </li>
        </ul>
    </ui:widget-body>
</ui:widget>