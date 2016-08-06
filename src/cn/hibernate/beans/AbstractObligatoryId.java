package cn.hibernate.beans;

/**
 * AbstractObligatoryId entity provides the base persistence definition of the
 * ObligatoryId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractObligatoryId implements java.io.Serializable {

	// Fields

	private String year;
	private Integer term;
	private String cid;
	private String cno;
	private Integer tno;
	private String info;

	// Constructors

	/** default constructor */
	public AbstractObligatoryId() {
	}

	/** minimal constructor */
	public AbstractObligatoryId(String year, Integer term, String cid,
			String cno, Integer tno) {
		this.year = year;
		this.term = term;
		this.cid = cid;
		this.cno = cno;
		this.tno = tno;
	}

	/** full constructor */
	public AbstractObligatoryId(String year, Integer term, String cid,
			String cno, Integer tno, String info) {
		this.year = year;
		this.term = term;
		this.cid = cid;
		this.cno = cno;
		this.tno = tno;
		this.info = info;
	}

	// Property accessors

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

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractObligatoryId))
			return false;
		AbstractObligatoryId castOther = (AbstractObligatoryId) other;

		return ((this.getYear() == castOther.getYear()) || (this.getYear() != null
				&& castOther.getYear() != null && this.getYear().equals(
				castOther.getYear())))
				&& ((this.getTerm() == castOther.getTerm()) || (this.getTerm() != null
						&& castOther.getTerm() != null && this.getTerm()
						.equals(castOther.getTerm())))
				&& ((this.getCid() == castOther.getCid()) || (this.getCid() != null
						&& castOther.getCid() != null && this.getCid().equals(
						castOther.getCid())))
				&& ((this.getCno() == castOther.getCno()) || (this.getCno() != null
						&& castOther.getCno() != null && this.getCno().equals(
						castOther.getCno())))
				&& ((this.getTno() == castOther.getTno()) || (this.getTno() != null
						&& castOther.getTno() != null && this.getTno().equals(
						castOther.getTno())))
				&& ((this.getInfo() == castOther.getInfo()) || (this.getInfo() != null
						&& castOther.getInfo() != null && this.getInfo()
						.equals(castOther.getInfo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getYear() == null ? 0 : this.getYear().hashCode());
		result = 37 * result
				+ (getTerm() == null ? 0 : this.getTerm().hashCode());
		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		result = 37 * result
				+ (getCno() == null ? 0 : this.getCno().hashCode());
		result = 37 * result
				+ (getTno() == null ? 0 : this.getTno().hashCode());
		result = 37 * result
				+ (getInfo() == null ? 0 : this.getInfo().hashCode());
		return result;
	}

}