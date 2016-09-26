<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String type=(String)request.getServletContext().getAttribute("type");
if(type==null)type = request.getParameter("type");
String typeName="";
if(type!=null){
	if("course".equals(type)) typeName = "上传课程信息";
	if("student".equals(type)) typeName = "上传学生信息";
	if("teacher".equals(type)) typeName = "上传教师信息";
	if("assitant".equals(type)) typeName = "上传辅导员信息";
	if("academy".equals(type)) typeName = "上传学院信息";
	if("major".equals(type)) typeName = "上传专业信息";
	if("classs".equals(type)) typeName = "上传班级信息";
}
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
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript">
	if('<%=error%>'=='NullFile') alert('请选择文件后再上传！');
	if('<%=error%>'=='NullID') alert('请选择一个单选框后再上传！');
	
</script>
  </head>
  
  <body>
  <%--<h2><%=typeName %></h2>
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
		     请输入上传文件中要使用的第几个Sheet：（不选则默认是第一个）<br/>
		     <select name="sheet">
		     	<OPTION VALUE="0" selected>第 1  个Sheet</OPTION>
		     	<OPTION VALUE="1">第 2  个Sheet</OPTION>
		     	<OPTION VALUE="2">第 3  个Sheet</OPTION>
		     	<OPTION VALUE="3">第 4  个Sheet</OPTION>
		     	<OPTION VALUE="4">第 5  个Sheet</OPTION>
		     	<OPTION VALUE="5">第 6  个Sheet</OPTION>
		     	<OPTION VALUE="6">第 7  个Sheet</OPTION>
		     	<OPTION VALUE="7">第 8  个Sheet</OPTION>
		     	<OPTION VALUE="8">第 9  个Sheet</OPTION>
		     	<OPTION VALUE="9">第 10 个Sheet</OPTION>
		     </select><br/>
		   <input type="submit" value="上传">
		</form>
	</div>
	</div> --%>
	
	<div class="upload" style="float: left;width:400px;">
		 		<form action="upload/uploadAction_saveFile.action"name="form1"  method="post"  enctype="multipart/form-data">
				 	<p><span style="font-weight: bold;font-size: 18px;color:blue;"><%=typeName%>:</span>

					 	<input type="file" name="uploadImage" ><br/>
						 		<!-- 单选框 -->
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
					 	<input type="hidden" name="types" value="<%=type%>"/>
					 	<!-- <input  class="typp" class="button" type="hidden" name="types" value="student"/> -->
					</p>
		           	<p>请输入上传文件中要使用的第几个Sheet:(默认是第一个)</p>
				    <select name="sheet" class="easyui-combobox">
					     <OPTION VALUE="0" selected>第 1  个Sheet</OPTION>
					     <OPTION VALUE="1">第 2  个Sheet</OPTION>
					     <OPTION VALUE="2">第 3  个Sheet</OPTION>
					     <OPTION VALUE="3">第 4  个Sheet</OPTION>
					     <OPTION VALUE="4">第 5  个Sheet</OPTION>
					     <OPTION VALUE="5">第 6  个Sheet</OPTION>
					     <OPTION VALUE="6">第 7  个Sheet</OPTION>
					     <OPTION VALUE="7">第 8  个Sheet</OPTION>
					     <OPTION VALUE="8">第 9  个Sheet</OPTION>
					     <OPTION VALUE="9">第 10 个Sheet</OPTION>
				     </select>
				 <input type="submit" class="button" value="上传">
				 <a href="pages/ExcelHelp.html" class="easyui-linkbutton" plain="true" iconCls="icon-help">查看文件上传帮助</a>
		 		</form>

		 	</div>
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="scripts/student.js"></script>
	<!-- <a href="pages/Main.jsp">首页</a> -->
	<!-- <a href="pages/ExcelHelp.html">查看文件上传帮助</a> -->

</body>
</html>
