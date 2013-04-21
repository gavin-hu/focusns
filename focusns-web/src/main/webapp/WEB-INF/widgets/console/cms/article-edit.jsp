<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<div class="widget">
    <div class="widget-hd">
        <h3>文章编辑</h3>
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
                    <form:hidden path="article.createdById" />
                    <form:hidden path="article.modifiedById" />
                    <button type="submit" name="redirect" value="${currentPath}">提交</button>
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