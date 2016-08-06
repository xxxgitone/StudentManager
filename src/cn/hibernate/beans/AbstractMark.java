package cn.hibernate.beans;

/**
 * AbstractMark entity provides the base persistence definition of the Mark
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMark implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sno;
	private String cno;
	private Float grade;
	private Float gpa;
	private Float makeup;
	private Float ultimate;
	private String year;
	private Integer term;
	private String info;

	// Constructors

	/** default constructor */
	public AbstractMark() {
	}

	/** minimal constructor */
	public AbstractMark(Integer sno, String cno, Float grade, Float gpa,
			String year, Integer term) {
		this.sno = sno;
		this.cno = cno;
		this.grade = grade;
		this.gpa = gpa;
		this.year = year;
		this.term = term;
	}

	/** full constructor */
	public AbstractMark(Integer sno, String cno, Float grade, Float gpa,
			Float makeup, Float ultimate, String year, Integer term, String info) {
		this.sno = sno;
		this.cno = cno;
		this.grade = grade;
		this.gpa = gpa;
		this.makeup = makeup;
		this.ultimate = ultimate;
		this.year = year;
		this.term = term;
		this.info = info;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSno() {
		return this.sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public String getCno() {
		return this.cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public Float getGrade() {
		return this.grade;
	}

	public void setGrade(Float grade) {
		this.grade = grade;
	}

	public Float getGpa() {
		return this.gpa;
	}

	public void setGpa(Float gpa) {
		this.gpa = gpa;
	}

	public Float getMakeup() {
		return this.makeup;
	}

	public void setMakeup(Float makeup) {
		this.makeup = makeup;
	}

	public Float getUltimate() {
		return this.ultimate;
	}

	public void setUltimate(Float ultimate) {
		this.ultimate = ultimate;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getTerm() {
		return this.term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}