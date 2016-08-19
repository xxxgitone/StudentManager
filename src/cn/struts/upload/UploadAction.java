package cn.struts.upload;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

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
		
		return "success";
	}
	//无效测试方法
	public String saveFiles()throws Exception{
		System.out.println("upload ================ save");
		
		return "success";
	}
}
