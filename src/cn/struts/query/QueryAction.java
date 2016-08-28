package cn.struts.query;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.hibernate.beans.Academy;
import cn.hibernate.beans.AcademyDAO;
import cn.hibernate.beans.Student;
import cn.hibernate.beans.StudentDAO;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class QueryAction extends ActionSupport {
	
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private final String SUCCESS = "success";
	
	/**获得所有student以List集合返回*/
	public void q_student_all(){
		StudentDAO dao = new StudentDAO();
		List <Student> students = dao.findAll();
//		String json = JSON.toJSONString(students);
//		System.out.println(json);
		//request = ServletActionContext.getRequest();
//		response = ServletActionContext.getResponse();
		//request.setAttribute("josn", json);
		sendJSON(students, response);
		
	}
	/**通过单属性查询student对象*/
	public void q_student_one(){
		StudentDAO dao = new StudentDAO();
//		request = ServletActionContext.getRequest();
//		response = ServletActionContext.getResponse();
		String value = request.getParameter("value");
		String property = request.getParameter("property");
		Student student = (Student) dao.findByProperty(property, value);
		sendJSON(student, response);
		
//		String json = JSON.toJSONString(student);
//		response = ServletActionContext.getResponse();
//		request.setAttribute("josn", json);
	}
	/**获得所有学院*/
	public void q_academy_all(){
		AcademyDAO dao = new AcademyDAO();
		List<Academy>list = dao.findAll();
//		response = ServletActionContext.getResponse();
		sendJSON(list, response);
	}
	public void q_academy_one(){
		AcademyDAO dao = new AcademyDAO();
		String value = request.getParameter("value");
		String property = request.getParameter("property");
		Academy a = (Academy) dao.findByProperty(property, value);
	}
	/**封装的JSON发送方法，应该要写多个的*/
	public void sendJSON(Object obj,HttpServletResponse response){
		String json = JSON.toJSONString(obj);
		System.out.println(json);
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String []a){
		QueryAction q = new QueryAction();
		q.q_student_all();
	}
}
