<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<div id="${widgetConfig.id}" class="widget">
    <div class="widget-hd">
        <h3>文章管理</h3>
    </div>
    <div class="widget-bd">
        <div class="articles">
            <c:choose>
                <c:when test="${empty articleCategories}">
                    栏目不存在？
                    <a href='<c:url value="/console/cms?mode=category-edit" />'>立即添加栏目！</a>
                </c:when>
                <c:when test="${empty page.results}">
                    ${articleCategory.label}栏目不存在文章？
                    <a href='<c:url value="/console/cms?mode=article-edit&categoryId=${articleCategory.id}" />'>立即添加文章！</a>
                </c:when>
                <c:otherwise>
                    <div>
                        <label>栏目</label>
                        <form:select id="categorySelect" path="articleCategory.id">
                            <form:options items="${articleCategories}" itemLabel="label" itemValue="id" />
                        </form:select>
                        <a href='<c:url value="/console/cms?mode=article-edit" /><c:if test="${not empty param.categoryId}">&categoryId=${param.categoryId}</c:if>'>新建</a>
                    </div>
                    <ul>
                        <c:forEach items="${page.results}" var="article">
                        <li>
                            <h3>${article.title}</h3>
                            | <fmt:formatDate value="${article.modifiedAt}" />
                            | (<a href='<c:url value="/console/cms?mode=article-edit&articleId=${article.id}" />'>编辑</a>)
                        </li>
                        </c:forEach>
                    </ul>

                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<script type="text/javascript">
$(document).ready(function(){
    $('div#${widgetConfig.id} #categorySelect').change(function(){
        window.location.href = '<c:url value="/console/cms?mode=article-list" />&categoryId=' + $(this).val();
    });
});
</script>