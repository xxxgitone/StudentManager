package cn.struts.save;

import java.util.Date;

import cn.hibernate.beans.Academy;
import cn.hibernate.beans.AcademyDAO;
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
import cn.hibernate.beans.Student;
import cn.hibernate.beans.StudentDAO;
import cn.hibernate.beans.Teacher;
import cn.hibernate.beans.TeacherDAO;
import cn.hibernate.utils.DBUtils;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 实现了保存，修改
 * 最好使用Hibernate的一对多等关系映射，多加练习下，
 * @author  Myth
 * @date 2016年9月7日 上午10:51:27
 * @TODO
 */
@SuppressWarnings("serial")
public class SaveAction extends ActionSupport {

	//四个身份，三个结构和课程的所有属性在这里
	long ano,tno,sno;
	Date sbirth,tbirth;
	float credit,theoryhour,practicehour;
	String pass,sname,ssex,sid,cid,spolitics,saddr,sinfo;
	String tname,tsex,tpolitics,tjob,tacademy,tinfo;
	String aname,asex,mid,ainfo,major;
	String mname,minfo,aid,academy,classs,cinfo;
	String cno,cname,ctype,cacademy;
	//private HttpServletResponse response = ServletActionContext.getResponse();
	
	public static void main(String[] args) {
		SaveAction a = new SaveAction();
		//a.ano = 232l;
//		a.aname ="ed";
//		a.pass = "ee";
//		a.asex = "dsd";
//		a.ainfo = "dfasfa";
//		a.mid="I10001";
//		
//		a.saveAssitant();
		
		a.cno="23132";
		a.cname = "yi";
		a.credit = 2;
		a.theoryhour = 32;
		a.practicehour = 32;
		a.ctype = "基础课程";
		a.cacademy = "IT";
		a.cinfo = "jsdflk";
		a.saveCourse();
	}

	/**
	 * 验证方法
	 */
	@Override
	public void validate() {
		// TODO Auto-generated method stub\\\
		System.out.println(pass+sname+ssex);
		super.validate();
	}
//保存记录
	public void saveStudent(){
		System.out.println("进入stu");
		StudentDAO dao = new StudentDAO();
		Student stu = new Student(pass, sname, ssex, sbirth, sid, cid, spolitics, saddr, sinfo);
		save(dao,stu);
	}
	public void saveTeacher(){
		TeacherDAO dao = new TeacherDAO();
		Teacher tea = new Teacher(tno, tname, pass, tsex, tbirth, tpolitics, tjob, tacademy, tinfo);
		save(dao,tea);
	}
	public void saveAssitant(){
		
		Assitant as = new Assitant(aname, pass, asex, mid, ainfo);
		AssitantDAO dao = new AssitantDAO();
		save(dao,as);
	}
	public void saveManager(){
		Manager ma = new Manager(mname, pass, minfo);
		ManagerDAO dao = new ManagerDAO();
		save(dao,ma);
	}
	public void saveCourse(){
		Course cu = new Course(cno, cname, credit, theoryhour, practicehour, ctype, cinfo, cacademy);
		CourseDAO dao = new CourseDAO();
		save(dao,cu);
	}
	public void saveAcademy(){
		Academy ac = new Academy(aid, academy, ainfo);
		AcademyDAO dao = new AcademyDAO();
		save(dao,ac);
	}
	public void saveMajor(){
		Major ma = new Major(mid, major, minfo);
		MajorDAO dao = new MajorDAO();
		save(dao,ma);
	}
	public void saveClasss(){
		Classs cl = new Classs(cid, classs, cinfo);
		ClasssDAO dao = new ClasssDAO();
		save(dao,cl);
	}
//修改记录
	public void updateStudent(){
		Student stu = new Student(pass, sname, ssex, sbirth, sid, cid, spolitics, saddr, sinfo);
		update(stu);
	}
	public void updateTeacher(){
		Teacher tea = new Teacher(tno, tname, pass, tsex, tbirth, tpolitics, tjob, tacademy, tinfo);
		update(tea);
	}
	public void updateAssitant(){
		Assitant as = new Assitant(ano, aname, pass, asex, mid, ainfo);
		update(as);
	}
	public void updateManager(){
		Manager ma = new Manager(mname, pass, minfo);
		update(ma);
	}
	public void updateCourse(){
		Course cu = new Course(cno, cname, credit, theoryhour, practicehour, ctype, cinfo, cacademy);
		update(cu);
	}
	public void updateAcademy(){
		Academy ac = new Academy(aid, academy, ainfo);
		update(ac);
	}
	public void updateMajor(){
		Major ma = new Major(mid, major, minfo);
		update(ma);
	}
	public void updateClasss(){
		Classs cl = new Classs(cid, classs, cinfo);
		update(cl);
	}
	/**多态调用dao保存Object*/
	public void save(BaseHibernateDAO dao,Object o){
		try {
			dao.save(o);
			//sendSaveJSON(true);
		} catch (Exception e) {
			//sendSaveJSON(false);
			e.printStackTrace();
		}
	}
	/**
	 * 直接输入对象，更新记录
	 * @param obj
	 */
	public void update(Object obj){
		try {
			DBUtils.update(obj);//主键不能改，其他属性可以改。
			sendUpdateJSON(true, "");
		} catch (Exception e) {
			sendUpdateJSON(false, "");
		}
		
	}
	/**返回JSON字符串*/
	public void sendSaveJSON(boolean flag){
		String json = "";
		if(flag){
			json = "{success:true}";
		}else{
			json="{success:false}";
		}
		System.out.println("JSON : "+json);
//		try {
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().write(json);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	public void sendUpdateJSON(boolean flag,String id){
		String json = "";
		if(flag){
			json = "{success:true,id:"+id+"}";
		}else{
			json="{success:false,id:"+id+"}";
		}
		System.out.println("JSON : "+json);
//		try {
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().write(json);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
//////////////////////////   setter   getter ////////////////////////////////
	
	public long getAno() {
		return ano;
	}

	public void setAno(long ano) {
		this.ano = ano;
	}

	public long getTno() {
		return tno;
	}

	public void setTno(long tno) {
		this.tno = tno;
	}

	public long getSno() {
		return sno;
	}

	public void setSno(long sno) {
		this.sno = sno;
	}

	public Date getSbirth() {
		return sbirth;
	}

	public void setSbirth(Date sbirth) {
		this.sbirth = sbirth;
	}

	public Date getTbirth() {
		return tbirth;
	}

	public void setTbirth(Date tbirth) {
		this.tbirth = tbirth;
	}

	public float getCredit() {
		return credit;
	}

	public void setCredit(float credit) {
		this.credit = credit;
	}

	public float getTheoryhour() {
		return theoryhour;
	}

	public void setTheoryhour(float theoryhour) {
		this.theoryhour = theoryhour;
	}

	public float getPracticehour() {
		return practicehour;
	}

	public void setPracticehour(float practicehour) {
		this.practicehour = practicehour;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSsex() {
		return ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getSpolitics() {
		return spolitics;
	}

	public void setSpolitics(String spolitics) {
		this.spolitics = spolitics;
	}

	public String getSaddr() {
		return saddr;
	}

	public void setSaddr(String saddr) {
		this.saddr = saddr;
	}

	public String getSinfo() {
		return sinfo;
	}

	public void setSinfo(String sinfo) {
		this.sinfo = sinfo;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTsex() {
		return tsex;
	}

	public void setTsex(String tsex) {
		this.tsex = tsex;
	}

	public String getTpolitics() {
		return tpolitics;
	}

	public void setTpolitics(String tpolitics) {
		this.tpolitics = tpolitics;
	}

	public String getTjob() {
		return tjob;
	}

	public void setTjob(String tjob) {
		this.tjob = tjob;
	}

	public String getTacademy() {
		return tacademy;
	}

	public void setTacademy(String tacademy) {
		this.tacademy = tacademy;
	}

	public String getTinfo() {
		return tinfo;
	}

	public void setTinfo(String tinfo) {
		this.tinfo = tinfo;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getAsex() {
		return asex;
	}

	public void setAsex(String asex) {
		this.asex = asex;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getAinfo() {
		return ainfo;
	}

	public void setAinfo(String ainfo) {
		this.ainfo = ainfo;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMinfo() {
		return minfo;
	}

	public void setMinfo(String minfo) {
		this.minfo = minfo;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public String getClasss() {
		return classs;
	}

	public void setClasss(String classs) {
		this.classs = classs;
	}

	public String getCinfo() {
		return cinfo;
	}

	public void setCinfo(String cinfo) {
		this.cinfo = cinfo;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getCacademy() {
		return cacademy;
	}

	public void setCacademy(String cacademy) {
		this.cacademy = cacademy;
	}

}
