<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--		动态包含base 样式--%>
	<%@include file="/pages/common/header.jsp"%>
</head>
<script type="text/javascript">
	$(function (){
		$(".comfirmdel").click(function (){
			confirm("你确定要删除"+$(this).parent().parent().find("td:first-child").text()+"吗?")
		})
		$("#clear").click(function () {
			confirm("你确定要清空购物车吗？")
		})
		//判断是否更改
		$(".updateCount").change(function (){
			var name=$(this).parent().parent().find("td:first-child").text();
			var count=this.value;//获取当前的数量
			var id=$(this).attr("bookId")//获取当前商品的id
			if(confirm("确认将"+name+"de商品数量修改为"+count+"吗？")){
				location.href="http://localhost:8080/book/cartServlet?action=updateCount&count="+count+"&id="+id;
			}else {
				this.value=this.defaultValue;//不确认就是返回原来的值
			}
		})
	})
</script>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="/pages/common/login_sucess_menu.jsp"%>
	</div>
	<div id="main">
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
<%--			空就不显示--%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">当前购物车没有书籍请到主页浏览图书并增添</a></td>
				</tr>
			</c:if>
<%--		不空显示数据	--%>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="cartitems">
					<tr>
						<td>${cartitems.value.name}</td>
						<td>
							<input class="updateCount" bookId="${cartitems.value.id}" style="width: 80px" type="text" value="${cartitems.value.count}">
						</td>
						<td>${cartitems.value.price}</td>
						<td>${cartitems.value.totalPrice}</td>
						<td><a   class="comfirmdel"  href="cartServlet?action=deleteItem&id=${cartitems.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clear" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=creatOrder">去结账</a></span>
			</div>
		</c:if>
	</div>
	<%--	包含页脚--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>