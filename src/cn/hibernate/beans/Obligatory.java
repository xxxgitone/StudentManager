package cn.hibernate.beans;

@SuppressWarnings("serial")
public class Obligatory extends AbstractObligatory {
	String year;
	int term;
	String cid;
	String cno;
	int tno;
	String info;

	public Obligatory() {
	}

	public Obligatory(String year, int term,String info) {
		this.year = year;
		this.term = term;
		this.info = info;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public int getTno() {
		return tno;
	}

	public void setTno(int tno) {
		this.tno = tno;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
