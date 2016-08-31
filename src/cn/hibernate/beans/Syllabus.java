package cn.hibernate.beans;

public class Syllabus extends AbstractSyllabus {
	String cid;
	String cno;
	int tno;
	String theoryroom;
	int week;
	int start;
	int amount;
	String year;
	int term;
	
	public Syllabus() {
	}

	public Syllabus(String cid, String cno, int tno, String theoryroom,
			int week, int start, int amount) {
		this.cid = cid;
		this.cno = cno;
		this.tno = tno;
		this.theoryroom = theoryroom;
		this.week = week;
		this.start = start;
		this.amount = amount;
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

	public String getTheoryroom() {
		return theoryroom;
	}

	public void setTheoryroom(String theoryroom) {
		this.theoryroom = theoryroom;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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
}
