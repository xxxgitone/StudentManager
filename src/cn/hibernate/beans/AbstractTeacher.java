package cn.hibernate.beans;

import java.util.Date;

/**
 * AbstractTeacher entity provides the base persistence definition of the
 * Teacher entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTeacher implements java.io.Serializable {

	// Fields

	private Integer tno;
	private String tname;
	private String pass;
	private String tsex;
	private Date tbirth;
	private String tpolitics;
	private String tjob;
	private String tacademy;
	private String tinfo;

	// Constructors

	/** default constructor */
	public AbstractTeacher() {
	}

	/** minimal constructor */
	public AbstractTeacher(String tname, String pass, String tsex, Date tbirth,
			String tjob, String tacademy) {
		this.tname = tname;
		this.pass = pass;
		this.tsex = tsex;
		this.tbirth = tbirth;
		this.tjob = tjob;
		this.tacademy = tacademy;
	}

	/** full constructor */
	public AbstractTeacher(String tname, String pass, String tsex, Date tbirth,
			String tpolitics, String tjob, String tacademy, String tinfo) {
		this.tname = tname;
		this.pass = pass;
		this.tsex = tsex;
		this.tbirth = tbirth;
		this.tpolitics = tpolitics;
		this.tjob = tjob;
		this.tacademy = tacademy;
		this.tinfo = tinfo;
	}

	// Property accessors

	public Integer getTno() {
		return this.tno;
	}

	public void setTno(Integer tno) {
		this.tno = tno;
	}

	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getTsex() {
		return this.tsex;
	}

	public void setTsex(String tsex) {
		this.tsex = tsex;
	}

	public Date getTbirth() {
		return this.tbirth;
	}

	public void setTbirth(Date tbirth) {
		this.tbirth = tbirth;
	}

	public String getTpolitics() {
		return this.tpolitics;
	}

	public void setTpolitics(String tpolitics) {
		this.tpolitics = tpolitics;
	}

	public String getTjob() {
		return this.tjob;
	}

	public void setTjob(String tjob) {
		this.tjob = tjob;
	}

	public String getTacademy() {
		return this.tacademy;
	}

	public void setTacademy(String tacademy) {
		this.tacademy = tacademy;
	}

	public String getTinfo() {
		return this.tinfo;
	}

	public void setTinfo(String tinfo) {
		this.tinfo = tinfo;
	}

}