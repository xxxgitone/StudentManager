package cn.struts.download;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.myth.mysql.Mysql;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

@SuppressWarnings("serial")
public class DownloadAction extends ActionSupport {
	String filetype;//参数自动填充 这是十多种Excel的一个区分标记 也是服务器上文件名
	String year;
	String term;
	String academy;//aid
	String major;//mid
	String classs;//cid
	
	//获取文件流
	public InputStream getFile(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack");//获取值栈
		String filename = TurnFileName(filetype);//用户所下载文件的文件名
		vs.set("filename",filename);
		
		
		return null;
	}
	/**根据传入type来创建文件，返回文件名 路径写成运行环境下的目录*/
	public String TurnFileName(String type){
		String filename = "系统有异常.xls";
		Mysql db = new Mysql("student","root","ad");
		ResultSet rs = null;
		List<String[]> list = null;
		switch (type) {
		case "g_classs":{
			list = db.SelectReturnList("select classs from classs where cid = '"+classs+"' ");
			String name = list.get(0)[0];
			filename = name+"班成绩单.xls";
			break;
		}
		case "g_major":{
			list = db.SelectReturnList("select major from major where mid = '"+major+"' ");
			String name = list.get(0)[0];
			filename = name+"-各班级成绩单.xls";
			break;
		}
		case "p_all":{
			filename = "所有人员信息表.xls";
			break;
		}
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
		case "s_major":{
			if(academy!=null){
				list = db.SelectReturnList("select academy from major where aid = '"+academy+"' ");
				String name = list.get(0)[0];
				filename = name+"-专业信息表.xls";
			}else{
				filename = "所有专业信息表.xls";
			}
			
			break;
		}
		case "s_classs":{
			if(major!=null){
				list = db.SelectReturnList("select major from major where mid = '"+major+"' ");
				String name = list.get(0)[0];
				filename = name+"-班级信息表.xls";
			}else{
				filename = "所有班级信息表.xls";
			}
			
			break;
		}
		case "s_academy":{
			list = db.SelectReturnList("select academy from major where aid = '"+academy+"' ");
			String name = list.get(0)[0];
			filename = name+"-学院信息表.xls";
			break;
		}
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
