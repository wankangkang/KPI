/**
 * FileName:ExcelField.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年11月3日下午11:16:49
 ********************************
 *Modifycation History:
 *date:2017年11月3日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.constans;

public enum ExcelField {

	PROGRAM_SERIAL_NUM("项目编号"),
	PROGRAM_NAME("项目名称"),
	STAFF_ID("员工ID"),
	STAFF_NAME("姓名"),
	DATE("日期"),
	SORT_SIGN("分类标识"),
	NORMAL_WORKING("正常时间投入（天）"),
	EXTRA_WORKTIME("加班时间投入（天）"),
	;
	
	private String code;

	
	private ExcelField(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
