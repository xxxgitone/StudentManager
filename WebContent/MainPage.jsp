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
	<link rel="stylesheet" type="text/css" href="css/main.css">
  </head>
  
  <body class="easyui-layout">
    <div data-options="region:'north',split:true,border:false,noheader:true" style="height: 75px;background-color: #005bc0;">
    	<div class="north-header">
    		学生成绩管理系统
    	</div>
    	<div class="nowuser">
    		欢迎您！<span class="username">小黄人</span>  &nbsp;&nbsp;角色：<span class="role">系统管理员</span>
    	</div>
    	<div class="north-nav"><a href="#">系统首页</a> | <a href="#">安全退出</a></div>
    </div>
    <div data-options="region:'south',split:true,noheader:true" style="height:30px;background-color: #005bc0;">
    	<p class="date">今天是<span>2016</span>年<span>9</span>月<span>5</span>日</p>
    </div>
    <div data-options="region:'west',title:'功能菜单',split:true,iconCls:'icon-world'," style="width:180px;">

    	<div id="nav" class="easyui-accordion" data-options="border:false" >
    		<div title="成绩录入" data-options="iconCls:'icon-blackboard_drawing'" style="height:360px;background-color: #f5f5f5;">
	    		<ul>
	    			<li><a name="XSCJ">学生成绩录入</a></li>
	    			<li><a name="BK">补考成绩录入</a></li>
	    			<li><a name="QK">清考成绩录入</a></li>
	    		</ul>
    		</div >
    		<div title="信息查询" data-options="iconCls:'icon-data'" style="height:360px;background-color: #f5f5f5;">
	    		<ul>
	    			<li><a name="GRCJ">个人成绩查询</a></li>
	    			<li><a name="BJCJ">班级成绩统计</a></li>
	    			<li><a name="ZYCJ">专业成绩统计</a></li>
	    		</ul>
    		</div>
    		<div title="个人信息" data-options="iconCls:'icon-manager'" style="height:360px;background-color: #f5f5f5;">
	    		<ul>
	    			<li><a name="CKGR">查看个人信息</a></li>
	    			<li><a name="XGDQ">修改当前密码</a></li>
	    		</ul>
    		</div>
    		<div title="毕业管理" data-options="iconCls:'icon-book'" style="height:360px;background-color: #f5f5f5;">
	    		<ul>
	    			<li><a name="CKBY">查看毕业资格</a></li>
	    		</ul>
    		</div>
    		<div title="后台管理" data-options="iconCls:'icon-control'" style="height:360px;background-color: #f5f5f5;">
	    		<ul>
	    			<li><a name="course">课程管理</a></li>
	    			<li><a name="KB">课表管理</a></li>
	    			<li><a name="student">学籍管理</a></li>
	    			<li><a name="teacher">教师管理</a></li>
	    			<li><a name="XXJG">学校结构管理</a></li>
	    			<li><a name="assitant">辅导员信息管理</a></li>
	    			<li><a name="GLY">管理员信息管理</a></li>
	    		</ul>
    		</div>
    	</div>
    </div>
    <div id="center" data-options="region:'center',title:'center title',noheader:true">
    	<div id="tabs">
			<div title="起始页" data-options="iconCls:'icon-house',closable:true,selected:false," style="padding:0 10px;display:block;">
				欢迎来到学生成绩管理系统！
			</div>
    	</div>
    </div>

</body>
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="scripts/main.js"></script>
</html>
