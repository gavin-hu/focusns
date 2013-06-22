<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget>
    <ui:widget-head>
        <h3>上传照片</h3>
    </ui:widget-head>
    <ui:widget-body>
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
    </ui:widget-body>
</ui:widget>