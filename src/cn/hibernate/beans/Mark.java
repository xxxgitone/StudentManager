package cn.hibernate.beans;

public class Mark {
	private int id;
	/*private int sno;*/
	private String cno;
	float grade;
	float gpa;
	float makeup;
	float ultimate;
	String year;
	int term;
	String info;
	Student student;

	public Mark() {
	}

	public Mark(String cno, float grade,String year, int term) {
		super();
		this.cno = cno;
		this.grade = grade;
		this.year = year;
		this.term = term;
//		this.student = student;
	}

	public Mark(int id, String cno, float grade, float gpa,
			float makeup, float ultimate, String year, int term, String info) {
		this.id = id;
		this.cno = cno;
		this.grade = grade;
		this.gpa = gpa;
		this.makeup = makeup;
		this.ultimate = ultimate;
		this.year = year;
		this.term = term;
		this.info = info;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}
*/
	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public float getGpa() {
		return gpa;
	}

	public void setGpa(float gpa) {
		this.gpa = gpa;
	}

	public float getMakeup() {
		return makeup;
	}

	public void setMakeup(float makeup) {
		this.makeup = makeup;
	}

	public float getUltimate() {
		return ultimate;
	}

	public void setUltimate(float ultimate) {
		this.ultimate = ultimate;
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
