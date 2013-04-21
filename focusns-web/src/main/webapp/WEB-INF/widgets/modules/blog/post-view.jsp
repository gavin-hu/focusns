<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-hd>
        <h3>${blogPost.title}</h3>
    </ui:widget-hd>
    <ui:widget-bd>
        <p>${blogPost.content}</p>
    </ui:widget-bd>
</ui:widget>