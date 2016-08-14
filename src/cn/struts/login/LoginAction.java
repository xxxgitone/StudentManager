package cn.struts.login;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

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
		Mysql db = new Mysql();
		String sql;
		if(!"manager".equals(ids))  sql = "select pass from "+ids+" where "+ids.substring(0,1)+"no="+user+"";
		else sql = "select pass from manager where mname='"+user+"'";
		ResultSet rs = db.SelectAll(sql);
		System.out.println(rs);
		try{
			if(rs.next()){
				if(pass.equals(rs.getString("pass"))) {
					db.updSQL("insert into history values('"+user+"',now(),'"+request.getLocalAddr()+"')");
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
