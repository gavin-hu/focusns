<%@tag description="Position Exists" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@attribute name="name" required="true" type="java.lang.String" %>

<c:if test="${not empty pageConfig.positionConfigMap[name]}">
    <jsp:doBody />
</c:if>