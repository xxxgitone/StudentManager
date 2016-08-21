package cn.struts.upload;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.hibernate.utils.ExcelToDB;
import cn.hibernate.utils.ReadExcel;

import com.opensymphony.xwork2.ActionSupport;


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
	
	/**
	 * 这是上传在了项目运行环境即Tomcat下，也可以放在工程目录下，
	 * @return
	 */
	public String saveFile(){
		System.out.println("UploadAction *********** saveFile()");
		
		ServletContext sc = ServletActionContext.getServletContext();
		//System.out.println("上传："+sc);
		String path = sc.getRealPath("/fileupload");//得到运行环境路径
		System.out.println(path);
//		path = UploadAction.class.getClass().getResource("/").getPath();
//		System.out.println(path);
//	    path = path.substring(1,path.length()-4);
//	    path += "excel/";
	    
		File file = new File(path, uploadImageFileName);
		try {
			FileUtils.copyFile(uploadImage, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		uploadImage.delete();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String year = (String)sc.getAttribute("years");
		String terms = (String)sc.getAttribute("term");
		int term;
		if(terms!=null) term = Integer.parseInt(terms);
		
		String types = request.getParameter("types");
//		System.out.println(types);
		if("grade".equals(types)){//期末成绩
			return ToGrade(sc,request);
		}else if("makeup".equals(types) || "ultimate".equals(types)){//补考 清考
			return ToBuOrQing(sc,request,types);
		}else if("kb".equals(types)){//课表
			return Tokb(sc);
		}else if("classs".equals(types)||"major".equals(types)){//专业 班级
			return ToMajorOrClass(sc, types, request);
		}else {// 学院，课程，教师，学生，辅导员
			System.out.println("other");
			return ToSimpleOne(sc, types);
		}
		
//		return "Upload";
	}
	/**期末成绩*/
	public String ToGrade(ServletContext sc,HttpServletRequest request){
		System.out.println("成绩");
		String course = request.getParameter("course");
		re = new ReadExcel();
		re.ToGrade(uploadImageFileName, course, year, term);
		sc.setAttribute("div", "XSCJ");
		return "Grade";
	}
	/**补考成绩 清考成绩*/
	public String ToBuOrQing(ServletContext sc,HttpServletRequest request,String type){
		System.out.println("补考");
		String course = request.getParameter("course");
		//执行插入动作
		re = new ReadExcel();
		re.ToBuOrQing(uploadImageFileName, course, type);
		//为了回到展示的DIV
		if("makeup".equals(type))sc.setAttribute("div", "BK");
		else sc.setAttribute("div", "QK");
		return "Grade";
	}
	
	/**录入课表信息*/
	public String Tokb(ServletContext sc){
		ReadExcel re = new ReadExcel();
		re.ToObligatory(uploadImageFileName, year, term);
		return "Upload";
	}
	
	/**
	 * 一对一的函数
	 * @param sc
	 * @param table
	 */
	public String ToSimpleOne(ServletContext sc,String table){
		System.out.println("0"+table+" file name "+uploadImageFileName);
		ReadExcel re = new ReadExcel();
//		ReadExcelUtils.ToSimpleTable(uploadImageFileName, table);
		System.out.println("1"+table);
		re.ToSimpleTable(uploadImageFileName, table);
		
		return "Upload";
	}
	/**录入专业 班级信息*/
	public String ToMajorOrClass(ServletContext sc,String table,HttpServletRequest request){
		String upID = request.getParameter("upid");
		ReadExcel re = new ReadExcel();
		re.ToMajorOrClasss(uploadImageFileName, table, upID);
		return "Upload";
	}
	
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
