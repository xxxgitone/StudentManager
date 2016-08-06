package cn.hibernate.beans;

import java.util.Date;

/**
 * AbstractStudent entity provides the base persistence definition of the
 * Student entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractStudent implements java.io.Serializable {

	// Fields

	private Integer sno;
	private String pass;
	private String sname;
	private String ssex;
	private Date sbirth;
	private String sid;
	private String cid;
	private String spolitics;
	private String saddr;
	private String sinfo;

	// Constructors

	/** default constructor */
	public AbstractStudent() {
	}

	/** minimal constructor */
	public AbstractStudent(String pass, String sname, String ssex, Date sbirth,
			String sid, String cid, String saddr) {
		this.pass = pass;
		this.sname = sname;
		this.ssex = ssex;
		this.sbirth = sbirth;
		this.sid = sid;
		this.cid = cid;
		this.saddr = saddr;
	}

	/** full constructor */
	public AbstractStudent(String pass, String sname, String ssex, Date sbirth,
			String sid, String cid, String spolitics, String saddr, String sinfo) {
		this.pass = pass;
		this.sname = sname;
		this.ssex = ssex;
		this.sbirth = sbirth;
		this.sid = sid;
		this.cid = cid;
		this.spolitics = spolitics;
		this.saddr = saddr;
		this.sinfo = sinfo;
	}

	// Property accessors

	public Integer getSno() {
		return this.sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSsex() {
		return this.ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public Date getSbirth() {
		return this.sbirth;
	}

	public void setSbirth(Date sbirth) {
		this.sbirth = sbirth;
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getSpolitics() {
		return this.spolitics;
	}

	public void setSpolitics(String spolitics) {
		this.spolitics = spolitics;
	}

	public String getSaddr() {
		return this.saddr;
	}

	public void setSaddr(String saddr) {
		this.saddr = saddr;
	}

	public String getSinfo() {
		return this.sinfo;
	}

	public void setSinfo(String sinfo) {
		this.sinfo = sinfo;
	}

}