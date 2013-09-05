<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget styleClass="admin-menu">
    <ui:widget-body>
        <h3>基本设置</h3>
        <ul>
            <li>
                <a href="<c:url value="/admin/project;p=${project.code},m=edit" />">空间信息</a>
            </li>
        </ul>
        <h3>安全相关</h3>
        <ul>
            <li>
                <a href="<c:url value="/admin/role;p=${project.code},m=edit" />">空间角色</a>
            </li>
            <li>
                <a href="<c:url value="/admin/permission;p=${project.code},m=edit" />">访问权限</a>
            </li>
        </ul>
    </ui:widget-body>
</ui:widget>