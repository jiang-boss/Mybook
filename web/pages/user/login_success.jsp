<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%--		动态包含base 样式--%>
	<%@include file="/pages/common/header.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >
				<%@include file="/pages/common/login_sucess_menu.jsp"%>
		</div>
		<div id="main">
		
			<h1>欢迎回来 <a href="index.jsp">转到主页</a></h1>
		</div>
		<%--	包含页脚--%>
		<%@include file="/pages/common/foot.jsp"%>
</body>
</html>