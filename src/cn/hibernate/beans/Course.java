package cn.hibernate.beans;

public class Course {
	String cno;
	String cname;
	float credit;
	float theoryhour;
	float practicehour;
	String ctype;
	String cacademy;
	String cinfo;

	public Course() {
	}

	public Course(String cno, String cname, float credit, float theoryhour,
			float practicehour, String ctype, String cacademy, String cinfo) {
		this.cno = cno;
		this.cname = cname;
		this.credit = credit;
		this.theoryhour = theoryhour;
		this.practicehour = practicehour;
		this.ctype = ctype;
		this.cacademy = cacademy;
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
