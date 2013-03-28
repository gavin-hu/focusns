<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<div class="widget">
    <div class="widget-hd">
        <c:choose>
            <c:when test="${reverse}">
                <h2>粉丝</h2>
            </c:when>
            <c:otherwise>
                <h2>关注</h2>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="widget-bd">
        <div class="project-links">
            <c:choose>
                <c:when test="${empty page.results}">
                    为空
                </c:when>
                <c:otherwise>
                    <c:forEach items="${page.results}" var="link">
                        <div class="project-link">
                            <c:if test="${reverse}">
                                <a href='<c:url value="/${link.toProject.code}/profile" />'>
                                    <ui:avatar dimension="50" projectId="${link.fromProject.id}" projectUserId="${link.fromProject.createById}" />
                                </a>
                            </c:if>
                            <c:if test="${!reverse}">
                                <a href='<c:url value="/${link.toProject.code}/profile" />'>
                                    <ui:avatar dimension="50" projectId="${link.toProject.id}" projectUserId="${link.toProject.createById}" />
                                </a>
                            </c:if>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <c:if test="${page.hasNext()}">
    <div class="widget-ft">
        <div class="more">
            <a href="/">更多 &gt;&gt;</a>
        </div>
    </div>
    </c:if>
</div>