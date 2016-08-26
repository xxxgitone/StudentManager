package cn.struts.query;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.hibernate.beans.Student;
import cn.hibernate.beans.StudentDAO;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class QueryAction extends ActionSupport {

	final String SUCCESS = "success";
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	/**获得所有student*/
	public String q_student(){
		StudentDAO dao = new StudentDAO();
		List <Student> students = dao.findAll();
		String json = JSON.toJSONString(students);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
}
