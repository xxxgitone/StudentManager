package cn.struts.query;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.hibernate.beans.Academy;
import cn.hibernate.beans.Assitant;
import cn.hibernate.beans.AssitantDAO;
import cn.hibernate.beans.BaseHibernateDAO;
import cn.hibernate.beans.Classs;
import cn.hibernate.beans.ClasssDAO;
import cn.hibernate.beans.Course;
import cn.hibernate.beans.CourseDAO;
import cn.hibernate.beans.Major;
import cn.hibernate.beans.MajorDAO;
import cn.hibernate.beans.Manager;
import cn.hibernate.beans.ManagerDAO;
import cn.hibernate.beans.Mark;
import cn.hibernate.beans.MarkDAO;
import cn.hibernate.beans.Obligatory;
import cn.hibernate.beans.ObligatoryDAO;
import cn.hibernate.beans.Student;
import cn.hibernate.beans.StudentDAO;
import cn.hibernate.beans.Teacher;
import cn.hibernate.beans.TeacherDAO;
import cn.hibernate.utils.ORM;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class QueryAction extends ActionSupport {
	//链接里带的参数，直接定义同名属性加上setget就行了，可能用上自定义类型转换器
	String values ;
	String propertys;
	private HttpServletResponse response = ServletActionContext.getResponse();
	
//人员
	public void q_manager_all(){
//		ManagerDAO dao = new ManagerDAO();
//		CreateList(dao);
		reflectList(Manager.class);
	}
	public void q_manager_one(){
//		ManagerDAO dao = new ManagerDAO();
//		CreateOne(dao);
		reflectOne(Manager.class);
	}
	public void q_assitant_all(){
//		AssitantDAO dao = new AssitantDAO();
//		CreateList(dao);
		reflectList(Assitant.class);
	}
	public void q_assitant_one(){
//		AssitantDAO dao = new AssitantDAO();
//		CreateOne(dao);
		reflectOne(Assitant.class);
	}
	public void q_teacher_all(){
//		TeacherDAO dao = new TeacherDAO();
//		CreateList(dao);
		reflectList(Teacher.class);
	}
	public void q_teacher_one(){
//		TeacherDAO dao = new TeacherDAO();
//		CreateOne(dao);
		reflectOne(Teacher.class);
	}
	public void q_student_all(){
//		StudentDAO dao = new StudentDAO();
//		CreateList(dao);reflectList(Student.class);		
		reflectList(Student.class);
	}
	public void q_student_one(){
//		StudentDAO dao = new StudentDAO();
//		CreateOne(dao);
		reflectOne(Student.class);
	}
	public void q_academy_all(){
//		AcademyDAO dao = new AcademyDAO();
//		CreateList(dao);reflectList(Student.class);
		reflectList(Academy.class);
	}
	public void q_academy_one(){
//		AcademyDAO dao = new AcademyDAO();
//		CreateOne(dao);reflectOne(Academy.class);
		reflectOne(Academy.class);
	}
	public void q_major_all(){
//		MajorDAO dao = new MajorDAO();
//		CreateList(dao);
		reflectList(Major.class);
	}
	public void q_major_one(){
//		MajorDAO dao = new MajorDAO();
//		CreateOne(dao);
		reflectOne(Major.class);
	}
	public void q_classs_all(){
//		ClasssDAO dao = new ClasssDAO();
//		CreateList(dao);
		reflectList(Classs.class);
	}
	public void q_classs_one(){
//		ClasssDAO dao = new ClasssDAO();
//		CreateOne(dao);
		reflectOne(Classs.class);
	}
	public void q_course_all(){
//		CourseDAO dao = new CourseDAO();
//		CreateList(dao);
		reflectList(Course.class);
	}
	public void q_course_one(){
//		CourseDAO dao = new CourseDAO();
//		CreateOne(dao);
		reflectOne(Course.class);
	}
	
	public void q_mark_all(){
//		MarkDAO dao = new MarkDAO();
//		CreateList(dao);
		reflectList(Mark.class);
	}
	public void q_mark_one(){
//		MarkDAO dao = new MarkDAO();
//		CreateOne(dao);
		reflectOne(Mark.class);
	}
	public void q_obligatory_all(){
//		ObligatoryDAO dao = new ObligatoryDAO();
//		CreateList(dao);
		reflectList(Obligatory.class);
	}
	public void q_obligatory_one(){
//		ObligatoryDAO dao = new ObligatoryDAO();
//		CreateOne(dao);
		reflectOne(Obligatory.class);
	}
	
	//传入对应的dao，来获取json
	public void CreateList(BaseHibernateDAO dao){
		@SuppressWarnings("rawtypes")
		List list = dao.findAll();
		System.out.println("进入————查询");
		sendJSONS(list, response);
	}
	public void CreateOne(BaseHibernateDAO dao){
		System.out.println(propertys+":"+ values+dao.getClass());
		@SuppressWarnings("unchecked")
		List<Academy>list = dao.findByProperty(propertys, values);
		sendJSONS(list, response);
	}
	
	//使用自定义的反射来实现
	public void reflectList(Class obj){
		List list = ORM.getRowsList(obj.getName());
//		System.out.println("使用反射来创建list查询");
		sendJSONS(list,response);
	}
	public void reflectOne(Class obj){
		List list = ORM.FindByProperty(obj.getName(), propertys, values);
		sendJSONS(list,response);
	}
	/**封装的JSON发送方法，应该要写多个的*/
	public void oneJSON(Object obj,HttpServletResponse response){
		String json = JSON.toJSONString(obj);
		json = "["+json+"]";
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
//			json = "["+json+"]";
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
//	public static void main(String []a){
//		QueryAction q = new QueryAction();
//		q.q_student_all();
//	}
	public void test(){
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(values+":"+propertys);
			System.out.println(values+":"+propertys);
		} catch (IOException e) {
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
