<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/widgets/widget.jsp" %>

<ui:widget>
    <ui:widget-bd>
        <form action='<c:url value="/project/user-avatar/upload.action" />'
              method="post" enctype="multipart/form-data">
            <fieldset>
                <legend>头像上传</legend>

                <input type="file" name="file" title="选择文件..." />

                <input type="hidden" name="projectId" value="${project.id}" />
                <input type="hidden" name="userId" value="${projectUser.id}" />
                <input type="hidden" name="redirect" value="${currentPath}" />
                <button class="btn btn-primary">上传</button>
            </fieldset>
        </form>

        <c:if test="${hasTempFile}">
            <div class="cropper-wrapper temp-avatar">
                <img id="cropper" class="thumbnail" src='<c:url value="/project/user-avatar.action?projectId=${project.id}&userId=${projectUser.id}&isTempFile=true" />' alt="Avatar" />
                <form action='<c:url value="/project/user-avatar/crop.action"/>' method="post">
                    <input type="hidden" id="x" name="x" />
                    <input type="hidden" id="y" name="y" />
                    <input type="hidden" id="w" name="w" />
                    <input type="hidden" id="h" name="h" />
                    <input type="hidden" name="projectId" value="${project.id}" />
                    <input type="hidden" name="userId" value="${projectUser.id}" />
                    <button class="btn btn-primary" type="submit" name="redirect" value="${currentPath}">裁剪</button>
                </form>
            </div>
        </c:if>
    </ui:widget-bd>
</ui:widget>