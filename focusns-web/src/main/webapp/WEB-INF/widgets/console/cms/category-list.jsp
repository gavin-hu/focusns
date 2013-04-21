<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<div class="widget">
    <div class="widget-hd">
        <h3>栏目管理</h3>
    </div>
    <div class="widget-bd">
        <div class="article-categories">
            <c:choose>
                <c:when test="${empty articleCategories}">
                    <a href='<c:url value="/console/cms?mode=category-edit" />'>新增</a>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                        <tr>
                            <th>栏目名称</th>
                            <th>创建人</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${articleCategories}" var="articleCategory">
                            <tr>
                                <td>${articleCategory.label}</td>
                                <td>${articleCategory.createdById}</td>
                                <td><fmt:formatDate value="${articleCategory.createdAt}" pattern="yyyy/M/d HH:mm" /></td>
                                <td>
                                    <a href='<c:url value="/console/cms?mode=category-edit" />'>新增</a>
                                    |
                                    <a href='<c:url value="/console/cms?mode=category-edit&categoryId=${articleCategory.id}" />'>修改</a>
                                    |
                                    <c:url value="/console/cms?mode=category-list" var="redirectUrl" />
                                    <a href='<c:url value="/console/cms/category-remove.action?id=${articleCategory.id}&redirect=${redirectUrl}" />'>删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>