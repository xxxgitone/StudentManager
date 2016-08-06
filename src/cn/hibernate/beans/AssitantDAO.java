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
 * Assitant entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.hibernate.beans.Assitant
 * @author MyEclipse Persistence Tools
 */
public class AssitantDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AssitantDAO.class);
	// property constants
	public static final String ANAME = "aname";
	public static final String PASS = "pass";
	public static final String ASEX = "asex";
	public static final String AINFO = "ainfo";

	public void save(Assitant transientInstance) {
		log.debug("saving Assitant instance");
		try {
			Session session  = getSession();
			session.save(transientInstance);
			session.getTransaction().commit();
			session.close();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Assitant persistentInstance) {
		log.debug("deleting Assitant instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Assitant findById(java.lang.Integer id) {
		log.debug("getting Assitant instance with id: " + id);
		try {
			Assitant instance = (Assitant) getSession().get(
					"cn.hibernate.beans.Assitant", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Assitant instance) {
		log.debug("finding Assitant instance by example");
		try {
			List results = getSession()
					.createCriteria("cn.hibernate.beans.Assitant")
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
		log.debug("finding Assitant instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Assitant as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAname(Object aname) {
		return findByProperty(ANAME, aname);
	}

	public List findByPass(Object pass) {
		return findByProperty(PASS, pass);
	}

	public List findByAsex(Object asex) {
		return findByProperty(ASEX, asex);
	}

	public List findByAinfo(Object ainfo) {
		return findByProperty(AINFO, ainfo);
	}

	public List findAll() {
		log.debug("finding all Assitant instances");
		try {
			String queryString = "from Assitant";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Assitant merge(Assitant detachedInstance) {
		log.debug("merging Assitant instance");
		try {
			Assitant result = (Assitant) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Assitant instance) {
		log.debug("attaching dirty Assitant instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Assitant instance) {
		log.debug("attaching clean Assitant instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}