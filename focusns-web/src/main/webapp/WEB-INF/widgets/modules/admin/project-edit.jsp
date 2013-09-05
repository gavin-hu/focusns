<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-head>
        <h3>空间编辑</h3>
    </ui:widget-head>
    <ui:widget-body>
        <c:if test="${navigateStatus eq 'project-modified'}">
            <div class="alert alert-info">
                编辑成功！
            </div>
            <c:remove var="redirectAttributes" scope="session" />
        </c:if>

        <widget:actionUrl value="/admin/project-modify" var="actionUrl" />
        <form class="valid-inline form-horizontal" action="${actionUrl}" method="post">
            <div class="control-group">
                <label class="control-label" for="inputCode">空间ID</label>
                <div class="controls">
                    <input type="text" id="inputCode" readonly="readonly" name="code" value="${project.code}" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="inputTitle">空间名称</label>
                <div class="controls">
                    <input type="text" id="inputTitle" name="title" value="${project.title}"
                           data-rule-required="true" data-msg-required="空间名称不能为空！"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="inputDesc">空间介绍</label>
                <div class="controls">
                    <input type="text" id="inputDesc" name="description" value="${project.description}"
                           data-rule-required="true" data-msg-required="空间介绍不能为空！"/>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <button type="submit" class="btn btn-primary">提交</button>

                    <input type="hidden" name="id" value="${project.id}" />
                </div>
            </div>
        </form>
    </ui:widget-body>
</ui:widget>