<%@tag description="Avatar Tag" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<%@attribute name="styleId" required="false" %>
<%@attribute name="styleClass" required="false" %>
<%@attribute name="projectUserId" required="true" %>
<%@attribute name="width" required="true" %>
<%@attribute name="height" required="true" %>

<img <c:if test="${not empty styleId}">id="${styleId}"</c:if>
     <c:if test="${not empty styleClass}">class="${styleClass}"</c:if>
     src="<widget:actionUrl value="/project/user-avatar/download?projectId=${projectId}&userId=${projectUserId}&width=${width}&height=${height}" />" />