<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>Focus SNS</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="${Request.contextPath}/static/default/css/reset.css" type="text/css" />
	<link rel="stylesheet" href="${Request.contextPath}/static/default/css/layout.css" type="text/css" />
	<link rel="stylesheet" href="${Request.contextPath}/static/default/css/console.css" type="text/css" />
	<link href="${Request.contextPath}/static/default/img/favicon.ico" type="image/x-icon" rel="shortcut icon">
</head>
<body>
	<div id="wrap">
		<div id="header">
		    <h1 id="logo">FocuS<span>NS</span></h1>
		    <h2 id="slogan">FocuSNS 是一个基于主题的网络社区</h2>
		</div>	
		<div id="sidebar" >
	    	<@block name="sidebar">${menu}</@block>
		</div>
		<div id="main"> 
	  		<@block name="main">mian body</@block>
	    	<br />
		</div>
		<div id="footer">
			<div id="footer-content">
			    &copy; Copyright 2011-2012 <strong>FocusSNS</strong> &nbsp;Develop by: <a href="#">Gavin Hu</a>
		 	</div>
		</div>
	</div>
</body>
</html>
