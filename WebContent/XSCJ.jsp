<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生成绩录入</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="easyui/themes/material/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
  </head>
  
  <body>
  <form action="upload/uploadAction_saveFile.action"name="form1"  method="post"  enctype="multipart/form-data"style="margin:40px 0 30px 0px;">
		 	<p><span style="font-weight: bold;font-size: 20px;color:blue;">上传成绩表格:</span><br/>
			 	<input type="file" name="uploadImage" > <a href="pages/ExcelHelp.html" class="easyui-linkbutton" plain="true" iconCls="icon-help">查看文件上传帮助</a><br/>
			</p>
	          	<span style="color:red;">请选择要使用的Sheet(默认是第一个)以及各个必选的参数，若新增就不选择，输入即可</span><br/>
		    <select name="sheet" class="easyui-combobox">
			     <OPTION VALUE="0" selected>第 1  个Sheet</OPTION>
		    	 <%for(int i=1;i<=14;i++) {%>
		    		<OPTION VALUE="<%=i %>">第 <%=i+1 %>  个Sheet</OPTION>
		    	 <%} %>
		    </select>
		      学年: <input name="year" class="easyui-combobox" style="height:20px;" data-options="
	   		valueField: 'value',
	   		textField: 'label',
	   		data: [<%for(int i=(new Date().getYear()+1900);i>2000;i--){ %>{
	   			label: '<%=i-1 %>年---<%=i %>年',
	   			value: '<%=i-1 %>-<%=i %>'
	   		},<%} %>]" />
	  		学期: <input name="term" class="easyui-combobox" style="width:70px;height:20px;" data-options="
	  		valueField: 'value',
	  		textField: 'label',
	  		data: [{label: '第一学期',value: '1'},{label: '第二学期',value: '2'}]" />
	  		
	  		学院: <input id="aa" name="academy" style="width:120px;height:20px;">
	  		专业: <input id="mm" name="major" style="width:120px;height:20px;">
	  		班级: <input id="cc" name="classs" style="width:70px;height:20px;">
	 		<input type="hidden" name="types" value="grades"/>
		 	<input type="submit" class="button" value="上传" style="width:80px;">
    </form>
    
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript">
	  $(function(){
		  $('#cc').combobox({
		      url:'query/Query_classs_all.action',
		      valueField:'cid',
		      textField:'classs'
		  });
		  $('#aa').combobox({
		      url:'query/Query_academy_all.action',
		      valueField:'aid',
		      textField:'academy'
		  });
		  $('#mm').combobox({
		      url:'query/Query_major_all.action',
		      valueField:'mid',
		      textField:'major'
		  });
	  });
  		
  </script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
  </body>
</html>
