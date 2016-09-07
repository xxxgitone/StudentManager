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
 * Obligatory entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.hibernate.beans.Obligatory
 * @author MyEclipse Persistence Tools
 */
public class ObligatoryDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ObligatoryDAO.class);

	// property constants

	public void save(Object oo) {
		Obligatory transientInstance = (Obligatory)oo;
		log.debug("saving Obligatory instance");
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

	public void delete(Obligatory persistentInstance) {
		log.debug("deleting Obligatory instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Obligatory findById(cn.hibernate.beans.ObligatoryId id) {
		log.debug("getting Obligatory instance with id: " + id);
		try {
			Obligatory instance = (Obligatory) getSession().get(
					"cn.hibernate.beans.Obligatory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Obligatory instance) {
		log.debug("finding Obligatory instance by example");
		try {
			List results = getSession()
					.createCriteria("cn.hibernate.beans.Obligatory")
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
		log.debug("finding Obligatory instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Obligatory as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Obligatory instances");
		try {
			String queryString = "from Obligatory";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Obligatory merge(Obligatory detachedInstance) {
		log.debug("merging Obligatory instance");
		try {
			Obligatory result = (Obligatory) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Obligatory instance) {
		log.debug("attaching dirty Obligatory instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Obligatory instance) {
		log.debug("attaching clean Obligatory instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}