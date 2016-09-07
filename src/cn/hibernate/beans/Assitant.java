package cn.hibernate.beans;

public class Assitant {
	long ano;
	String aname;
	String pass;
	String asex;
	String mid;
	String ainfo;

	public Assitant() {
	}

	public Assitant(long ano, String aname, String pass, String asex,String mid,
			String ainfo) {
		this.ano = ano;
		this.aname = aname;
		this.pass = pass;
		this.asex = asex;
		this.mid = mid;
		this.ainfo = ainfo;
	}

	public Assitant(String aname, String pass, String asex, String mid,
			String ainfo) {
		this.aname = aname;
		this.pass = pass;
		this.asex = asex;
		this.mid = mid;
		this.ainfo = ainfo;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public long getAno() {
		return ano;
	}

	public void setAno(long ano) {
		this.ano = ano;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getAsex() {
		return asex;
	}

	public void setAsex(String asex) {
		this.asex = asex;
	}

	public String getAinfo() {
		return ainfo;
	}

	public void setAinfo(String ainfo) {
		this.ainfo = ainfo;
	}
}
