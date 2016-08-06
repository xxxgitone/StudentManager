package cn.hibernate.beans;

public class Classs extends AbstractClasss {
	String cid;
	String classs;
	String cinfo;

	public Classs() {
	}

	public Classs(String cid, String classs, String cinfo) {
		this.cid = cid;
		this.classs = classs;
		this.cinfo = cinfo;
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
