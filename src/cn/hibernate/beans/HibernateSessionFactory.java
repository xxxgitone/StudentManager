package cn.hibernate.beans;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	
	private static String configfile="/hibernate.cfg.xml";
	//private static final Logger logger = Logger.getLogger(HibernateSessionFactory.class);
	
	/*ThreadLocal本地线程化 */
	private static final ThreadLocal<Session> THREAD_LOCAL = new ThreadLocal<Session>();
	private static Configuration config = new Configuration();
	private static org.hibernate.SessionFactory sessionFactory;
	
	/*读取配置文件，创建会话工厂，这段代码为静态块，编译后已经运行*/
	static {
		try{
			config.configure(configfile);
			sessionFactory = config.buildSessionFactory();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("加载配置文件没问题");
		}
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
			System.out.println(">>>>>>>>>>产生一个Session :ID: "+session.hashCode());
		}
		return session;
	}

	/**
	 * 重新创建一个会话工厂
	 */
	public static void rebuildSesssionFactory() {
		try{
//			config.configure();
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
}
