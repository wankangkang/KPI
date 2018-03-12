/**
 * FileName:ExcelHandler.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年11月16日下午10:04:43
 ********************************
 *Modifycation History:
 *date:2017年11月16日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.excel;

import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.formssi.bean.FormssiKpi;

public abstract class ExcelHandler {
	protected static final Logger log = LoggerFactory.getLogger(HssfExcelHandler.class);
	static final String FONT_NAME = "宋体";
	
	public abstract void writeExcel(OutputStream outputStream,List<FormssiKpi> smpKpiList);
	
	protected void initHbookCommonStyle(CellStyle style){
		//设置背景填充
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		//设置边框格式
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		//设置竖直居中
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		//设置水平居中
		style.setAlignment(HorizontalAlignment.CENTER);
		//环绕
		style.setWrapText(true);
	}
	
	protected void initXbookCommonStyle(XSSFCellStyle xStyle){
		//设置背景填充
		xStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		//设置边框格式
		xStyle.setBorderBottom(BorderStyle.THIN);
		xStyle.setBorderTop(BorderStyle.THIN);
		xStyle.setBorderRight(BorderStyle.THIN);
		xStyle.setBorderLeft(BorderStyle.THIN);
		//设置竖直居中
		xStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		//设置水平居中
		xStyle.setAlignment(HorizontalAlignment.CENTER);
		//环绕
		xStyle.setWrapText(true);
	}
	
	protected void initFont(Font font,Boolean ifBold,short fontSize){
		//字体类型及大小
		font.setFontName(FONT_NAME);
		font.setFontHeightInPoints(fontSize);
		font.setBold(ifBold);
	}
}
