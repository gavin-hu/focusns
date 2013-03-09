<%@tag description="Default Layout!" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/layout" %>

<%@attribute name="stylesheet" fragment="true" %>
<%@attribute name="javascript" fragment="true" %>

<%@attribute name="userMenu" fragment="true" %>
<%@attribute name="mainMenu" fragment="true" %>
<%@attribute name="subMenu" fragment="true" %>
<%@attribute name="leftColumn" fragment="true" %>
<%@attribute name="mainColumn" fragment="true" %>
<%@attribute name="rightColumn" fragment="true" %>
<%@attribute name="copyright" fragment="true" %>

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
        <jsp:invoke fragment="stylesheet" />
        <!-- Include Javascript-->
        <jsp:invoke fragment="javascript" />
    </head>
    <body>
        <div id="hd" class="yui3-g">
            <div class="yui3-u-1">
                <div class="area">
                    <a href="/">
                        <img id="logo" />
                    </a>
                    <!-- 用户菜单 -->
                    <c:choose>
                        <c:when test="${userMenu!=null}">
                        <jsp:invoke fragment="userMenu" />
                        </c:when>
                        <c:otherwise>
                        <div id="user-menu">
                            <c:forEach items="${positions[userMenu]}" var="widgetUrl">
                                <jsp:include page="${widgetUrl}"/>
                            </c:forEach>
                        </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <!-- 主菜单 -->
                <c:choose>
                    <c:when test="${mainMenu!=null}">
                    <jsp:invoke fragment="mainMenu"/>
                    </c:when>
                    <c:otherwise>
                    <c:if test="${positions['mainMenu']!=null}">
                    <div id="main-menu">
                        <div class="bar">
                            <div class="bar-in">
                            <c:forEach items="${positions['mainMenu']}" var="widgetUrl">
                                <jsp:include page="/widget/${widgetUrl}"/>
                            </c:forEach>
                            </div>
                        </div>
                    </div>
                    </c:if>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${subMenu!=null}">
                        <jsp:invoke fragment="subMenu"/>
                    </c:when>
                    <c:otherwise>
                        <div id="sub-menu">
                            <div class="subbar">
                                <div class="subbar-in">
                                <c:forEach items="${positions[subMenu]}" var="widgetUrl">
                                    <jsp:include page="${widgetUrl}"/>
                                </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div id="bd" class="yui3-g">
            <!-- left column -->
            <c:choose>
                <c:when test="${leftColumn!=null}">
                    <jsp:invoke fragment="leftColumn"/>
                </c:when>
                <c:otherwise>
                    <div id="left-column" class="yui3-u">
                        <c:forEach items="${positions[leftColumn]}" var="widgetUrl">
                            <jsp:include page="${widgetUrl}"/>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
            <!-- main column -->
            <c:choose>
                <c:when test="${mainColumn!=null}">
                    <jsp:invoke fragment="mainColumn"/>
                </c:when>
                <c:otherwise>
                    <div id="main-column" class="yui3-u">
                        <c:forEach items="${positions[mainColumn]}" var="widgetUrl">
                            <jsp:include page="${widgetUrl}"/>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
            <!-- right column -->
            <c:choose>
                <c:when test="${rightColumn!=null}">
                    <jsp:invoke fragment="rightColumn"/>
                </c:when>
                <c:otherwise>
                    <div id="right-column" class="yui3-u">
                        <c:forEach items="${positions[rightColumn]}" var="widgetUrl">
                            <jsp:include page="${widgetUrl}"/>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <div id="ft" class="yui3-g">
            <!-- copyright -->
            <c:choose>
                <c:when test="${copyright!=null}">
                    <jsp:invoke fragment="copyright" />
                </c:when>
                <c:otherwise>
                    <div id="copyright" class="yui3-u-1">
                        <c:forEach items="${positions[copyright]}" var="widgetUrl">
                            <jsp:include page="${widgetUrl}"/>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>

