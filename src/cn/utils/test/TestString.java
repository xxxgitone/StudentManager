package cn.utils.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.myth.mysql.Mysql;


/**
 * 测试正则的使用
 * @author  Myth
 * @date 2016年9月26日 上午10:48:32
 * @TODO
 */
public class TestString {

	public static void main(String[] args) {
		//testString();
		//testMap();
		//testSet();
		testFloat();
	}
	//测试float查询时是可以用整数来查询的
	public static void testFloat(){
		Mysql db = new Mysql();
		List<String[]> data = db.SelectReturnList("select practicehour from course where practicehour=32");
		System.out.println(data.get(0)[0]);
	}
	//测试set
	public static void testSet(){
		Set<String> set = new HashSet<String>();
		for(int i=0;i<100000;i++){
			set.add(i+"");
		}
		System.out.println(""+set.contains("8"));
		
	}
	//测试map
	public static void testMap(){
		Map<String,String> map = new HashMap<String,String>();
		for(int i=0;i<100000;i++){
			map.put(""+i, "value:"+i);
		}
		System.out.println("查询结果："+map.get("99999"));
	}
	//测试字符串的正则表达式
	public static void testString (){
		String string = "http://localhost:8080/SMS/MainPage.jsp?uuu=ioio";
		String ss[] = string.split("/");
		
		for (int i=0;i<ss.length;i++){
			System.out.println(i+":"+ss[i]);
		}
//				ss = ss[ss.length-1].split("(.jsp+$)");
		ss = ss[ss.length-1].split("\\.");
		ss = ss[ss.length-1].split("\\?");
		System.out.println("长度："+ss.length);
		for (int i=0;i<ss.length;i++){
			System.out.println(i+":"+ss[i]);
		}
		if("jsp".equals(ss[0])){
			
			System.out.println("是jsp");
		}else{
			System.out.println("不是jsp");
		}
	}

}
