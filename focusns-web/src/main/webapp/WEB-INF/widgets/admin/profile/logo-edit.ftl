<div class="widget">
    <div class="widget-hd">
        <h2>标志编辑</h2>
    </div>
    <div class="widget-bd">
         <div class="admin-logo">
            <form action="${Request.contextPath}/project/logo/upload" 
                  method="post" enctype="multipart/form-data">
                <input type="file"name="file" accept="image/*" />
                <button type="submit">上传</button>
            </form>
            <#if Request.hasLogo>
            <img src="${Request.contextPath}/project/logo/link" alt="Logo" />
            </#if>
            <#if Request.hasTmpLogo>
            
            <div id="cropper-wrap">
                <img id="cropper" src="${Request.contextPath}/project/logo/link.tmp" alt="Logo" />
                <form class="hidden" action="${Request.contextPath}/project/logo/crop" method="post">
                    <input type="hidden" id="x" name="x" />
                    <input type="hidden" id="y" name="y" />
                    <input type="hidden" id="w" name="w" />
                    <input type="hidden" id="h" name="h" />
                    <button type="submit">裁剪</button>
                </form>
            </div>
            </#if>
        </div>
    </div>
</div>

<script type="text/javascript">
$(function(){
    $('#cropper').Jcrop({
        onSelect:function(c) {
            $('#x').val(c.x);
            $('#y').val(c.y);
            $('#w').val(c.w);
            $('#h').val(c.h);
        }
    });
});
</script>