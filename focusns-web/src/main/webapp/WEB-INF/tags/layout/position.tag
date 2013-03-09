<%@tag description="Position Fragment" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="name" required="true" type="java.lang.String" %>

<c:set var="position" value="${pageConfig.widgetConfigMap[name]}" />

<c:forEach items="${position}" var="widgetConfig">
<jsp:include page="/widget/${widgetConfig.target}" />
</c:forEach>
