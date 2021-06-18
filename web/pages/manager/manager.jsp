<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
	<%--		动态包含base 样式--%>
	<%@include file="/pages/common/header.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">后台管理系统</span>
		<%@include file="/pages/common/manager_menu.jsp"%>

	</div>
	<div id="main">
		<h1>欢迎管理员进入后台管理系统</h1>
	</div>
	<%--	包含页脚--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>