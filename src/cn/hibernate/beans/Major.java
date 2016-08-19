package cn.hibernate.beans;

public class Major {
	String mid;
	String major;
	String minfo;
	String aid;
	
	public Major() {
	}

	public Major(String mid, String major, String minfo) {
		this.mid = mid;
		this.major = major;
		this.minfo = minfo;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMinfo() {
		return minfo;
	}

	public void setMinfo(String minfo) {
		this.minfo = minfo;
	}
}
