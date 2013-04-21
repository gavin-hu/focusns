<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-bd>
        <c:choose>
            <c:when test="${empty page.results}">
                <div class="alert alert-info">
                    <c:if test="${not empty param.categoryId}">
                        <c:url var="redirectTo" value="/${project.code}/${projectFeature.code}/post-edit?categoryId=${param.categoryId}" />
                    </c:if>
                    <c:if test="${empty param.categoryId}">
                        <c:url var="redirectTo" value="/${project.code}/${projectFeature.code}/post-edit?categoryId=${param.categoryId}" />
                    </c:if>
                    当前分类没有内容，赶快 <a href='${redirectTo}'>添加</a> 吧！
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${page.results}" var="blogPost">
                    <div class="media">
                        <a class="pull-left" href='<c:url value="/${blogPost.createdBy.project.code}/profile" />'>
                            <t:img-avatar dimension="64" projectId="${blogPost.projectId}" projectUserId="${blogPost.createdById}" />
                        </a>
                        <div class="media-body">
                            <h4 class="media-heading">
                                <a href='<c:url value="/${project.code}/${projectFeature.code}/post-view?id=${blogPost.id}" />'>
                                        ${blogPost.title}
                                </a>
                                <a href='<c:url value="/${project.code}/${projectFeature.code}/post-edit?id=${blogPost.id}" />'>
                                    <small>编辑 </small>
                                </a>
                            </h4>
                            <p>${blogPost.summary}...</p>
                            <div>
                                By
                                <a href='<c:url value="/${blogPost.createdBy.project.code}/profile" />'>
                                        ${blogPost.createdBy.username}
                                </a>
                                <t:abbr-date value="${blogPost.createdAt}" />
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </ui:widget-bd>
</ui:widget>