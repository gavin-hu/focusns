<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="widget">
    <div class="widget-bd">
        <form action='<c:url value="/project/user-avatar/upload.action" />'
              method="post" enctype="multipart/form-data">
            <input type="file" name="file" accept="image/*" />
            <input type="hidden" name="projectId" value="${project.id}" />
            <input type="hidden" name="userId" value="${projectUser.id}" />
            <button type="submit">上传</button>
        </form>

        <c:if test="${hasTempFile}">
        <div class="cropper-wrapper">
            <img id="cropper" src='<c:url value="/project/user-avatar.action?projectId=${project.id}&userId=${projectUser.id}" />' alt="Avatar" />
            <form class="hidden" action='<c:url value="/project/user-avatar/crop.action"/>' method="post">
                <input type="hidden" id="x" name="x" />
                <input type="hidden" id="y" name="y" />
                <input type="hidden" id="w" name="w" />
                <input type="hidden" id="h" name="h" />
                <button type="submit">裁剪</button>
            </form>
        </div>
        </c:if>

        <img class="thumbnail" src='<c:url value="/project/user-avatar.action?userId=${projectUser.id}" />' />
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