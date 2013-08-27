<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget styleClass="team-role-list">
    <ui:widget-head>
        <h3>好友分组</h3>
    </ui:widget-head>
    <ui:widget-body>
        <ul>
            <li>
                <i class="icon-user"></i>
                <a href="<c:url value="/team;p=${project.code}"/>">所有分组</a>
            </li>
            <c:forEach items="${projectRoles}" var="projectRole">
                <li>
                    <i class="icon-user"></i>
                    <a href="<c:url value="/team;p=${project.code},roleId=${projectRole.id}"/>">${projectRole.label}</a>
                </li>
            </c:forEach>
        </ul>
    </ui:widget-body>
</ui:widget>