<%@tag description="Position Render" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@attribute name="name" required="true" type="java.lang.String" %>
<c:set var="positionConfig" value="${pageConfig.positionConfigMap[name]}" />
<c:forEach items="${positionConfig.orderedWidgetConfigList}" var="widgetConfig">
    <c:if test="${not empty fn:trim(widgetResponseMap[widgetConfig.id])}" >
        ${widgetResponseMap[widgetConfig.id]}
    </c:if>
</c:forEach>

