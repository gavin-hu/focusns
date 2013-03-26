<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="widget">
    <div class="widget-bd">
        <div class="profile-user-edit">
            <form action='<c:url value="/project/user-avatar/upload.action" />'
                  method="post" enctype="multipart/form-data">
                <input type="file" name="file" accept="image/*" />
                <input type="hidden" name="projectId" value="${project.id}" />
                <input type="hidden" name="userId" value="${projectUser.id}" />
                <button type="submit" name="redirect" value="${currentPath}">上传</button>
            </form>
            <c:if test="${hasTargetFile}">
                <div class="target-avatar">
                    <img class="thumbnail" src='<c:url value="/project/user-avatar.action?projectId=${project.id}&userId=${projectUser.id}" />' />
                </div>
            </c:if>
            <c:if test="${hasTempFile}">
            <div class="cropper-wrapper temp-avatar">
                <img id="cropper" src='<c:url value="/project/user-avatar.action?projectId=${project.id}&userId=${projectUser.id}&isTempFile=true" />' alt="Avatar" />
                <form class="hidden" action='<c:url value="/project/user-avatar/crop.action"/>' method="post">
                    <input type="hidden" id="x" name="x" />
                    <input type="hidden" id="y" name="y" />
                    <input type="hidden" id="w" name="w" />
                    <input type="hidden" id="h" name="h" />
                    <input type="hidden" name="projectId" value="${project.id}" />
                    <input type="hidden" name="userId" value="${projectUser.id}" />
                    <button type="submit" name="redirect" value="${currentPath}">裁剪</button>
                </form>
            </div>
            </c:if>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function(){
        $('#cropper').Jcrop({
            aspectRatio:1,
            minSize:[80, 80],
            onSelect:function(c) {
                $('#x').val(c.x);
                $('#y').val(c.y);
                $('#w').val(c.w);
                $('#h').val(c.h);
            }
        });
    });
</script>