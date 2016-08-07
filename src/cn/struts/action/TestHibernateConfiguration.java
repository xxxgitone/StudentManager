package cn.struts.action;

import java.sql.Date;

import cn.hibernate.beans.Student;
import cn.hibernate.beans.StudentDAO;
/**
 * 用来测试Hibernate搭建是否成功
 * 以及测试相关配置文件
 * @author  Myth
 * @date 2016年8月6日 下午9:46:21
 * @TODO
 */
public class TestHibernateConfiguration {
	public static void main(String []s){
		/**测试Manager类*/
//		Manager s2 = new Manager("4", "24", "2");
//		Manager s3 = new Manager("5", "df", "dasf");
//		System.out.println("main");
//		DBUtils.add(s3);
//		DBUtils.delete(s2);
		
		/**测试student类*/
		Student stu = new Student(67, "1", "2", "2", new Date(20160212), "21", "3", "3", "45", "55");
			//DBUtils.add(stu);//使用Utils测试成功
		//使用DAO测试成功
		StudentDAO sd = new StudentDAO ();
		sd.save(stu);
		
		/**测试表关系配置文件的正确性*/
		/*Session ss = HibernateSessionFactory.getSession();
		Query qq = ss.createQuery("from Student");
		List l = qq.list();
		for (int i=0;i<l.size();i++){
			Student student = (Student)l.get(i);
			System.out.println("sno : "+student.getSno());
			Set mark = student.getMarks();
			Iterator it = mark.iterator();
			while(it.hasNext()){
				Mark m = (Mark)it.next();
				
				System.out.println("mark:"+m.getId());
			}
		}*/
		
		
		
	}
}
