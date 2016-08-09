package cn.struts.action;

import java.sql.Date;

import cn.hibernate.beans.Student;
import cn.hibernate.beans.StudentDAO;
/**
 * 用来测试Hibernate搭建是否成功
 * @author  Myth
 * @date 2016年8月6日 下午9:46:21
 * @TODO
 */
public class Test {
	public static void main(String []s){
		//测试Manager类
//		Manager s2 = new Manager("4", "24", "2");
//		Manager s3 = new Manager("5", "df", "dasf");
//		System.out.println("main");
//		DBUtils.add(s3);
//		DBUtils.delete(s2);
		//测试student类
		Student stu = new Student( "1", "2", "2", new Date(20160212), "2122", "3", "3", "45", "55");
		//DBUtils.add(stu);//测试成功
		StudentDAO sd = new StudentDAO ();
		sd.delete(stu);
		//Major sss= new Major("","");//
	}
}
