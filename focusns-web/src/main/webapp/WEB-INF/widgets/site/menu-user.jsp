<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-bd>
        <div class="navbar-text pull-right dropdown">
            <a class="navbar-link dropdown-toggle" data-toggle="dropdown" href="#">
                <t:img-avatar styleClass="avatar" dimension="24" projectId="${sessionScope.user.projectId}" projectUserId="${sessionScope.user.id}" />
                ${sessionScope.user.username}
            </a>
            <a class="navbar-link" href='<c:url value="/site/logout.action" />'>
                退出
            </a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                <c:forEach items="${features}" var="feature">
                    <li>
                        <a href='<c:url value="/${sessionScope.user.project.code}/${feature.code}" />'>${feature.label}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </ui:widget-bd>
</ui:widget>
