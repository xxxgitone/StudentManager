package cn.struts.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.myth.mysql.Mysql;
import com.myth.mysql.Table2Excel;
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
	String FileName;//文件名
	String path;//缓存文件的路径 在Tomcat下
	{
		sc = ServletActionContext.getServletContext();
		path = sc.getRealPath("/excel");//得到运行环境路径
	}
	//获取文件流
	public InputStream getDownloadFile(){
		System.out.println("执行了获取文件");
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack");//获取值栈
		
		CreateFile(filetype);//用户所下载文件的文件名
		try {
			FileName = new String(FileName.getBytes(),"ISO-8859-1");//将文件名进行转码
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		
//		System.out.println("转码后的："+fileName);
		vs.set("filename",FileName);//压入值栈，在xml文件中获取
		File file = new File(path);
		System.out.println("获取的路径："+path);
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(file);
		}catch(Exception e){
			e.printStackTrace();
		}
		return fis;
	}
	/**根据传入type来创建对应文件, 路径写成运行环境下的目录*/
	public void CreateFile(String type){
		FileName = "系统有异常.xls";
		Mysql db = new Mysql("student","root","ad");
		List<String[]> list = null;
		
		switch (type) {
			case "g_classs":{//某学年某学期某班级的成绩单
				list = db.SelectReturnList("select classs from classs where cid = '"+classs+"' ");
				String name = list.get(0)[0];//班级名称
				FileName = name+"班成绩单.xls";
				list = getData(db,classs);
				Table2Excel.ListToExcel(FileName, list, path);//单Sheet
				break;
			}
			case "g_major":{
				list = db.SelectReturnList("select major,cid from major m,classs c where c.mid=m.mid and m.mid = '"+major+"' ");
				String name = list.get(0)[0];
				List<List<String []>> data  = new ArrayList<List<String[]>>();
				
				for(int i=0;i<list.size();i++){
					list = getData(new Mysql("student","root","ad"),list.get(i)[1]);
					data.add(list);
				}
				FileName = name+"-各班级成绩单.xls";
				Table2Excel.ListToExcels(FileName, data, path);
				break;
			}
			/*case "p_all":{
				FileName = "所有人员信息表.xls";
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
				FileName = name+"班级课程表.xls";
				break;
			}
			case "o_major":{
				list = db.SelectReturnList("select major from major where mid = '"+major+"' ");
				String name = list.get(0)[0];
				FileName = name+"-各班级课程表.xls";
				break;
			}
			//下面的做成树结构查看或查询
			/*case "s_major":{
				if(academy!=null){
					list = db.SelectReturnList("select academy from major where aid = '"+academy+"' ");
					String name = list.get(0)[0];
					FileName = name+"-专业信息表.xls";
				}else{
					FileName = "所有专业信息表.xls";
				}
				
				break;
			}*/
			/*case "s_classs":{
				if(major!=null){
					list = db.SelectReturnList("select major from major where mid = '"+major+"' ");
					String name = list.get(0)[0];
					FileName = name+"-班级信息表.xls";
				}else{
					FileName = "所有班级信息表.xls";
				}
				
				break;
			}*/
			/*case "s_academy":{
				list = db.SelectReturnList("select academy from major where aid = '"+academy+"' ");
				String name = list.get(0)[0];
				FileName = name+"-学院信息表.xls";
				break;
			}*/
			default:
				break;
		}
	}
	/**
	 * 生成 某班级成绩单 返回list数据
	 * @param db 数据库对象
	 * @param cid 班级号
	 */
	public List<String[]> getData(Mysql db,String cid){
		
		
		String getCol="select o.cno,cname from obligatory o,course c where o.cno=c.cno and cid = '"+cid
				+"' and year='"+year+"' and term = "+term+" ";
		
		List<String[]> list = db.SelectReturnList(getCol);//查出某学期某班级 的 课程信息 后面需要调用
		int Nums = list.size();
		
		//查询出表格
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
		create +=" s.sno = col_0.sno and cid = '"+cid +"';";
//		System.out.println("SQL:-"+create+"-" );
		//db = new Mysql("student","root","ad");
		rs 已关闭的异常
		List<String []> table= db.SelectReturnList(create);//原始数据
//		System.out.println("原始数据 大小："+table.size());
//		ResultSet rs = db.SelectAll(create);
		List<String [] > data = new ArrayList<String[]>();//将输出到Excel的数据
		try {
			String ColName[] = new String[Nums+5];
			ColName[0] = "序号";ColName[1] = "学号";ColName[2] = "姓名";
			ColName[3] = "总学分";ColName[Nums+4] = "成绩总分";
			for (int i=0;i<list.size();i++){
				ColName[i+4] = list.get(i)[1];//填入名字,因为后面的拼凑出来的成绩单的课程顺序就是用的List的顺序
			}
			data.add(ColName);//加入表头
			int AllCol = table.get(0).length;
			for(int i=0;i<table.size();i++){//遍历所有查询到的数据
				String row [] = new String[Nums+5];
//				System.out.println("大小："+Nums);
				row[0] = (i+1)+"";
				row[1] = table.get(i)[0];
//				System.out.println("huoqu :"+i);
				row[2] = table.get(i)[1];
				int now = 3;
				float SumCredit = 0;
				float SumGrade = 0;
				for(int j=2;j<AllCol;j++){//遍历所有列
					if(j%2==0){ //将成绩写入
						String grades = table.get(i)[j];
						float grade = Float.parseFloat(grades);
						row[now] = grades;
						SumGrade+=grade;
						if(grade>=60){
							SumCredit += Float.parseFloat(table.get(i)[j+1]);
						}
						now++;
					}
					
				}
				row[now++] = SumCredit+"";
				row[now] = SumGrade+"";
				data.add(row);//包含进去
			}
			
			path+="\\"+FileName;
			System.out.println("路径："+path);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.closeAll();
		}
		return data;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("获取："+year+"-"+term+"-"+classs);
		return super.execute();
	}
//////////////////////////////////////////////////////setget
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
