package cn.hibernate.beans;

/**
 * AbstractCourse entity provides the base persistence definition of the Course
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCourse implements java.io.Serializable {

	// Fields

	private String cno;
	private String cname;
	private Float credit;
	private Float theoryhour;
	private Float practicehour;
	private String ctype;
	private String cacademy;
	private String cinfo;

	// Constructors

	/** default constructor */
	public AbstractCourse() {
	}

	/** minimal constructor */
	public AbstractCourse(String cno, String cname, Float credit,
			Float theoryhour, String ctype, String cacademy) {
		this.cno = cno;
		this.cname = cname;
		this.credit = credit;
		this.theoryhour = theoryhour;
		this.ctype = ctype;
		this.cacademy = cacademy;
	}

	/** full constructor */
	public AbstractCourse(String cno, String cname, Float credit,
			Float theoryhour, Float practicehour, String ctype,
			String cacademy, String cinfo) {
		this.cno = cno;
		this.cname = cname;
		this.credit = credit;
		this.theoryhour = theoryhour;
		this.practicehour = practicehour;
		this.ctype = ctype;
		this.cacademy = cacademy;
		this.cinfo = cinfo;
	}

	// Property accessors

	public String getCno() {
		return this.cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Float getCredit() {
		return this.credit;
	}

	public void setCredit(Float credit) {
		this.credit = credit;
	}

	public Float getTheoryhour() {
		return this.theoryhour;
	}

	public void setTheoryhour(Float theoryhour) {
		this.theoryhour = theoryhour;
	}

	public Float getPracticehour() {
		return this.practicehour;
	}

	public void setPracticehour(Float practicehour) {
		this.practicehour = practicehour;
	}

	public String getCtype() {
		return this.ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getCacademy() {
		return this.cacademy;
	}

	public void setCacademy(String cacademy) {
		this.cacademy = cacademy;
	}

	public String getCinfo() {
		return this.cinfo;
	}

	public void setCinfo(String cinfo) {
		this.cinfo = cinfo;
	}

}