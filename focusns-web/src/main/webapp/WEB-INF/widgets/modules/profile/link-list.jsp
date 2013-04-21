<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-hd>
        <c:choose>
            <c:when test="${reverse}">
                <h3>粉丝</h3>
            </c:when>
            <c:otherwise>
                <h3>关注</h3>
            </c:otherwise>
        </c:choose>
    </ui:widget-hd>
    <ui:widget-bd>
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
                                <a href='<c:url value="/${link.fromProject.code}/profile" />'>
                                    <t:img-avatar dimension="60" projectId="${link.fromProject.id}" projectUserId="${link.fromProject.createById}" />
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href='<c:url value="/${link.toProject.code}/profile" />'>
                                    <t:img-avatar dimension="60" projectId="${link.toProject.id}" projectUserId="${link.toProject.createById}" />
                                </a>
                            </c:otherwise>
                        </c:choose>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </ui:widget-bd>
    <ui:widget-ft>
        <c:if test="${page.hasNext()}">
            <a class="btn" href="/">更多 &gt;&gt;</a>
        </c:if>
    </ui:widget-ft>
</ui:widget>
