<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<div class="widget">
    <div class="widget-hd">
        <h3>Java环境</h3>
    </div>
    <div class="widget-bd">
        <div class="summary-jre">
            <table class="table table-bordered">
                <tr>
                    <th>Java运行时名称</th>
                    <td>${envJava.javaRuntimeName}</td>
                </tr>
                <tr>
                    <th>Java运行时版本</th>
                    <td>${envJava.javaRuntimeVersion}</td>
                </tr>
                <tr>
                    <th >Java提供商</th>
                    <td>${envJava.javaVendor}</td>
                </tr>
                <tr>
                    <th>Java版本</th>
                    <td>${envJava.javaVersion}</td>
                </tr>
                <tr>
                    <th>Java虚拟机名称</th>
                    <td>${envJava.javaVMName}</td>
                </tr>
                <tr>
                    <th>Java虚拟机版本</th>
                    <td>${envJava.javaVMVersion}</td>
                </tr>
                <tr>
                    <th>Java虚拟机提供商</th>
                    <td>${envJava.javaVMVendor}</td>
                </tr>
                <tr>
                    <th>Java虚拟机信息</th>
                    <td>${envJava.javaVMInfo}</td>
                </tr>
                <tr>
                    <th>Java主目录</th>
                    <td>${envJava.javaHome}</td>
                </tr>
                <tr>
                    <th>Java启动选项</th>
                    <td>${envJava.javaOptions}</td>
                </tr>
                <tr>
                    <th>Java类路径</th>
                    <td>${envJava.javaClassPath}</td>
                </tr>
                <tr>
                    <th>Java库路径</th>
                    <td>${envJava.javaLibraryPath}</td>
                </tr>
            </table>
        </div>
    </div>
</div>