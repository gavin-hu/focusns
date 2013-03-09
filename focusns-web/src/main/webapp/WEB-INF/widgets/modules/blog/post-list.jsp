<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="widget">
    <div class="widget-hd">
        <h2>日志列表</h2>
    </div>
    <div class="widget-bd">
        <div class="blog-posts">
            <c:choose>
                <c:when test="${not empty page.results}">
                    <c:forEach items="${page.results}" var="blogPost">
                    <div class="post">
                        <a href="">
                            <img class="thumbnail" src="${Request.contextPath}/project/user/${blogPost.createById}/logo" />
                        </a>
                        <div class="title">
                            <a href="${Request.contextPath}/${Session.project.code}/blog/post?id=${blogPost.id}">
                                <h3>${blogPost.title}</h3>
                            </a>
                        </div>
                        <div class="summary">
                                ${blogPost.summary}
                        </div>
                        <div class="status">
                                ${blogPost.createAt}
                        </div>
                    </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    当前无日志
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>