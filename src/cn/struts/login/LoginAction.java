package cn.struts.login;

import java.sql.ResultSet;

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
		try{
			if(rs.next()){
				if(pass.equals(rs.getString("pass"))) {
					String name= rs.getString("name");
					db.updSQL("insert into history values('"+user+"',now(),'"+request.getLocalAddr()+"')");
					HttpSession pu = request.getSession(true);
					pu.setMaxInactiveInterval(300);//设置生命周期 单位s
					pu.setAttribute("name", name);//用户的姓名
					pu.setAttribute("user", user);//用户编号
					pu.setAttribute("ids", ids);// 用户的身份
					pu.setAttribute("levels", levels);//用户的身份 中文
					return "main";
				}
			}else return "nouser";
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.closeAll();
		}
		return "login";
	}

}
