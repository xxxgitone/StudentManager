<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

HttpSession sessions = request.getSession(false);//得到当前的Session
if(sessions.getAttribute("user")==null){
	response.sendRedirect("pages/Login.jsp?param=outtime");
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    
    <title>主页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<style type="text/css">
	*{padding:0;margin:0;list-style:none;}
	.top{color:#333;font-size:24px;/*text-align:center;margin:50px 40%; */font-family: "楷体";margin:0 40px;}
	.bar{height:30px;margin:0 0 0 30px;}
	.all{/*width:550px;height:30px; margin:100px auto;*/background:white;/*url()*/padding-left:10px;}
	.all li{width:150px;height:30px;background: #3CF;
	line-height:30px;text-align:center; float:left;margin-right:10px;_display:inline; position:relative;color:white;}
	.all>li{color: #000;}/*第一行颜色*/
	.all li a{color: #fff; text-decoration:none; cursor:pointer;}
	.all ul{ position:absolute;left:0;top:30px;display:none;}
	.center{ height:580px;}
</style>

<script type="text/javascript" src="Jquery/jquery-3.0.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('.all>li').mouseover(function(e){
		//$(this).children().show();
		$(this).children().stop().slideDown();
	});
	$('.all>li').mouseout(function(e){
		//$(this).children().hide();
		$(this).children().stop().slideUp();//会有事件排队机制
	});
	$('.all>li>ul>li').click(function(){
		$('.center').css('display','none');
		$("#"+$(this).children().attr("name")).css('display','block');
		//$('#center').load($(this).children().attr("name")+"",null,function(){});
					/*$.ajax({
					  url: "Image.html",
					  cache: false,
					  success: function(html){
						$("#center").html(html);
					  
					});}*/ 
	});

});
</script>
</head>

<body> <!--background="../632471.jpg"-->
<div class="top">
<h2>成绩管理系统</h2>
</div>
<div class="bar">
<ul class="all">
	<li id="one">信息录入
    	<ul>
        	<li><a name="XSCJ">学生成绩录入</a></li>
            <li><a name="BK">补考成绩录入</a></li>
            <li><a name="QK">清考成绩录入</a></li>
            <!--<li><a name="">课程资料录入</a></li>-->
        </ul>
    </li>
    <li id="two">信息查询
    	<ul>
        	<li><a name="XSGR">学生个人课表</a></li>
        	<li><a name="GRCJ">个人成绩查询</a></li>
            <li><a name="BJCJ">班级成绩统计</a></li>
            <li><a name="ZYCJ">专业成绩统计</a></li>
            <li><a name=""></a></li>
            
        </ul>
    </li>
    <li id="three">个人信息
    	<ul>
        	<li><a name="CKGR">查看个人信息</a></li>
            <li><a name="XGDQ">修改当前密码</a></li>
            <li><a name=""></a></li>
            <li><a name=""></a></li>
        </ul>
    </li>
    <li id="four">毕业管理
    	<ul>
        	<li><a name="CKBY">查看毕业资格</a></li>
            <li><a name=""></a></li>
            <li><a name=""></a></li>
            <li><a name=""></a></li>
        </ul>
    </li>
    <li id="five">后台管理
    	<ul>
        	<li><a name="KCGL">课程管理</a></li>
            <li><a name="KBGL">课表管理</a></li>
        	<li><a name="XJGL">学籍管理</a></li>
            <li><a name="JSGL">教师管理</a></li>
            <li><a name="FDY">辅导员信息管理</a></li>
            <li><a name="GLY">管理员信息管理</a></li>
            
        </ul>
    </li>
</ul>
</div>
<div class="center" id="XSCJ">1</div>
<div class="center" id="BK">2</div>
<div class="center" id="QK">3</div>
<div class="center" id="XSGR">4</div>
<div class="center" id="GRCJ">5</div>
<div class="center" id="BJCJ">6</div>
<div class="center" id="ZYCJ">7</div>
<div class="center" id="CKGR">8</div>
<div class="center" id="XGDQ">9</div>
<div class="center" id="CKBY">10</div>
<div class="center" id="KCGL">11</div>
<div class="center" id="KBGL">12</div>
<div class="center" id="XJGL">13</div>
<div class="center" id="JSGL">14</div>
<div class="center" id="FDY">15</div>
<div class="center" id="GLY">16</div>

</body>
</html>