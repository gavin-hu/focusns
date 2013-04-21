<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<ui:widget>
    <ui:widget-hd>
        <h3>分类</h3>
    </ui:widget-hd>
    <ui:widget-bd>
        <ul>
            <li>
                <i class="icon-folder-close"></i>
                <a href='<c:url value="/${project.code}/blog" />'>所有分类</a>
            </li>
            <c:forEach items="${blogCategories}" var="blogCategory">
                <li>
                    <i class="icon-folder-close"></i>
                    <a href='<c:url value="/${project.code}/blog?categoryId=${blogCategory.id}" />'>
                        ${blogCategory.label}
                    </a>
                </li>
            </c:forEach>
            <li>
                <i class="icon-folder-close"></i>
                <a href='<c:url value="/${project.code}/blog?categoryId=-1" />'>未分类</a>
            </li>
        </ul>
    </ui:widget-bd>
</ui:widget>