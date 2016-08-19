package cn.struts.upload;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {
	
	/*
	 * 上传文件的存储的临时文件：
	 * E:\\TOOLS\\apache-tomcat-6.0.35\\work\\Catalina\\localhost\\itcast1105_struts\\upload__5fee1dc7_13ad3d1835b__8000_00000000.tmp
	 */
	private File uploadImage;
	
	//上传文件的类型：text/plain
	private String uploadImageContentType;
	
	//上传文件的真实名称
	private String uploadImageFileName;
	
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

	/**
	 * 这是上传在了项目运行环境即Tomcat下，也可以放在工程目录下，
	 * @return
	 */
	public String saveFile(){
		System.out.println("UploadAction *********** saveFile()");
		
		ServletContext sc = ServletActionContext.getServletContext();
		String path = sc.getRealPath("/fileupload");//得到运行环境路径
		File file = new File(path, uploadImageFileName);
		try {
			FileUtils.copyFile(uploadImage, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		uploadImage.delete();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String types = request.getParameter("types");
		
		switch (types) {
		case "0":
			return ToGrade0(sc,request);
		case "1":
			return ToGrade1(sc,request);
		case "2":
			return ToGrade2(sc,request);
		case "course":
			return course(sc);
		case "kb":
			break;
		case "student":
			break;
		case "teacher":
			break;
		case "assitant":
			break;
		case "academy":
			break;
		case "major":
			break;
		case "classs":
			break;

		default:
			break;
		}
		
		return "success";
	}
	/**期末成绩*/
	public String ToGrade0(ServletContext sc,HttpServletRequest request){
		System.out.println("成绩");
		String course = request.getParameter("course");
		
		sc.setAttribute("div", "XSCJ");
		return "Grade";
	}
	/**补考成绩*/
	public String ToGrade1(ServletContext sc,HttpServletRequest request){
		System.out.println("补考");
		String course = request.getParameter("course");
		
		sc.setAttribute("div", "BK");
		return "Grade";
	}
	/**清考成绩*/
	public String ToGrade2(ServletContext sc,HttpServletRequest request){
		System.out.println("清考");
		String course = request.getParameter("course");
		
		sc.setAttribute("div", "QK");
		return "Grade";
	}
	/**录入课程信息*/
	public String course(ServletContext sc){
		return "Upload";
	}
	/**录入学生信息*/
	public String student(ServletContext sc){
		return "Upload";
	}
	/**录入课表信息*/
	public String kb(ServletContext sc){
		return "Upload";
	}
	/**录入教师信息*/
	public String teacher(ServletContext sc){
		return "Upload";
	}
	/**录入辅导员信息*/
	public String assitant(ServletContext sc){
		return "Upload";
	}
	/**录入学院信息*/
	public String academy(ServletContext sc){
		return "Upload";
	}
	/**录入专业信息*/
	public String major(ServletContext sc){
		return "Upload";
	}
	/**录入班级信息*/
	public String classs(ServletContext sc){
		return "Upload";
	}
	
}
