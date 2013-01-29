<div class="widget">
    <div class="widget-hd">
        <h2>系统环境</h2>
    </div>
    <div class="widget-bd">
        <div class="summary-os">
            <table>
                <tr>
                    <th>操作系统名称</th>
                    <td>${Request.envOS.osName!"无..."}</td>
                </tr>
                <tr>
                    <th>操作系统版本</th>
                    <td>${Request.envOS.osVersion!"无..."}</td>
                </tr>
                <tr>
                    <th >系统CPU架构</th>
                    <td>${Request.envOS.osArch!"无..."}</td>
                </tr>
                <tr>
                    <th>操作系统补丁</th>
                    <td>${Request.envOS.osPatch!"无..."}</td>
                </tr>
                <tr>
                    <th>操作系统PATH</th>
                    <td>${Request.envOS.osPath!"无..."}</td>
                </tr>
            </table>
        </div>
    </div>
</div>