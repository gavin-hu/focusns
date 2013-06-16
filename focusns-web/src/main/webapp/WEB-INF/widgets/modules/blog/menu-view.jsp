<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget>
    <ui:widget-head>
        <h3>日志管理</h3>
    </ui:widget-head>
    <ui:widget-body>
        <ul>
            <li>
                <i class="icon-edit"> </i>
                <a href="<c:url value="/blog/post-edit;p=${project.code}" />">发表日志</a>
            </li>
            <li>
                <i class="icon-folder-close"></i>
                <a href="<c:url value="/blog/category-edit;p=${project.code}" />">分类管理</a>
            </li>
        </ul>
    </ui:widget-body>
</ui:widget>