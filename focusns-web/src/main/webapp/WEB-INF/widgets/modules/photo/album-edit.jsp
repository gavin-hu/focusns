<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget>
    <ui:widget-head>
        <h3>新建相册</h3>
    </ui:widget-head>
    <ui:widget-body>

        <form class="valid" action="<widget:actionUrl value="/photo/album-modify" />" method="post">
            <div class="control-group">
                <label class="control-label">名称</label>
                <div class="controls">
                    <input type="text" name="label" value="${album.label}"
                           data-rule-required="true" data-msg-required="相册名称不能为空！"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">描述</label>
                <div class="controls">
                    <textarea name="description">${album.description}</textarea>
                </div>
            </div>

            <button class="btn btn-primary">提交</button>

            <form:hidden path="album.id" />
            <form:hidden path="album.projectId" />
            <form:hidden path="album.createdById" />
            <form:hidden path="album.modifiedById" />
        </form>

    </ui:widget-body>
</ui:widget>