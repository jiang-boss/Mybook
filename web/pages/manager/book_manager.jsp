<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%--		动态包含base 样式--%>
	<%@include file="/pages/common/header.jsp"%>
</head>
<script type="text/javascript">
	$(function (){
		$("a.delete").click(function (){
			/**
			 * 提示是否删除书籍 confirm 有两个返回值当返回FALSE的时候阻止提交
			 */
			return confirm("确认删除"+"<<"+$(this).parent().parent().find("td:first-child").text()+">>?")
		})
	})
</script>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookservlet?action=getBook&id=${book.id}&method=update&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="delete"  href="manager/bookservlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?method=add&pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>
		</table>
		<%--		包含分页条--%>
		<%@include file="/pages/common/page_nav.jsp"%>
	</div>
	<%--	包含页脚--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>