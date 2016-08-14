package cn.hibernate.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 去掉注释，切记要导JAR驱动包，还有配置好URL
 * @author  Myth
 * @date 2016年8月11日 下午10:39:08
 * @TODO 封装好的MysqL操作类，对外开放了三个方法，便于简化代码
 */
public class Mysql {

	static int count = 0;
	PreparedStatement ps = null;
	Connection cn = null;
	ResultSet rs = null;
	
	String Driver = "com.mysql.jdbc.Driver";
	String URL="jdbc:mysql://localhost:3306/student?user=root&password=ad&userUnicode=true&characterEncoding=UTF8";

	/*public static void main(String []a){
		Mysql db = new Mysql();
		ResultSet rs = db.SelectAll("select * from excel");
		try {
			while(rs.next()){
				System.out.println("a="+rs.getString("a"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.closeAll();
		}
	}*/
/**查询全部的操作 返回值是ResultSet 切记使用完后要finally关闭*/
	public ResultSet SelectAll(String sql){
		count++;
		System.out.println(sql);
		try {
			Class.forName(Driver);
			cn = DriverManager.getConnection(URL);
			ps=cn.prepareStatement(sql);
			
			rs=ps.executeQuery();
		} catch (Exception e) {
		}finally {
		}
		System.out.println("做了"+count+"次查询操作");
		return rs;
	}
/**把增删改 合在一起 返回值是 布尔值 已经关闭了不用再次关闭了*/
	public boolean updSQL(String sql){
		boolean flag = true;
		try{
			Class.forName(Driver);
			cn = DriverManager.getConnection(URL);
			
			ps=cn.prepareStatement(sql);
			int i=ps.executeUpdate();
			System.out.print("    增删改查成功_"+i+"_行受影响-->");
			if(i!=1){
				flag=false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("增删改查失败");
			flag=false;
		}finally {
			this.closeAll();
		}
		return flag;
	}
	/**关闭数据库资源*/
	public void closeAll(){
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(cn!=null) cn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		System.out.println("正常-关闭资源");
	}
}
