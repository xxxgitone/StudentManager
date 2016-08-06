package cn.hibernate.beans;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author  Myth
 * @date 2016年8月5日 下午8:15:01
 * @TODO 将指定数据库下所有的表都生成持久类
 */
public class Table2Class{
	static String smalltable = "";//小写的表名
	static String database="",user="",pass="",table="";
	static String Driver = "com.mysql.jdbc.Driver";
	static String URL ="";
	PreparedStatement ps = null;
	Connection cn = null;
	ResultSet rs = null;
	static List<String> codes = null;
	static List<table> tableInfo = null;
	
	public Table2Class(String db,String id,String pa){
		database = db;
		user = id;
		pass = pa;
		URL ="jdbc:mysql://localhost:3306/"+database+"?user="+user+"&password="+pass
				+"&userUnicode=true&characterEncoding=UTF8";
	}
	public Table2Class(String db,String id,String pa,String ta){
		this(db, id, pa);
		table = ta;
	}
	
	/**
	 * 配置好包名，数据库参数就可以用了
	 */
	static String packageName="cn.hibernate.beans"; 
	public static void main(String []d){
		Table2Class test = new Table2Class("student","root","ad","student");
		List<String>tables = test.getTables();
		for(int i=0;i<tables.size();i++){
			table = tables.get(i);//获取表名
			smalltable = table;
			//表名首字母大写
			table = table.substring(0,1).toUpperCase()+table.substring(1,table.length());
			tableInfo = getTableList();
			codes = showCode(tableInfo);
			CreateClass();
		}
		
		/**手动输入单表，来测试*/
//		new Table2Class("student","root","ad","assitant");
//		smalltable = table;
//		table = table.substring(0,1).toUpperCase()+table.substring(1,table.length());
//		
//		tableInfo = getTableList();
//		//Display(tableInfo);//未转，是Mysql的数据类型
//		codes = showCode(tableInfo);
		//Display(tableInfo);//转后，是Java数据类型
		//
		/*for(int i=0;i<codes.size();i++){
			System.out.print(codes.get(i));
		}*/
//		CreateClass();
		//CreateXml(tableInfo); 
	}
	/**
	 * @TODO 生成持久类
	 */
	public static void CreateClass(){
		String name = table+".java";
		List2File(codes, name);
		System.out.println("转换了 "+database+" 下的 "+table+" 持久类成功");
	}

	/*public static void CreateXml(List<table> Info){
		List<String>files = new ArrayList<String>();
		files.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		files.add("<!DOCTYPE hibernate-mapping PUBLIC\n\t\"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"\n\t");
		files.add("\"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\"\n>\n");
		files.add("<hibernate-mapping>\n");
		files.add("\t<class name=\""+packageName+"."+table+"\" table=\""+smalltable+"\" catalog=\""+database+"\">\n");
		if("int".equals(Info.get(0).type)){
			files.add("\t\t<id name=\""+Info.get(0).name+"\" type=\"java.lang.Integer\" >\n");
			files.add("\t\t\t<column name=\""+Info.get(0).name+"\"/>\n");
			files.add("\t\t\t<generator class=\"identity\"></generator>\n");
		}else {
			files.add("\t\t<id name=\""+Info.get(0).name+"\" type=\""+Info.get(0).type+"\" >\n");
			files.add("\t\t\t<column name=\""+Info.get(0).name+"\" length=\"20\" />\n");
			files.add("\t\t\t<generator class=\"assigned\"></generator>\n");
		}
		files.add("\t\t</id>\n");
		for(int i=1;i<Info.size();i++){
			files.add("\t\t<property name=\""+Info.get(i).name+"\" type=\""+Info.get(i).type+"\">\n");
			files.add("\t\t\t<column name=\""+Info.get(i).name+"\" length=\"20\" />\n");
			files.add("\t\t</property>\n");
		}
		
		files.add("\t</class>\n");
		files.add("</hibernate-mapping>\n");
		
		DisPlayList(files);
	}*/
	/**
	 * 将List代码输出到指定package下
	 * @param file
	 * @param fileName 给定生成的文件的文件名
	 */
	public static void List2File(List<String> file,String fileName){
		BufferedWriter bw = null;
		OutputStream out = null;
		OutputStreamWriter os = null;
		String packages = packageName.replace(".", "/");//置换
		try {
			out = new FileOutputStream("./src/"+packages+"/"+fileName);
			os = new OutputStreamWriter(out);
			bw = new BufferedWriter(os);
			
			for(int i=0;i<file.size();i++){
				bw.write(file.get(i));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("IO有异常");
		}finally {
			try {
				bw.close();
				os.close();
				out.close();
			}catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("资源关闭异常");
			}
		
		}
		
	}
	
	/**
	 * 获取数据库下所有表的信息
	 * @return
	 */
	public static List<table> getTableList(){
		Table2Class r = new Table2Class(database,user,pass,table);
		List<table> tables = new ArrayList<table>();
		ResultSet rs = r.SelectAll("desc "+table);
		
		try {
			while(rs.next()){
				//System.out.println(rs.getString(1));
				table tb = new table(rs.getString(1),rs.getString(2));
				tables.add(tb);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			r.closeAll();
		}
//		for(int i=0;i<tables.size();i++){
//			System.out.println(tables.get(i).name+"::::"+tables.get(i).type);
//		}
		return tables;
	}
	/**
	 * @param T 表的集合，名称和数据类型
	 * @return 当前表对应的持久类所有代码的集合
	 */
	public static List<String> showCode(List<table>T){
//		for(int j=0;j<T.size();j++){
//			System.out.println(T.get(j).type+":"+T.get(j).name);
//		}
		
		List<String>codesList = new ArrayList<String>();
//		System.out.println("package "+packageName+";");
			codesList.add("package "+packageName+";\n\n");
//		System.out.println("public class "+table+"{");
			codesList.add("public class "+table+" {\n");
		/***/
		for(int i=0;i<T.size();i++){
			String types = "";
			String type = T.get(i).type;
			String name = T.get(i).name;
			
			if("int".equals(type.substring(0, 3))){
				types = "int";
			}else if("var".equals(type.substring(0, 3)) || "cha".equals(type.substring(0,3))){
				types = "String";
			}else if("date".equals(type.substring(0, 4))){
				types = "java.sql.Date";
			}else if("fl".equals(type.substring(0, 2))){
				types = "float";
			}else if("do".equals(T.get(i).type.substring(0, 2))){
				types = "double";
			}
			
			T.get(i).type = types;
			T.get(i).name = name;
//			System.out.println("    "+types+" "+name+";");
				codesList.add("    "+types+" "+name+";\n");
		}
//		System.out.println();
			codesList.add("\n");
//		System.out.println("    public "+table+"(){}");
			codesList.add("    public "+table+"(){}\n");
		String Method="    public "+table+"(";
		boolean flag = true,flag2=true;
		for(int i=0;i<T.size();i++){
			flag2=true;
			Method+=T.get(i).type+" "+T.get(i).name+", ";
			//调整构造器代码长度，适当换行
			if(Method.length()>80 && flag) {Method+="\n            ";flag=false;flag2=false;}
		}
		Method = Method.substring(0,Method.length()-2);
		if(!flag2) Method = Method.substring(0,Method.length()-13);
		Method +="){";
		for(int i=0;i<T.size();i++){
			Method+="\n\tthis."+T.get(i).name+" = "+T.get(i).name+";";
		}
		Method+="\n    }\n";
//		System.out.print(Method);
			codesList.add(Method);
//		System.out.println();
			codesList.add("\n");
		for(int i=0;i<T.size();i++){
			String types = T.get(i).type;
			String name = T.get(i).name;
			//锟斤拷锟斤拷母转锟斤拷写
			String names = T.get(i).name.substring(0,1).toUpperCase()+T.get(i).name.substring(1,T.get(i).name.length());
			/** 生成SetGet方法*/
//			System.out.println("    public "+types+" get"+names+"(){\n\treturn "+name+";\n    }");
				codesList.add("    public "+types+" get"+names+"(){\n\treturn "+name+";\n    }\n");
//			System.out.println("    public void set"+names+"("+types+" "+name+"){\n\tthis."+name+" = "+name+";\n    }");
				codesList.add("    public void set"+names+"("+types+" "+name+"){\n\tthis."+name+" = "+name+";\n    }\n");
		}
		
//		System.out.println("}");
			codesList.add("}\n");
		return codesList;
	}
	
	public ResultSet SelectAll(String sql){
		try {
			Class.forName(Driver);
			cn = DriverManager.getConnection(URL);
			ps=cn.prepareStatement(sql);
			
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void closeAll(){
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(cn!=null) cn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}
	/**
	 * 查询指定数据库下所有表名
	 * @return 表名集合
	 * 
	 */
	public List<String> getTables(){
		List<String>tables = new ArrayList<String>();
		try {
			Class.forName(Driver);
			cn = DriverManager.getConnection(URL);
			cn.prepareStatement(" use "+database+"").execute();
			ps=cn.prepareStatement( "show tables");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				tables.add(rs.getString(1));
			}
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return tables;
	}
	public static void Display(List<table> h){
		for (int i=0;i<h.size();i++){
			System.out.println(h.get(i).toString());
		}
	}
	public static void DisPlayList(List<String> lists){
		for(int i=0;i<lists.size();i++){
			System.out.print(lists.get(i));
		}
	}
}

class table{
	String name;
	String type;
	public table(String name,String type){
		this.name = name;
		this.type = type;
	}
	@Override
	public String toString() {
		return "table [name=" + name + ", type=" + type + "]";
	}
}