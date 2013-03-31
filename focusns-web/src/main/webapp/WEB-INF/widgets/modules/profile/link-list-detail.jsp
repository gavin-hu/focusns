<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<div id="${widgetConfig.id}" class="widget">
    <div class="widget-hd">
        <h2>关注列表</h2>
    </div>
    <div class="widget-bd">
        <div class="project-links-detail">
            <c:choose>
                <c:when test="${empty page.results}">
                    当前还没有关注...
                </c:when>
                <c:otherwise>
                    <ul class="">
                    <c:forEach items="${page.results}" var="projectLink">
                        <li url='<c:url value="/${projectLink.toProject.code}/profile"/>'>
                            <ui:avatar dimension="65" projectId="${projectLink.toProject.id}" projectUserId="${projectLink.toProject.createById}" />
                            <h3>
                                ${projectLink.toProject.title}
                                <c:choose>
                                    <c:when test="${projectLink.mutual}">
                                        <span>(相互关注)</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span>(已关注)</span>
                                    </c:otherwise>
                                </c:choose>

                            </h3>
                            <p class="description">
                                ${projectLink.toProject.description}
                            </p>
                        </li>
                    </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function(){
        $('#${widgetConfig.id} ul li').mouseover(function(){
            $(this).css('cursor', 'pointer');
        });
        $('#${widgetConfig.id} ul li').click(function(){
            var url = $(this).attr('url');
            document.location.href = url;
        });
    });
</script>