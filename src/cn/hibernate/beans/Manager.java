package cn.hibernate.beans;

public class Manager {
	String mname;
	String pass;
	String minfo;

	public Manager() {
	}

	public Manager(String mname, String pass, String minfo) {
		this.mname = mname;
		this.pass = pass;
		this.minfo = minfo;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getMinfo() {
		return minfo;
	}

	public void setMinfo(String minfo) {
		this.minfo = minfo;
	}
}
