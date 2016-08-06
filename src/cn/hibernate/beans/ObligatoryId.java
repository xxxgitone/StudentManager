package cn.hibernate.beans;

/**
 * ObligatoryId entity. @author MyEclipse Persistence Tools
 */
public class ObligatoryId extends AbstractObligatoryId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ObligatoryId() {
	}

	/** minimal constructor */
	public ObligatoryId(String year, Integer term, String cid, String cno,
			Integer tno) {
		super(year, term, cid, cno, tno);
	}

	/** full constructor */
	public ObligatoryId(String year, Integer term, String cid, String cno,
			Integer tno, String info) {
		super(year, term, cid, cno, tno, info);
	}

}
