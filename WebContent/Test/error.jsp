<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ServletContext sc = request.getServletContext();
String type =  (String)sc.getAttribute("type");
String error = (String)sc.getAttribute("uperror");
if(type!=null){
	if("major".equals(type)||"academy".equals(type)||"classs".equals(type)){
		type="XXJG.jsp";
	}else if ("grades".equals(type)){
		type="XSCJ.jsp";
	}else{
		type+=".html";
	}
}
sc.removeAttribute("type");
request.getServletContext().removeAttribute("uperror");
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'error.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	if('<%=error%>'=='InsertError') 
		alert('文件上传时读取失败！');
	var type = '<%=type%>';
	window.setTimeout("window.location='./"+type+"'",2500);

</script>
  </head>
  
  <body>
    <h2>上传文件失败</h2>
    <%if("InsertError".equals(error)){ %>
    <h3>您上传的Excel文件在读取时发生错误，请检查文件是否符合要求或者是否选对了Sheet</h3>
    <% }%>
    <p><s:fielderror/></p>
    3s后将自动返回...
    <a href="./<%=type %>">若无响应请点击这里返回</a>
  </body>
</html>
