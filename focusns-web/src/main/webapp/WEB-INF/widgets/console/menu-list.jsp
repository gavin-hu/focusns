<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<div class="widget">
    <div class="widget-bd">
        <div class="well console-menu">
            <ul class="nav nav-list">
                <li class="nav-header">
                    站点管理
                </li>
                <li>
                    <a href='#'>首页管理</a>
                </li>
                <li>
                    <a href='<c:url value="/console/site?mode=menu-list" />'>菜单管理</a>
                </li>
                <li>
                    <a href='#'>版权管理</a>
                </li>

                <li class="nav-header">
                    内容管理
                </li>
                <li>
                    <a href='<c:url value="/console/cms?mode=category-list" />'>栏目管理</a>
                </li>
                <li>
                    <a href='<c:url value="/console/cms?mode=article-list" />'>文章管理</a>
                </li>
                <li>
                    <a href='<c:url value="/console/cms?mode=comment-list" />'>评论管理</a>
                </li>

                <li class="nav-header">
                    安全管理
                </li>
                <li>
                    <a href='#'>系统权限管理</a>
                </li>
                <li>
                    <a href='#'>分类权限管理</a>
                </li>
                <li class="nav-header">
                    环境信息
                </li>
                <li <c:if test="${fn:contains(pageContext.request.requestURI, 'm=os')}">class="active"</c:if>>
                    <a href='<c:url value="/console/environment;m=os" />'>系统环境</a>
                </li>
                <li <c:if test="${fn:contains(pageContext.request.requestURI, 'm=jre')}">class="active"</c:if>>
                    <a href='<c:url value="/console/environment;m=jre" />'>Java环境</a>
                </li>
                <li <c:if test="${fn:contains(pageContext.request.requestURI, 'm=db')}">class="active"</c:if> >
                    <a href='<c:url value="/console/environment;m=db" />'>数据库环境</a>
                </li>
            </ul>
        </div>
    </div>
</div>