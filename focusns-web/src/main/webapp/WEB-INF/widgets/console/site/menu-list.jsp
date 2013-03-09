<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="widget">
    <div class="widget-hd">
        <h2>菜单管理</h2>
    </div>
    <div class="widget-bd">
        <div class="menu-items">
            <table>
                <tr>
                    <th>菜单编码</th>
                    <th>菜单名称</th>
                    <th>菜单顺序</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.code}</td>
                    <td>${category.label}</td>
                    <td>${category.level}</td>
                    <td>
                        <a href='<c:url value="/console/site?mode=menu-edit" />'>新增</a>
                        <a href='<c:url value="/console/site?mode=menu-edit&id=${category.id}" />'>修改</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>