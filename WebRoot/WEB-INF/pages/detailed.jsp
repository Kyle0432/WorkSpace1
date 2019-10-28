<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.kyle.bookstore.domain.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'detailed.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
  <form>
  <table border="1" cellpadding="10" cellspacing="0" style="position: relative;left: 200px;top: 50px;">
      <tr>
      <td colspan="3"><h2 align="center">图书详细信息</h2></td>
      </tr>            <!-- 这里的遍历是指的 这个对象里的字段的所有值 -->
      <tr>
          <td width="100">图书作者:</td>
          <td width="300">${book.author}</td>
          <td width="250" rowspan="4"><img src="images/${book.images}"/></td>
      </tr>
      <tr>
          <td width="100">图书名字:</td>
          <td width="300">${book.title}</td>
      </tr>
      <tr>
          <td width="100">图书价格:</td>
          <td width="300">￥${book.price}</td>
      </tr>
      <tr>
          <td width="100">图书描述:</td>
          <td width="300">${book.remark}</td>
      </tr>
  </table>
  </form>
  <div class="add" style="position: relative;top: 40px;left: 550px;">
  <a href="<%=request.getContextPath()%>/bookServlet?method=getBooks">返回</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="">加入购物车</a>
  </div>
  </body>
</html>
