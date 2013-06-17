<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/widgets/widget.jsp"%>

<style>
    table.project-info {
        width: 100%;
        border: goldenrod 3px solid;
        margin-bottom: 10px;
    }
    table.project-info th,
    table.project-info td {
        padding: 0 10px;
        border: gold 1px solid;
    }
    table.project-info th {
        width: 35%;
        text-align: right;
    }
</style>
<ui:widget>
    <ui:widget-head>
        <h3>空间信息</h3>
    </ui:widget-head>
    <ui:widget-body>
        <table class="project-info">
            <tr>
                <th>空间ID</th>
                <td>${project.code}</td>
            </tr>
            <tr>
                <th>空间名称</th>
                <td>${project.title}</td>
            </tr>
            <tr>
                <th>空间介绍</th>
                <td>${project.description}</td>
            </tr>
            <tr>
                <th>创建者</th>
                <td>${project.createdBy.username}</td>
            </tr>
            <tr>
                <th>创建时间</th>
                <td><fmt:formatDate value="${project.createdAt}" /></td>
            </tr>
        </table>
    </ui:widget-body>
    <ui:widget-foot>
        <div class="alert alert-error">
         这是一个简单的空间信息显示插件...
        </div>
    </ui:widget-foot>
</ui:widget>