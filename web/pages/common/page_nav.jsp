<%--
  Created by IntelliJ IDEA.
  User: 22498
  Date: 2021/5/20
  Time: 8:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.uri}&pageNo=1">首页</a>
        <a href="${requestScope.page.uri}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <c:choose>
        <%--				情况一如果页码小于五 页码的范围是一到总页码--%>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>
        <%--				情况二 页码大于五 假设现在有9页--%>
        <c:when test="${requestScope.page.pageTotal>5}">
            <c:choose>
                <c:when test="${requestScope.page.pageNo<=3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <c:when test="${requestScope.page.pageNo>=requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i==requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i!=requestScope.page.pageNo}">
            <a href="${requestScope.page.uri}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.uri}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.uri}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页



    <input  id="search" type="button" value="确定">

    <script type="text/javascript">
        $(function (){
            $("#search").click(function (){
                var pageNo=$("#pn_input").val();
                location.href="http://localhost:8080/book/${requestScope.page.uri}&pageNo="+pageNo;
            })
        })
    </script>
</div>
