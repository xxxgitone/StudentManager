<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
HttpSession sessions = request.getSession(false);
String param = (String)sessions.getAttribute("param");
sessions.removeAttribute("param");
//String param = request.getParameter("param");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<style type="text/css">
	#box{margin:100px 0 0 100px;}
	#box input{width:250px;}
	#box1 span input{width:10px;background:red;}
</style>
<script type="text/javascript">
if('<%=param%>'=='error'){
	alert("密码错误！");
}else if('<%=param%>' == 'nouser'){
	alert("没有该用户！");
}else if('<%=param%>'=='outtime'){
	alert("登录超时请重新登录！");
}
</script>
</head>
<body>
    <div id="box">
	<form action="${pageContext.request.contextPath }/login/LoginAction.action" method="post">
		<input type="text" name="user" id="user" placeholder="请输入用户名" value="900001"/><br/>
		<br/>
		<input type="password" name="pass" id="pass" placeholder="请输入密码" value="1"/><br>
		<br/>
		<div id="box1"><!-- check表示默认选择选项 -->
	   <span><input type="radio" name="ids"  value="student" >学生</span>
	   <span><input type="radio" name="ids" value="teacher" checked="checked">教师</span>
	   <span><input type="radio" name="ids"  value="assitant" >辅导员</span>
	   <span><input type="radio" name="ids"  value="manager" >管理员</span>
		 </div><br/>
		<input type="submit" value="登录"/>
	</form>
</div>
</body>
</html>
