<%@tag description="Widget Wrapper" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="widgetHead" fragment="true" %>
<%@attribute name="widgetFoot" fragment="true" %>

<div id="${widgetConfig.id}" class="widget">
    <c:choose>
        <c:when test="${not empty widgetHead}">
        <div class="widget-hd">
            <jsp:invoke fragment="widgetHead" />
        </div>
        </c:when>
        <c:when test="${not empty widgetConfig.title}">
            <div class="widget-hd">
                <h2>${widgetConfig.title}</h2>
            </div>
        </c:when>
    </c:choose>
    <div class="widget-bd">
        <jsp:doBody />
    </div>
    <c:choose>
        <c:when test="${not empty widgetFoot}">
        <div class="widget-ft">
            <jsp:invoke fragment="widgetFoot" />
        </div>
        </c:when>
    </c:choose>
</div>