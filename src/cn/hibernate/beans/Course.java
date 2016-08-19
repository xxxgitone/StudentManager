package cn.hibernate.beans;

import java.util.HashSet;
import java.util.Set;

public class Course {
	String cno;
	String cname;
	float credit;
	float theoryhour;
	float practicehour;
	String ctype;
	Academy aid;
	String cacademy;
	String cinfo;
	Set <Mark>marks = new HashSet<Mark>();
	Set <Obligatory> obs = new HashSet<Obligatory>();
	
	public Course() {
	}

	public Course(String cno, String cname, float credit, float theoryhour,
			float practicehour, String ctype, String cinfo) {
		this.cno = cno;
		this.cname = cname;
		this.credit = credit;
		this.theoryhour = theoryhour;
		this.practicehour = practicehour;
		this.ctype = ctype;
		this.cinfo = cinfo;
	}

	public Set<Obligatory> getObs() {
		return obs;
	}

	public void setObs(Set<Obligatory> obs) {
		this.obs = obs;
	}

	public Academy getAid() {
		return aid;
	}

	public void setAid(Academy aid) {
		this.aid = aid;
	}

	public Set<Mark> getMarks() {
		return marks;
	}

	public void setMarks(Set<Mark> marks) {
		this.marks = marks;
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

	public String getCinfo() {
		return cinfo;
	}

	public void setCinfo(String cinfo) {
		this.cinfo = cinfo;
	}
}
