<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget styleClass="team-member-list">
    <ui:widget-head>
        <ul class="nav nav-tabs">
            <li <c:if test="${empty param.tab}">class="active"</c:if> >
                <a href="<c:url value="/team;p=${project.code}" />" >所有分组</a>
            </li>
            <c:forEach items="${projectRoles}" var="projectRole">
                <li <c:if test="${param.tab eq 'link1'}">class="active"</c:if> >
                    <a href="<c:url value="/team;p=${project.code},roleId=${projectRole.id}" />">${projectRole.label}</a>
                </li>
            </c:forEach>
        </ul>
    </ui:widget-head>
    <ui:widget-body>
        <ul class="thumbnails">
            <c:forEach items="${teamMemberPage.results}" var="teamMember">
                <li class="span3">
                    <div class="thumbnail">
                        <div class="media">
                            <a href="<c:url value="/profile;p=${teamMember.project.code}" />" class="pull-left">
                                <tool:img-avatar projectUserId="${teamMember.user.id}" styleClass="media-object" width="80" height="80" />
                            </a>
                            <div class="media-body">
                                <h3 class="media-heading">
                                    <a href="<c:url value="/profile;p=${teamMember.project.code}" />">
                                            ${teamMember.user.nickname}
                                    </a>
                                        <%--<span class="pull-right badge badge-important">已关注</span>--%>
                                </h3>
                                <p>${teamMember.user.project.description}</p>
                            </div>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </ui:widget-body>
</ui:widget>


