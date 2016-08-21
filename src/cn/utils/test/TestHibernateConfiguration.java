package cn.utils.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import cn.hibernate.beans.Course;
import cn.hibernate.beans.CourseDAO;
import cn.hibernate.beans.HibernateSessionFactory;
import cn.hibernate.beans.Mark;
import cn.hibernate.beans.MarkDAO;
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
		/**测试mark部分属性构造器实例化对象并写入数据库,结论是可以*/
		
		/**测试一对多表关系配置文件的正确性*/
		//selects();
		//cu();
		
		
	}
}
