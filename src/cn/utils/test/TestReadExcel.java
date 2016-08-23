package cn.utils.test;

import cn.hibernate.utils.ReadExcel;

public class TestReadExcel {

	public static void main(String[] args) {
		ReadExcel re = new ReadExcel();
		try {
			re.ToSimpleTable("Student.xls","student");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
