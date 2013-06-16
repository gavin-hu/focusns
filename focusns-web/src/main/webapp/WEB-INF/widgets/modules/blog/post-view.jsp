<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-head>
        <h3>${blogPost.title}</h3>
    </ui:widget-head>
    <ui:widget-body>
        <p>${blogPost.content}</p>
    </ui:widget-body>
</ui:widget>