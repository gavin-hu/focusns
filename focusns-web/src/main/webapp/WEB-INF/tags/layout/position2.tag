<%@tag description="Position Fragment" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%@attribute name="name" required="true" type="java.lang.String" %>

<c:set var="positionConfig" value="${pageConfig.positionConfigMap[name]}" />

<c:if test="${not empty positionConfig}">
    <c:forEach items="${positionConfig.widgetConfigList}" var="widgetConfig">
        <jsp:include page="/widget/${widgetConfig.target}?position=${positionConfig.name}&widget=${widgetConfig.id}" />
    </c:forEach>
</c:if>