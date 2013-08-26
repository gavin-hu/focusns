<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget styleClass="team-role-list">
    <ui:widget-head>
        <h3>好友分组</h3>
    </ui:widget-head>
    <ui:widget-body>
        <c:choose>
            <c:when test="${empty projectRoles}" >
                <div class="alert alert-info">
                    当前还没有分组，现在就来 <a href="<c:url value="/team/role;p=${project.code},m=edit" />">
                    <span class="badge badge-info">添加</span></a> 吧！
                </div>
            </c:when>
            <c:otherwise>
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
            </c:otherwise>
        </c:choose>
    </ui:widget-body>
</ui:widget>