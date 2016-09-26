<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ServletContext sc = request.getServletContext();
String action = (String)sc.getAttribute("action");
sc.removeAttribute("action");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>成绩</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="easyui/themes/material/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	.form{margin:0 0 0 100px;}
	.form input{}
	</style>
	<script type="text/javascript">
	if('<%=action%>' == 'f5'){
		//location.replace('MainPage.jsp');
		//window.location='MainPage.jsp';
		alert('数据库中没有你想要的数据，下载失败，请检查各个下拉框是否正确');
	}
		
		
	</script>
  </head>
  
  <body>
  <form class="form" action="${pageContext.request.contextPath }/download/downloadAction.action" method="get" style="margin:80px 0 30px 100px;">
     	学年: <input name="year" class="easyui-combobox" data-options="
    		valueField: 'value',
    		textField: 'label',
    		data: [<%for(int i=(new Date().getYear()+1900);i>2000;i--){ %>{
    			label: '<%=i-1 %>年---<%=i %>年',
    			value: '<%=i-1 %>-<%=i %>'
    		},<%} %>]" />
  		学期: <input name="term" class="easyui-combobox" data-options="
  		valueField: 'value',
  		textField: 'label',
  		data: [{label: '第一学期',value: '1'},{label: '第二学期',value: '2'}]" />
	   班级: <input id="cc" name="classs">
    	<input type="submit" value="下载本班级成绩文件"/>
    	<input type="hidden" name="filetype" value="g_classs"/>
    </form>
    <form class="form" action="${pageContext.request.contextPath }/download/downloadAction.action" method="get">
      	学年: <input name="year" class="easyui-combobox" data-options="
    		valueField: 'value',
    		textField: 'label',
    		data: [<%for(int i=(new Date().getYear()+1900);i>2000;i--){ %>{
    			label: '<%=i-1 %>年---<%=i %>年',
    			value: '<%=i-1 %>-<%=i %>'
    		},<%} %>]" />
   		 学期: <input name="term" class="easyui-combobox" data-options="
   		valueField: 'value',
   		textField: 'label',
   		data: [{label: '第一学期',value: '1'},{label: '第二学期',value: '2'}]" />
	      专业: <input id="mm" name="major">
    	<input type="submit" value="下载本专业成绩文件"/>
    	<input type="hidden" name="filetype" value="g_major"/>
    </form>
    <%-- <a href="${pageContext.request.contextPath }/download/downloadAction.action?filetype=g_classs&year=2015-2016&term=1&classs=3">下载某班级成绩表格文件</a>
    <a href="${pageContext.request.contextPath }/download/downloadAction.action?filetype=g_major&year=2015-2016&term=1&major=I10001">下载某专业成绩表格文件</a> --%>
  	
  	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript">
	  $(function(){
		  $('#mm').combobox({
		      url:'query/Query_major_all.action',
		      valueField:'mid',
		      textField:'major'
		  });
		  $('#cc').combobox({
		      url:'query/Query_classs_all.action',
		      valueField:'cid',
		      textField:'classs'
		  });
	  });
  
  </script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
  </body>
</html>
