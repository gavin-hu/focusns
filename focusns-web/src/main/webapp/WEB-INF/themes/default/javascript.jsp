<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="yuiHost" value="http://yui.yahooapis.com/3.8.0" />

<script type="text/javascript" src='<c:url value="${yuiHost}/build/yui/yui-min.js" />'></script>

<script type="text/javascript" src='<c:url value="/static/libjs/jquery/jquery.min.js" />'></script>
<script type="text/javascript" src='<c:url value="/static/libjs/xheditor/xheditor.min.js" />'></script>
<!-- jcrop -->
<script type="text/javascript" src='<c:url value="/static/libjs/jcrop/jquery.Jcrop.min.js" />'></script>
<!-- timeago -->
<script type="text/javascript" src='<c:url value="/static/libjs/timeago/jquery.timeago.js" />'></script>
<script type="text/javascript" src='<c:url value="/static/libjs/timeago/locales/jquery.timeago.zh-CN.js" />'></script>