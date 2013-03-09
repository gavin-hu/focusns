<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="widget">
    <div class="widget-bd">
        <div class="share">
            <form action='<c:url value="/widget/project/history-create" />' method="post">
                <textarea name="content"></textarea>
                <input type="hidden" name="createById" value="${template.createById}" />
                <input type="hidden" name="targetId" value="${template.targetId}" />
                <input type="hidden" name="targetType" value="${template.targetType}" />
                <input type="hidden" name="projectId" value="${template.projectId}" />
                <input type="hidden" name="redirect" value='<c:url value="/${project.code}/profile" />' />
                <div class="submit">
                    <button type="submit">分享</button>
                </div>
            </form>
        </div>
    </div>
</div>