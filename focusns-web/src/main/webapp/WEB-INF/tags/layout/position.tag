<%@tag description="Position Fragment" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="name" required="true" type="java.lang.String" %>

<c:set var="positionConfig" value="${pageConfig.positionConfigMap[name]}" />


<c:choose>
    <c:when test="${empty positionConfig.grid}">
        <c:forEach items="${positionConfig.widgetConfigList}" var="widgetConfig">
            <jsp:include page="/widget/${widgetConfig.target}" />
        </c:forEach>
    </c:when>
    <c:otherwise>
        <table>
        <c:forEach items="${positionConfig.widgetConfigList}" var="widgetConfig" varStatus="status">
            <c:if test="${status.index % positionConfig.columns == 0}">
            <tr>
            </c:if>
                <td><jsp:include page="/widget/${widgetConfig.target}" /></td>
            <c:if test="${status.index % positionConfig.columns == positionConfig.rows-1}">
            </tr>
            </c:if>
        </c:forEach>
        </table>
    </c:otherwise>
</c:choose>