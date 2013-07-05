<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src='<c:url value="/static/assets/jquery/jquery.js" />'></script>
<script src='<c:url value="/static/assets/bootstrap/js/bootstrap.js" />'></script>
<script src='<c:url value="/static/assets/bootstrap/js/bootstrap-fileinput.js" />'></script>

<!-- validation -->
<script src='<c:url value="/static/assets/validation/jquery.validate.js" />'></script>
<script src='<c:url value="/static/assets/validation/additional-methods.js" />'></script>

<!-- kindeditor -->
<script src='<c:url value="/static/assets/kindeditor/kindeditor.js" />'></script>
<script src='<c:url value="/static/assets/kindeditor/lang/zh_CN.js" />'></script>

<!-- timeago -->
<script src='<c:url value="/static/assets/timeago/jquery.timeago.js" />'></script>
<script src='<c:url value="/static/assets/timeago/locales/jquery.timeago.zh-CN.js" />'></script>

<!-- jcrop -->
<script src='<c:url value="/static/assets/jcrop/jquery.Jcrop.min.js" />'></script>

<!-- yoxview -->
<script src='<c:url value="/static/assets/yoxview/yox.js" />'></script>
<script src='<c:url value="/static/assets/yoxview/jquery.yoxthumbs.js" />'></script>
<script src='<c:url value="/static/assets/yoxview/jquery.yoxview.js" />'></script>

<script type=text/javascript>
$(function() {
    // time ago
    $('abbr.date').timeago();
    $('#history-list textarea').focus(function(){
        $(this).css('height', '40px');
    });
});
</script>

<script type="text/javascript">
KindEditor.ready(function(K) {
    var options = {
        minWidth:300,
        minHeight:100
    }
    window.editor = K.create('.editor', options);
});
</script>

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

<script type="text/javascript">
    $(function(){
        $('.yoxview').yoxview({
            lang: 'zh-cn',
            skin: 'top_menu',
            allowedUrls: /.*/i
        });
    });
</script>

<script type="text/javascript">
    $(function(){
        $('form.valid').validate({
            errorClass : 'help-inline',
            errorElement : 'span',
            errorPlacement: function(error, element){
                var controlGroup = element.parents('div.control-group');
                if(controlGroup!=null) {
                    controlGroup.removeClass('success');
                    controlGroup.addClass('error');
                }
                error.insertAfter(element);
            },
            success : function(success, element) {
                var controlGroup = success.parents('div.control-group');
                if(controlGroup!=null) {
                    controlGroup.addClass('success');
                    controlGroup.removeClass('error');
                }
            }
        });
    });
</script>