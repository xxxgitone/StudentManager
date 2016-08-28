package cn.hibernate.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

public class Student  {
	long sno;
	String pass;
	String sname;
	String ssex;
	@JSONField (format="yyyy-MM-dd")
	Date sbirth;
	String sid;
	String cid;
	String spolitics;
	String saddr;
	String sinfo;
	
	public Student() {
	}

	public Student(int sno){
		this.sno = sno;
	}
	public Student(String pass, String sname, String ssex,
			Date sbirth, String sid, String cid, String spolitics,
			String saddr, String sinfo) {
//		this.sno = sno; 因为Hibernate控制了自动增长，初始化了也没有用
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

	public long getSno() {
		return sno;
	}

	public void setSno(long sno) {
		this.sno = sno;
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

	public Date getSbirth() {
		return sbirth;
	}

	public void setSbirth(Date sbirth) {
		this.sbirth = sbirth;
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
}
