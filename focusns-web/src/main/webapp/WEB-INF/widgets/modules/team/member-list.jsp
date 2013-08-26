<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget styleClass="team-member-list">
    <ui:widget-head>
        <ul class="nav nav-tabs">
            <li <c:if test="${empty param.tab or param.tab eq 'team'}">class="active"</c:if> >
                <a href="#team" data-toggle="tab">
                    <c:choose>
                        <c:when test="${empty teamRole}">所有分组</c:when>
                        <c:otherwise>${teamRole.label}</c:otherwise>
                    </c:choose>
                </a>
            </li>
            <li <c:if test="${param.tab eq 'link1'}">class="active"</c:if> >
                <a href="#link1" data-toggle="tab">关注</a>
            </li>
            <li <c:if test="${param.tab eq 'link2'}">class="active"</c:if> >
                <a href="#link2" data-toggle="tab">粉丝</a>
            </li>
        </ul>
    </ui:widget-head>
    <ui:widget-body>
        <div class="tab-content">
            <div id="team" class="tab-pane active">
                <ul class="thumbnails">
                    <li class="span3">
                        <div class="thumbnail">
                            <img data-src="holder.js/300x200" alt="">
                            <h3>Thumbnail label</h3>
                            <p>Thumbnail caption...</p>
                        </div>
                    </li>
                    <li class="span3">
                        <div class="thumbnail">
                            <img data-src="holder.js/300x200" alt="">
                            <h3>Thumbnail label</h3>
                            <p>Thumbnail caption...</p>
                        </div>
                    </li>
                    <li class="span3">
                        <div class="thumbnail">
                            <img data-src="holder.js/300x200" alt="">
                            <h3>Thumbnail label</h3>
                            <p>Thumbnail caption...</p>
                        </div>
                    </li>
                    <li class="span3">
                        <div class="thumbnail">
                            <tool:img-avatar projectUserId="${projectUser.id}" width="60" height="60" />
                            <h3>Thumbnail label</h3>
                            <p>Thumbnail caption...</p>
                        </div>
                    </li>
                </ul>
            </div>

            <div id="link1" class="tab-pane">
                <c:choose>
                    <c:when test="${empty linkPage.results}">
                        <div class="alert alert-info">
                            当前还没有关注...
                        </div>
                    </c:when>
                    <c:otherwise>
                        <ul class="thumbnails">
                            <c:forEach items="${linkPage.results}" var="projectLink">
                                <li class="span3">
                                    <div class="thumbnail">
                                        <tool:img-avatar projectUserId="${projectLink.toProject.createdById}" width="80" height="80" />
                                        <h3>${projectLink.toProject.title}</h3>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>

            <div id="link2" class="tab-pane">
                <c:choose>
                    <c:when test="${empty reversedLinkPage.results}">
                        <div class="alert alert-info">
                            当前还没有粉丝...
                        </div>
                    </c:when>
                    <c:otherwise>
                    <ul class="thumbnails">
                        <c:forEach items="${reversedLinkPage.results}" var="projectLink">
                            <li class="span3">
                                <div class="thumbnail">
                                    <tool:img-avatar projectUserId="${projectLink.fromProject.createdById}" width="80" height="80" />
                                    <h3>${projectLink.fromProject.title}</h3>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </ui:widget-body>
</ui:widget>


