package cn.hibernate.beans;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public abstract class BaseHibernateDAO implements IBaseHibernateDAO {
	private static final Logger log2 = LoggerFactory.getLogger(AcademyDAO.class);
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}

	//为了给子类重写的方法 写成抽象方法，利于管理
	public abstract List findAll();
	public abstract List findByProperty(String propertyName, Object value);
	public void save(Object transientInstance){
		log2.debug("saving one instance");
		try {
			Session session  = getSession();
			session.beginTransaction();
			session.save(transientInstance);
			session.getTransaction().commit();
			
			log2.debug("save successful");
		} catch (RuntimeException re) {
			log2.error("save failed", re);
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	};
}