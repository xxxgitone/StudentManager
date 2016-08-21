package cn.hibernate.utils;

public class ReadExcelUtils {
	
	public static void ToBuOrQing(String fileName,String course,String type){
		ReadExcel re = new ReadExcel();
		re.ToBuOrQing(fileName, course, type);
	}
	public static void ToGrade(String fileName,String course,String year,int term){
		ReadExcel re = new ReadExcel();
		re.ToGrade(fileName, course, year, term);
	}
	public static void ToObligatory(String fileName,String year,int term){
		ReadExcel re = new ReadExcel();
		re.ToObligatory(fileName, year, term);
	}
	public static void ToMajorOrClasss(String fileName,String table,String UPid){
		ReadExcel re = new ReadExcel();
		re.ToMajorOrClasss(fileName, table, UPid);
	}
	public static void ToSimpleTable(String filename,String table){
		ReadExcel re = new ReadExcel();
		re.ToSimpleTable(filename, table);
	}
}
