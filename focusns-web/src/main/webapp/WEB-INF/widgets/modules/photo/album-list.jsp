<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget>
    <ui:widget-head>
        <h3>相册列表</h3>
    </ui:widget-head>
    <ui:widget-body>
        <c:choose>
            <c:when test="${empty albums}">
                <div class="alert alert-info">
                    当前还没有相册，现在就来 <a href="<c:url value="/photo/album-edit;p=${project.code}" />">添加</a> 吧？
                </div>
            </c:when>
            <c:otherwise>
                <ul class="thumbnails">
                    <c:forEach items="${albums}" var="album">
                        <li class="span3">
                            <div class="thumbnail">
                                <a href="<c:url value="/photo/photo-list;p=${project.code}?albumId=${album.id}" />">
                                    <c:choose>
                                        <c:when test="${empty album.photo}">
                                            <img alt="260x260" src="<c:url value="/static/themes/default/img/album-no.jpg" />">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="<widget:actionUrl value="/photo/photo-download?photoId=${album.photoId}&width=260&height=200" />" />
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                                <div class="caption">
                                    <h3>${album.label}</h3>
                                    <p>${album.description}</p>
                                    <a class="btn-link" href="<c:url value="/photo/album-edit;p=${project.code}?albumId=${album.id}" />">编辑</a>
                                    <a class="btn-link" href="<c:url value="/photo/photo-edit;p=${project.code}?albumId=${album.id}" />">上传</a>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </ui:widget-body>
</ui:widget>