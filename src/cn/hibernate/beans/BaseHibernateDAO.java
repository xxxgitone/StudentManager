package cn.hibernate.beans;

import java.util.List;

import org.hibernate.Session;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
//		HibernateSessionFactory.rebuildSesssionFactory();
		return HibernateSessionFactory.getSession();
	}

	//为了给子类重写的方法
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		System.out.println(propertyName+"::"+ value);
		return null;
	}
	public void save(Object transientInstance) {
		System.out.println("父类的DAO的save方法");
	}
}