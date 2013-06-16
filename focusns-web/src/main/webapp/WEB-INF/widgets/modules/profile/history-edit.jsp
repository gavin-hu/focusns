<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-body>
        <form action='<widget:actionUrl value="/project/history-create" />' method="post">
            <fieldset>
                <legend>说说</legend>
                <textarea name="content"></textarea>
                <input type="hidden" name="createdById" value="${template.createdById}" />
                <input type="hidden" name="targetId" value="${template.targetId}" />
                <input type="hidden" name="targetType" value="${template.targetType}" />
                <input type="hidden" name="projectId" value="${template.projectId}" />
                <input type="hidden" name="redirect" value='<c:url value="/${project.code}/profile" />' />

                <button class="btn btn-primary pull-right" type="submit">分享</button>
            </fieldset>
        </form>
    </ui:widget-body>
</ui:widget>