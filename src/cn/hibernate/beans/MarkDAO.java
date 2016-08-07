package cn.hibernate.beans;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Mark
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see cn.hibernate.beans.Mark
 * @author MyEclipse Persistence Tools
 */
public class MarkDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(MarkDAO.class);
	// property constants
	public static final String SNO = "sno";
	public static final String CNO = "cno";
	public static final String GRADE = "grade";
	public static final String GPA = "gpa";
	public static final String MAKEUP = "makeup";
	public static final String ULTIMATE = "ultimate";
	public static final String YEAR = "year";
	public static final String TERM = "term";
	public static final String INFO = "info";

	public void save(Mark transientInstance) {
		log.debug("saving Mark instance");
		try {
			Session session  = getSession();
			session.beginTransaction();
			session.save(transientInstance);
			session.getTransaction().commit();
			session.close();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Mark persistentInstance) {
		log.debug("deleting Mark instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Mark findById(java.lang.Integer id) {
		log.debug("getting Mark instance with id: " + id);
		try {
			Mark instance = (Mark) getSession().get("cn.hibernate.beans.Mark",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Mark instance) {
		log.debug("finding Mark instance by example");
		try {
			List results = getSession()
					.createCriteria("cn.hibernate.beans.Mark")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Mark instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Mark as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySno(Object sno) {
		return findByProperty(SNO, sno);
	}

	public List findByCno(Object cno) {
		return findByProperty(CNO, cno);
	}

	public List findByGrade(Object grade) {
		return findByProperty(GRADE, grade);
	}

	public List findByGpa(Object gpa) {
		return findByProperty(GPA, gpa);
	}

	public List findByMakeup(Object makeup) {
		return findByProperty(MAKEUP, makeup);
	}

	public List findByUltimate(Object ultimate) {
		return findByProperty(ULTIMATE, ultimate);
	}

	public List findByYear(Object year) {
		return findByProperty(YEAR, year);
	}

	public List findByTerm(Object term) {
		return findByProperty(TERM, term);
	}

	public List findByInfo(Object info) {
		return findByProperty(INFO, info);
	}

	public List findAll() {
		log.debug("finding all Mark instances");
		try {
			String queryString = "from Mark";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Mark merge(Mark detachedInstance) {
		log.debug("merging Mark instance");
		try {
			Mark result = (Mark) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Mark instance) {
		log.debug("attaching dirty Mark instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Mark instance) {
		log.debug("attaching clean Mark instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}