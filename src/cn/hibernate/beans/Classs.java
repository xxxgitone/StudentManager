package cn.hibernate.beans;

public class Classs {
	String cid;
	String classs;
	String cinfo;
	String mid;
	
	public Classs() {}
	public Classs(String cid, String classs, String cinfo) {
		this.cid = cid;
		this.classs = classs;
		this.cinfo = cinfo;
	}

	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
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
}
