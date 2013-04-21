<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<ui:widget>
    <jsp:body>
        <div class="article">
            <div class="article-head">
                <h3>${article.title}</h3>
                <span class="author">
                    ${article.createdById} 发表于
                    <fmt:formatDate value="${article.createdAt}" pattern="yyyy/M/d"/>
                </span>
            </div>
            <div class="article-body">
                ${article.content}
            </div>
        </div>
    </jsp:body>
</ui:widget>