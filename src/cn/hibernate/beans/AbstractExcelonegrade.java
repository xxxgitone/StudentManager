package cn.hibernate.beans;

/**
 * AbstractExcelonegrade entity provides the base persistence definition of the
 * Excelonegrade entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractExcelonegrade implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sno;
	private String sname;
	private Float grade;

	// Constructors

	/** default constructor */
	public AbstractExcelonegrade() {
	}

	/** full constructor */
	public AbstractExcelonegrade(Integer sno, String sname, Float grade) {
		this.sno = sno;
		this.sname = sname;
		this.grade = grade;
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

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Float getGrade() {
		return this.grade;
	}

	public void setGrade(Float grade) {
		this.grade = grade;
	}

}