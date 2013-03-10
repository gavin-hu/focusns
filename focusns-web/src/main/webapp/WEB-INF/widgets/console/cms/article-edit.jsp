<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="widget">
    <div class="widget-hd">
        <h2>文章编辑</h2>
    </div>
    <div class="widget-bd">
        <div class="article">
            <form action='<c:url value="/console/cms/article-modify.action" />' method="post">
                <div>
                    <label>分类</label>
                    <form:select path="article.categoryId">
                        <form:options items="${articleCategories}"  itemLabel="label" itemValue="id" />
                    </form:select>
                </div>
                <div class="title">
                    <label>标题</label>
                    <form:input path="article.title" />
                </div>
                <div class="content">
                    <label>内容</label>
                    <form:textarea path="article.content" />
                </div>
                <div class="submit">
                    <form:hidden path="article.id" />
                    <form:hidden path="article.createById" />
                    <form:hidden path="article.modifyById" />
                    <button type="submit" name="redirect" value='<c:url value="/console/cms?mode=article-list" />'>提交</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function(){
        $('#summary').xheditor({skin:'nostyle', tools:'simple'});
        $('#content').xheditor({skin:'nostyle'});
    });
</script>