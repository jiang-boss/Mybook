<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>尚硅谷会员注册页面</title>
<%--		动态包含base 样式--%>
		<%@include file="/pages/common/header.jsp"%>
		<script type="text/javascript">
			// 页面加载完成之后
			$(function () {
				//给用户名添加ajax请求 只要焦点一离开就判断是否可用
				$(".itxt").blur(function (){
					var username=this.value;
					$.getJSON("http://localhost:8080/book/userservlet","action=ajaxExitUser&username="+username,function (data){
						if(data.exitusername==true){
							$("span.errorMsg").text("用户名已存在！")
						}else {
							$("span.errorMsg").text("用户名可用！")
						}
					})
				})
				//给验证码绑定事件
				$("#code_img").click(function (){
					this.src="${basePath}kaptchaServlet";
				})
				$("#sub_btn").click(function (){
					//这里执行表单验证  判断用户名
				var username = $("#username").val();
				var usernameTest=/^\w{5,12}$/;
				if(!usernameTest.test(username)){
					$("span.errorMsg").text("用户不合法");
					return false;
				}
				$("sapn.errorMsg").text("");
					//判断密码
					var  password=$("#password").val();
					var passwordTest=/^\w{5,12}$/;
					if(!passwordTest.test(password)){
						$("span.errorMsg").text("你输入的密码不正确");
						return false;
					}
					$("span.errorMsg").text("")
					// 判断两次的密码是不是一样
					var repwd=$("#repwd").val();
					if(repwd!=password){
						$("span.errorMsg").text("两次的密码不一致");
						return false;
					}
					$("span.errorMsg").text("");

					//判断电子邮件
					var email=$("#email").val();
					var emailTest=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
					if(!emailTest.test(email)){
						$("span.errorMsg").text("你的邮箱格式不正确！");
						return false;
					}
					$("span.errorMsg").text("");

					//判断验证码
					var code=$("#code").val();
					codeTest=$.trim(code)
					if(codeTest==null||codeTest==""){
						$("span.errorMsg").text("验证码 不能为空")
						return false;
					}
					$("sapn.errorMsg").text("");
				})
			});
		</script>
	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}
	</style>
	</head>
	<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">${empty requestScope.msg?"":requestScope.msg}</span>
							</div>
							<div class="form">
								<form action="userservlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
									       value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
									         value="${requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 100px;" name="code" id="code"/>
									<img  id="code_img" alt="" src="kaptchaServlet" style="float: right;margin-right: 40px;width: 100px;height: 40px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
		<%--	包含页脚--%>
		<%@include file="/pages/common/foot.jsp"%>
	</body>
</html>