<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <style type="text/css">
    .table{
       margin-top: 10px;
    }
    .table tr td{
    height: 40px;  
    }
    h2{
    margin-top: 20px;
    }
    a{
    list-style: none; text-decoration: none;
    }
    </style>
  </head>
  
  <body>
      <h2 align="center">用户注册</h2>
      <form action="bookServlet?method=register" method="post" name="form1">
      <table align="center" class="table" >
      <tr>
      <td> 用户ID:<input type="text" name="user_id" /></td>
      </tr>
      <tr>
      <td>用户名:<input type="text" name="user_name" /></td>
      </tr>
      <tr>
      <td>&nbsp;&nbsp;&nbsp;密码:<input type="password" name="password" /></td>
      </tr>
      <tr>
      <td> 账户ID:<input type="text" name="account_id" /></td>
      </tr>
       </table>
       </form>
       <a href="javascript:document.form1.submit();">
       <p align="center" style="height:25px;width: 60px;
       border: 1px solid black; background-color: silver;
       margin-left: 650px;margin-bottom: -42px;">注册</p></a>
       
       
       <a href="<%=request.getContextPath()%>/bookServlet?method=getBooks">
       <p align="center" style="height:25px;width: 60px;
       border: 1px solid black; background-color: silver;
       margin-left: 550px;">返回</p></a>
  </body>
</html>
