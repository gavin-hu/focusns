<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="menu">
    <c:forEach items="${features}" var="feature">
    <li <c:if test="${projectFeature.code eq feature.code}">class="active"</c:if> >
        <a href='<c:url value="/${project.code}/${feature.code}" />'>${feature.label}</a>
    </li>
    </c:forEach>
</ul>