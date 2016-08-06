package cn.hibernate.beans;

public class Assitant {
	int ano;
	String aname;
	String pass;
	String asex;
	String ainfo;

	public Assitant() {
	}

	public Assitant(int ano, String aname, String pass, String asex,
			String ainfo) {
		this.ano = ano;
		this.aname = aname;
		this.pass = pass;
		this.asex = asex;
		this.ainfo = ainfo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
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
