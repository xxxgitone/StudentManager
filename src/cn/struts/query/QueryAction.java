package cn.struts.query;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.hibernate.beans.Academy;
import cn.hibernate.beans.AcademyDAO;
import cn.hibernate.beans.AssitantDAO;
import cn.hibernate.beans.BaseHibernateDAO;
import cn.hibernate.beans.ClasssDAO;
import cn.hibernate.beans.CourseDAO;
import cn.hibernate.beans.MajorDAO;
import cn.hibernate.beans.ManagerDAO;
import cn.hibernate.beans.MarkDAO;
import cn.hibernate.beans.ObligatoryDAO;
import cn.hibernate.beans.StudentDAO;
import cn.hibernate.beans.TeacherDAO;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class QueryAction extends ActionSupport {
	//链接里带的参数，直接定义同名属性加上setget就行了，可能用上自定义类型转换器
	String values ;
	String propertys;
//	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
//	private final String SUCCESS = "success";
	
//人员
	public void q_manager_all(){
		ManagerDAO dao = new ManagerDAO();
		CreateList(dao);
	}
	public void q_manager_one(){
		ManagerDAO dao = new ManagerDAO();
		CreateOne(dao);
	}
	public void q_assitant_all(){
		AssitantDAO dao = new AssitantDAO();
		CreateList(dao);
	}
	public void q_assitant_one(){
		AssitantDAO dao = new AssitantDAO();
		CreateOne(dao);
	}
	public void q_teacher_all(){
		TeacherDAO dao = new TeacherDAO();
		CreateList(dao);
	}
	public void q_teacher_one(){
		TeacherDAO dao = new TeacherDAO();
		CreateOne(dao);
	}
	/**获得所有student以List集合返回*/
	public void q_student_all(){
		StudentDAO dao = new StudentDAO();
		CreateList(dao);
//		@SuppressWarnings("unchecked")
//		List <Student> students = dao.findAll();
//		String json = JSON.toJSONString(students);
//		System.out.println(json);
		//request = ServletActionContext.getRequest();
//		response = ServletActionContext.getResponse();
		//request.setAttribute("josn", json);
//		sendJSONS(students, response);
		
	}
	/**通过单属性查询student对象*/
	public void q_student_one(){
		StudentDAO dao = new StudentDAO();
		CreateOne(dao);
//		request = ServletActionContext.getRequest();
//		response = ServletActionContext.getResponse();
//		String value = request.getParameter("value");
//		String property = request.getParameter("property");
//		@SuppressWarnings("unchecked")
//		List<Student> students =  dao.findByProperty(propertys, values);
//		sendJSONS(students, response);
		
//		String json = JSON.toJSONString(student);
//		response = ServletActionContext.getResponse();
//		request.setAttribute("josn", json);
	}
	/**获得所有学院*/
	public void q_academy_all(){
		AcademyDAO dao = new AcademyDAO();
		CreateList(dao);
	}
	public void q_academy_one(){
		AcademyDAO dao = new AcademyDAO();
		CreateOne(dao);
	}
	/***/
	public void q_major_all(){
		MajorDAO dao = new MajorDAO();
		CreateList(dao);
	}
	public void q_major_one(){
		MajorDAO dao = new MajorDAO();
		CreateOne(dao);
	}
	public void q_classs_all(){
		ClasssDAO dao = new ClasssDAO();
		CreateList(dao);
	}
	public void q_classs_one(){
		ClasssDAO dao = new ClasssDAO();
		CreateOne(dao);
	}
	public void q_course_all(){
		CourseDAO dao = new CourseDAO();
		CreateList(dao);
	}
	public void q_course_one(){
		CourseDAO dao = new CourseDAO();
		CreateOne(dao);
	}
	
	public void q_mark_all(){
		MarkDAO dao = new MarkDAO();
		CreateList(dao);
	}
	public void q_mark_one(){
		MarkDAO dao = new MarkDAO();
		CreateOne(dao);
	}
	public void q_obligatory_all(){
		ObligatoryDAO dao = new ObligatoryDAO();
		CreateList(dao);
	}
	public void q_obligatory_one(){
		ObligatoryDAO dao = new ObligatoryDAO();
		CreateOne(dao);
	}
	//传入对应的dao，来获取json
	public void CreateList(BaseHibernateDAO dao){
		@SuppressWarnings("rawtypes")
		List list = dao.findAll();
		sendJSONS(list, response);
	}
	public void CreateOne(BaseHibernateDAO dao){
		System.out.println(propertys+":"+ values+dao.getClass());
		@SuppressWarnings("unchecked")
		List<Academy>list = dao.findByProperty(propertys, values);
		sendJSONS(list, response);
	}
	/**封装的JSON发送方法，应该要写多个的*/
	public void oneJSON(Object obj,HttpServletResponse response){
		String json = JSON.toJSONString(obj);
		System.out.println(json);
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**封装集合*/
	@SuppressWarnings("rawtypes")
	public void sendJSONS(List list,HttpServletResponse response){
		 if(list==null){
			//发送空，待定
			 try {
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write("null");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}else if(list.size()>1){
			//应该还有处理部分
			String json = JSON.toJSONString(list);
			System.out.println(json);
			try {
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(list.size()==1){
			oneJSON(list.get(0), response);
		}
		
	}
	public static void main(String []a){
		QueryAction q = new QueryAction();
		q.q_student_all();
	}
	public void test(){
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(values+":"+propertys);
			System.out.println(values+":"+propertys);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	public String getPropertys() {
		return propertys;
	}
	public void setPropertys(String propertys) {
		this.propertys = propertys;
	}
}
