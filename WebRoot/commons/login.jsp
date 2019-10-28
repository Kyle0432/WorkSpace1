<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <script type="text/javascript">
    function show() {
		document.getElementById("hsDiv").style.display = "block";
		document.getElementById("overDiv").style.display = "block";
	}
    function closeDiv() {
		document.getElementById("hsDiv").style.display = "none";
		document.getElementById("overDiv").style.display ="none";
	}
  </script>
  </head>
  <body>
 <div id="hsDiv"style="display:none;width:305px;height:280px;border:1px solid gary;
   background-color:gray;position: absolute;left: 40%;top: 25%;position: fixed;
   z-index: 120;border-radius: 5px;">
 <div id="closeDiv">
 <a href="javascript:void(0);" onclick="closeDiv()"><img style="width: 10%; margin-left:275PX;" src="images/close.png"></a>
 </div> 
 <div id="tbDiv" align="center">
 <form action="bookServlet?method=login" method="post" name="form1">
      <table id="loginTb">
        <tr><td style="font-size: 30px;">用户登录<br><br></td></tr>
        
        <tr>
        <td><input type="text" name="userName" placeholder="请输入用户名"/><br><br></td>
        </tr>
        
        <tr>
        <td><input type="password" name="passWord" placeholder="请输入密码"/><br></td>
        </tr>
        
        <tr>
        <td><a href="javascript:document.form1.submit();">
        <p align="center" style="width: 162px;height: 30px; border: 1px solid #aacbee;
        background-color: white;position: relative; top: 10px; right: 0px;">登录</p>
        </a></td>
        </tr>
      </table>
 </form>
 </div>     
 </div>
   <div id="overDiv" style="display:none; 
   background-color:#000;width: 100%;height: 110%;
   opacity: 0.65;z-index: 110;position: absolute;
   left: 0;top: 0;filter: alpha(opacity = 65);"></div>
  </body>
</html>
