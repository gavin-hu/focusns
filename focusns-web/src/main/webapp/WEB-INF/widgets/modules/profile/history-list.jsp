<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="widget">
    <div class="widget-bd">
        <div class="histories">
            <c:forEach items="${page.results}" var="history">
            <div class="history">
                <a href='<c:url value="/index" />'>
                    <img class="thumbnail" src='<c:url value="/profile/${history.projectId}/logo" />'/>
                </a>

                <div class="content">${history.content}</div>
                <div class="status">
                    <abbr class="date" title="${history.createAt}">${history.createAt}</abbr>
                </div>
                <c:if test="${not empty history.children}">
                <div class="history-children">
                    <c:forEach items="${history.children}" var="childHistory">
                    <div class="history">
                        <img class="thumbnail" src='<c:url value="/profile/user/${childHistory.createById}/logo" />'/>
                        <div class="content">${childHistory.content}</div>
                        <div class="status">
                            <abbr class="date"title="${childHistory.createAt}">${childHistory.createAt}</abbr>
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