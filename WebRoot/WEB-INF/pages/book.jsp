<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'book.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/book.css">
  </head>
  <body>
  <jsp:include page="/commons/login.jsp"></jsp:include>
   <div class="nav" align="center">
   <c:choose>
   <c:when test="${empty sessionScope.userName }">
      <li>考拉书城欢迎您!</li>
      <li><a href="javascript:void(0);" onclick="show()">登录</a></li>
   </c:when>
   <c:otherwise>
      <li>${sessionScope.userName }</li>
      <li><a href="<%=request.getContextPath()%>/bookServlet?method=secede">退出登录</a></li>
   </c:otherwise>
   </c:choose>
      <li><a href="<%= request.getContextPath()%>/register.jsp">免费注册</a></li>
      <li><a href="">我的订单</a></li>
      <li><a href="">我的购物车</a></li>
      <li><a href="">个人中心</a></li>
      <li><a href="">意见反馈</a></li>
      <li><a href="">更多</a></li>
  </div>
  <div class="loginAndSearch" style="height: 90px; ">
    <img src="images/login.png" style="margin-left: 200px">
    <form action="bookServlet?method=query" method="post" name="form2">
    <input type="text" name="remark" size="70"style="height: 30px; margin: 0px;position: relative;bottom: 38px;left: 450px;"/>
    </form>
     <a href="javascript:document.form2.submit();"><p align="center" style="width: 55px;height:27px;border: 1px skyblue solid;background-color: skyblue;color: white;
     position: relative;left: 960px;bottom: 83px;">搜索</p></a>
    <div class="minAndmax" style="width: 220px;height:30px;position: relative;left: 600px;bottom:90px;">
    <form action="bookServlet?method=getBooks" method="post">
	价格: 
	<input type="text" size="1" name="minPrice" /> - 
	<input type="text" size="1" name="maxPrice" />
	<input type="submit" value="查询"/>
	</form>
	</div>
  </div>
  
 <div class="main" align="center" style="height:480px ;margin-top:30px;">
     <c:forEach items="${bookPage.list }" var="book">
 <div class="book" style="float: left; width: 380px;height: 250px;">
  <dl>
       <dt><a><img src="images/${book.images }" style="width: 40%;"/></a></dt>
       <dd>${book.remark}</dd>
       <dd><span style="color: red;font-size: 25px;"><a href="<%=request.getContextPath()%>/bookServlet?method=doLogin&userName=${sessionScope.userName }&book_id=${book.book_id}">购买</a>￥${book.price}</span></dd>
  </dl>
  </div>
     </c:forEach>
 </div>
 <div class="paging" align="center" style="height: 70px;">
  <br><br>
  共${bookPage.totalPageNumber }页
  &nbsp;&nbsp;
  当前第${bookPage.pageNo }页
  &nbsp;&nbsp;
  <c:if test="${bookPage.hasPrev }">
  <a href="bookServlet?method=getBooks&pageNo=1">首页</a>
  &nbsp;&nbsp;
  <a href="bookServlet?method=getBooks&pageNo=${bookPage.prevPage }">上一页</a>
  </c:if>
  &nbsp;&nbsp;
  <c:if test="${bookPage.hasNext }">
  <a href="bookServlet?method=getBooks&pageNo=${bookPage.nextPage }">下一页</a>
  &nbsp;&nbsp;
  <a href="bookServlet?method=getBooks&pageNo=${bookPage.totalPageNumber }">末页</a>
  </c:if>
  &nbsp;&nbsp;
     转到 <input type="text" size="1" id="pageNo"/> 页	
 </div>
  </body>
</html>
