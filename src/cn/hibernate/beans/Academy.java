package cn.hibernate.beans;

public class Academy extends AbstractAcademy {
	String aid;
	String academy;
	String ainfo;

	public Academy() {
	}

	public Academy(String aid, String academy, String ainfo) {
		this.aid = aid;
		this.academy = academy;
		this.ainfo = ainfo;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public String getAinfo() {
		return ainfo;
	}

	public void setAinfo(String ainfo) {
		this.ainfo = ainfo;
	}
}
