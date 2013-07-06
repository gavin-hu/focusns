<%@tag description="Avatar Tag" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@attribute name="styleClass" required="false" %>

<%@attribute name="page" required="true" type="org.focusns.model.common.Page" %>
<%@attribute name="pageLink" required="false" type="java.lang.String" %>
<%@attribute name="paginationSize" required="false" type="java.lang.Integer" %>

<c:if test="${empty pageLink}">
    <c:set var="pageLink" value="${pageContext.request.requestURI}" />
</c:if>
<c:if test="${empty paginationSize}">
    <c:set var="paginationSize" value="10" />
</c:if>

<c:choose>
    <c:when test="${fn:contains(pageLink, '?')}">
        <c:set var="pageLink" value="${pageLink}&pageNo=" />
    </c:when>
    <c:otherwise>
        <c:set var="pageLink" value="${pageLink}?pageNo=" />
    </c:otherwise>
</c:choose>
<c:if test="${page.totalPages>1}">
<div class="pagination ${styleClass}">
    <ul>
        <c:choose>
            <c:when test="${page.pageNo>1}">
                <li>
                    <a href="${pageLink}1">&laquo;</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="disabled">
                    <span>&laquo;</span>
                </li>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${page.hasPre}">
            <li>
                <a href="${pageLink}${page.prePage}">&lsaquo;</a>
            </li>
            </c:when>
            <c:otherwise>
                <li class="disabled">
                    <span>&lsaquo;</span>
                </li>
            </c:otherwise>
        </c:choose>

        <c:if test="${page.totalPages>1}">
            <c:set var="begin" value="${page.pageNo}" />
            <c:if test="${(page.pageNo-paginationSize/2)>0}">
                <fmt:formatNumber var="begin" value="${page.pageNo-paginationSize/2}"  maxFractionDigits="0" />
            </c:if>
            <c:if test="${(page.pageNo-paginationSize/2)<0}">
                <fmt:formatNumber var="begin" value="1"  maxFractionDigits="0" />
            </c:if>

            <c:set var="end" value="${page.totalPages}"/>
            <c:if test="${(page.pageNo + paginationSize/2)>page.totalPages}">
                <fmt:formatNumber var="end" value="${page.totalPages}"  maxFractionDigits="0" />
            </c:if>
            <c:if test="${(page.pageNo + paginationSize/2)<page.totalPages}">
                <fmt:formatNumber var="end" value="${page.pageNo + paginationSize/2}"  maxFractionDigits="0" />
            </c:if>

            <c:if test="${begin==page.first && (begin + paginationSize)<page.totalPages}">
                <fmt:formatNumber var="end" value="${begin+paginationSize}"  maxFractionDigits="0" />
            </c:if>
            <c:if test="${end==page.totalPages && (end - paginationSize)>0}">
                <fmt:formatNumber var="begin" value="${end-paginationSize}"  maxFractionDigits="0" />
            </c:if>

            <c:forEach begin="${begin}" end="${end}" var="pageNo">
                <c:choose>
                    <c:when test="${page.pageNo==pageNo}">
                        <li class="disabled">
                            <span>${pageNo}</span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="${pageLink}${pageNo}">${pageNo}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:if>
        <c:choose>
            <c:when test="${page.hasNext}">
                <li>
                    <a href="${pageLink}${page.nextPage}">&rsaquo;</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="disabled">
                    <span>&rsaquo;</span>
                </li>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${page.totalPages>page.pageNo}">
                <li>
                    <a href="${pageLink}${page.totalPages}">&raquo;</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="disabled">
                    <span>&raquo;</span>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
</c:if>