<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget styleId="project-user-edit">
    <ui:widget>
        <ul class="nav nav-tabs">
            <li <c:if test="${empty param.tab or param.tab=='user'}">class="active"</c:if>>
                <a href="#user" data-toggle="tab">个人档案</a>
            </li>
            <li <c:if test="${param.tab=='pwd'}">class="active"</c:if>>
                <a href="#pwd" data-toggle="tab">密码修改</a>
            </li>
            <li <c:if test="${param.tab=='avatar'}">class="active"</c:if>>
                <a href="#avatar" data-toggle="tab">头像上传</a>
            </li>
        </ul>
    </ui:widget>
    <ui:widget-body>
        <div class="tab-content">
            <div id="user" class="tab-pane <c:if test="${empty param.tab or param.tab=='user'}">active</c:if>" >
                <widget:actionUrl var="actionUrl" value="/project/user-modify" />
                <form:form commandName="projectUser" cssClass="valid-inline form-horizontal" action="${actionUrl}">
                    <div class="control-group">
                        <label class="control-label">昵称</label>
                        <div class="controls">
                            <form:input path="nickname" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">邮箱</label>
                        <div class="controls">
                            <form:input path="email" />
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <button type="submit" class="btn btn-primary">提交</button>
                        </div>
                    </div>
                </form:form>
            </div>
            <div id="pwd" class="tab-pane <c:if test="${param.tab=='pwd'}">active</c:if>" >
                <c:if test="${not empty serviceException}">
                <div class="alert alert-error">
                    ${serviceException.message}
                </div>
                </c:if>
                <widget:actionUrl var="actionUrl" value="/project/user-modify"/>
                <form:form commandName="projectUser" cssClass="valid-inline form-horizontal" action="${actionUrl}">
                    <div for="inputOldPassword" class="control-group">
                        <label class="control-label">旧密码</label>
                        <div class="controls">
                            <input type="password" id="inputOldPassword" name="oldPassword"
                                   data-rule-required="true" data-msg-required="旧密码不能为空！"
                                   data-rule-minlength="6" data-msg-minlength="旧密码长度必须大于6个字符！" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="inputNewPassword" class="control-label">新密码</label>
                        <div class="controls">
                            <input type="password" id="inputNewPassword" name="newPassword"
                                   data-rule-required="true" data-msg-required="新密码不能为空！"
                                   data-rule-minlength="6" data-msg-minlength="新密码长度必须大于6个字符！" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="inputConfirmNewPassword" class="control-label">确认新密码</label>
                        <div class="controls">
                            <input type="password" id="inputConfirmNewPassword" name="confirmNewPassword"
                                   data-rule-equalto="#inputNewPassword" data-msg-equalto="确认新密码错误！"
                                   data-rule-required="true" data-msg-required="确认密码不能为空！"
                                   data-rule-minlength="6" data-msg-minlength="确认密码长度必须大于6个字符！" />
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <button class="btn btn-primary">修改</button>
                        </div>
                    </div>
                </form:form>
            </div>
            <div id="avatar" class="tab-pane <c:if test="${param.tab=='avatar'}">active</c:if>" >
                <form class="valid-inline" method="post" enctype="multipart/form-data"
                      action="<widget:actionUrl value="/project/user-avatar/upload" />">
                    <div class="control-group">
                        <div class="controls">
                            <input type="file" name="file" title="本地浏览..." accept="image/*"
                                   data-rule-required="true" data-msg-required="请从本地选择要上传的头像！"/>
                            <button class="btn btn-primary">上传</button>
                        </div>
                    </div>
                    <input type="hidden" name="projectId" value="${project.id}" />
                    <input type="hidden" name="userId" value="${projectUser.id}" />

                </form>

                <c:if test="${tempExists}">
                    <div class="cropper-wrapper temp-avatar">
                        <img id="cropper" src="<widget:actionUrl value="/project/user-avatar/download?projectId=${project.id}&userId=${projectUser.id}&temp=true" />" alt="Avatar" />
                        <form action='<widget:actionUrl value="/project/user-avatar/crop"/>' method="post">
                            <input type="hidden" id="x" name="x" />
                            <input type="hidden" id="y" name="y" />
                            <input type="hidden" id="w" name="w" />
                            <input type="hidden" id="h" name="h" />
                            <input type="hidden" name="projectId" value="${project.id}" />
                            <input type="hidden" name="userId" value="${projectUser.id}" />
                            <button class="btn btn-primary" type="submit">裁剪</button>
                        </form>
                    </div>
                </c:if>
            </div>
        </div>
    </ui:widget-body>
</ui:widget>