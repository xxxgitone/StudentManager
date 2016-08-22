<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

HttpSession sessions = request.getSession(false);//得到当前的Session
String user = (String)sessions.getAttribute("user");
if(user==null){
	sessions.setAttribute("param", "outtime");
	response.sendRedirect("Login.jsp");
}
ServletContext sc = request.getServletContext();
String div = (String)sc.getAttribute("div");
String error = (String)sc.getAttribute("uperror");
String years = (String)sc.getAttribute("years");
String terms = (String)sc.getAttribute("term");
if(terms==null) terms="1";
int term = Integer.parseInt(terms);
sc.removeAttribute("div");
//String div = request.getParameter("div");
if(div==null) div="0";

%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<sql:setDataSource dataSource= "jdbc/student" />
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
	.top{color:#333;font-size:24px;/*text-align:center;margin:50px 40%; */font-family: "楷体";margin:10px 40px;}
	.bar{height:30px;margin:0 0 0 30px;}
	.all{/*width:550px;height:30px; margin:100px auto;*/background:white;/*url()*/padding-left:10px;}
	.all li{width:150px;height:30px;background: #3CF;
	line-height:30px;text-align:center; float:left;margin-right:10px;_display:inline; position:relative;color:white;}
	.all>li{color: #000;}/*第一行颜色*/
	.all li a{color: #fff; text-decoration:none; cursor:pointer;}
	.all ul{ position:absolute;left:0;top:30px;display:none;}
	.center{ height:550px;margin:30px 0 0 40px;}
	#QK,#BK,#XSCJ{margin:80px 0 0 40px;}
	
</style>

<script type="text/javascript" src="Jquery/jquery-3.0.0.min.js"></script>
<script type="text/javascript">

var ThisDiv = '<%=div%>';
//alert("-"+ThisDiv+"-");
if('<%=error%>' == 'InsertError'){alert('数据库插入数据失败，请检查文件是否符合要求');}

$(function(){
	$('.center').css('display','none');
	if(ThisDiv!='0'){
		$('#'+ThisDiv).css('display','block');//显示参数指定的div
		alert('上传文件成功,写入数据库成功！');
	}
	$('.all>li').mouseover(function(e){
		//$(this).children().show();
		$(this).children().stop().slideDown();
	});
	$('.all>li').mouseout(function(e){
		//$(this).children().hide();
		$(this).children().stop().slideUp();//会有事件排队机制
	});
	//显示和隐藏动作的函数
	$('.all>li>ul>li').click(function(){
		$('.center').css('display','none');
		$("#"+$(this).children().attr("name")).css('display','block');
	});

});
</script>
</head>

<body> <!--background="../632471.jpg"-->
<div class="top">
	<span style="font-size:30px;margin:0 400px 0 30px;">成绩管理系统</span>
	<span>${sessionScope.levels}：${sessionScope.name }</span>
	<span><a href="pages/Login.jsp">注销</a></span>
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
        	<li><a name="course">课程管理</a></li>
            <li><a name="KB">课表管理</a></li>
        	<li><a name="student">学籍管理</a></li>
            <li><a name="teacher">教师管理</a></li>
            <li><a name="XXJG">学校结构管理</a></li>
            <li><a name="assitant">辅导员信息管理</a></li>
            <li><a name="GLY">管理员信息管理</a></li>
            
        </ul>
    </li>
</ul>
</div>

<!-- 学生成绩录入 -->

<div class="center" id="XSCJ"> 
	<div style="float:left;margin:0 0 0 100px;">
		<form action="${pageContext.request.contextPath}/upload/uploadAction_saveFile.action"  
		          name="form1"  method="post"  enctype="multipart/form-data" >
		     <h3>上传学生成绩表格:</h3><br/><input type="file" name="uploadImage"><br/>
		     <input type="hidden" name="types" value="grade"/><br/>
		     <%if(user!=null){%>
		     <sql:query sql="select distinct obligatory.cno,cname from obligatory,course where tno=${sessionScope.user } and course.cno=obligatory.cno" var="e"/>
           	 <c:forEach var="o" items="${e.rows }">
           	 	<span><input type="radio" name="course"  value="${o.cno }">${o.cname}</span>
           	 </c:forEach><br/>
           	 <%} %>
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
	<a href="pages/ExcelHelp.html">查看文件上传帮助</a>
</div>

<div class="center" id="BK">
	<div style="float:left;margin:0 0 0 100px;">
		<form action="${pageContext.request.contextPath}/upload/uploadAction_saveFile.action"  
		          name="form1"  method="post"  enctype="multipart/form-data" >
		     <h3>上传补考成绩表格:</h3><br/><input type="file" name="uploadImage"><br/>
		     <input type="hidden" name="types" value="makeup"/><br/>
		     <%if(user!=null){%>
		     <sql:query sql="select distinct obligatory.cno,cname from obligatory,course where tno=${sessionScope.user } and course.cno=obligatory.cno" var="e"/>
           	 <c:forEach var="o" items="${e.rows }">
           	 	<span><input type="radio" name="course"  value="${o.cno }">${o.cname}</span>
           	 </c:forEach><br/>
           	 <%} %>
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
	<a href="pages/ExcelHelp.html">查看文件上传帮助</a>
</div>
<div class="center" id="QK">
	<div style="float:left;margin:0 0 0 100px;">
		<form action="${pageContext.request.contextPath}/upload/uploadAction_saveFile.action"  
		          name="form1"  method="post"  enctype="multipart/form-data" >
		     <h3>上传清考成绩表格:</h3><br/><input type="file" name="uploadImage"><br/>
		     <input type="hidden" name="types" value="ultimate"/><br/>
		     <%if(user!=null){%>
		     <sql:query sql="select distinct obligatory.cno,cname from obligatory,course where tno=${sessionScope.user } and course.cno=obligatory.cno" var="e"/>
           	 <c:forEach var="o" items="${e.rows }">
           	 	<span><input type="radio" name="course"  value="${o.cno }">${o.cname}</span>
           	 </c:forEach><br/>
           	 <%} %>
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
	<a href="pages/ExcelHelp.html">查看文件上传帮助</a>
</div>

<div class="center" id="XSGR">4</div>
<div class="center" id="GRCJ">5</div>
<div class="center" id="BJCJ">6</div>
<div class="center" id="ZYCJ">7</div>
<div class="center" id="CKGR">8</div>
<div class="center" id="XGDQ">9</div>
<div class="center" id="CKBY">10</div>

<div class="center" id="course">
	<div><a href="pages/upload.jsp?type=course">上传文件录入课程信息</a></div>
	表格插件来做增删改
</div>
<div class="center" id="KB">
	<div><a href="pages/upload.jsp?type=kb">上传文件录入任教信息</a></div>
	表格插件来做增删改
</div>
<div class="center" id="student">
	<div><a href="pages/upload.jsp?type=student">上传文件录入学生学籍</a></div>
	表格插件来做增删改
</div>
<div class="center" id="teacher">
	<div><a href="pages/upload.jsp?type=teacher">上传文件录入教师信息</a></div>
	表格插件来做增删改
</div>
<div class="center" id="assitant">
	<div><a href="pages/upload.jsp?type=assitant">上传文件录入辅导员信息</a></div>
	表格插件来做增删改
</div>
<div class="center" id="XXJG">
	<div>
	<a href="pages/upload.jsp?type=academy">上传文件录入学院信息</a>
	<a href="pages/upload.jsp?type=major">上传文件录入专业信息</a>
	<a href="pages/upload.jsp?type=classs">上传文件录入班级信息</a>
	</div>
	表格插件来做增删改
</div>
<div class="center" id="GLY">
	表格插件来做增删改
</div>
</body>
</html>