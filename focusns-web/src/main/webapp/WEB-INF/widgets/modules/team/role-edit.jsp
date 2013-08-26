<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget styleClass="team-role-edit">
    <ui:widget-head>
        <h3>分组编辑</h3>
    </ui:widget-head>
    <ui:widget-body>
        <form class="valid-inline form-inline" action="<widget:actionUrl value="/team/role-create" />" method="post">
            <div class="control-group">
                <label class="control-label">分组名称</label>

                <div class="controls">
                    <input type="text" name="label" value="${projectRole.label}" placeholder="分组名称"
                           data-rule-required="true" data-msg-required="分组名称不能为空！"/>
                </div>
            </div>

            <input type="hidden" name="id" value="${projectRole.id}"/>
            <input type="hidden" name="projectId" value="${projectRole.projectId}"/>

            <button type="submit" class="btn btn-primary">添加</button>
        </form>

        <c:forEach items="${projectRoles}" var="projectRole" varStatus="status">
            <form class="valid-inline form-inline" action="<widget:actionUrl value="/team/role-modify" />"
                  method="post">
                <div class="control-group">
                    <div class="controls">
                        <input type="text" name="label" value="${projectRole.label}" placeholder="分组名称"
                               data-rule-required="true" data-msg-required="分组名称不能为空！"/>
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