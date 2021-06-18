<%--
  Created by IntelliJ IDEA.
  User: 22498
  Date: 2021/5/19
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
   //动态的获取请求地址
   String basePath=request.getScheme()+
           "://"+request.getServerName()+
           ":"+request.getServerPort()+
           request.getContextPath()+"/";
%>
<base href=<%=basePath%>>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>

