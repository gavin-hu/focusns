<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="widget">
    <div class="widget-hd">
        <h2>系统环境</h2>
    </div>
    <div class="widget-bd">
        <div class="summary-os">
            <table>
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