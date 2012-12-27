<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>Focus SNS</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="${Request.contextPath}/themes/default/css/reset.css" type="text/css" />
	<link rel="stylesheet" href="${Request.contextPath}/themes/default/css/layout.css" type="text/css" />
	<link rel="stylesheet" href="${Request.contextPath}/themes/default/css/console.css" type="text/css" />
	<link href="${Request.contextPath}/themes/default/img/favicon.ico" type="image/x-icon" rel="shortcut icon" />
    
    <link rel="stylesheet" href="${Request.contextPath}/components/Jcrop-master/css/jquery.Jcrop.min.css" type="text/css" />
    
    <script type="text/javascript" src="${Request.contextPath}/components/jQuery/jquery.min.js" ></script>
    <script type="text/javascript" src="${Request.contextPath}/components/Jcrop-master/js/jquery.Jcrop.min.js"></script>
    
    <link rel="stylesheet" href="${Request.contextPath}/components/markitup/sets/default/style.css" type="text/css" />
    <link rel="stylesheet" href="${Request.contextPath}/components/markitup/skins/simple/style.css" type="text/css" />
    <script type="text/javascript" src="${Request.contextPath}/components/markitup/jquery.markitup.js" ></script>
    <script type="text/javascript" src="${Request.contextPath}/components/markitup/sets/default/set.js" ></script>
</head>
<body>
<div id="wrap">
	<div id="header">
	    <form method="post" class="search" action="#">
	      <p>
	        <input name="search_query" class="textbox" type="text" />
	        <input name="search" class="button" value="Search" type="submit" />
	      </p>
	    </form>
	    <h1 id="logo">FocuS<span>NS</span></h1>
	    <h2 id="slogan">FocuSNS是一个开源的主题社会化网络软件</h2>
	</div>
    <@position name="menu" >
	<div id="menu">${menu}</div>
    </@position>
    <@position name="menubar">
    <div id="menubar">${menubar}</div>
    </@position>
    <@position name="submenu" >
	<div id="submenu">${submenu}</div>
    </@position>
    <@position name="sidebar">
    <div id="sidebar">${sidebar}</div>
    </@position>
    <@position name="main" >
	<div id="main"> ${main}<br /></div>
    </@position>
</div>
<div id="footer">
	<div id="footer-content">
		&copy; Copyright 2011-2012 <strong>FocuSNS</strong> &nbsp;Developed by: <a href="#">Gavin Hu</a>
	</div>
</div>
</body>
</html>
