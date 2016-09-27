package cn.struts.login;

import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.myth.file.DeleteFile;

import com.myth.mysql.Mysql;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport {

	String user;
	String pass;
	String ids;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String levels = null;
		Mysql db = null;
		String sql;
		ResultSet rs = null;
		String top = null;
		if(user!=null){//参数为空的话，后面的执行是没有意义的而且浪费资源
			user.trim();
			pass.trim();
			ids.trim();
			if("student".equals(ids)){
				levels="学生";
			}else if("teacher".equals(ids)){
				levels="教师";
			}else if("manager".equals(ids)){
				levels="管理员";
			}else if("assitant".equals(ids)){
				levels = "辅导员";
			}
			db = new Mysql();
			top = ids.substring(0,1);
			if(!"manager".equals(ids))  sql = "select pass,"+top+"name from "+ids+" where "+top+"no="+user+"";
			else sql = "select pass ,"+top+"name from manager where mname='"+user+"'";
			rs = db.SelectAll(sql);
		}
		System.out.println("登录的三个参数："+user+"="+pass+"="+ids);
		
//		System.out.println(rs);
		HttpSession pu = request.getSession(true);
		try{
			if(rs!=null && rs.next()){
				if(pass.equals(rs.getString("pass"))) {
					String name= rs.getString(top+"name");
					db.updSQL("insert into history values('"+user+"',now(),'"+request.getLocalAddr()+"')");
					
					pu.setMaxInactiveInterval(300);//设置生命周期 单位s
					pu.setAttribute("name", name);//用户的姓名
					pu.setAttribute("user", user);//用户编号
					pu.setAttribute("ids", ids);// 用户的身份
					pu.setAttribute("levels", levels);//用户的身份 中文
					
					ServletContext sc = ServletActionContext.getServletContext();
					String [] info = getYear();
					sc.setAttribute("years", info[0]);
					sc.setAttribute("term", info[1]);
					System.out.println(info[0]+"<-->"+info[1]);
					return "main";
				}
			}else {
				pu.setAttribute("param", "nouser");
				return "login";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//没有参数的情况db是没有初始化的，所以就会空指针异常，以后的写法要写成这样，规避异常
			if(db!=null)db.closeAll();
		}
		pu.setAttribute("param", "error");
		return "login";
	}
	
	public String logout(){
		System.out.println("退出登录");
		HttpSession session = ServletActionContext.getRequest().getSession();
		ServletContext sc = ServletActionContext.getServletContext();
		String upload = sc.getRealPath("/fileupload");
		String download = sc.getRealPath("/excel");
		System.out.println("删除上传文件夹 : "+DeleteFile.delAllFile(upload));
		System.out.println("删除下载文件夹 : "+DeleteFile.delAllFile(download));
		session.setMaxInactiveInterval(0);
		return "login";
	}
	public String[] getYear(){
		String[] info = new String [2];
		Date s = new Date();
		@SuppressWarnings("deprecation")
		int y = s.getYear();
		@SuppressWarnings("deprecation")
		int m = s.getMonth();
		
		if(m<12 && m>8 || m==0) {info[1]=1+"";info[0] = (y+1900)+"-"+(y+1901);}
		else {info[1] = 2+"";info[0] = (y+1899)+"-"+(y+1900);}
		return info;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
