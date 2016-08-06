package cn.hibernate.beans;

/**
 * AbstractManager entity provides the base persistence definition of the
 * Manager entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractManager implements java.io.Serializable {

	// Fields

	private String mname;
	private String pass;
	private String minfo;

	// Constructors

	/** default constructor */
	public AbstractManager() {
	}

	/** minimal constructor */
	public AbstractManager(String mname, String pass) {
		this.mname = mname;
		this.pass = pass;
	}

	/** full constructor */
	public AbstractManager(String mname, String pass, String minfo) {
		this.mname = mname;
		this.pass = pass;
		this.minfo = minfo;
	}

	// Property accessors

	public String getMname() {
		return this.mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getMinfo() {
		return this.minfo;
	}

	public void setMinfo(String minfo) {
		this.minfo = minfo;
	}

}