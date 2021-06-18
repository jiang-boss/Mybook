<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%--		动态包含base 样式--%>
    <%@include file="/pages/common/header.jsp" %>
    <script type="text/javascript">
        $(function (){
            $(".additem").click(function (){
                var bookid=$(this).attr("bookId");
                // location.href="http://localhost:8080/book/cartServlet?action=addItem&id="+bookid;
                $.getJSON("http://localhost:8080/book/cartServlet","action=addItemAjax&id="+bookid,function (data){
                    $("#cartTotalCount").text("您的购物车中有"+data.totalCount+"件商品");
                    $("#Name").text("您刚刚将"+data.lastName+"加入到了购物车中");
                })
            })
        })
    </script>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <div>
<%--        判断session里有没有数据--%>
        <c:if test="${empty sessionScope.user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
<%--    有数据的时候 --%>
        <c:if test="${not empty sessionScope.user}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
            <a href="pages/order/order.jsp">我的订单</a>
            <a href="userservlet?action=Logout">注销</a>&nbsp;&nbsp;&nbsp;
        </c:if>
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/bookservlet" method="post">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="min" value=""> 元 -
                <input id="max" type="text" name="max" value=""> 元
                <input type="submit" value="查询"/>
            </form>
        </div>
        <div style="text-align: center">
            <c:if test="${empty sessionScope.cart.items}">
                <span id="cartTotalCount"></span>
                <div>
                    <span id="Name" style="color: red">当前购物车为空</span>
                </div>
            </c:if>

            <c:if test="${not empty sessionScope.cart.items}">
                <span id="cartTotalCount">购物车有${sessionScope.cart.totalCount}件商品</span>
                <div>
                    您刚刚将<span id="Name" style="color: red">${sessionScope.lastname}</span>加入到了购物车中
                </div>
            </c:if>
        </div>
        <c:forEach items="${requestScope.page.items}" var="books">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${books.imgPath}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${books.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${books.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">${books.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${books.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${books.stock}</span>
                    </div>
                    <div class="book_add">
                        <button class="additem" bookId="${books.id}">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <%--		包含分页条--%>
    <%@include file="/pages/common/page_nav.jsp"%>
</div>
<%--	包含页脚--%>
<%@include file="/pages/common/foot.jsp" %>
</body>
</html>