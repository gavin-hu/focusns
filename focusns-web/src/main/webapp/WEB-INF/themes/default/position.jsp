<c:set var="leftColumnExists" value="${not empty pageConfig.positionConfigMap['leftColumn']}" />
<c:set var="mainColumnExists" value="${not empty pageConfig.positionConfigMap['mainColumn']}" />
<c:set var="rightColumnExists" value="${not empty pageConfig.positionConfigMap['rightColumn']}" />

<c:choose>
    <c:when test="${leftColumnExists && !rightColumnExists}">
        <c:set var="leftColumnClass" value="span2" />
        <c:set var="mainColumnClass" value="span10" />
    </c:when>
    <c:when test="${!leftColumnExists && rightColumnExists}">
        <c:set var="leftColumnClass" value="span9" />
        <c:set var="mainColumnClass" value="span3" />
    </c:when>
    <c:when test="${!leftColumnExists && rightColumnExists}">
        <c:set var="mainColumnClass" value="span9" />
        <c:set var="rightColumnClass" value="span3" />
    </c:when>
    <c:when test="${!leftColumnExists && !rightColumnExists}">
        <c:set var="mainColumnClass" value="span12" />
    </c:when>
    <c:otherwise>
        <c:set var="leftColumnClass" value="span3" />
        <c:set var="mainColumnClass" value="span6" />
        <c:set var="rightColumnClass" value="span3" />
    </c:otherwise>
</c:choose>