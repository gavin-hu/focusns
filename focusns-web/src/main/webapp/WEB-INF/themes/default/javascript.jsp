<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src='<c:url value="/static/assets/jquery/jquery.js" />'></script>
<script src='<c:url value="/static/assets/bootstrap/js/bootstrap.js" />'></script>
<script src='<c:url value="/static/assets/bootstrap/js/bootstrap-fileinput.js" />'></script>

<!-- validation -->
<script src='<c:url value="/static/assets/validation/jquery.validate.js" />'></script>
<script src='<c:url value="/static/assets/validation/additional-methods.js" />'></script>

<!-- ueditor -->
<script type="text/javascript" charset="utf-8">
    window.UEDITOR_HOME_URL = "<c:url value="/static/assets/ueditor/" />";
</script>
<script src='<c:url value="/static/assets/ueditor/ueditor.parse.js" />'></script>
<script src='<c:url value="/static/assets/ueditor/ueditor.config.js" />'></script>
<script src='<c:url value="/static/assets/ueditor/ueditor.config.toolbar.js" />'></script>
<script src='<c:url value="/static/assets/ueditor/ueditor.all.js" />'></script>

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
        // normal options
        var options = {
            ignore : '.ignore',
            errorElement : 'label',
            errorPlacement: function(error, element){
                var controlGroup = element.closest('div.control-group');
                if(controlGroup!=null) {
                    controlGroup.removeClass('success');
                    controlGroup.addClass('error');
                }
                error.each(function(){
                    if('LABEL'==this.tagName) {
                        $(this).addClass('help-block');
                    } else if('SPAN'==this.tagName) {
                        $(this).addClass('help-inline');
                    }
                });
                error.insertAfter(element);
            },
            success : function(success, element) {
                var controlGroup = success.parents('div.control-group');
                if(controlGroup!=null) {
                    controlGroup.addClass('success');
                    controlGroup.removeClass('error');
                }
                success.remove();
            }
        };
        // inline options
        var inlineOptions = $.extend(options, {
            errorElement : 'span'
        });
        //
        $('form.valid').each(function(){
            $(this).validate(options)
        });
        $('form.valid-inline').each(function(){
            $(this).validate(inlineOptions);
        });
    });
</script>

<script type="text/javascript">
    $(function(){
        $('.editor').each(function(){
            var editor = new UE.ui.Editor(editor_simple);
            editor.render(this);
        });
    });
</script>