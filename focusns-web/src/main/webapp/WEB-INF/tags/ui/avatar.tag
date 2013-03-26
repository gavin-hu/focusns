<%@tag description="Widget Wrapper" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="styleId" required="false" %>
<%@attribute name="styleClass" required="false" %>
<%@attribute name="dimension" required="true" %>
<%@attribute name="projectId" required="true" %>
<%@attribute name="projectUserId" required="true" %>

<img id="${styleId}" class="${styleClass}" src='<c:url value="/project/user-avatar.action?projectId=${projectId}&userId=${projectUserId}&dimension=${dimension}" />' />