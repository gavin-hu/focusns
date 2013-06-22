<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<div id="${widgetConfig.id}" class="widget">
    <div class="widget-hd">
        <h3>关注列表</h3>
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
                            <tool:img-avatar projectUserId="${projectLink.toProject.createById}" width="65" height="65"/>
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