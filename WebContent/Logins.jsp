<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
HttpSession sessions = request.getSession(false);
String param = (String)sessions.getAttribute("param");
sessions.removeAttribute("param");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
	<title>登录</title>

	<script type="text/javascript" src="scripts/login.js"></script>
	<link rel="stylesheet" type="text/css" href="css/login.css"/>
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
	
	<div class="cloud"></div>
	<div class="loginbg"></div>
	
	<div class="login">
		<p class="header">学生成绩管理系统</p>
		<hr/>
		<div class="login_content">
			<form action="${pageContext.request.contextPath }/login/LoginAction.action"  method="post">
				<p>
					<label>用户名：</label>
					<input class="textbox" type="text" name="user" placeholder="请输入用户名" required />
				</p>
				<p>
					<label>密<span style="color:white;">空</span>码：</label>
					<input class="textbox" type="pass" name="pass" placeholder="请输入密码" required/>
				</p>
				<p>
					<label class="checked">验证码：</label>
					<input class="textbox1" type="text" name="checked" placeholder="请输入验证码" required/>
					<span id="code_success"></span>
					<span id="code_error"></span>
					<span id="code">1S7Y</span>
					<span id="reload"></span>
				</p>
				<p class="role">
					<label>角<span style="color:white;">空</span>色：</label>
					<input type="radio" name="ids"  value="student" ><span>学生</span>
	   				<input type="radio" name="ids" value="teacher" checked="checked"><span>教师</span>
				    <input type="radio" name="ids"  value="assitant" ><span>辅导员</span>
				    <input type="radio" name="ids"  value="manager" ><span>管理员</span>
				</p>
				<p>
					<button class="button" type="button">登录</button>
				</p>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript" src="scripts/login.js"></script>
</html>