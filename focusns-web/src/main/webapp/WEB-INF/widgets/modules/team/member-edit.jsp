<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget styleClass="team-member-edit">
    <ui:widget-head>
        <ul class="nav nav-tabs">
            <li <c:if test="${empty param.tab}">class="active"</c:if> >
                <a href="<c:url value="/team/member;p=${project.code},m=edit" />">所有成员</a>
            </li>
            <li <c:if test="${param.tab eq 'link1'}">class="active"</c:if> >
                <a href="<c:url value="/team/member;p=${project.code},m=edit,tab=link1" />">关注</a>
            </li>
            <li <c:if test="${param.tab eq 'link2'}">class="active"</c:if> >
                <a href="<c:url value="/team/member;p=${project.code},m=edit,tab=link2" />">粉丝</a>
            </li>
        </ul>
    </ui:widget-head>
    <ui:widget-body>
        <div class="tab-content">
            <div id="users" class="tab-pane <c:if test="${empty param.tab}">active</c:if> ">
                <ul class="thumbnails">
                    <c:forEach items="${userPage.results}" var="projectUser">
                        <li class="thumbnail span3">
                            <div class="media">
                                <a href="<c:url value="/profile;p=${projectUser.project.code}" />" class="pull-left">
                                    <tool:img-avatar projectUserId="${projectUser.id}" styleClass="media-object" width="80" height="80" />
                                </a>
                                <div class="media-body">
                                    <h3 class="media-heading">
                                        <a href="<c:url value="/profile;p=${projectUser.project.code}" />">
                                                ${projectUser.nickname}
                                        </a>
                                        <span class="pull-right badge badge-important">已关注</span>
                                    </h3>
                                    <p>${projectUser.project.description}</p>
                                </div>
                            </div>
                            <div class="pull-right btn-group dropup">
                                <a class="btn btn-small dropdown-toggle" data-toggle="dropdown" href="#">
                                    加为好友
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <c:forEach items="${projectRoles}" var="projectRole">
                                    <li>
                                        <a href="#">${projectRole.label}</a>
                                    </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div id="myModal" class="modal hide fade">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3>Modal header</h3>
                </div>
                <div class="modal-body">
                    <p>One fine body…</p>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn" data-dismiss="modal" aria-hidden="true">Close</a>
                    <a href="#" class="btn btn-primary">Save changes</a>
                </div>
            </div>

            <div id="link1" class="tab-pane <c:if test="${param.tab eq 'link1'}">active</c:if> ">
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

            <div id="link2" class="tab-pane <c:if test="${param.tab eq 'link2'}">active</c:if> ">
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

