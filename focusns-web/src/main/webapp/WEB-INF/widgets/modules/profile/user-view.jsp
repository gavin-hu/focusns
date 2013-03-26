<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<div class="widget">
    <div class="widget-bd">
        <div class="profile-user">
            <ui:avatar dimension="64" projectId="${project.id}" projectUserId="${projectUser.id}" />
            <a href='<c:url value="/${project.code}/profile?mode=edit" />'>编辑</a>
        </div>
    </div>
</div>