package cn.utils.test;

import cn.hibernate.utils.ReadExcel;

public class TestReadExcel {

	public static void main(String[] args) {
		ReadExcel re = new ReadExcel();
		re.ToSimpleTable("Student.xls","student");

	}

}
