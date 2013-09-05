<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget styleClass="admin-role-edit">
    <ui:widget-head>
        <h3>角色编辑</h3>
    </ui:widget-head>
    <ui:widget-body>
        <form class="valid-inline form-inline" action="<widget:actionUrl value="/admin/role-create" />" method="post">
            <div class="control-group">
                <label class="control-label">角色名称</label>

                <div class="controls">
                    <input type="text" name="label" value="${projectRole.label}" placeholder="角色名称"
                           data-rule-required="true" data-msg-required="角色名称不能为空！"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">角色级别</label>

                <div class="controls">
                    <input type="text" name="level" value="${projectRole.level}" placeholder="角色级别"
                           data-rule-required="true" data-msg-required="角色级别不能为空！"/>
                </div>
            </div>

            <input type="hidden" name="id" value="${projectRole.id}"/>
            <input type="hidden" name="projectId" value="${projectRole.projectId}"/>

            <button type="submit" class="btn btn-primary">添加</button>
        </form>

        <c:forEach items="${projectRoles}" var="projectRole" varStatus="status">
            <form class="valid-inline form-inline" action="<widget:actionUrl value="/admin/role-modify" />"
                  method="post">
                <div class="control-group">
                    <div class="controls">
                        <input type="text" name="label" value="${projectRole.label}" placeholder="角色名称"
                               data-rule-required="true" data-msg-required="角色名称不能为空！"/>
                        <input type="text" name="level" value="${projectRole.level}" placeholder="角色级别"
                               data-rule-required="true" data-msg-required="角色级别不能为空！"/>
                        <button type="submit" class="btn btn-primary">修改</button>
                        <a class="btn" href="<widget:actionUrl value="/team/role-remove?id=${projectRole.id}" />">删除</a>
                    </div>
                </div>

                <input type="hidden" name="id" value="${projectRole.id}"/>
                <input type="hidden" name="projectId" value="${projectRole.projectId}"/>
            </form>
        </c:forEach>
    </ui:widget-body>
</ui:widget>