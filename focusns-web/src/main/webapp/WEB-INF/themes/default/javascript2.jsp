<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src='<c:url value="/static/assets/js/jquery.js" />'></script>
<script src='<c:url value="/static/assets/js/bootstrap.js" />'></script>
<script src='<c:url value="/static/assets/js/bootstrap-fileinput.js" />'></script>
<!--
<script src='<c:url value="/static/assets/js/bootstrap-transition.js" />'></script>
<script src='<c:url value="/static/assets/js/bootstrap-alert.js" />'></script>
<script src='<c:url value="/static/assets/js/bootstrap-modal.js" />'></script>
<script src='<c:url value="/static/assets/js/bootstrap-dropdown.js" />'></script>
<script src='<c:url value="/static/assets/js/bootstrap-scrollspy.js" />'></script>
<script src='<c:url value="/static/assets/js/bootstrap-tab.js" />'></script>
<script src='<c:url value="/static/assets/js/bootstrap-tooltip.js" />'></script>
<script src='<c:url value="/static/assets/js/bootstrap-popover.js" />'></script>
<script src='<c:url value="/static/assets/js/bootstrap-button.js" />'></script>
<script src='<c:url value="/static/assets/js/bootstrap-collapse.js" />'></script>
<script src='<c:url value="/static/assets/js/bootstrap-carousel.js" />'></script>
<script src='<c:url value="/static/assets/js/bootstrap-typeahead.js" />'></script>
-->

<!-- kindeditor -->
<script src='<c:url value="/static/assets/js/kindeditor/kindeditor.js" />'></script>
<script src='<c:url value="/static/assets/js/kindeditor/lang/zh_CN.js" />'></script>

<!-- timeago -->
<script src='<c:url value="/static/assets/js/timeago/jquery.timeago.js" />'></script>
<script src='<c:url value="/static/assets/js/timeago/locales/jquery.timeago.zh-CN.js" />'></script>

<!-- jcrop -->
<script src='<c:url value="/static/assets/js/jcrop/jquery.Jcrop.min.js" />'></script>

<!-- yoxview -->
<script src='<c:url value="/static/assets/js/yoxview/yox.js" />'></script>
<script src='<c:url value="/static/assets/js/yoxview/jquery.yoxview.js" />'></script>

<script type=text/javascript>
$(function() {
    // time ago
    $('abbr.date').timeago();
    $('#id_a993efce53e27d65e81a74f00580d4b8 textarea').focus(function(){
        $(this).css('height', '62px');
    });
});
</script>

<script type="text/javascript">
KindEditor.ready(function(K) {
    window.editor = K.create('#content');
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