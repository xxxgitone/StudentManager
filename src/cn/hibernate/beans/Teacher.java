package cn.hibernate.beans;

public class Teacher{
	int tno;
	String tname;
	String pass;
	String tsex;
	java.sql.Date tbirth;
	String tpolitics;
	String tjob;
	String tacademy;
	String tinfo;

	public Teacher() {
	}

	public Teacher(int tno, String tname, String pass, String tsex,
			java.sql.Date tbirth, String tpolitics, String tjob,
			String tacademy, String tinfo) {
		this.tno = tno;
		this.tname = tname;
		this.pass = pass;
		this.tsex = tsex;
		this.tbirth = tbirth;
		this.tpolitics = tpolitics;
		this.tjob = tjob;
		this.tacademy = tacademy;
		this.tinfo = tinfo;
	}

	public int getTno() {
		return tno;
	}

	public void setTno(int tno) {
		this.tno = tno;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getTsex() {
		return tsex;
	}

	public void setTsex(String tsex) {
		this.tsex = tsex;
	}

	public java.sql.Date getTbirth() {
		return tbirth;
	}

	public void setTbirth(java.sql.Date tbirth) {
		this.tbirth = tbirth;
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
}