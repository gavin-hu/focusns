<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget styleClass="menu-sub">
    <ui:widget-body>
        <ul class="nav nav-pills">
            <c:forEach items="${features}" var="feature">
                <li <c:if test="${projectFeature.code eq feature.code}">class="active"</c:if> >
                    <a href='<c:url value="/${feature.code};p=${project.code}" />'>
                        <h2>${feature.label}</h2>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </ui:widget-body>
</ui:widget>