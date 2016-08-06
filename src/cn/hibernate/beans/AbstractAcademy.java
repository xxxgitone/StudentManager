package cn.hibernate.beans;

/**
 * AbstractAcademy entity provides the base persistence definition of the
 * Academy entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAcademy implements java.io.Serializable {

	// Fields

	private String aid;
	private String academy;
	private String ainfo;

	// Constructors

	/** default constructor */
	public AbstractAcademy() {
	}

	/** minimal constructor */
	public AbstractAcademy(String aid, String academy) {
		this.aid = aid;
		this.academy = academy;
	}

	/** full constructor */
	public AbstractAcademy(String aid, String academy, String ainfo) {
		this.aid = aid;
		this.academy = academy;
		this.ainfo = ainfo;
	}

	// Property accessors

	public String getAid() {
		return this.aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAcademy() {
		return this.academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public String getAinfo() {
		return this.ainfo;
	}

	public void setAinfo(String ainfo) {
		this.ainfo = ainfo;
	}

}