package cn.utils.test;
/**
 * 测试正则的使用
 * @author  Myth
 * @date 2016年9月26日 上午10:48:32
 * @TODO
 */
public class TestString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string = "http://localhost:8080/SMS/MainPage.jsp?uuu=ioio";
		String ss[] = string.split("/");
		
		for (int i=0;i<ss.length;i++){
			System.out.println(i+":"+ss[i]);
		}
//		ss = ss[ss.length-1].split("(.jsp+$)");
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
