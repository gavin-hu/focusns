<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget>
    <ui:widget-head>
        <h3>新建相册</h3>
    </ui:widget-head>
    <ui:widget-body>

        <form action="<widget:actionUrl value="/photo/album-modify" />" method="post">
            <fieldset>
                <label>名称</label>
                <form:input path="album.label" />
                <label>描述</label>
                <form:textarea path="album.description" />

                <form:hidden path="album.id" />
                <form:hidden path="album.projectId" />
                <form:hidden path="album.createdById" />
                <form:hidden path="album.modifiedById" />

                <label>
                    <button class="btn btn-primary">提交</button>
                </label>
            </fieldset>
        </form>

    </ui:widget-body>
</ui:widget>