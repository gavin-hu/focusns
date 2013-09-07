<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@include file="position.jsp" %>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <title>FocusSNS</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@include file="stylesheet.jsp" %>
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
                <l:positionRender name="headerBar"/>
            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>

<div class="doc-bd">
    <div class="container">
        <div class="row-fluid">
            <div class="span12">
                <l:positionExists name="subMenu">
                    <l:positionRender name="subMenu" />
                </l:positionExists>
            </div>
        </div>
        <div class="row-fluid">
            <l:positionExists name="leftColumn">
                <div class="${leftColumnClass}">
                    <l:positionRender name="leftColumn"/>
                </div>
            </l:positionExists>
            <l:positionExists name="mainColumn">
                <div class="${mainColumnClass}">
                    <l:positionRender name="mainColumn"/>
                </div>
            </l:positionExists>
            <l:positionExists name="rightColumn">
                <div class="${rightColumnClass}">
                    <l:positionRender name="rightColumn"/>
                </div>
            </l:positionExists>
        </div>
    </div>
</div>
<div class="navbar navbar-fixed-bottom">
    <div class="navbar-inner">
        <div class="doc-ft">
            <div class="container">
                <p>&copy; FocusSNS 2013</p>
            </div>
        </div>
    </div>
</div>
<%@include file="javascript.jsp" %>
</body>
</html>
