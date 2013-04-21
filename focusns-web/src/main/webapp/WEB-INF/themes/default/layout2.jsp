<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>

<c:set var="leftColumnExists" value="${not empty pageConfig.positionConfigMap['leftColumn']}" />
<c:set var="mainColumnExists" value="${not empty pageConfig.positionConfigMap['mainColumn']}" />
<c:set var="rightColumnExists" value="${not empty pageConfig.positionConfigMap['rightColumn']}" />

<c:choose>
    <c:when test="${leftColumnExists && !rightColumnExists}">
        <c:set var="leftColumnClass" value="span3" />
        <c:set var="mainColumnClass" value="span9" />
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
</c:choose>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <title>FocusSNS</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <%@include file="stylesheet2.jsp"%>
</head>

<body>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href='<c:url value="/" />'>FocusSNS</a>
            <div class="nav-collapse collapse">
                <l:position2 name="headerBar" />
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>

<div class="container">

    <div class="row">
        <l:positionExists2 name="leftColumn">
        <div class="${leftColumnClass}">
            <l:position2 name="leftColumn" />
        </div>
        </l:positionExists2>
        <l:positionExists2 name="mainColumn">
            <div class="${mainColumnClass}">
                <l:position2 name="mainColumn" />
            </div>
        </l:positionExists2>
        <l:positionExists2 name="rightColumn">
            <div class="${rightColumnClass}">
                <l:position2 name="rightColumn" />
            </div>
        </l:positionExists2>
    </div>

    <hr>

    <footer>
        <p>&copy; FocusSNS 2013</p>
    </footer>

</div> <!-- /container -->

<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<%@include file="javascript2.jsp" %>
</body>
</html>
