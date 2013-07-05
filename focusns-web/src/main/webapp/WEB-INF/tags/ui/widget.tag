<%@tag description="Widget Wrapper" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="styleId" required="false" %>
<%@attribute name="styleClass" required="false" %>

<div <c:if test="${not empty widgetConfig.styleId}">id="${widgetConfig.styleId}"</c:if>
     class="widget<c:if test="${not empty widgetConfig.styleClass}"> ${widgetConfig.styleClass}</c:if>"
     <c:if test="${not empty widgetConfig.order}">order="${widgetConfig.order}"</c:if>>
    <div <c:if test="${not empty styleId}">id="${styleId}"</c:if>
         <c:if test="${not empty styleClass}">class="${styleClass}"</c:if> >
        <jsp:doBody />
    </div>
</div>