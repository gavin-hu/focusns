<%@tag description="Position Fragment" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%@attribute name="name" required="true" type="java.lang.String" %>

<c:set var="positionConfig" value="${pageConfig.positionConfigMap[name]}" />


<c:choose>
    <c:when test="${empty positionConfig.grid}">
        <c:forEach items="${positionConfig.widgetConfigList}" var="widgetConfig">
            <jsp:include page="/widget/${widgetConfig.target}?position=${positionConfig.name}&widget=${widgetConfig.id}" />
        </c:forEach>
    </c:when>
    <c:otherwise>
        <table class="grids" style="table-layout: fixed">
        <c:forEach items="${positionConfig.widgetConfigList}" var="widgetConfig" varStatus="status">
            <c:choose>
                <c:when test="${status.index % positionConfig.columns == 0}">
                <tr>
                    <td class="first">
                </c:when>
                <c:when test="${status.index % positionConfig.columns == positionConfig.columns-1}">
                    <td class="last">
                </c:when>
                <c:otherwise>
                    <td>
                </c:otherwise>
            </c:choose>
                    <jsp:include page="/widget/${widgetConfig.target}?position=${positionConfig.name}&widget=${widgetConfig.id}" />
            <c:choose>
                <c:when test="${status.index % positionConfig.columns == 0}">
                    </td>
                </c:when>
                <c:when test="${status.index % positionConfig.columns == positionConfig.columns-1}">
                    </td>
                </tr>
                </c:when>
                <c:otherwise>
                    </td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        </table>
    </c:otherwise>
</c:choose>