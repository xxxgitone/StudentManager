<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

HttpSession sessions = request.getSession(false);//得到当前的Session
String user = (String)sessions.getAttribute("user");
if(user==null || "".equals(user)){
	sessions.setAttribute("param", "outtime");
	response.sendRedirect("Login.jsp");
}
String role = (String)sessions.getAttribute("ids");
ServletContext sc = request.getServletContext();
String div = (String)sc.getAttribute("div");
String error = (String)sc.getAttribute("uperror");
String years = (String)sc.getAttribute("years");
String terms = (String)sc.getAttribute("term");
//对于一次性的提示信息的属性要及时清除出去
sc.removeAttribute("div");
sc.removeAttribute("uperror");

if(terms==null) terms="1";
int term = Integer.parseInt(terms);
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
	.top{color:#333;font-size:20px;/*text-align:center;margin:50px 40%; */font-family: "楷体";margin:10px 40px;}
	.bar{height:30px;margin:0 0 0 30px;}
	.all{/*width:550px;height:30px; margin:100px auto;*/background:white;/*url()*/padding-left:10px;}
	.all li{width:220px;height:30px;background: #3CF;
		line-height:30px;text-align:center; float:left;margin-right:10px;_display:inline; position:relative;color:white;}
	.all>li{color: #000;}/*第一行颜色*/
	.all li a{color: #fff; text-decoration:none; cursor:pointer;}
	.all ul{ position:absolute;left:0;top:30px;display:none;}
	.center{ height:550px;margin:30px 0 0 40px;}
	#QK,#BK,#XSCJ{margin:80px 0 0 40px;}
	
</style>

<script type="text/javascript" src="Jquery/jquery-3.0.0.min.js"></script>
<script type="text/javascript">

var ThisDiv = '<%=div%>';//当前div
var role = '<%=role%>';//登录的角色
//alert("-"+ThisDiv+"-");
if('<%=error%>' == 'InsertError'){alert('数据库插入数据失败，请检查文件是否符合要求！');}

$(function(){
	//将第一个div复制过去
	$('#XSCJ').children().clone(true).appendTo(".center2");
	$('#BK h3').replaceWith('<h3>上传补考成绩表格:</h3>');
	//alert($('#BK input.typp').attr('value'));
	$('#BK input.typp').prop('value','makeup');
	//alert($('#BK input.typp').attr('value'));
	$('#QK h3').replaceWith('<h3>上传清考成绩表格:</h3>');
	$('#QK input.typp').prop('value','ultimate');
	
	//将div全部隐藏
	$('.center,.center2').css('display','none');
	//$('.center2').css('display','none');
	if(ThisDiv!='0'){
		$('#'+ThisDiv).css('display','block');//显示参数指定的当前div
		alert('上传文件成功,写入数据库成功！');
	}
	
	//以下是绑定各个动作函数
	$('.all>li').mouseover(function(e){
		//$(this).children().show();
		$(this).children().stop().slideDown();
	});
	$('.all>li').mouseout(function(e){
		//$(this).children().hide();
		$(this).children().stop().slideUp();//会有事件排队机制
	});
	//分角色来绑定事件，和修改节点
	if(role == 'student'){
		$('#one ul').remove();
		for(var i=0;i<3;i++)$('#two ul li:eq(2)').remove();
		//$('#four ul').remove();
		for(var i=0;i<7;i++)$('#five ul li:eq(0)').remove();
	}else if(role == 'teacher'){
		$('#two ul li:eq(1)').remove();
		//$('#two ul li:eq(2)').remove();
		for(var i=0;i<7;i++)$('#five ul li:eq(0)').remove();
	}else if(role == 'assitant'){
		for(var i=0;i<3;i++)$('#one ul li:eq(0)').remove();
		//$('#two ul li:eq(1)').remove();
		for(var i=0;i<7;i++)$('#five ul li:eq(0)').remove();
	}else if(role == 'manager'){
		//$('#two ul li:eq(1)').remove();
	}
	//绑定点击事件：显示和隐藏相应的div动作的函数
	$('.all>li>ul>li').click(function(){
		$('.center,.center2').css('display','none');
		//$('.center2').css('display','none');
		$("#"+$(this).children().attr("name")).css('display','block');
	});
	$('.all>li>ul>li>a').mouseover(function(e){
		$(this).css('color','yellow');
	});
	$('.all>li>ul>li>a').mouseout(function(e){
		$(this).css('color','white');
	});
});
</script>
</head>
<body> <!--background="../632471.jpg"-->
<div class="top">
	
	
	<font style="color:blue;font-size:14px;font-family: '微软雅黑';">
	 <script language="JavaScript">
			tmpDate = new Date();
			date = tmpDate.getDate();
			month= tmpDate.getMonth() + 1 ;
			year= tmpDate.getYear()+1900;
			document.write(year);
			document.write("年");
			document.write(month);
			document.write("月");
			document.write(date);
			document.write("日 ");
			
			myArray=new Array(6);
			myArray[0]="星期日";
			myArray[1]="星期一";
			myArray[2]="星期二";
			myArray[3]="星期三";
			myArray[4]="星期四";
			myArray[5]="星期五";
			myArray[6]="星期六";
			weekday=tmpDate.getDay();
			if (weekday==0 | weekday==6)
			{document.write(myArray[weekday]);}else{document.write(myArray[weekday]);};
</script> </font>
<span style="font-size:30px;margin:0 0 0 300px;">成绩管理系统</span>
<span style="margin:0 0 0 260px;">${sessionScope.levels}:${sessionScope.name }</span>
</div>
<div class="bar">
<ul class="all">
	<li id="one">成绩录入
    	<ul>
        	<li><a name="XSCJ">学生成绩录入</a></li>
            <li><a name="BK">补考成绩录入</a></li>
            <li><a name="QK">清考成绩录入</a></li>
            <!-- <li></li> -->
        </ul>
    </li>
    <li id="two">信息查询
    	<ul>
    		<%-- <li><a href="${pageContext.request.contextPath }/download/downloadAction.action">创建本学期课表</a></li> --%>
        	<!-- <li><a name="XSGR">课表查询</a></li> -->
        	<li><a name="GRCJ">个人成绩查询</a></li>
            <li><a name="BJCJ">班级成绩统计</a></li>
            <li><a name="ZYCJ">专业成绩统计</a></li>
            <!-- <li><a name=""></a></li> -->
            
        </ul>
    </li>
    <li id="three">个人信息
    	<ul>
        	<li><a name="CKGR">查看个人信息</a></li>
            <li><a name="XGDQ">修改当前密码</a></li>
            <!-- <li><a name=""></a></li>
            <li><a name=""></a></li> -->
        </ul>
    </li>
    <li id="four">毕业管理
    	<ul>
        	<li><a name="CKBY">查看毕业资格</a></li>
            <!-- <li><a name=""></a></li>
            <li><a name=""></a></li>
            <li><a name=""></a></li> -->
        </ul>
    </li>
    <li id="five">后台管理
    	<ul>
        	<li><a name="course">课程管理</a></li>
            <!-- <li><a name="KB">课表管理</a></li> -->
        	<li><a name="student">学籍管理</a></li>
            <li><a name="teacher">教师管理</a></li>
            <li><a name="XXJG">学校结构管理</a></li>
            <li><a name="assitant">辅导员信息管理</a></li>
            <li><a name="GLY">管理员信息管理</a></li>
            
        </ul>
    </li>
</ul>
<span style=""><a href="${pageContext.request.contextPath }/login/LogOutAction.action"><button style="width:60px;height:30px;/* background: red; */font-size:15px;color:red;">注销</button></a></span>

</div>

<!-- 学生成绩录入 -->

<div class="center" id="XSCJ"> 
	<div style="float:left;margin:0 0 0 100px;">
		<form action="${pageContext.request.contextPath}/upload/uploadAction_saveFile.action"  
		          name="form1"  method="post"  enctype="multipart/form-data" >
		     <h3>上传学生成绩表格:</h3><br/><input type="file" name="uploadImage"><br/>
		     <input  class="typp" type="hidden" name="types" value="grade"/><br/>
		     <% if(role!=null){ 
		    	 if("teacher".equals(role)){%>
		     		<sql:query sql="select distinct obligatory.cno,cname from obligatory,course where tno=${sessionScope.user } and course.cno=obligatory.cno" var="e"/>
           	  <%}else if("manager".equals(role)){ %>
           	  		<sql:query sql="select cno,cname from course " var="e"/>
           	  <%}} %>
           	 <c:forEach var="o" items="${e.rows }">
           	 	<span><input type="radio" name="course"  value="${o.cno }">${o.cname}</span>
           	 </c:forEach><br/><br/>
           	
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

<div class="center2" id="BK">

</div>
<div class="center2" id="QK">

</div>

<div class="center" id="XSGR">4

</div>
<div class="center" id="GRCJ">5</div>
<div class="center" id="BJCJ">
	<a href = "Test/Down.jsp">下载页面</a>
	 <a href="${pageContext.request.contextPath }/download/downloadAction.action?filetype=g_classs&year=2015-2016&term=1&classs=3">下载某班级成绩表格文件</a>
</div>
<div class="center" id="ZYCJ">
	 <a href="${pageContext.request.contextPath }/download/downloadAction.action?filetype=g_major&year=2015-2016&term=1&major=I10001">下载某专业成绩表格文件</a>
</div>
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