package cn.struts.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.myth.mysql.Mysql;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
/**
 * 带了setget方法的属性会在URL链接中自动获取到值，如果没有就是null
 * @author  Myth
 * @date 2016年8月30日 下午8:55:44
 * @TODO 下载的action，依据参数来生成对应Excel文件再下载
 */
@SuppressWarnings("serial")
public class DownloadAction extends ActionSupport {
	String filetype;//这是十多种Excel的一个区分标记 也是服务器上文件名
	String year;//学年 在环境中有值
	String term;//学期
	String academy;//aid
	String major;//mid
	String classs;//cid
	ServletContext sc;
	String path;//缓存文件的路径 在Tomcat下
	{
		sc = ServletActionContext.getServletContext();
		path = sc.getRealPath("/download");//得到运行环境路径
	}
	//获取文件流
	public InputStream getFile(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack");//获取值栈
		String filename = TurnFileName(filetype);//用户所下载文件的文件名
		vs.set("filename",filename);//压入值栈，在xml文件中获取
		File file = new File(path, filename);
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(file);
		}catch(Exception e){
			e.printStackTrace();
		}
		return fis;
	}
	/**根据传入type来创建对应文件，返回文件名 路径写成运行环境下的目录*/
	public String TurnFileName(String type){
		String filename = "系统有异常.xls";
		Mysql db = new Mysql("student","root","ad");
		List<String[]> list = null;
		
		switch (type) {
			case "g_classs":{//某学年某学期某班级的成绩单
				list = db.SelectReturnList("select classs from classs where cid = '"+classs+"' ");
				String name = list.get(0)[0];//班级名称
				filename = name+"班成绩单.xls";
				// 0 cno 1 cname
				String getCol="select o.cno,cname from obligatory o,course c where o.cno=c.cno and cid = '"+classs
						+"' and year='"+year+"' and term = "+term+" ";
				list = db.SelectReturnList(getCol);//查出某学期某班级有多少课程
				//创建MAP
				//Map <String,String>Cname = new HashMap<String,String>();
				
				int Nums = list.size();
				
//				String sql[] = new String[Nums+5];
//				String ColName[] = new String[Nums+5];
				//列的名字初始化赋值
//				ColName[0] = "序号";ColName[1] = "学号";ColName[2] = "姓名";
//				ColName[3] = "总学分";ColName[Nums+4] = "成绩总分";
//				for (int i=0;i<list.size();i++){
//					ColName[i+4] = list.get(i)[1];//填入名字
//				}
				System.out.println("Nums = "+Nums);
				for (int i=0;i<list.size();i++){
					System.out.println(i+":"+list.get(i)[0]+":"+list.get(i)[1]);
				}
				
				String create = " select distinct col_0.sno,sname ,";
				for(int i=0;i<Nums;i++){
					create +="col_"+i+".grade c_"+list.get(i)[0]+",col_"+i+".credit cr_"+list.get(i)[0]+",";
				}
				create = create.substring(0, create.length()-1);
				create += " from ";
				String key = "and year='"+year+"' and term = "+term;
				for(int i=0;i<Nums;i++){
					create += "(select sno,grade,credit from mark,course where course.cno=mark.cno and mark.cno = '"
							+list.get(i)[0]+"' "+key+" ) col_"+i+" ,";
				}
				create +="student s where ";
				for(int i=0;i<Nums-1;i++){
					create +=" col_"+i+".sno=col_"+(i+1)+".sno and";
				}
				create +=" s.sno = col_0.sno and cid = '"+classs +"';";
				System.out.println("SQL:-"+create+"-" );
				List<String[]> data= db.SelectReturnList(create);//得到查询到的临时表
				
				break;
			}
			case "g_major":{
				list = db.SelectReturnList("select major from major where mid = '"+major+"' ");
				String name = list.get(0)[0];
				filename = name+"-各班级成绩单.xls";
				break;
			}
			/*case "p_all":{
				filename = "所有人员信息表.xls";
				String sql[] = new String [4];
				String title[] = new String [4];
				title[0] = "学生信息";
				title[1] = "教师信息";
				title[2] = "辅导员信息";
				title[3] = "管理员信息";
				sql[0] = "";
				sql[1] = "";
				sql[2] = "";
				sql[3] = "";
				Table2Excel.Runs(sql, title, path);
				break;
			}*/
			case "o_classs":{
				list = db.SelectReturnList("select classs from classs where cid = '"+classs+"' ");
				String name = list.get(0)[0];
				filename = name+"班级课程表.xls";
				break;
			}
			case "o_major":{
				list = db.SelectReturnList("select major from major where mid = '"+major+"' ");
				String name = list.get(0)[0];
				filename = name+"-各班级课程表.xls";
				break;
			}
			//下面的做成树结构查看或查询
			/*case "s_major":{
				if(academy!=null){
					list = db.SelectReturnList("select academy from major where aid = '"+academy+"' ");
					String name = list.get(0)[0];
					filename = name+"-专业信息表.xls";
				}else{
					filename = "所有专业信息表.xls";
				}
				
				break;
			}*/
			/*case "s_classs":{
				if(major!=null){
					list = db.SelectReturnList("select major from major where mid = '"+major+"' ");
					String name = list.get(0)[0];
					filename = name+"-班级信息表.xls";
				}else{
					filename = "所有班级信息表.xls";
				}
				
				break;
			}*/
			/*case "s_academy":{
				list = db.SelectReturnList("select academy from major where aid = '"+academy+"' ");
				String name = list.get(0)[0];
				filename = name+"-学院信息表.xls";
				break;
			}*/
			default:
				break;
		}
		return filename;
	}
//setget
	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClasss() {
		return classs;
	}
	public void setClasss(String classs) {
		this.classs = classs;
	}
}
