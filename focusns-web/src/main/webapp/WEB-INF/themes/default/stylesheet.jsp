<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- YUI Stylesheet -->
<link rel="stylesheet" type="text/css" href='<c:url value="/static/themes/default/css/cssreset.css"/>' />
<link rel="stylesheet" type="text/css" href='<c:url value="/static/themes/default/css/cssfonts.css" />' />
<link rel="stylesheet" type="text/css" href='<c:url value="/static/themes/default/css/cssgrids.css" />' />

<link rel="stylesheet" type="text/css" href='<c:url value="/static/libjs/jcrop/css/jquery.Jcrop.min.css" />' />

<!-- Theme Stylesheet -->
<link rel="stylesheet" href="<c:url value="/static/themes/default/css/style.css" />" type="text/css" />

<c:set var="leftColumnExist" value="${pageConfig.widgetConfigMap['leftColumn']!=null}" />
<c:set var="mainColumnExist" value="${pageConfig.widgetConfigMap['mainColumn']!=null}" />
<c:set var="rightColumnExist" value="${pageConfig.widgetConfigMap['rightColumn']!=null}" />

<style type="text/css">
<c:choose>
    <c:when test="${leftColumnExist && mainColumnExist && rightColumnExist}">
    #left-column {width:15%; padding-right:10px;}
    #main-column {width:56.92%;}
    #right-column {width:26%; padding-left:10px;}
    </c:when>
    <c:when test="${leftColumnExist && mainColumnExist && !rightColumnExist}">
    #left-column {width:15%;}
    #main-column {width:83.9%; margin-left:10px;}
    </c:when>
    <c:when test="${!leftColumnExist && mainColumnExist && rightColumnExist}">
    #main-column {width:70%;}
    #right-column {width:26%; margin-left:10px;}
    </c:when>
    <c:when test="${!leftColumnExist && mainColumnExist && !rightColumnExist}">
    #main-column {width:100%;}
    </c:when>
</c:choose>
</style>