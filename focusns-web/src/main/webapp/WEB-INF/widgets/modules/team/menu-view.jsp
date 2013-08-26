<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp"%>

<ui:widget>
    <ui:widget-head>
        <h3>好友管理</h3>
    </ui:widget-head>
    <ui:widget-body>
        <div class="team-menu">
            <ul>
                <li>
                    <i class="icon-plus"></i>
                    <a href="<c:url value="/team/member;p=${project.code},m=edit"/>">添加好友</a>
                </li>
                <li>
                    <i class="icon-folder-close"></i>
                    <a href="<c:url value="/team/role;p=${project.code},m=edit"/>">分组管理</a>
                </li>
            </ul>
        </div>
    </ui:widget-body>
</ui:widget>