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
                    当前还没有相册，现在就来 <a href="<c:url value="/photo/album;p=${project.code},m=edit" />">
                    <span class="badge badge-info">添加</span></a> 吧？
                </div>
            </c:when>
            <c:otherwise>
                <form class="valid-inline" action='<widget:actionUrl value="/photo/photo-upload" />'
                      method="post" enctype="multipart/form-data">
                    <div class="control-group">
                        <label class="control-label">相册</label>
                        <div class="controls">
                            <form:select path="photo.albumId">
                                <form:options items="${albums}" itemValue="id" itemLabel="label" />
                            </form:select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">照片</label>
                        <div class="controls">
                            <input type="file" name="file" title="本地浏览..."
                                   data-rule-required="true" data-msg-required="请从本地选择要上传的照片！"/>
                            <button type="submit" class="btn btn-primary">上传</button>
                        </div>
                    </div>

                    <form:hidden path="photo.projectId" />
                    <form:hidden path="photo.createdById" />
                </form>
            </c:otherwise>
        </c:choose>
    </ui:widget-body>
</ui:widget>