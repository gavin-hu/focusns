<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="widget">
    <div class="widget-hd">
        <h2>栏目管理</h2>
    </div>
    <div class="widget-bd">
        <div class="blog-categories">
            <c:forEach items="${blogCategories}" var="blogCategory">
                <a href='<c:url value="/${project.code}/${projectFeature.code}" /> <c:if test="${blogCategory.id gt 0}">?categoryId=${blogCategory.id}</c:if>'>
                    <h3>${blogCategory.label}</h3>
                </a>
            </c:forEach>
        </div>
    </div>
</div>