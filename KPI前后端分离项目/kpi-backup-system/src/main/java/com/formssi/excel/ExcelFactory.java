/**
 * FileName:ExcelFactory.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年11月16日下午11:04:05
 ********************************
 *Modifycation History:
 *date:2017年11月16日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.excel;

public class ExcelFactory {

	static final String XLS = "xls";
	
	public static ExcelHandler getExcelHandler(String fileType){
		return XLS.equals(fileType) ? new HssfExcelHandler():new XssfExcelHandler();
	}
}
