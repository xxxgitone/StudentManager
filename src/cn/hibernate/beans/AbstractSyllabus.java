package cn.hibernate.beans;

/**
 * AbstractSyllabus entity provides the base persistence definition of the
 * Syllabus entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSyllabus implements java.io.Serializable {

	// Fields

	private SyllabusId id;

	// Constructors

	/** default constructor */
	public AbstractSyllabus() {
	}

	/** full constructor */
	public AbstractSyllabus(SyllabusId id) {
		this.id = id;
	}

	// Property accessors

	public SyllabusId getId() {
		return this.id;
	}

	public void setId(SyllabusId id) {
		this.id = id;
	}

}