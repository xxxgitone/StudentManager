package cn.struts.action;

import java.sql.Date;

import cn.hibernate.beans.Student;
import cn.hibernate.beans.StudentDAO;

public class Test {
	public static void main(String []s){
		//测试Manager类
//		Manager s2 = new Manager("4", "24", "2");
//		Manager s3 = new Manager("5", "df", "dasf");
//		System.out.println("main");
//		DBUtils.add(s3);
//		DBUtils.delete(s2);
		//测试student类
		Student stu = new Student(67, "1", "2", "2", new Date(20160212), "21", "3", "3", "45", "55");
		//DBUtils.add(stu);//测试成功
		StudentDAO sd = new StudentDAO ();
		sd.save(stu);
		//Major sss= new Major("","");//
	}
}
