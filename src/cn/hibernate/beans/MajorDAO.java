package cn.hibernate.beans;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Major
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see cn.hibernate.beans.Major
 * @author MyEclipse Persistence Tools
 */
public class MajorDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(MajorDAO.class);
	// property constants
	public static final String MAJOR = "major";
	public static final String MINFO = "minfo";

	public void save(Major transientInstance) {
		log.debug("saving Major instance");
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

	public void delete(Major persistentInstance) {
		log.debug("deleting Major instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Major findById(java.lang.String id) {
		log.debug("getting Major instance with id: " + id);
		try {
			Major instance = (Major) getSession().get(
					"cn.hibernate.beans.Major", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Major instance) {
		log.debug("finding Major instance by example");
		try {
			List results = getSession()
					.createCriteria("cn.hibernate.beans.Major")
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
		System.out.println(propertyName+"::："+ value);
		log.debug("finding Major instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Major as model where model."
					+ propertyName + "= ?";
			System.out.println(queryString);
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMajor(Object major) {
		return findByProperty(MAJOR, major);
	}

	public List findByMinfo(Object minfo) {
		return findByProperty(MINFO, minfo);
	}

	public List findAll() {
		log.debug("finding all Major instances");
		try {
			String queryString = "from Major";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Major merge(Major detachedInstance) {
		log.debug("merging Major instance");
		try {
			Major result = (Major) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Major instance) {
		log.debug("attaching dirty Major instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Major instance) {
		log.debug("attaching clean Major instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}