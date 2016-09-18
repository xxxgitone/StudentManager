package cn.hibernate.beans;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Excelonegrade entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.hibernate.beans.Excelonegrade
 * @author MyEclipse Persistence Tools
 */
public class ExcelonegradeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ExcelonegradeDAO.class);
	// property constants
	public static final String SNO = "sno";
	public static final String SNAME = "sname";
	public static final String GRADE = "grade";

//	public void save(Excelonegrade transientInstance) {
//		log.debug("saving Excelonegrade instance");
//		try {
//			Session session  = getSession();
//			session.save(transientInstance);
//			session.getTransaction().commit();
//			session.close();
//			log.debug("save successful");
//		} catch (RuntimeException re) {
//			log.error("save failed", re);
//			throw re;
//		}
//	}

	public void delete(Excelonegrade persistentInstance) {
		log.debug("deleting Excelonegrade instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Excelonegrade findById(java.lang.Integer id) {
		log.debug("getting Excelonegrade instance with id: " + id);
		try {
			Excelonegrade instance = (Excelonegrade) getSession().get(
					"cn.hibernate.beans.Excelonegrade", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Excelonegrade instance) {
		log.debug("finding Excelonegrade instance by example");
		try {
			List results = getSession()
					.createCriteria("cn.hibernate.beans.Excelonegrade")
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
		log.debug("finding Excelonegrade instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Excelonegrade as model where model."
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

	public List findBySname(Object sname) {
		return findByProperty(SNAME, sname);
	}

	public List findByGrade(Object grade) {
		return findByProperty(GRADE, grade);
	}

	public List findAll() {
		log.debug("finding all Excelonegrade instances");
		try {
			String queryString = "from Excelonegrade";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Excelonegrade merge(Excelonegrade detachedInstance) {
		log.debug("merging Excelonegrade instance");
		try {
			Excelonegrade result = (Excelonegrade) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Excelonegrade instance) {
		log.debug("attaching dirty Excelonegrade instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Excelonegrade instance) {
		log.debug("attaching clean Excelonegrade instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}