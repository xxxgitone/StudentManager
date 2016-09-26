<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
HttpSession Session = request.getSession(false);
session.setAttribute("param", "outtime");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>404错误</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body{text-align:center;}
		.cc{margin:200px 0 0 0;}
	</style>
<script type="text/javascript">
	window.setTimeout("window.location='./Logins.jsp'",2000);
</script>
  </head>
  
  <body>
  <div class="cc">
	  <h2>您访问的路径是不存在的，请检查是否输入错误</h2>
	   3s后将自动返登录页面...<br>
	   <a href="./Logins.jsp">若无响应请点击这里返回</a>
    </div>
  </body>
</html>
