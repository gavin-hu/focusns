<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ul class="nav nav-tabs">
    <c:forEach items="${features}" var="feature">
        <li <c:if test="${projectFeature.code eq feature.code}">class="active"</c:if> >
            <a href='<c:url value="/${project.code}/${feature.code}" />'>${feature.label}</a>
        </li>
    </c:forEach>
</ul>