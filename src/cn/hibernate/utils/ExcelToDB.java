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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
/**
 * 需要实现的功能是 从Excel中获取到数据后，成功插入到数据库中去，注意特定格式
 *  如果要和数据库绑定的话，实际上也是写死了，只能一个表写一个这样的类 不过幸好那些表格格式是一致的
 * 注意了，加了一个case后能够获取到公式的值了
 * 如果要使用多个Sheet的话，就要确保Sheet是同一个格式（也就是说是要插入到一个表的，不然代码量太多，也没有意义）
 * 	就比如同系各个班级的成绩表
 * 
 * @author Myth
 * @date 2016年7月23日
 */
public class ExcelToDB {

	
	private POIFSFileSystem fs;      
    private HSSFWorkbook wb;      
    private HSSFSheet sheet;      
    private HSSFRow row; 
    int AllRows;
    int AllCols;
    int sheetNum;//最好实现一个机制，自动去获取后面的Sheet（如果有数据的话）
    int StartRow = 2; //内容起始行
    static String path;
    static Map<Position,String> map = null ;//要注意：这里的Map采用的是自然的行号和列号（1开头）
    static String[] title ;
    
    static{
    	//获取类的绝对路径（是已经编译了的字节码文件）
    	path = ExcelToDB.class.getClass().getResource("/").getPath();
		System.out.println(path);
		//System.out.println("new File 方法得到的路径 ："+new File("").getPath());
	    path = path.substring(1,path.length()-4);
	    path += "excel/";
	    System.out.println("最终路径"+path);
	    //上面的path就是上传的绝对路径获取方式了
	    //最终路径E:/Git/Test/Test/Test/excel/
    }
	public static void main(String [] s){
	    String fileName = "excel.xls";
	    ExcelToDB ed = new ExcelToDB();
	    //ed.ExcelInsertTable(fileName);
	    ed.ReadExcel(fileName);
	}  
	/**
	 * 输入文件名，拼接一下，调用方法
	 */
	public void ReadExcel(String fileName){
		path += fileName;
		ExcelInsertTable(path);
	};
	/**
	 * 获取到Excel的内容并插入到数据库的表格中
	 * @param fileName
	 */
	public  void ExcelInsertTable(String path){
//		this.path = path;
		System.out.println("得到的PATH"+path);
		InputStream is =null;
    	try { 
	        is = new FileInputStream(path);
	        fs = new POIFSFileSystem(is);  //封装特定的输入流 
            wb = new HSSFWorkbook(fs); //创建工作簿
	    }catch (FileNotFoundException e) {System.out.println("未找到指定路径的文件!"); e.printStackTrace();}
    	catch(IOException ew){System.out.println("创建后两个对象出了问题"); ew.printStackTrace();}
    	catch(Exception es){System.out.println("也不知道什么鬼BUG"); es.printStackTrace(); }
    	
    	//获取展示Sheet信息
    	ShowSheetInfo();
        
    	//第二个参数是SheetNum
        title = readExcelTitle(is,1);
        map = readExcelContent(is,1);
    	InsertTable();
    	//控制台输出标题
        /*System.out.println("获得Excel表格的标题:");      
        for (String s1 : title) {      
            System.out.print(s1 + " ");      
        }      
        System.out.println();*/
        //控制台输出内容
        /*System.out.println("获得Excel表格的内容:"); 
        for (int i=1; i<=AllRows; i++) {      
        	for (int k=1;k<=AllCols;k++)
        		System.out.print(new Position(i,k).toString()+""+map.get(new Position(i,k))+"-"); //这里的get就没有办法获取到值，因为创建的对象是一个新的地址  
        	System.out.println();
	    }*/
	}
	/** 
	 * @DataSource 其标题和内容数据来源就是那个String[] 和 Map
	 * @addition 插入到数据库的表中，并且是使用了事务处理
	 */
	final void InsertTable(){
		PreparedStatement ps = null;
		Connection cn = null;
		ResultSet rs = null;
		
		String Driver = "com.mysql.jdbc.Driver";
		String URL="jdbc:mysql://localhost:3306/test?user=root&password=ad&userUnicode=true&characterEncoding=UTF8";
		
		/**用上了事务，因为保证其表格的统一性和原子性*/
		try{
			Class.forName(Driver);
			cn = DriverManager.getConnection(URL);
			cn.setAutoCommit(false);//取消自动提交
			
			
			String sql;
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
			//插入表的内容 必须保证，Excel的列和数据库的列一一对应
			for (int i=StartRow;i<=AllRows;i++){
				sql ="insert into excel2 values(";
				for (int k=1;k<=AllCols;k++) sql += "'"+map.get(new Position(i,k))+"',";
				sql = sql.substring(0,sql.length()-1);
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("增删改查失败");
			
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
	 * 获取表格标题 
	 * @param is
	 * @param sheetNum 
	 * @return 返回值是String [] 就直接设定标题只有一行（）
	 */
	public String[] readExcelTitle(InputStream is,int sheetNum) {      
		/*try {      
        fs = new POIFSFileSystem(is);      
        wb = new HSSFWorkbook(fs);      
	    } catch (IOException e) {
	        e.printStackTrace();      
	    }*/
		sheet = wb.getSheetAt(sheetNum); //第一Sheet     
        row = sheet.getRow(0);//第一行 ,因为指定了第一行是标题，虽然标题可能占据多行，但是第一行必须是标题行，不然也会有错误     
        //标题总列数      
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
	 * 读取Excel数据内容    
	 * @param is
	 * @return
	 */
	public Map<Position,String> readExcelContent(InputStream is,int sheetNum) { 
		Map<Position,String> content = new HashMap<Position,String>();
////////////////////////////////
		//预加载进Excel文件，得到其相关参数 注意：行列都是 数组下标表示规则
		/*try {      
            fs = new POIFSFileSystem(is);      
            wb = new HSSFWorkbook(fs);      
        } catch (IOException e) {
            e.printStackTrace();      
        }*/      
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
     * 获取单元格数据内容为字符串类型的数据    
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
            	}else	
            		strCell = String.valueOf(cell.getNumericCellValue());   //普通数字类型
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
        	}else{	strCell = String.valueOf(cell.getNumericCellValue());   //普通数字类型
        	 if(".0".equals(strCell.subSequence(strCell.length()-2, strCell.length())))strCell = Integer.parseInt(strCell.substring(0,strCell.length()-2))+"";
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
    public void ShowSheetInfo(){
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
