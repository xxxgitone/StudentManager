package cn.hibernate.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.Date;

import cn.hibernate.beans.Student;

import com.myth.mysql.Mysql;

/**
 * 反射自动查询和封装，对象拼凑成对应SQL语句 
 * 
 * @author  Myth
 * @date 2016年9月10日 下午8:20:12
 * @TODO
 */
public class ORM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//路径的正则：
//		String name = ORM.class.getName();//获取类的路径
//		System.out.println(name.split("\\.")[name.split("\\.").length-1]);//使用正则表达式，来截取字符串，获得类名(最后一个串) 把包名去掉
		
//		Student student= (Student)ORM.QueryOneObject(Student.class.getName(), "sno", "12",true);
//		System.out.println("sno : "+student.toString());
		boolean flag = Insert(new Student("pass", "name", "sex", new java.util.Date(), "sid", "3", "sp", "add", "info"));
		System.out.println(flag);
	}
	/**
	 * 将输入对象转换成SQL语句
	 * 1不能有除了属性的get方法之外的get方法，不然这里的SQL拼接会失败
	 * @param obj
	 * @return boolean 是否成功 
	 */
	public static boolean Insert(Object obj){
		Class class1 = obj.getClass();
		//使用StringBuffer的原因是为了多线程的安全，一个类似于String 字符串缓冲区，但不能修改
		StringBuffer sb = new StringBuffer("insert into ");
		StringBuffer va = new StringBuffer("values(");
		
		Method [] ms = class1.getMethods();
		Field [] fs = class1.getDeclaredFields();
		
		String className = class1.getName();
		System.out.println("类名称 : "+className);
		
		//通过正则表达式来截取类名，赋值给表名
		String tableName = className.split("\\.")[className.split("\\.").length-1];
		System.out.println("表名 : "+tableName);
		sb.append(tableName).append(" (");
		
		for(Method m:ms){
			String mName = m.getName();
			if(mName.startsWith("get") && !mName.startsWith("getClass")){//将所有get开头的方法取出来
				String colName = mName.substring(3,mName.length());
				sb.append(colName+",");
				Class returnType = m.getReturnType();
				System.out.print(mName+"方法的返回值是"+returnType.getName()+" \n");
				
				try {
					if(returnType == String.class){
						String p=  (String)m.invoke(obj);
						if(p!=null) va.append("'"+p+"',");
					}else if(returnType==long.class || returnType==int.class ){
						long p = (long)m.invoke(obj);
						va.append(p+",");
					}else if( returnType==Integer.class){
						Integer p = (Integer)m.invoke(obj);
						if(p!=null) va.append(p+",");
					}else if( returnType==java.util.Date.class){
						Date p = (Date)m.invoke(obj);
						StringBuffer pp = new StringBuffer(p.toLocaleString());
						if(p!=null) va.append("'"+pp.delete(pp.length()-9,pp.length())+"',");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		sb.delete(sb.length()-1,sb.length());
		va.delete(va.length()-1,va.length());
		sb.append(")");
		va.append(")");
		
		
		String sql = sb.toString()+va.toString();
		System.out.println("sql:"+sql);
		Mysql db = new Mysql();
		return db.updSQL(sql);
	}
	/**
	 * 根据类名字和一个属性名和值，来获取一行记录对应的对象
	 * 	对象的类必须要有无参构造器
	 * @param className 类路径
	 * @param perproty  属性名
	 * @param value  属性值
	 * @param IntOrString 整型是true 否则是false
	 * @return Object 对象
	 */
	public static Object QueryOneObject(String className,String perproty,String value,boolean IntOrString){
		Object obj = null;
		String tableName = className.split("\\.")[className.split("\\.").length-1];
		System.out.println("表名 : "+tableName);
		
		Class class1 = null;
		try {
			class1 = Class.forName(className);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//拼接SQL语句
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ").append(tableName).append(" where ")
		.append(perproty).append("=");
		if(IntOrString){
			sb.append("").append(value).append("");
		}else{
			sb.append("'").append(value).append("'");
		}
		
		String sql = sb.toString();
		ResultSet rs = null;
		Mysql db = null;
		System.out.println("查询的SQL语句是 ： "+sql);
		try {
			db = new Mysql();
			rs = db.SelectAll(sql);
			Method ms[] = class1.getMethods();
			if(rs.next()){
				obj = class1.newInstance();
				
				for (Method m :ms){//获取所有方法
					String mName = m.getName();
					if(mName.startsWith("set")){//将所有set开头的方法取出来
						//根据方法名字自动提取表中对应的列名
						String cname = mName.substring(3,mName.length());
						//打印所有 set 的方法名
						System.out.print("从set方法名中取到的列名："+cname);
						//得到方法的参数类型
						Class [] params = m.getParameterTypes();
						
						System.out.print(" : "+rs.getString(cname)+"\n");
						//根据相应的类型来给对象赋值
						if(params[0] == String.class){
							m.invoke(obj,rs.getString(cname));
						}else if(params[0] == int.class){
							m.invoke(obj,rs.getInt(cname));
						}else if(params[0] == long.class){
							m.invoke(obj,rs.getLong(cname));
						}else if(params[0] == Date.class){
							m.invoke(obj,rs.getDate(cname));
						}
					}
				}
			}else{
				System.out.println("请注意 ："+perproty+" = "+value+"的条件  没有查询到数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.closeAll();
		}
		return obj;
	}
}
