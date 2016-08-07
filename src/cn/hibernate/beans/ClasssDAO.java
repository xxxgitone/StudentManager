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
 * Classs entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.hibernate.beans.Classs
 * @author MyEclipse Persistence Tools
 */
public class ClasssDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ClasssDAO.class);
	// property constants
	public static final String CLASSS = "classs";
	public static final String CINFO = "cinfo";

	public void save(Classs transientInstance) {
		log.debug("saving Classs instance");
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

	public void delete(Classs persistentInstance) {
		log.debug("deleting Classs instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Classs findById(java.lang.String id) {
		log.debug("getting Classs instance with id: " + id);
		try {
			Classs instance = (Classs) getSession().get(
					"cn.hibernate.beans.Classs", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Classs instance) {
		log.debug("finding Classs instance by example");
		try {
			List results = getSession()
					.createCriteria("cn.hibernate.beans.Classs")
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
		log.debug("finding Classs instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Classs as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByClasss(Object classs) {
		return findByProperty(CLASSS, classs);
	}

	public List findByCinfo(Object cinfo) {
		return findByProperty(CINFO, cinfo);
	}

	public List findAll() {
		log.debug("finding all Classs instances");
		try {
			String queryString = "from Classs";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Classs merge(Classs detachedInstance) {
		log.debug("merging Classs instance");
		try {
			Classs result = (Classs) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Classs instance) {
		log.debug("attaching dirty Classs instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Classs instance) {
		log.debug("attaching clean Classs instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}