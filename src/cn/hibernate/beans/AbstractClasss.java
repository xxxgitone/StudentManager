package cn.hibernate.beans;

/**
 * AbstractClasss entity provides the base persistence definition of the Classs
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractClasss implements java.io.Serializable {

	// Fields

	private String cid;
	private String classs;
	private String cinfo;
	private String mid;

	// Constructors

	/** default constructor */
	public AbstractClasss() {
	}

	/** minimal constructor */
	public AbstractClasss(String cid, String classs) {
		this.cid = cid;
		this.classs = classs;
	}

	/** full constructor */
	public AbstractClasss(String cid, String classs, String cinfo) {
		this.cid = cid;
		this.classs = classs;
		this.cinfo = cinfo;
	}

	// Property accessors

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getClasss() {
		return this.classs;
	}

	public void setClasss(String classs) {
		this.classs = classs;
	}

	public String getCinfo() {
		return this.cinfo;
	}

	public void setCinfo(String cinfo) {
		this.cinfo = cinfo;
	}

}