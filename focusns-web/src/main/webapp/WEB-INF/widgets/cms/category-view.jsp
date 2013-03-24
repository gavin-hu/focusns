<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="${widgetConfig.id}" class="widget">
    <div class="widget-hd">
        <h2>${articleCategory.label}</h2>
    </div>
    <div class="widget-bd">
        <div class="article-category">
        <c:choose>
            <c:when test="${empty page.results}">
                无结果集！
            </c:when>
            <c:otherwise>
                <ul>
                    <c:forEach items="${page.results}" var="article">
                    <li>
                        <span class="title"><a href='<c:url value="/article?id=${article.id}" />'>${article.title}</a></span>
                        <span class="author">
                            ${article.createById} 发表于
                            <fmt:formatDate value="${article.createAt}" pattern="yyyy/M/d" />
                        </span>
                    </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
        </div>
    </div>
</div>