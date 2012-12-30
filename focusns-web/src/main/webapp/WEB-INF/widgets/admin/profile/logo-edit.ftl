<#import "/WEB-INF/libftl/utils.ftl" as utils>

<div class="widget">
    <div class="widget-hd">
        <h2>标志编辑</h2>
    </div>
    <div class="widget-bd">
         <div class="admin-logo">
            <form action="${Request.contextPath}/${Request.project.code}/${Request.feature.code}/project/logo-upload.action" 
                  method="post" enctype="multipart/form-data">
                <input type="file"name="file" accept="image/*" />
                <button type="submit">上传</button>
            </form>
            <#if Request.hasLogo>
            <img src="<@utils.urlPrefix />/project/logo-link.jpg" alt="Logo" />
            </#if>
            <#if Request.hasTmpLogo>
            
            <div id="cropper-wrap">
                <img id="cropper" src="<@utils.urlPrefix />/project/logo-link.tmp" alt="Logo" />
                <form class="hidden" action="<@utils.urlPrefix />/project/logo-crop.action" method="post">
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
YUI().use('node', 'yui2-imagecropper', function(Y) {
    //
    Y.one('body').addClass('yui-skin-sam');
    //
    var YAHOO = Y.YUI2;
    var cropper = new YAHOO.widget.ImageCropper('cropper', {
        initWidth:100,
        initHeight:100,
    });
    var cropHandler = function(){
        var region = cropper.getCropCoords(); 
        Y.one('#x').set('value', region.left);
        Y.one('#y').set('value', region.top);
        Y.one('#w').set('value', region.width);
        Y.one('#h').set('value', region.height);
    }

    cropper.on('moveEvent', cropHandler);
    
    cropHandler.call();

});
</script>