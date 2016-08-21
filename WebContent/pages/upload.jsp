<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="Jquery/jquery-3.0.0.min.js"></script>
	<script type="text/javascript">
		$(function(){
			
		});
	</script>
  </head>
  
  <body>
  <h2>${param.type }</h2>
     <div class="center">
	<!-- <div style="float:left;">
		<h3>补考成绩.xls 文件格式规范：</h3>
		<table border="solid" width="300px" height="200px">
			<tr><td></td><td>A</td><td>B</td><td>C</td><td>D</td></tr>
			<tr><td>1</td><td>标题</td><td></td><td></td><td></td></tr>
			<tr><td>2</td><td>序号</td><td>学号</td><td>姓名</td><td>成绩</td></tr>
			<tr><td>3</td><td>*</td><td>*</td><td>*</td><td>*</td></tr>
		</table>
	</div> -->
	<div style="float:left;margin:0 0 0 100px;">
		<form action="${pageContext.request.contextPath}/upload/uploadAction_saveFile.action"  
		          name="form1"  method="post"  enctype="multipart/form-data" >
		     <input type="file" name="uploadImage"><br/>
		     <input type="hidden" name="types" value="${param.type }"/><br/>
		   <input type="submit" value="上传">
		</form>
	</div>
</div>
</body>
</html>
