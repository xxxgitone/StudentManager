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
 * Syllabus entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.hibernate.beans.Syllabus
 * @author MyEclipse Persistence Tools
 */
public class SyllabusDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SyllabusDAO.class);

	// property constants

	public void save(Syllabus transientInstance) {
		log.debug("saving Syllabus instance");
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

	public void delete(Syllabus persistentInstance) {
		log.debug("deleting Syllabus instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Syllabus findById(cn.hibernate.beans.SyllabusId id) {
		log.debug("getting Syllabus instance with id: " + id);
		try {
			Syllabus instance = (Syllabus) getSession().get(
					"cn.hibernate.beans.Syllabus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Syllabus instance) {
		log.debug("finding Syllabus instance by example");
		try {
			List results = getSession()
					.createCriteria("cn.hibernate.beans.Syllabus")
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
		log.debug("finding Syllabus instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Syllabus as model where model."
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
		log.debug("finding all Syllabus instances");
		try {
			String queryString = "from Syllabus";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Syllabus merge(Syllabus detachedInstance) {
		log.debug("merging Syllabus instance");
		try {
			Syllabus result = (Syllabus) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Syllabus instance) {
		log.debug("attaching dirty Syllabus instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Syllabus instance) {
		log.debug("attaching clean Syllabus instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}