<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<div class="widget">
    <div class="widget-bd">
        <div class="histories">
            <c:forEach items="${page.results}" var="history">
            <div class="history">
                <a href='<c:url value="/${history.createBy.project.code}/profile" />'>
                    <ui:avatar styleClass="thumbnail" dimension="50" projectId="${history.createBy.projectId}" projectUserId="${history.createBy.id}" />
                </a>
                <div class="content">${history.content}</div>
                <div class="status">
                    <abbr class="date" title='<fmt:formatDate value="${history.createAt}" pattern="yyyy-MM-dd HH:mm:ss" />'>
                    ${history.createAt}</abbr>
                </div>
                <c:if test="${not empty history.children}">
                <div class="history-children">
                    <c:forEach items="${history.children}" var="childHistory">
                    <div class="history">
                        <a href='<c:url value="/${childHistory.createBy.project.code}/profile" />'>
                        <ui:avatar styleClass="thumbnail" dimension="50" projectId="${childHistory.createBy.projectId}" projectUserId="${childHistory.createBy.id}" />
                        </a>
                        <div class="content">${childHistory.content}</div>
                        <div class="status">
                            <abbr class="date" title='<fmt:formatDate value="${childHistory.createAt}" pattern="yyyy-MM-dd HH:mm:ss" />'>
                            ${childHistory.createAt}</abbr>
                        </div>
                    </div>
                    </c:forEach>
                </div>
                </c:if>
                <c:if test="${not empty sessionScope.user}">
                <div class="history-reply">
                    <form action='<c:url value="/widget/project/history-create" />' method="post">
                        <textarea name="content"></textarea>

                        <input type="hidden" name="targetType" value="${template.targetType}"/>
                        <input type="hidden" name="targetId" value="${template.targetId}"/>
                        <input type="hidden" name="projectId" value="${template.projectId}"/>
                        <input type="hidden" name="createById" value="${template.createById}"/>
                        <input type="hidden" name="parentId" value="${history.id}"/>
                        <input type="hidden" name="redirect" value='<c:url value="/${project.code}/profile" />' />
                        <div class="submit">
                            <button type="submit">回复</button>
                        </div>
                    </form>
                </div>
                </c:if>
            </div>
        </c:forEach>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        // time ago
        $('abbr.date').timeago();
        //
        $('div.history-reply textarea').focus(function () {
            $(this).height(50);
        });
    });
</script>