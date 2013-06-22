<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-head>
        <h3>日志列表</h3>
    </ui:widget-head>
    <ui:widget-body>
        <c:choose>
            <c:when test="${empty page.results}">
                <div class="alert alert-info">
                    <c:if test="${not empty param.categoryId}">
                        <c:url var="redirectTo" value="/${projectFeature.code}/post-edit;p=${project.code}?categoryId=${param.categoryId}" />
                    </c:if>
                    <c:if test="${empty param.categoryId}">
                        <c:url var="redirectTo" value="/${projectFeature.code}/post-edit;p=${project.code}?categoryId=${param.categoryId}" />
                    </c:if>
                    当前分类没有内容，赶快 <a href='${redirectTo}'>添加</a> 吧！
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${page.results}" var="blogPost">
                    <div class="media">
                        <a class="pull-left" href='<c:url value="/profile;p=${blogPost.createdBy.project.code}" />'>
                            <tool:img-avatar projectUserId="${blogPost.createdById}" width="60" height="60" />
                        </a>
                        <div class="media-body">
                            <h4 class="media-heading">
                                <a href='<c:url value="/${projectFeature.code}/post-view;p=${project.code}?postId=${blogPost.id}" />'>
                                        ${blogPost.title}
                                </a>
                                <a href='<c:url value="/${projectFeature.code}/post-edit;p=${project.code}?postId=${blogPost.id}" />'>
                                    <small>编辑 </small>
                                </a>
                            </h4>
                            <p>${blogPost.summary}...</p>
                            <div>
                                By
                                <a href='<c:url value="/${blogPost.createdBy.project.code}/profile" />'>
                                        ${blogPost.createdBy.username}
                                </a>
                                <tool:abbr-date value="${blogPost.createdAt}" />
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </ui:widget-body>
</ui:widget>