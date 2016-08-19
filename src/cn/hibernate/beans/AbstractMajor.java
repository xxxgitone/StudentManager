package cn.hibernate.beans;

/**
 * AbstractMajor entity provides the base persistence definition of the Major
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMajor implements java.io.Serializable {

	// Fields

	private String mid;
	private String major;
	private String minfo;
	private String aid;

	// Constructors

	/** default constructor */
	public AbstractMajor() {
	}

	/** minimal constructor */
	public AbstractMajor(String mid, String major) {
		this.mid = mid;
		this.major = major;
	}

	/** full constructor */
	public AbstractMajor(String mid, String major, String minfo) {
		this.mid = mid;
		this.major = major;
		this.minfo = minfo;
	}

	// Property accessors

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getMid() {
		return this.mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMinfo() {
		return this.minfo;
	}

	public void setMinfo(String minfo) {
		this.minfo = minfo;
	}

}