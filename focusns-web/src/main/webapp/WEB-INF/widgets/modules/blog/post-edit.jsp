<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget styleId="blog-post-edit">
    <ui:widget-body>
        <form action="<widget:actionUrl value="/blog/post-modify" />" method="post">
            <fieldset>
                <legend><h3>日志编辑</h3></legend>
                <label>分类</label>
                <form:select cssClass="span3" path="blogPost.categoryId">
                    <form:option value="-1">未分类</form:option>
                    <form:options items="${blogCategories}" itemLabel="label" itemValue="id" />
                </form:select>

                <label>标题</label>
                <form:input cssClass="span6" path="blogPost.title" />

                <label>内容</label>
                <form:textarea path="blogPost.content" cssClass="editor" />

                <button type="submit" class="btn btn-primary" >提交</button>
                <form:hidden path="blogPost.id" />
                <form:hidden path="blogPost.projectId" />
                <form:hidden path="blogPost.createdById" />
                <form:hidden path="blogPost.modifiedById" />
            </fieldset>
        </form>
    </ui:widget-body>
</ui:widget>