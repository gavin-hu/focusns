<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>

<c:set var="userMenuExist" value="${pageConfig.positionConfigMap['userMenu']!=null}" />
<c:set var="mainMenuExist" value="${pageConfig.positionConfigMap['mainMenu']!=null}" />
<c:set var="subMenuExist" value="${pageConfig.positionConfigMap['subMenu']!=null}" />
<c:set var="leftColumnExist" value="${pageConfig.positionConfigMap['leftColumn']!=null}" />
<c:set var="mainColumnExist" value="${pageConfig.positionConfigMap['mainColumn']!=null}" />
<c:set var="rightColumnExist" value="${pageConfig.positionConfigMap['rightColumn']!=null}" />
<c:set var="copyrightExist" value="${pageConfig.positionConfigMap['copyright']!=null}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh" lang="zh">
<head>
    <title>FocusSNS</title>
    <meta content="zh-CN" http-equiv="Content-Language" />
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
    <meta content="" name="Keywords" />
    <meta content="" name="Description" />
    <!--
    <meta content="3; url=/index" http-equiv="refresh" />
    -->
    <!-- Include Stylesheet -->
    <%@include file="stylesheet.jsp"%>
    <!-- Include Javascript-->
    <%@include file="javascript.jsp"%>
</head>
    <body>
        <div id="hd" class="yui3-g">
            <div class="yui3-u-1">
                <div class="area">
                    <a href='<c:url value="/" />'>
                        <img id="logo" />
                    </a>
                    <c:if test="${userMenuExist}">
                    <div id="user-menu">
                        <l:position name="userMenu" />
                    </div>
                    </c:if>
                </div>
                <c:if test="${mainMenuExist}">
                <div id="main-menu">
                    <div class="bar">
                        <div class="bar-in">
                            <l:position name="mainMenu" />
                        </div>
                    </div>
                </div>
                </c:if>
                <c:if test="${subMenuExist}">
                <div id="sub-menu">
                    <div class="subbar">
                        <div class="subbar-in">
                            <l:position name="subMenu" />
                        </div>
                    </div>
                </div>
                </c:if>
            </div>
        </div>
        <div id="bd" class="yui3-g">
            <!-- render left column if exists -->
            <c:if test="${leftColumnExist}">
            <div id="left-column" class="yui3-u">
                <l:position name="leftColumn" />
            </div>
            </c:if>
            <!-- render main column if exists -->
            <c:if test="${mainColumnExist}">
            <div id="main-column" class="yui3-u">
                <l:position name="mainColumn" />
            </div>
            </c:if>
            <!-- render right column if exists -->
            <c:if test="${rightColumnExist}">
            <div id="right-column" class="yui3-u">
                <l:position name="rightColumn" />
            </div>
            </c:if>
        </div>
        <div id="ft" class="yui3-g">
            <c:if test="${copyrightExist}">
            <div id="copyright" class="yui3-u-1">
                <l:position name="copyright" />
            </div>
            </c:if>
        </div>
    </body>
</html>