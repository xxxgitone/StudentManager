package cn.struts.upload;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.hibernate.utils.ReadExcel;

import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class UploadAction extends ActionSupport {
	
	//上传文件的存储的临时文件：
	private File uploadImage;
	//上传文件的类型：text/plain
	private String uploadImageContentType;
	//上传文件的真实名称
	private String uploadImageFileName;
	private ReadExcel re = null;
	private String year;
	private int term;
	private int sheet;
	
	/**
	 * 这是上传在了项目运行环境即Tomcat下，也可以放在工程目录下，
	 * @return
	 */
	public String saveFile(){
		System.out.println("UploadAction *********** saveFile()");
		//System.out.println("_"+uploadImage+"_"+uploadImageContentType+"_"+uploadImageFileName+"_");
		ServletContext sc = ServletActionContext.getServletContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String types = request.getParameter("types");
		sheet = Integer.parseInt(request.getParameter("sheet"));
		System.out.println("参数 Sheet："+sheet);
		if(uploadImage==null || uploadImageContentType==null || uploadImageFileName==null){
			sc.setAttribute("uperror", "NullFile");
			sc.setAttribute("type", types);
			return "Upload";
		}
		year = (String)sc.getAttribute("years");
		String terms = (String)sc.getAttribute("term");
		if(terms!=null) term = Integer.parseInt(terms);
		else term=1;
		String path = sc.getRealPath("/fileupload");//得到运行环境路径
//		System.out.println(path);
	    
		File file = new File(path, uploadImageFileName);
		try {
			FileUtils.copyFile(uploadImage, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		uploadImage.delete();//删除缓存文件
		//以上是文件的写入
		System.out.println("插入的表格:"+types);
		System.out.println("参数 year:"+year+" term:"+term);
		//执行插入数据库的具体方法
		String result;
		try{
			if("grade".equals(types)){//期末成绩
				result =  ToGrade(sc,request);
			}else if("makeup".equals(types) || "ultimate".equals(types)){//补考 清考
				result =  ToBuOrQing(sc,request,types);
			}else if("kb".equals(types)){//课表
				result = Tokb(sc);
			}else if("classs".equals(types)||"major".equals(types)){//专业 班级
				result = ToMajorOrClass(sc, types, request);
			}else if("grades".equals(types)){
				result = ToGrades(sc,request);
			}else {// 学院，课程，教师，学生，辅导员
				result = ToSimpleOne(sc, types);
			}
			f5(sc);
			System.out.println("没有异常");
			return result;
		}catch(Exception s){
			//从读取Excel然后执行插入动作都将异常上抛，这是最终的捕获的地方，为了统一利于重定向
			//s.printStackTrace();
			System.out.println("进入了catch");
			sc.setAttribute("uperror", "InsertError");
//			request.setAttribute("uperror", "InsertError");
			sc.setAttribute("type", types);
			return "input";
		}
		
//		return "Upload";
	}
	public String ToGrades(ServletContext sc,HttpServletRequest request) throws Exception{
		System.out.println("进入输入不规则成绩函数");
		//得到对应的学院，专业，班级，如果不存在会新建（按顺序得到编号）
		String year = request.getParameter("year");
		int term = Integer.parseInt(request.getParameter("term"));
		String classs = request.getParameter("classs");
		String major = request.getParameter("major");
		String academy = request.getParameter("academy");
		
		System.out.println("");
		re = new ReadExcel(sheet);//读取Sheet
		re.ToGrades(uploadImageFileName,academy,major, classs, year, term);
		System.out.println("不规则的成绩表输入无异常！"); 
		return "Main";
	}
	/**期末成绩*/
	public String ToGrade(ServletContext sc,HttpServletRequest request) throws Exception{
		System.out.println("进入 成绩 函数");
		String course = request.getParameter("course");
		re = new ReadExcel(sheet);
		re.ToGrade(uploadImageFileName, course, year, term);
		System.out.println("无异常！");
//		sc.setAttribute("div", "XSCJ");
		return "Main";
	}
	/**补考成绩 清考成绩*/
	public String ToBuOrQing(ServletContext sc,HttpServletRequest request,String type) throws Exception{
		System.out.println("补考");
		String course = request.getParameter("course");
		//执行插入动作
		re = new ReadExcel(sheet);
		re.ToBuOrQing(uploadImageFileName, course, type);
		//为了回到展示的DIV
		if("makeup".equals(type))sc.setAttribute("div", "BK");
//		else sc.setAttribute("div", "QK");
		return "Main";
	}
	
	/**录入需要上的课的信息*/
	public String Tokb(ServletContext sc) throws Exception{
		System.out.println("方法获取到的"+year+term);
		ReadExcel re = new ReadExcel(sheet);
		re.ToObligatory(uploadImageFileName, year, term);
//		sc.setAttribute("div", "KB");
		return "Main";
	}
	
	/**
	 * excel和数据库表格一一对应的函数
	 * @param sc
	 * @param table
	 */
	public String ToSimpleOne(ServletContext sc,String table) throws Exception{
//		System.out.println("0"+table+" file name "+uploadImageFileName);
		ReadExcel re = new ReadExcel(sheet);
//		System.out.println("1"+table);
		re.ToSimpleTable(uploadImageFileName, table);
		if("academy".equals(table)) table="XXJG";
//		sc.setAttribute("div", table);
		System.out.println("一对一写入的参数：");
		return "Main";
	}
	/**录入专业 班级信息*/
	public String ToMajorOrClass(ServletContext sc,String table,HttpServletRequest request) throws Exception{
		String upID = request.getParameter("upid");
		if(upID==null){
			sc.setAttribute("uperror", "NullID");
			sc.setAttribute("type", table);
			return "Upload";
		}
		System.out.println("id "+upID);
		ReadExcel re = new ReadExcel(sheet);
		re.ToMajorOrClasss(uploadImageFileName, table, upID);
//		sc.setAttribute("div", "XXJG");
		return "XXJG";
	}
	/**刷新框架的父页面，有效的解决了问题*/
	public void f5(ServletContext sc){
		sc.setAttribute("f5", "f5");
		sc.setAttribute("success", "up");
	}
////////////////////////////////////////////////////////////////////////////////////////
	public File getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}

	public String getUploadImageContentType() {
		return uploadImageContentType;
	}

	public void setUploadImageContentType(String uploadImageContentType) {
		this.uploadImageContentType = uploadImageContentType;
	}

	public String getUploadImageFileName() {
		return uploadImageFileName;
	}

	public void setUploadImageFileName(String uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}
}
