<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget>
    <ui:widget-head>
        <h3>相册浏览</h3>
    </ui:widget-head>
    <ui:widget-body>
        <c:choose>
            <c:when test="${empty photos}">
                <div class="alert alert-info">
                    当前还没有上传照片，现在就来 <a href="<c:url value="/photo;p=${project.code},m=edit,albumId=${album.id}" />">
                    <span class="badge badge-info">上传</span></a> 吧？
                </div>
            </c:when>
            <c:otherwise>
                <div class="yoxview">
                    <ul class="thumbnails">
                        <c:forEach items="${photos}" var="photo">
                            <li class="span2">
                                <div class="thumbnail">
                                    <a href="<widget:actionUrl value="/photo/photo-download?photoId=${photo.id}" />">
                                        <img src="<widget:actionUrl value="/photo/photo-download?photoId=${photo.id}&dimension=200x200" />" />
                                    </a>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:otherwise>
        </c:choose>
    </ui:widget-body>
</ui:widget>