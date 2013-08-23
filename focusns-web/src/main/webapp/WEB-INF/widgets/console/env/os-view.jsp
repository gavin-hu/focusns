<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<div class="widget">
    <div class="widget-hd">
        <h3>系统环境</h3>
    </div>
    <div class="widget-bd">
        <div class="summary-os">
            <table class="table table-bordered">
                <tr>
                    <th>操作系统名称</th>
                    <td>${envOS.osName}</td>
                </tr>
                <tr>
                    <th>操作系统版本</th>
                    <td>${envOS.osVersion}</td>
                </tr>
                <tr>
                    <th >系统CPU架构</th>
                    <td>${envOS.osArch}</td>
                </tr>
                <tr>
                    <th>操作系统补丁</th>
                    <td>${envOS.osPatch}</td>
                </tr>
                <tr>
                    <th>操作系统PATH</th>
                    <td>${envOS.osPath}</td>
                </tr>
            </table>
        </div>
    </div>
</div>