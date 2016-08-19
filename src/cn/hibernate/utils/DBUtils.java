package cn.hibernate.utils;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * 增删改适合使用，查的话最好是使用封装好了的DAO文件
 * @author  Myth
 * @date 2016年8月6日 下午9:52:45
 * @TODO
 */
public class DBUtils {
	private static SessionFactory sessionFactory;
	private static Configuration config = new Configuration();
	private static final ThreadLocal<Session> THREAD_LOCAL = new ThreadLocal<Session>();
	
	
	static{
		config.configure();
		sessionFactory = config.buildSessionFactory();
		System.out.println("成功");
	}
	/**
	 * 通过会话工厂打开会话，就可以访问数据库了
	 */
	public static Session getSession(){
		Session session = (Session)THREAD_LOCAL.get();
		if(session == null || !session.isOpen()){
			if(sessionFactory == null){
				rebuildSesssionFactory();
			}
			session = (sessionFactory!=null)?sessionFactory.openSession():null;
			THREAD_LOCAL.set(session);
		}
		return session;
	}
	/**
	 * 重新创建一个会话工厂
	 */
	private static void rebuildSesssionFactory() {
		// TODO Auto-generated method stub
		try{
			config.configure();
			sessionFactory = config.buildSessionFactory();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 关闭数据库的会话
	 */
	public static void closeSession(){
		Session session = (Session)THREAD_LOCAL.get();
		THREAD_LOCAL.set(null);
		if(session!=null){
			session.close();
		}
	}
	/**
	 * 依据入参对象，插入一条记录
	 * @param obj
	 */
	public static void add(Object obj){
		Session session = null;
		Transaction tx = null;
		try{
			session = DBUtils.getSession();
			tx = session.beginTransaction();
			session.save(obj);
			//save方法如果没有开启事务，他还是会执行该插入语句，但是由于没有提交事务，就会回滚
			//session.persist(obj); 该方法在事务没有开启的时候是不会执行的，就是说没有插入语句
			tx.commit();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
			throw e;//必须抛出异常
		}finally{
			if(session!=null){
				session.close();
			}
		}
	}
	/**
	 * 批量插入数据
	 * @param lists
	 */
	public static void adds(List<Object> lists){
		
	}
	/**
	 * 依据入参（对象）的各项属性，来修改记录
	 * @param obj
	 */
	public static void update(Object obj){
		Session session = null;
		Transaction tx = null;
		try{
			session = DBUtils.getSession();
			tx = session.beginTransaction();
			session.update(obj);
			tx.commit();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
			throw e;//必须抛出异常
		}finally{
			if(session!=null){
				session.close();
			}
		}
	}
	/**
	 * 依据入参（对象）的主键来删除记录
	 * @param obj
	 */
	public static void delete(Object obj){
		Session session = null;
		Transaction tx = null;
		try{
			session = DBUtils.getSession();
			tx = session.beginTransaction();
			session.delete(obj);
			tx.commit();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
			throw e;//必须抛出异常
		}finally{
			if(session!=null){
				session.close();
			}
		}
	}
	/**
	 * 指定了对象的类，和Id值来查询
	 * @param class1 要查的类，即表
	 * @param id 值
	 * @return 查找回来的对象，注意执行方法后要强转
	 */
	public static Object findById(Class class1,Serializable id){
		Session session = null;
		try{
			session = DBUtils.getSession();
			Object obj = session.get(class1, id);
			//get() 和 load() 区别：load()不会立即去访问数据库，只有在第一次使用的时候才会去加载（懒加载）
			//load() 方法永远不可能返回空对象（如果不存在，其会产生一个class1的子类）具体可以去差资料
			return obj;
		}finally{
			if(session!=null) session.close();
		}
	}
}
