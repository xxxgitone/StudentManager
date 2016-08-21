<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String type=(String)request.getServletContext().getAttribute("type");
if(type==null)type = request.getParameter("type");

String error = (String)request.getServletContext().getAttribute("uperror");
request.getServletContext().removeAttribute("uperror");
request.getServletContext().removeAttribute("type");
if(error==null) error="0";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<sql:setDataSource dataSource= "jdbc/student" />
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
	<!-- <script type="text/javascript" src="Jquery/jquery-3.0.0.min.js"></script> -->
<script type="text/javascript">
	if('<%=error%>'=='NullFile') alert('请选择文件后再上传！');
	if('<%=error%>'=='NullID') alert('请选择一个单选框后再上传！');
</script>
  </head>
  
  <body>
  <h2><%=type %></h2>
  <div class="center">
	<div style="float:left;margin:0 0 0 100px;">
		<form action="${pageContext.request.contextPath}/upload/uploadAction_saveFile.action"  
		          name="form1"  method="post"  enctype="multipart/form-data" >
		     <input type="file" name="uploadImage"><br/>
		     <%if(type!=null){ if("major".equals(type)){%>
		     	<sql:query sql="select aid id,academy name from academy" var="e"/>
		     	<c:forEach var="o" items="${e.rows }">
           	 	<span><input type="radio" name="upid"  value="${o.aid }">${o.academy}</span>
           	 </c:forEach><br/>
		     <% }else if("classs".equals(type)){%>
		     	<sql:query sql="select mid ,major from major " var="e"/>
		     	<c:forEach var="o" items="${e.rows }">
           	 	<span><input type="radio" name="upid"  value="${o.mid }">${o.major}</span>
           	 </c:forEach><br/>
		     <%} }%>
		     <input type="hidden" name="types" value="<%=type%>"/><br/>
		   <input type="submit" value="上传">
		</form>
	</div>
	<a href="pages/Main.jsp">首页</a>
	<a href="pages/ExcelHelp.html">查看文件上传帮助</a>
</div>
</body>
</html>
