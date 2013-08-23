<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget>
    <ui:widget-head>
        <h3>相册</h3>
    </ui:widget-head>
    <ui:widget-body>
        <ul>
            <li>
                <i class="icon-folder-close"></i>
                <a href="<c:url value="/photo;p=${project.code}" />">相册列表</a>
            </li>
            <c:forEach items="${albums}" var="album">
            <li>
                <i class="icon-folder-close"></i>
                <a href="<c:url value="/photo;p=${project.code},m=list,albumId=${album.id}" />">${album.label}</a>
            </li>
            </c:forEach>
        </ul>
    </ui:widget-body>
</ui:widget>