<%@tag description="Abbr Date" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="alertClass" required="false"  %>
<%@attribute name="status" required="true" %>
<%@attribute name="statusMessage" required="true" %>

<c:if test="${empty alertClass}" >
    <c:set var="alertClass" value="alert-info" />
</c:if>

<c:if test="${navigateStatus eq status}">
    <div class="alert ${alertClass}">
        ${statusMessage}
    </div>
    <c:remove var="redirectAttributes" scope="session" />
</c:if>