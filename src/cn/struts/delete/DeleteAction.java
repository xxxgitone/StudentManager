package cn.struts.delete;


import com.myth.mysql.Mysql;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 只要赋予主键的值就直接删除记录，而且是级联的操作，
 * 	需要前端有确认框，那么就还是需要一个查询(放弃实现了)
 * @author  Myth
 * @date 2016年9月8日 上午7:44:06
 * @TODO
 */
@SuppressWarnings("serial")
public class DeleteAction extends ActionSupport {

	//private HttpServletResponse response = ServletActionContext.getResponse();
	long sno,tno,ano;
	String aid,cid,mid,cno,mname;
	
	@Override
	public String execute() throws Exception {
		return super.execute();
	}
	
	public static void main(String[] args) {
		DeleteAction da = new DeleteAction();
		da.sno = 2025;
		da.deleteStudent();
	}
//要特别注意那种有着依赖的表需要先查询是否被引用了，直接删除就会报错的
//但是也可以直接删除，建立级联操作，需要前端弹出确认框
	public void deleteStudent(){
		String sql = "delete from student where sno ="+sno;
		delete(sql);
	}
	public void deleteTeacher(){
		String sql = "delete from teacher where tno ="+tno;
		delete(sql);
	}
	public void deleteAssitant(){
		String sql = "delete from assitant where ano ="+ano;
		delete(sql);
	}
	public void deleteManager(){
		String sql = "delete from manager where mname ='"+mname+"'";
		delete(sql);
	}
	public void deleteCourse(){
		String sql = "delete from course where cno = '"+cno+"'";
		delete(sql);
	}
	public void deleteAcademy(){
		String sql = "delete from academy where aid = '"+aid+"'";
		delete(sql);
	}
	public void deleteMajor(){
		String sql = "delete from major where mid ='"+mid+"'";
		delete(sql);
	}
	public void deleteClasss(){
		String sql = "delete from classs where cid ='"+cid+"'";
		delete(sql);
	}
	//删除操作
	public void delete(String sql){
		//DBUtils.delete(new Student(21));//数据库中非空的字段都必须给初始化。。。
		Mysql db = new Mysql();
		boolean flag = db.updSQL(sql);
		System.out.println("删除的sql : "+sql);
		sendJSON(flag);
	}
	public void sendJSON(boolean flag){
		String json = "";
		if(flag){
			json = "1";
		}else{
			json="0";
		}
		System.out.println("删除    JSON : "+json);
//		try {
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().write(json);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	//级联操作的专用函数，先查询到返回弹窗得到确认
//	public void deleteCascadeSendJSON(String sql,String confirm){
//		Mysql db = new Mysql("student","root","ad");
//		List<String[]> result = db.SelectReturnList(confirm);
//		boolean flag = true;
//		if ("0".equals(result.get(0)[0])){
//			flag = db.updSQL(sql);
//		}else{
//			flag = false;
//		}
//		
//		String json = "";
//		if(flag){
//			json = "{success:true}";
//		}else{
//			json="{success:false}";
//		}
//		System.out.println("JSON : "+json);
////		try {
////			response.setCharacterEncoding("UTF-8");
////			response.getWriter().write(json);
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
//	}
	public long getSno() {
		return sno;
	}
	public void setSno(long sno) {
		this.sno = sno;
	}
	public long getTno() {
		return tno;
	}
	public void setTno(long tno) {
		this.tno = tno;
	}
	public long getAno() {
		return ano;
	}
	public void setAno(long ano) {
		this.ano = ano;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
}
