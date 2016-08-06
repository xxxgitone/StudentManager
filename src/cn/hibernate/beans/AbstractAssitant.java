package cn.hibernate.beans;

/**
 * AbstractAssitant entity provides the base persistence definition of the
 * Assitant entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAssitant implements java.io.Serializable {

	// Fields

	private Integer ano;
	private String aname;
	private String pass;
	private String asex;
	private String ainfo;

	// Constructors

	/** default constructor */
	public AbstractAssitant() {
	}

	/** minimal constructor */
	public AbstractAssitant(String aname, String pass, String asex) {
		this.aname = aname;
		this.pass = pass;
		this.asex = asex;
	}

	/** full constructor */
	public AbstractAssitant(String aname, String pass, String asex, String ainfo) {
		this.aname = aname;
		this.pass = pass;
		this.asex = asex;
		this.ainfo = ainfo;
	}

	// Property accessors

	public Integer getAno() {
		return this.ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getAname() {
		return this.aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getAsex() {
		return this.asex;
	}

	public void setAsex(String asex) {
		this.asex = asex;
	}

	public String getAinfo() {
		return this.ainfo;
	}

	public void setAinfo(String ainfo) {
		this.ainfo = ainfo;
	}

}