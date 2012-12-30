<#import "/WEB-INF/libftl/utils.ftl" as utils>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh" lang="zh">
    <head>
        <title>FocusSNS</title>
        <meta content="zh-CN" http-equiv="Content-Language" />
        <meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
        <meta content="" name="Keywords" />
        <meta content="" name="Description" />
        <!-- Base Stylesheet -->
        <#include "stylesheet.ftl" />
        <!-- Theme Stylesheet-->
        <#include "styletheme.ftl" />
        
        <script src="http://yui.yahooapis.com/3.8.0/build/yui/yui-min.js"></script>
    </head>
    <body <#if category?? || feature??>class="${category.code} ${feature.code}"</#if>>
        <div id="hd" class="yui3-g">
        	<div class="yui3-u-1">
                <div class="area">
                    <div id="logo">
                        <a href="#">FocusSNS</a>
                    </div>
                    <@utils.position name="userMenu" >
                        <div id="user-menu">${userMenu}</div>
                    </@utils.position>
                </div>
                <@utils.position name="mainMenu" >
                <div id="main-menu">
                    <div class="bar">
                        <div class="bar-in">${mainMenu}</div>
                    </div>
                </div>
                </@utils.position>
                <@utils.position name="projectMenu">
                <div id="project-menu">
                    <div class="subbar">
                        <div class="subbar-in">${projectMenu}</div>
                    </div>
                </div>
                </@utils.position>
            </div>
        </div>
        <div id="bd" class="yui3-g">
            <#-- render left column if exists -->
            <@utils.position name="leftColumn">
            <div id="left-column" class="yui3-u">${leftColumn}</div>
            </@utils.position>
            <#-- render main column if exists -->
            <@utils.position name="mainColumn">
            <div id="main-column" class="yui3-u">${mainColumn}</div>
            </@utils.position>
            <#-- render right column if exists -->
            <@utils.position name="rightColumn">
            <div id="right-column" class="yui3-u">${rightColumn}</div>
            </@utils.position>
        </div>
        <div id="ft" class="yui3-g">
            <div class="yui3-u-1">
                <@utils.position name="footer">
                <div id="footer">${footer}</div>
                </@utils.position>
            </div>
        </div>
    </body>
</html>
