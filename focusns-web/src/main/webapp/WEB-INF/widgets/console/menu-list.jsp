<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="widget">
    <div class="widget-hd">
        <h2>控制台</h2>
    </div>
    <div class="widget-bd">
        <div class="console-menu">
            <h3>站点管理</h3>
            <ul>
                <li>
                    <a href='#'>首页管理</a>
                </li>
                <li>
                    <a href='<c:url value="/console/site?mode=menu-list" />'>菜单管理</a>
                </li>
                <li>
                    <a href='#'>版权管理</a>
                </li>
            </ul>
            <h3>内容管理</h3>
            <ul>
                <li>
                    <a href='<c:url value="/console/cms?mode=category-list" />'>栏目管理</a>
                </li>
                <li>
                    <a href='<c:url value="/console/cms?mode=article-list" />'>文章管理</a>
                </li>
                <li>
                    <a href='<c:url value="/console/cms?mode=comment-list" />'>评论管理</a>
                </li>
            </ul>
            <h3>安全管理</h3>
            <ul>
                <li>
                    <a href='#'>系统权限管理</a>
                </li>
                <li>
                    <a href='#'>分类权限管理</a>
                </li>
            </ul>
            <h3>环境信息</h3>
            <ul class="last">
                <li>
                    <a href='<c:url value="/console/environment?mode=os" />'>系统环境</a>
                </li>
                <li>
                    <a href='<c:url value="/console/environment?mode=jre" />'>Java环境</a>
                </li>
                <li>
                    <a href='<c:url value="/console/environment?mode=db" />'>数据库环境</a>
                </li>
            </ul>
        </div>
    </div>
</div>