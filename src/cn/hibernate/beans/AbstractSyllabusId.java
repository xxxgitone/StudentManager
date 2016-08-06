package cn.hibernate.beans;

/**
 * AbstractSyllabusId entity provides the base persistence definition of the
 * SyllabusId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSyllabusId implements java.io.Serializable {

	// Fields

	private String cid;
	private String cno;
	private Integer tno;
	private String theoryroom;
	private Integer week;
	private Integer start;
	private Integer amount;

	// Constructors

	/** default constructor */
	public AbstractSyllabusId() {
	}

	/** full constructor */
	public AbstractSyllabusId(String cid, String cno, Integer tno,
			String theoryroom, Integer week, Integer start, Integer amount) {
		this.cid = cid;
		this.cno = cno;
		this.tno = tno;
		this.theoryroom = theoryroom;
		this.week = week;
		this.start = start;
		this.amount = amount;
	}

	// Property accessors

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCno() {
		return this.cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public Integer getTno() {
		return this.tno;
	}

	public void setTno(Integer tno) {
		this.tno = tno;
	}

	public String getTheoryroom() {
		return this.theoryroom;
	}

	public void setTheoryroom(String theoryroom) {
		this.theoryroom = theoryroom;
	}

	public Integer getWeek() {
		return this.week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Integer getStart() {
		return this.start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractSyllabusId))
			return false;
		AbstractSyllabusId castOther = (AbstractSyllabusId) other;

		return ((this.getCid() == castOther.getCid()) || (this.getCid() != null
				&& castOther.getCid() != null && this.getCid().equals(
				castOther.getCid())))
				&& ((this.getCno() == castOther.getCno()) || (this.getCno() != null
						&& castOther.getCno() != null && this.getCno().equals(
						castOther.getCno())))
				&& ((this.getTno() == castOther.getTno()) || (this.getTno() != null
						&& castOther.getTno() != null && this.getTno().equals(
						castOther.getTno())))
				&& ((this.getTheoryroom() == castOther.getTheoryroom()) || (this
						.getTheoryroom() != null
						&& castOther.getTheoryroom() != null && this
						.getTheoryroom().equals(castOther.getTheoryroom())))
				&& ((this.getWeek() == castOther.getWeek()) || (this.getWeek() != null
						&& castOther.getWeek() != null && this.getWeek()
						.equals(castOther.getWeek())))
				&& ((this.getStart() == castOther.getStart()) || (this
						.getStart() != null && castOther.getStart() != null && this
						.getStart().equals(castOther.getStart())))
				&& ((this.getAmount() == castOther.getAmount()) || (this
						.getAmount() != null && castOther.getAmount() != null && this
						.getAmount().equals(castOther.getAmount())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		result = 37 * result
				+ (getCno() == null ? 0 : this.getCno().hashCode());
		result = 37 * result
				+ (getTno() == null ? 0 : this.getTno().hashCode());
		result = 37
				* result
				+ (getTheoryroom() == null ? 0 : this.getTheoryroom()
						.hashCode());
		result = 37 * result
				+ (getWeek() == null ? 0 : this.getWeek().hashCode());
		result = 37 * result
				+ (getStart() == null ? 0 : this.getStart().hashCode());
		result = 37 * result
				+ (getAmount() == null ? 0 : this.getAmount().hashCode());
		return result;
	}

}