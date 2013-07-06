<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget styleId="blog-post-edit">
    <ui:widget-body>
        <form class="valid" action="<widget:actionUrl value="/blog/post-modify" />" method="post">
            <fieldset>
                <legend><h3>日志编辑</h3></legend>

                <div class="control-group">
                    <label class="control-label">分类</label>
                    <div class="controls">
                        <form:select cssClass="span3" path="blogPost.categoryId">
                            <form:option value="-1">未分类</form:option>
                            <form:options items="${blogCategories}" itemLabel="label" itemValue="id" />
                        </form:select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">标题</label>
                    <div class="controls">
                        <form:input cssClass="span6" input="" path="blogPost.title"
                                    data-rule-required="true" data-msg-required="标题不能为空！" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">内容</label>
                    <div class="controls">
                        <form:textarea path="blogPost.content" cssClass="editor"
                                       data-rule-required="true" data-msg-required="内容不能为空！"/>
                        <button type="submit" class="btn btn-primary" >提交</button>
                    </div>
                </div>


                <form:hidden path="blogPost.id" />
                <form:hidden path="blogPost.projectId" />
                <form:hidden path="blogPost.createdById" />
                <form:hidden path="blogPost.modifiedById" />
            </fieldset>
        </form>
    </ui:widget-body>
</ui:widget>