package cn.hibernate.beans;

/**
 * SyllabusId entity. @author MyEclipse Persistence Tools
 */
public class SyllabusId extends AbstractSyllabusId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SyllabusId() {
	}

	/** full constructor */
	public SyllabusId(String cid, String cno, Integer tno, String theoryroom,
			Integer week, Integer start, Integer amount,String year,Integer term) {
		super(cid, cno, tno, theoryroom, week, start, amount,year,term);
	}

}
