<div class="widget">
    <div class="widget-hd">
        <h2>属性编辑</h2>
    </div>
    <div class="widget-bd">
        <div class="admin-attribute">
            <form action="${Request.contextPath}/project/attribute/edit" method="post">
                <span>属性名</span>
                <input name="name" />
                <span>属性值</span>
                <input name="value" />
                <span>类型</span>
                <select name="type">
                    <option value="TEXT">Text</option>
                    <option value="URL">Url</option>
                    <option value="EMAIL">Email</option>
                </select>
                <span>顺序</span>
                <select name="level">
                    <#list 1..20 as i>
                    <option value="${i*4}">${i*5}</option>
                    </#list>
                </select>
                <div class="submit">
                    <input type="hidden" name="projectId" value="${Request.project.id}" />
                    <button type="submi">添加</button>
                </div>
            </form>
            
            <ul>
                <#list Request.attributes as attribute>
                <li>
                <form action="${Request.contextPath}/project/attribute/edit" method="post">
                    <span>属性名</span>
                    <input name="name" value="${attribute.name}" />
                    <span>属性值</span>
                    <input name="value" value="${attribute.value}" />
                    <span>类型</span>
                    <select name="type">
                        <option <#if attribute.type=='TEXT'>selected="selected"</#if> value="TEXT">Text</option>
                        <option <#if attribute.type=='URL'>selected="selected"</#if> value="URL">Url</option>
                        <option <#if attribute.type=='EMAIL'>selected="selected"</#if> value="EMAIL">Email</option>
                    </select>
                    <span>顺序</span>
                    <select name="level">
                        <#list 1..20 as i>
                        <option <#if attribute.level=i*5>selected="selected"</#if> value="${i*5}">${i*5}</option>
                        </#list>
                    </select>
                    <div class="submit">
                        <input type="hidden" name="id" value="${attribute.id}" />
                        <input type="hidden" name="projectId" value="${attribute.projectId}" />
                        <button type="submi">修改</button>
                    </div>
                </form>
                </li>
                </#list>
            </ul>
        </div>
    </div>
</div>