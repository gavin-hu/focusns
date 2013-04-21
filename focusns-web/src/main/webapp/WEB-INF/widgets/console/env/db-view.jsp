<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<div class="widget">
    <div class="widget-hd">
        <h3>数据库环境</h3>
    </div>
    <div class="widget-bd">
        <div class="summary-db">
            <table>
                <tr>
                    <th>数据库名称</th>
                    <td>${envDB.databaseName}</td>
                </tr>
                <tr>
                    <th>数据库版本</th>
                    <td>${envDB.databaseVersion}</td>
                </tr>
                <tr>
                    <th >数据库驱动名称</th>
                    <td>${envDB.driverName}</td>
                </tr>
                <tr>
                    <th>数据库驱动版本</th>
                    <td>${envDB.driverVersion}</td>
                </tr>
                <tr>
                    <th>数据库URL</th>
                    <td>${envDB.url}</td>
                </tr>
                <tr>
                    <th>数据库用户名</th>
                    <td>${envDB.username}</td>
                </tr>
            </table>
        </div>
    </div>
</div>