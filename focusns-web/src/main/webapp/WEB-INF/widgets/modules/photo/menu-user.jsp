<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget>
    <ui:widget-head>
        <h3>相册管理</h3>
    </ui:widget-head>
    <ui:widget-body>
        <ul>
            <li>
                <i class="icon-upload"></i>
                <c:choose>
                    <c:when test="${not empty param.albumId}">
                        <a href="<c:url value="/photo;p=${project.code},m=edit,albumId=${param.albumId}" />">上传照片</a>
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value="/photo;p=${project.code},m=edit" />">上传照片</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                <i class="icon-folder-close"></i>
                <a href="<c:url value="/photo/album;p=${project.code},m=edit" />">新建相册</a>
            </li>
        </ul>
    </ui:widget-body>
</ui:widget>