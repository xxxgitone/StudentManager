package cn.struts.action;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import cn.hibernate.beans.HibernateSessionFactory;
import cn.hibernate.beans.Mark;
import cn.hibernate.beans.Student;
import cn.hibernate.beans.StudentDAO;
import cn.hibernate.utils.DBUtils;
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
		/*Student stu = new Student(67, "1", "2", "2", new Date(20160212), "21", "3", "3", "45", "55");
			//DBUtils.add(stu);//使用Utils测试成功
		//使用DAO测试成功
		StudentDAO sd = new StudentDAO ();
		sd.save(stu);*/
		/**测试mark部分属性构造器实例化对象并写入数据库*/
		//新增学生，新增成绩
		
		/*Student stu = new Student("1", "2", "2", new Date(20160212), "15", "3", "3", "45", "55");
		Mark m = new Mark("20003", 87, "2015-2016", 1);
		stu.getMarks().add(m);
		//Mark m2 = new Mark();
		DBUtils.add(stu);*/
		//
		Session session = DBUtils.getSession();
		Student stu2 = (Student)session.load(Student.class, 20);
		session.beginTransaction();
		Mark m3 = new Mark("20003", 87, "2015-2016", 1);
		m3.setStudent(stu2);
		session.save(m3);
		session.getTransaction().commit();
		
		/**测试一对多表关系配置文件的正确性*/
		/*Session ss = HibernateSessionFactory.getSession();
		Query qq = ss.createQuery("from Student");
		List l = qq.list();
		for (int i=0;i<l.size();i++){
			Student student = (Student)l.get(i);
			System.out.println("sno : "+student.getSno());
			Set mark = student.getMarks();//通过学生实例来查询其对应的成绩，学生实例可以有空列，但是成绩不能有，必须要有默认值
			Iterator it = mark.iterator();
			while(it.hasNext()){
				Mark m = (Mark)it.next();
				
				System.out.println("mark:"+m.getId());
			}
		}*/
		
		
		
	}
}
