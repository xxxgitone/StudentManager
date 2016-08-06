package cn.hibernate.beans;

/**
 * AbstractObligatory entity provides the base persistence definition of the
 * Obligatory entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractObligatory implements java.io.Serializable {

	// Fields

	private ObligatoryId id;

	// Constructors

	/** default constructor */
	public AbstractObligatory() {
	}

	/** full constructor */
	public AbstractObligatory(ObligatoryId id) {
		this.id = id;
	}

	// Property accessors

	public ObligatoryId getId() {
		return this.id;
	}

	public void setId(ObligatoryId id) {
		this.id = id;
	}

}