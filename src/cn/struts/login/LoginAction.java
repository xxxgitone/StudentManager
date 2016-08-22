package cn.struts.login;

import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.hibernate.utils.Mysql;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		String ids = request.getParameter("ids");
		//if("asstant".equals(ids)){userID="ano";}
		System.out.println(user+"="+pass+"="+ids);
		String levels = null;
		if("student".equals(ids)){
			levels="学生";
		}else if("teacher".equals(ids)){
			levels="教师";
		}else if("manager".equals(ids)){
			levels="管理员";
		}else if("assitant".equals(ids)){
			levels = "辅导员";
		}
		
		Mysql db = new Mysql();
		String sql;
		String top = ids.substring(0,1);
		if(!"manager".equals(ids))  sql = "select pass,"+top+"name from "+ids+" where "+top+"no="+user+"";
		else sql = "select pass ,"+top+"name from manager where mname='"+user+"'";
		ResultSet rs = db.SelectAll(sql);
		System.out.println(rs);
		HttpSession pu = request.getSession(true);
		try{
			if(rs.next()){
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
			db.closeAll();
		}
		pu.setAttribute("param", "error");
		return "login";
	}
	public String[] getYear(){
		String[] info = new String [2];
		Date s = new Date();
		int y = s.getYear();
		int m = s.getMonth();
		
		
		if(m<12 && m>8 || m==0) {info[1]=1+"";info[0] = (y+1900)+"-"+(y+1901);}
		else {info[1] = 2+"";info[0] = (y+1899)+"-"+(y+1900);}
		return info;
	}

}
