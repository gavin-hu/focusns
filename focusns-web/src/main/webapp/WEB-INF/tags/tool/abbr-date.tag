<%@tag description="Abbr Date" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@attribute name="value" required="true" type="java.util.Date" %>
<%@attribute name="pattern" required="false" type="java.lang.String" %>

<c:if test="${empty pattern}">
    <c:set var="pattern" value="yyyy-MM-dd HH:mm:ss" />
</c:if>

<abbr class="date" title='<fmt:formatDate value="${value}" pattern="${pattern}" />'>
    <fmt:formatDate value="${value}" pattern="${pattern}" />
</abbr>