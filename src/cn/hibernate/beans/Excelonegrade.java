package cn.hibernate.beans;

public class Excelonegrade {
	int id;
	int sno;
	String sname;
	float grade;

	public Excelonegrade() {
	}

	public Excelonegrade(int id, int sno, String sname, float grade) {
		this.id = id;
		this.sno = sno;
		this.sname = sname;
		this.grade = grade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}
}
