package cn.hibernate.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;

import cn.myth.reflect.Config;

import com.myth.mysql.Mysql;


/**
 * 对之前的进行修改，做成工具类，实现输入表名，文件名，插入到数据库
 * 注意这里对数字的获取是默认使用的实型
 * @author  Myth
 * @date 2016年8月18日 下午9:51:31
 * @TODO
 */
public class ReadExcel {

	private POIFSFileSystem fs;      
    private HSSFWorkbook wb;      
    private HSSFSheet sheet;      
    private HSSFRow row; 
    private InputStream is =null;
    private int AllRows;//内容的行数 1
    private int AllCols;//内容的列数 1
    private int startRow=2;//内容起始行 除去表的列名
    private int sheetNum;//最好实现一个机制，自动去获取后面的Sheet（如果有数据的话）
    private int currentSheet=0;//使用的Sheet,数组下标表示法
    private PreparedStatement ps = null;
	private Connection cn = null;
	private ResultSet rs = null;
	private String Driver ;
	private String URL;
	private Map<Position,String> map = null ;//要注意：这里的Map采用的是自然的行号和列号（1开头）
    private String[] title ;
    
    private String path;
    
	/*public static void main(String [] s){
	    ReadExcel ed = new ReadExcel();
	    try {
			ed.ToSimpleTable("Student.xls","student");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//一对一的插入记录
	}*/
    //默认读取0的Sheet
	public ReadExcel(){
		Config con = new Config("mysql.properties");
		this.Driver = con.getString("Driver");
		this.URL = con.getString("URL");
	}
	public ReadExcel(int sheet){
		Config con = new Config("mysql.properties");
		this.Driver = con.getString("Driver");
		this.URL = con.getString("URL");
		this.currentSheet = sheet;
	}
	/**
	 * 将不规则的Excel表插入数据库，第二次插入时要将第二次要插入的课程成绩相应的清空（指定学年学期，不是直接删课程那个基本表）
	 * @param fileName
	 * @param classs
	 * @param year
	 * @param term
	 * @throws Exception
	 */
	public void ToGrades(String fileName,String classs, String year,  int term)throws Exception{
		ReadFile(fileName);
		
		Mysql db = new Mysql();
		List<String[]> dataCache = null;//多次查询数据库的结果暂存集合
//		Set<String> courseSet = new HashSet<String>();
		Map<String,String> courseMap = new HashMap<String,String>();//数据库中课程
		Set<String> studentSet = new HashSet<String>();//数据库中学生
		List<String[]>courseE = new ArrayList<String[]>();//Excel中课程
		List<String[]>studentE = new ArrayList<String[]>();//Excel中学生
		Set<String> upstuE = new HashSet<String>();//不是新增的那些修改的学生
		Set<String> upcouE = new HashSet<String>();//不是新增的那些修改的课程
		List<String>ColCno = new ArrayList<String>();//存放了Excel中顺序下每个课程的课程号
		int Currentcno;
		
		/**用上了事务，因为保证其表格的统一性和原子性*/
		try {
			Class.forName(Driver);
			cn = DriverManager.getConnection(URL);
			cn.setAutoCommit(false);//取消自动提交
//			System.out.println("正文大小"+map.size());
			for(int i=1;i<=AllRows;i++){
				for(int j=1;j<=AllCols;j++){
					System.out.print("("+i+","+j+")"+map.get(new Position(i,j))+"--");
				}
				System.out.println();
			}
			System.out.println(AllRows+"*"+AllCols+":"+currentSheet+":c"+classs);
			String sql;
			
			//读取数据库中课程
			dataCache = db.SelectReturnList("select cname,cno  from course where cinfo='"+year+"' and practicehour="+term);
			for(int i=0;i<dataCache.size();i++){
//				courseSet.add(dataCache.get(i)[0]);
				courseMap.put(dataCache.get(i)[0], dataCache.get(i)[1]);
			}
//			System.out.println("正文大小"+map.size());
			//读取Excel课程
			for(int i=5;i<=AllCols;i++){
				String course = map.get(new Position(1,i));
				courseE.add(course.split("/"));
//				String[] dd= course.split("/");
//				System.out.println(i+"-kecheng:"+map.get(new Position(1,i))+"-");
//				for(String d:dd){
//					System.out.print("##"+d+"##");
//				}
//				System.out.println();
			}
			//添加课程
			dataCache = db.SelectReturnList("select max(cno) from course");
			if(dataCache.size()!=0)Currentcno = Integer.parseInt(dataCache.get(0)[0]);
			else Currentcno = 10001;//若没有课程则从10001开始计数
			for(int i=0;i<courseE.size();i++){
				if(!courseMap.containsKey(courseE.get(i)[0])){//Excel中课程是数据库所没有的就插入
					Currentcno++;
					String[] cinfo = courseE.get(i);
					//sql = "insert into course(cno,cname,credit,ctype,practicehour,cinfo)values('"
							//+Currentcno+"','"+cinfo[0]+"','"+cinfo[2]+"','"+cinfo[1]+"',"+term+",'"+term+"') ";
					//插入到课程表中
					sql="insert into course(cno,cname,credit,ctype,practicehour,cinfo)values(?,?,?,?,?,?)";
					ps=cn.prepareStatement(sql);
					ps.setString(1, Currentcno+"");
					ps.setString(2, cinfo[0]);
					ps.setString(3, cinfo[2]);
					ps.setString(4, cinfo[1]);
					ps.setInt(5, term);
					ps.setString(6, year);
					ps.executeUpdate();
					//插入到排课表中
					//sql = "insert into obligatory(year,term,cid,cno)values('"+year+"',"+term+",'"+classs+"','"+Currentcno+"')";
					sql = "insert into obligatory(year,term,cid,cno)values(?,?,?,?)";
					ps=cn.prepareStatement(sql);
					ps.setString(1, year);
					ps.setInt(2, term);
					ps.setString(3, classs);
					ps.setString(4, Currentcno+"");
					ps.execute();
					ColCno.add(Currentcno+"");
				}else{
					upcouE.add(courseE.get(i)[0]);
					ColCno.add(courseMap.get(courseE.get(i)[0]));
				}
			}
			
			//读取数据库的学生    
			dataCache = db.SelectReturnList("select sno from student");
			for(int i=0;i<dataCache.size();i++){
				studentSet.add(dataCache.get(i)[0]);
			}
			//读取Excel的学生
			
			for (int i=2;i<=AllRows;i++){
				String stu[] = new String[2];
				//如果放在外面，那么就只有一个String[] 的内存被开辟，所以加入List的只有一个，即最后一个，但是下标会有好多，但是都指向同一个对象
				stu[0] = map.get(new Position(i,2));
				stu[1] = map.get(new Position(i,3));
				studentE.add(stu);
			}
			
			//插入学生
			for(int i=0;i<studentE.size();i++){
				String [] stu = studentE.get(i);
				if(!studentSet.contains(stu[0])){
//					System.out.println(i+"要插入的学号是："+stu[0]);
					sql = "insert into student (sno,sname,cid,pass) values("+stu[0]+",?,?,'123')";
					ps=cn.prepareStatement(sql);
					//ps.setLong(1, stu[0]);
					ps.setString(1, stu[1]);
					ps.setString(2, classs);
					ps.execute();
				}else{
					upstuE.add(stu[0]);
				}
			}
			
			//插入成绩
			for (int i=2;i<=AllRows;i++){//第一行是列头
				int courseIndex = 0;
				for(int j=5;j<=AllCols;j++){
					if(!"".equals(map.get(new Position(i,j)))){//如果Excel中成绩不是空
						//如果记录已有，就是修改
						if(upstuE.contains(map.get(new Position(i,2))) || upcouE.contains(ColCno.get(courseIndex))){
							sql = "update mark set grade=? where sno="+map.get(new Position(i,2))+" and cno=? and year=? and term=?";
							ps = cn.prepareStatement(sql);
							ps.setFloat(1, Float.parseFloat(map.get(new Position(i,j))));
							ps.setString(2, ColCno.get(courseIndex));
							ps.setString(3, year);
							ps.setInt(4, term);
							ps.execute();
						}else{
							sql = "insert into mark(sno,cno,grade,year,term) values("+map.get(new Position(i,2))+",?,?,?,?)";
							ps = cn.prepareStatement(sql);
							ps.setString(1, ColCno.get(courseIndex));
							ps.setFloat(2, Float.parseFloat(map.get(new Position(i,j))));
							ps.setString(3, year);
							ps.setInt(4, term);
							ps.execute();
						}
					}else{
						if(upstuE.contains(map.get(new Position(i,2))) || upcouE.contains(ColCno.get(courseIndex))){
							
						}else{//新增
							sql = "insert into mark(sno,cno,grade,year,term) values("+map.get(new Position(i,2))+",?,?,?,?)";
							ps = cn.prepareStatement(sql);
							ps.setString(1, ColCno.get(courseIndex));
							ps.setFloat(2, -1);
							ps.setString(3, year);
							ps.setInt(4, term);
							ps.execute();
						}
					}
					courseIndex++;
				}
			}
		}catch(Exception e){
			System.out.println("成绩的批量增改失败");
			try {
				cn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw e;
		}finally {
			try{
				cn.setAutoCommit(true);//改回来
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(cn!=null) cn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	/**
	 * @To 补考或清考 插入
	 * （序号，学号，姓名，成绩）根据学号和课程号，确定记录，修改补考或清考成绩
	 * @param fileName
	 * @param course
	 * @param type 补考：makeup 清考：ultimate
	 */
	public void ToBuOrQing(String fileName,String course,String type) throws Exception{
		ReadFile(fileName);
		/**用上了事务，因为保证其表格的统一性和原子性*/
		try{
			Class.forName(Driver);
			cn = DriverManager.getConnection(URL);
			cn.setAutoCommit(false);//取消自动提交
			String sql;
			
			for (int i=startRow;i<=AllRows;i++){
				sql = "update mark set "+type+"="+map.get(new Position(i,4))+" where sno="
						+ map.get(new Position(i,2))+" and cno='"+course+"'";
				//System.out.println(i+"插入成绩 : "+sql);
				ps=cn.prepareStatement(sql);
				ps.executeUpdate();
				System.out.println(i+": 修改记录成功");
			}
			cn.commit();//无异常再提交
		}catch(Exception e){
			try {
				cn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("增删改查失败");
			throw e;
		}finally {
			try{
				cn.setAutoCommit(true);//改回来
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(cn!=null) cn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/** 
	 * @To 成绩  需要 参数课程，学年，学期
	 * （序号，学号，姓名，成绩）
	 * @param fileName
	 * @param course
	 * @param year
	 * @param term
	 */
	public void ToGrade(String fileName,String course,String year,int term)throws Exception{
		ReadFile(fileName);
		/**用上了事务，因为保证其表格的统一性和原子性*/
		try{
			Class.forName(Driver);
			cn = DriverManager.getConnection(URL);
			cn.setAutoCommit(false);//取消自动提交
			String sql;
			
			for (int i=startRow;i<=AllRows;i++){
				sql ="insert into mark(sno,cno,grade,year,term) values(" + map.get(new Position(i,2))+",'"+course+"',"+map.get(new Position(i,4))+",'"+year+"',"+term+")";
				//System.out.println(i+"插入成绩 : "+sql);
				ps=cn.prepareStatement(sql);
				ps.executeUpdate();
				System.out.println(i+": 插入记录成功");
			}
			cn.commit();//无异常再提交
		}catch(Exception e){
			
			try {
				cn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("增删改查失败");
			throw e;
		}finally {
			try{
				cn.setAutoCommit(true);//改回来
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(cn!=null) cn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * @To 课表. Excel中有（班级，课程，教师，备注 ）需要插入学年和学期
	 * @param fileName
	 * @param year
	 * @param term
	 */
	public void ToObligatory(String fileName,String year,int term) throws Exception{
		ReadFile(fileName);
		/**用上了事务，因为保证其表格的统一性和原子性*/
		try{
			Class.forName(Driver);
			cn = DriverManager.getConnection(URL);
			cn.setAutoCommit(false);//取消自动提交
			String sql;
					
			for (int i=startRow;i<=AllRows;i++){
				sql ="insert into obligatory values('"+year+"',"+term+",";
				for (int k=1;k<=AllCols;k++) {
					sql += "'"+map.get(new Position(i,k))+"',";
				}
				sql = sql.substring(0,sql.length()-1);//去掉逗号
				sql+=")";
				//System.out.println(i+" : "+sql);
				ps=cn.prepareStatement(sql);
				ps.executeUpdate();
				System.out.println(i+": 插入记录成功");
			}
			cn.commit();//无异常再提交
		}catch(Exception e){
			try {
				cn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("增删改查失败");
			throw e;
		}finally {
			try{
				cn.setAutoCommit(true);//改回来
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(cn!=null) cn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @To 这是专业和班级的函数
	 * @P 需要给出学院id或专业id
	 * @param fileName
	 * @param table
	 * @param UPid 上级外码的id
	 */
	public void ToMajorOrClasss(String fileName,String table,String UPid) throws Exception{
		ReadFile(fileName);
		/**用上了事务，因为保证其表格的统一性和原子性*/
		try{
			Class.forName(Driver);
			cn = DriverManager.getConnection(URL);
			cn.setAutoCommit(false);//取消自动提交
			String sql;
						
			//插入表的内容 必须保证，Excel的列和数据库的列一一对应
			for (int i=startRow;i<=AllRows;i++){
				sql ="insert into "+table+" values(";
				for (int k=1;k<=AllCols;k++) {
					sql += "'"+map.get(new Position(i,k))+"',";
					if(k==2) sql+=" '"+UPid+"',";
				}
				sql = sql.substring(0,sql.length()-1);//去掉逗号
				sql+=")";
				//System.out.println(i+" : "+sql);
				ps=cn.prepareStatement(sql);
				ps.executeUpdate();
				System.out.println(i+": 插入记录成功");
			}
			cn.commit();//无异常再提交
		}catch(Exception e){
			try {
				cn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("增删改查失败");
			throw e;
		}finally {
			try{
				cn.setAutoCommit(true);//改回来
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(cn!=null) cn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/** 
	 * @To 这是一对一的通用方法
	 * @DataSource 其标题和内容数据来源就是那个String[] 和 Map，一定要先获取了
	 * @addition 插入到数据库的表中，并且是使用了事务处理
	 * @param filename 文件名
	 * @param table 表名
	 */
	public void ToSimpleTable(String filename,String table) throws Exception{
		ReadFile(filename);
		/**用上了事务，因为保证其表格的统一性和原子性*/
		try{
			Class.forName(Driver);
			cn = DriverManager.getConnection(URL);
			cn.setAutoCommit(false);//取消自动提交
			String sql;
						
			//插入表的内容 必须保证，Excel的列和数据库的列一一对应
			for (int i=startRow;i<=AllRows;i++){
				sql ="insert into "+table+" values(";
				for (int k=1;k<=AllCols;k++) sql += "'"+map.get(new Position(i,k))+"',";
				sql = sql.substring(0,sql.length()-1);//去掉逗号
				sql+=")";
				System.out.println(i+" 一对一 : "+sql);
				ps=cn.prepareStatement(sql);
				ps.executeUpdate();
				System.out.println(i+": 插入记录成功");
			}
			cn.commit();//无异常再提交
		}catch(Exception e){
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("增删改查失败");
			throw e;
		}finally {
			try{
				cn.setAutoCommit(true);//改回来
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(cn!=null) cn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 输入文件名，拼接得到完整路径
	 * 读取文件打开流，并获取到文件数据内容
	 * @param fileName
	 */
	public void ReadFile(String fileName) throws Exception{
		ServletContext sc = ServletActionContext.getServletContext();
		path = sc.getRealPath("/fileupload");
		//path = "E:/TsetExcel/";
		
		System.out.println(path);
	    
		path+="\\";
		path += fileName;
    	try { 
	        is = new FileInputStream(path);
	        fs = new POIFSFileSystem(is);  //封装特定的输入流 
            wb = new HSSFWorkbook(fs); //创建工作簿
	    }catch (FileNotFoundException e) {System.out.println("未找到指定路径的文件!"); e.printStackTrace();}
    		catch(IOException ew){System.out.println("创建后两个对象出了问题"); ew.printStackTrace();}
    			catch(Exception es){System.out.println("也不知道什么鬼BUG"); es.printStackTrace(); }
    	
		//将数据读到集合内
		title = readExcelTitle(is,currentSheet);
        map = readExcelContent(is,currentSheet);
        //关闭文件流
        try {
    		if(is!=null) is.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
	
	/**
	 * 获取指定Sheet的表格标题 
	 * @param is,sheetNum
	 * @return 返回值是String [] 就直接设定标题只有一行（）
	 */
	private String[] readExcelTitle(InputStream is,int sheetNum) {      
		sheet = wb.getSheetAt(sheetNum); //第一Sheet 
		
        row = sheet.getRow(0);//第一行 ,因为指定了第一行是标题，虽然标题可能占据多行，但是第一行必须是标题行，不然也会有错误     
        //标题总列数 
        System.out.println(row);
        int colNum = row.getPhysicalNumberOfCells();   //由这里限制了列的大小？
        //int colNum = row.getLastCellNum();
        		System.out.println("标题的列数为："+colNum);
        String[] title = new String[colNum];  
        //遍历标题
        for (int i=0; i<colNum; i++) {      
            title[i] = getStringCellValue(row.getCell(i)); //为什么要将i转型为short     
        }      
        return title;      
    }      
          
	/**
	 * 读取指定Sheet的表格数据内容    
	 * @param is,sheetNum
	 * @return Map<Position,String>
	 */
	private Map<Position,String> readExcelContent(InputStream is,int sheetNum) { 
		Map<Position,String> content = new HashMap<Position,String>();
////////////////////////////////
        sheet = wb.getSheetAt(sheetNum);      
        //得到总行数      
        int rowNum = sheet.getLastRowNum();
		AllRows = rowNum;
        row = sheet.getRow(rowNum-1);  //暂时使用这个方法，全表行数的倒数第二行，我就不信你整个表就只有标题
        int colNum = row.getPhysicalNumberOfCells(); //得到实际列数？     还只是第一行也就是标题
        AllCols = colNum;
        System.out.println("正文得到的列数："+colNum);
/////////////////////////////////////		
		for (int i = 1; i <= rowNum; i++) {   //遍历所有行，除了第一行  数组规则
            row = sheet.getRow(i);      
            for(int j=0;j<colNum;j++) {      
            	//每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据      
            	//也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean   
            	String cellss = getStringCellValue(row.getCell(j)).trim();
            	content.put(new Position(i,j+1), cellss); //这里创建的对象，地址是已经固定了，也不可得到,重写HashCode方法即可让值和地址绑定
                //System.out.println("=="+cellss+"==");
            }      
        }
		return content;
	}
   
    /**    
     * 获取单元格数据内容为字符串，数值，时间类型的数据    
     * @param cell Excel单元格    
     * @return String 单元格数据内容    
     */     
    @SuppressWarnings("unused")
	private String getStringCellValue(HSSFCell cell) {      
        String strCell = "";      
        switch (cell.getCellType()) { 
        case Cell.CELL_TYPE_FORMULA:
        	try{
        		/**
        		 * 此处判断使用公式生成的字符串有问题，因为HSSFDateUtil.isCellDateFormatted(cell)判断过程中cell.getNumericCellValue();方法
        		 * 会抛出java.lang.NumberFormatException异常
        		 */
        		if (HSSFDateUtil.isCellDateFormatted(cell)) {//判断是日期类型
            		Date date = cell.getDateCellValue();//从单元格获取日期数据
            		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");//设定转换的格式
            		strCell = formater.format(date);//将日期数据（Date 或者直接输入的格式正确的字符串）转换成String类型
            		break;
            	}else{
            		double Num = cell.getNumericCellValue();   //普通数字类型
    	        	DecimalFormat formatCell = (DecimalFormat)NumberFormat.getPercentInstance();
    	        	formatCell.applyPattern("0");
    	        	strCell = formatCell.format(Num);
    	        	if(Double.parseDouble(strCell)!=Num){
    	        		formatCell.applyPattern(Double.toString(Num));
    	        		strCell = formatCell.format(Num);
    	        	}
    	        	if(".0".equals(strCell.subSequence(strCell.length()-2, strCell.length())))strCell = Integer.parseInt(strCell.substring(0,strCell.length()-2))+"";
    	        		System.out.println("数字："+strCell);
            	}
            		//strCell = String.valueOf(cell.getNumericCellValue());   //普通数字类型
        	}catch(IllegalStateException e){
        		strCell = String.valueOf(cell.getRichStringCellValue());
        		//strCell =new XSSFCell().getCtCell().getV(); 没有这个JAR包
        	}
        	break;
        case HSSFCell.CELL_TYPE_STRING:      
            strCell = cell.getStringCellValue();      
            break; 
        case HSSFCell.CELL_TYPE_NUMERIC: 
        	if (HSSFDateUtil.isCellDateFormatted(cell)) {//判断是日期类型
        		Date date = cell.getDateCellValue();//从单元格获取日期数据
        		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");//设定转换的格式
        		strCell = formater.format(date);//将日期数据（Date 或者直接输入的格式正确的字符串）转换成String类型
        	}else{	
        		double Num = cell.getNumericCellValue();   //普通数字类型
	        	DecimalFormat formatCell = (DecimalFormat)NumberFormat.getPercentInstance();
	        	formatCell.applyPattern("0");
	        	strCell = formatCell.format(Num);
	        	if(Double.parseDouble(strCell)!=Num){
	        		formatCell.applyPattern(Double.toString(Num));
	        		strCell = formatCell.format(Num);
	        	}
	        	//System.out.println(strCell+strCell.length());
	        	if(strCell.length()>2 && ".0".equals(strCell.subSequence(strCell.length()-2, strCell.length())))strCell = Integer.parseInt(strCell.substring(0,strCell.length()-2))+"";
	        		//System.out.println("数字："+strCell);
	        }
            break;      
        case HSSFCell.CELL_TYPE_BOOLEAN:      
            strCell = String.valueOf(cell.getBooleanCellValue());      
            break;      
        case HSSFCell.CELL_TYPE_BLANK:      
            strCell = "";  
            //System.out.print("空格");
            break;      
        default:      
            strCell = "";      
            break;      
        }      
        if (strCell.equals("") || strCell == null) {      
            return "";      
        }      
        if (cell == null) {      
            return "";      
        }      
        return strCell;      
    }    
    
    /**将获取到的数据在控制台输出*/
    public void showExcel(){
    	//控制台输出标题
        System.out.println("获得Excel表格的标题:");      
        for (String s1 : title) {      
            System.out.print(s1 + " ");      
        }      
        System.out.println();
    	//控制台输出内容
        System.out.println("获得Excel表格的内容:"); 
        for (int i=1; i<=AllRows; i++) {      
        	for (int k=1;k<=AllCols;k++)
        		System.out.print(new Position(i,k).toString()+""+map.get(new Position(i,k))+"-"); //这里的get就没有办法获取到值，因为创建的对象是一个新的地址  
        	System.out.println();
	    }
    }
    
    /**计算得出有效的Sheet*/
    public void showSheet(){
    	//若有多个Sheet，这里只处理单Sheet
    	sheetNum = wb.getNumberOfSheets();
    	System.out.println("Sheet数量："+sheetNum);
    	int tempSheet=0;
    	for(int i=0;i<sheetNum;i++){
    		HSSFSheet sheet = wb.getSheetAt(i);
    	    int EffectiveRow=0;//有效行数
    	    for (Row row : sheet){
    	    	if(row==null) continue;
    	    	EffectiveRow++;
    	    }
    	    System.out.println("有效的行数："+EffectiveRow);
    	    if(EffectiveRow==0){
    	    	System.out.println("空Sheet");
    	    	continue;
    	    }
    	    tempSheet++;
    	}
    	sheetNum = tempSheet;
    	System.out.println("实际有效的Sheet："+sheetNum);

    }
    
}
/**
 * @author  Myth
 * @date 2016年8月18日 下午10:13:27
 * @TODO 这个类是为了处理表格的位置，能够得到准确的定位
 */
class Position {
	private int col;
	private int row;
	public Position (){}
	public Position ( int row, int col){
		this.col = col;
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	@Override
	public String toString() {
		return "<" + row + "," + col + "> ";
	}
	/**
	 * 重写的equals要注意是一定能做到将对象的值相同的判定为相等，不能有任何错误
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this == obj) return true; //如果就是当时new的对象，当然是true了
		if(!(obj instanceof Position)) return false;//如果类型都对不上，当然要返回false了
		Position key = (Position)obj;//将进来的Object进行强转，因为上面已经筛选掉了类型不对的情况，这里可以放心强转
		return row== key.row && col == key.col; //自定义的比对方案
		
	}
	/**
	 * 重写的HashCode方法要特别注意key的唯一性，一旦有重复，就是个BUG了
	 */
	@Override
	public int hashCode() {
		int result = row;
		result = 37*result+col;
		return result;
	}
}
//插入表的标题
//多Sheet插入同一张表的话就需要把标题舍弃了，毕竟没有实际用处，还违背了数据库的基本原则
/*sql = "insert into excel2 values(";
for (String temp:title){
	if("".equals(temp)) temp= "NULL";
	else temp="'"+temp+"'";
	sql += ""+temp+",";
}
sql = sql.substring(0,sql.length()-1);
sql+=")";
//System.out.println("标题 : "+sql);
ps=cn.prepareStatement(sql);
ps.executeUpdate();
System.out.println("标题插入成功");*/