package cn.utils.test;

import java.util.List;

import cn.hibernate.beans.Student;
import cn.hibernate.beans.StudentDAO;
/**
 * 用来测试Hibernate搭建是否成功
 * 以及测试相关配置文件
 * 	测试一对多的映射的时候要注意规范和实际需求
 * @author  Myth
 * @date 2016年8月6日 下午9:46:21
 * @TODO
 */
public class TestHibernateConfiguration {
	@SuppressWarnings("unchecked")
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
		//测试DAO的查询
		StudentDAO sd = new StudentDAO ();
		List<Student> d = sd.findByProperty("sid", "902");
		System.out.println(d.get(0).getSname());
	}
}
