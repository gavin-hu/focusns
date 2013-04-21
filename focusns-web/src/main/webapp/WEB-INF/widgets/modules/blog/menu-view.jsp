<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<ui:widget>
    <ui:widget-hd>
        <h3>日志管理</h3>
    </ui:widget-hd>
    <ui:widget-bd>
        <ul>
            <li>
                <i class="icon-edit"> </i>
                <a href='<c:url value="/${project.code}/blog/post-edit" />'>发表日志</a>
            </li>
            <li>
                <i class="icon-folder-close"></i>
                <a href='<c:url value="/${project.code}/blog/category-edit" />'>分类管理</a>
            </li>
        </ul>
    </ui:widget-bd>
</ui:widget>