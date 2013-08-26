<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-head>
        <c:choose>
            <c:when test="${reverse}">
                <h3>粉丝</h3>
            </c:when>
            <c:otherwise>
                <h3>关注</h3>
            </c:otherwise>
        </c:choose>
    </ui:widget-head>
    <ui:widget-body>
        <c:choose>
            <c:when test="${empty page.results}">
                <c:choose>
                    <c:when test="${reverse}">
                        <div class="alert alert-info">
                        当前还没有粉丝...
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-info">
                        当前还没有关注...
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <ul>
                    <c:forEach items="${page.results}" var="link" varStatus="status">
                        <li>
                        <c:choose>
                            <c:when test="${reverse}">
                                <a href='<c:url value="/profile;p=${link.fromProject.code}" />'>
                                    <tool:img-avatar projectUserId="${link.fromProject.createdById}" width="60" height="60" />
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href='<c:url value="/profile;p=${link.toProject.code}" />'>
                                    <tool:img-avatar projectUserId="${link.toProject.createdById}" width="60" height="60" />
                                </a>
                            </c:otherwise>
                        </c:choose>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </ui:widget-body>
    <ui:widget-foot>
        <c:if test="${page.hasNext}">
            <a class="btn" href="/">更多 &gt;&gt;</a>
        </c:if>
    </ui:widget-foot>
</ui:widget>
