<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生成绩管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="easyui/themes/material/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<style type="text/css">	
	
	.textbox{
			
		    border-radius: 4px;
		    font-size: 12px;
		    padding: 4px;
		    vertical-align: top;
		    height:23px;
		};
		
	</style>
  </head>
  
  <body>
  	<table id="GRCJ" class="easyui-datagrid" ></table>
  	
  	<div id="GRCJ-tool" >
		<form class="form" action="#" method="get" style="margin:20px;">
     	学年: <input id="years1" name="year" >
   		学期: <input id="terms1" name="term" >
  		学号: <input id="sid" class="textbox" name="sid">
  		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="tool.serach1()">查询</a>
  		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="tool.serach2()">查学年</a>
  		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="tool.serach3()">查在校</a>
    	</form>
	</div>
  	
  	
   
  </body>
  <script type="text/javascript" src="easyui/jquery.min.js"></script>
  <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	  $(function(){
		  var termss = [{label: '第一学期',no: '1'},{label: '第二学期',no: '2'}];
		  var yearss = [<%for(int i=(new Date().getYear()+1900);i>2000;i--){ %>{
	   			label: '<%=i %>年---<%=i+1 %>年',
	   			value: '<%=i %>-<%=i+1 %>'
	   		},<%} %>];
		  $('#mm').combobox({
		      url:'query/Query_major_all.action',
		      valueField:'mid',
		      textField:'major'
		  });
		  $('#terms1').combobox({
		      data:termss,
		      valueField:'no',
		      textField:'label',
		      onLoadSuccess:function(){
		    	  var data  = $('#terms1').combobox('getData');
		    	  $('#terms1').combobox('select',data[0].no);
		      }
		  });
		  $('#years1').combobox({
		      data:yearss,
		      valueField:'value',
		      textField:'label',
		      onLoadSuccess:function(){
		    	  var data  = $('#years1').combobox('getData');
		    	  $('#years1').combobox('select',data[0].value);
		      }
		  });
	  });
  
  </script>
	<script type="text/javascript" src="scripts/GRCJ.js"></script>
</html>
