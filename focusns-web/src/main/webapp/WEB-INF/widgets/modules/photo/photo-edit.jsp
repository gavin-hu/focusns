<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget>
    <ui:widget-head>
        <h3>上传照片</h3>
    </ui:widget-head>
    <ui:widget-body>
        <c:choose>
            <c:when test="${empty albums}">
                <div class="alert alert-info">
                    当前还没有相册，现在就来 <a href="<c:url value="/photo/album-edit;p=${project.code}" />">添加</a> 吧？
                </div>
            </c:when>
            <c:otherwise>
                <form action='<widget:actionUrl value="/photo/photo-upload" />'
                      method="post" enctype="multipart/form-data">
                    <label>相册</label>
                    <form:select path="photo.albumId">
                        <form:options items="${albums}" itemValue="id" itemLabel="label" />
                    </form:select>
                    <label>照片</label>
                    <input type="file" name="file" title="本地浏览..." />

                    <form:hidden path="photo.projectId" />
                    <form:hidden path="photo.createdById" />

                    <button type="submit" class="btn btn-primary">上传</button>
                </form>
            </c:otherwise>
        </c:choose>
    </ui:widget-body>
</ui:widget>