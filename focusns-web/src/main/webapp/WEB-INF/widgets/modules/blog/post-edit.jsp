<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<ui:widget>
    <ui:widget-bd>
        <form action='<c:url value="/blog/post-modify.action" />' method="post">
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
                <form:textarea path="blogPost.content" />

                <button type="submit" class="btn btn-primary" name="redirect" value='<c:url value="/${project.code}/blog" />'>提交</button>
                <form:hidden path="blogPost.id" />
                <form:hidden path="blogPost.projectId" />
                <form:hidden path="blogPost.createdById" />
                <form:hidden path="blogPost.modifiedById" />
            </fieldset>
        </form>
    </ui:widget-bd>
</ui:widget>