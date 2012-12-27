<div class="logo">
    <form action="${Request.contextPath}/${Request.project.code}/${Request.feature.code}/project/logo-upload.action" 
          method="post" enctype="multipart/form-data">
        <input type="file"name="file" accept="image/*" />
        <button type="submit">上传</button>
    </form>
    <#if Request.hasLogo>
    <img src="${Request.contextPath}/${Request.project.code}/${Request.feature.code}/project/logo-link.jpg" alt="Logo" />
    </#if>
    <#if Request.hasTmpLogo>
    <img src="${Request.contextPath}/${Request.project.code}/${Request.feature.code}/project/logo-link.tmp" id="cropbox" alt="Logo" />
    <div class="jc_coords">
        <form class="hidden" action="${Request.contextPath}/${Request.project.code}/${Request.feature.code}/project/logo-crop.action" method="post" onsubmit="return checkCoords();">
            <input type="hidden" id="x" name="x" />
            <input type="hidden" id="y" name="y" />
            <input type="hidden" id="w" name="w" />
            <input type="hidden" id="h" name="h" />
            <button type="submit">裁剪图片</button>
        </form>
    </div>
    </#if>
</div>

<script language="Javascript">
jQuery(function(){
	jQuery('#cropbox').Jcrop({
        minSize : [160, 160],
        maxSize : [200, 200],
        aspectRatio: 1,
		onSelect: updateCoords
	});
});

function updateCoords(c)
{
	jQuery('#x').val(c.x);
	jQuery('#y').val(c.y);
	jQuery('#w').val(c.w);
	jQuery('#h').val(c.h);
};

function checkCoords()
{
	if (parseInt(jQuery('#w').val())>0) return true;
	alert('Please select a crop region then press submit.');
	return false;
};

jQuery(function () {
	jQuery('#showcode').FieldsetToggle('Code for this demo');
});
</script>




