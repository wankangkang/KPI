/**
 * FileName:ExcelHandler.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年11月4日上午12:36:54
 ********************************
 *Modifycation History:
 *date:2017年11月4日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.excel;

import java.awt.Color;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.formssi.bean.FormssiKpi;
import com.formssi.constans.ExcelField;

public class XssfExcelHandler extends ExcelHandler{

	@SuppressWarnings("resource")
	public void writeExcel(OutputStream outputStream,List<FormssiKpi> smpKpiList){
		XSSFWorkbook xb = new XSSFWorkbook();
		Sheet sheet = null;
		int cellPosition = 0;
		int rownum = 1;
		try {
			//过滤掉全为0的情况
			smpKpiList = smpKpiList.stream().filter(formssiKpi -> {
				if (0 == Optional.ofNullable(formssiKpi.getExtraWork()).orElse(0.0)  && 0 == Optional.ofNullable(formssiKpi.getNormalWork()).orElse(0.0)) {
					return false;
				}
				return true;
			}).collect(Collectors.toList());
			sheet = xb.createSheet();
			//设置自定义颜色
			XSSFColor personalPink = new XSSFColor(new Color(255,153,204));
			XSSFColor personalBlue = new XSSFColor(new Color(204,255,255));
			Row row = sheet.createRow(0);
			row.setHeight((short)1080);
			XSSFCellStyle style = xb.createCellStyle();
			//为第一行设置样式
			Font font1 = xb.createFont();
			initStyle(style, font1, personalPink, true,(short)11);
			/*initXbookCommonStyle(style);
			initFont(font1,, (short)11);
			style = Optional.ofNullable(style).orElse(xb.createCellStyle());
			style.setFont(font1);
			style.setFillForegroundColor(personalPink);*/
			
			//第一行前两列的样式为左对齐
			XSSFCellStyle styleforFirst = xb.createCellStyle();
			initStyle(styleforFirst, font1, personalPink, true,(short)11);
			styleforFirst.setAlignment(HorizontalAlignment.LEFT);
			//设置第二种字体样式
			Font font2 = xb.createFont();
			//设置第三种字体样式
			Font font3 = xb.createFont();
			//创建背景填充为自定义粉红的样式
			XSSFCellStyle style1 = xb.createCellStyle();
			initStyle(style1, font2, personalPink,false,(short)10);
			//创建背景填充为自定义蓝的样式
			XSSFCellStyle style2 = xb.createCellStyle();
			initStyle(style2, font3, personalBlue,true,(short)11);
			style2.setAlignment(HorizontalAlignment.RIGHT);
			//创建背景填充为白色的样式
			XSSFCellStyle style3 = xb.createCellStyle();
			initStyle(style3, font2, new XSSFColor(new Color(255,255,255)),false,(short)10);
			//创建背景填充为自定义粉红的样式左对齐的样式
			XSSFCellStyle style4 = xb.createCellStyle();
			initStyle(style4, font2, personalPink,false,(short)10);
			style4.setAlignment(HorizontalAlignment.LEFT);
			for (ExcelField field : ExcelField.values()) {
				Cell cell = row.createCell(cellPosition);
				cell.setCellValue(field.getCode());
				if (field == ExcelField.PROGRAM_NAME || field == ExcelField.PROGRAM_SERIAL_NUM) {
					cell.setCellStyle(styleforFirst);
					if (field == ExcelField.PROGRAM_SERIAL_NUM) {
						sheet.setColumnWidth(cellPosition, 30*256);
					}else {
						sheet.setColumnWidth(cellPosition, 45*256);
					}
				}else {
					cell.setCellStyle(style);
					if (field == ExcelField.SORT_SIGN || field == ExcelField.STAFF_NAME || field == ExcelField.DATE) {
						sheet.setColumnWidth(cellPosition, 15*256);
					}else {
						sheet.setColumnWidth(cellPosition, 10*256);
					}
				}
				cellPosition ++ ;
			}
			for (FormssiKpi smpKpi : smpKpiList) {
				row = sheet.createRow(rownum);
				row.setHeight((short)780);
				Cell cell0 = row.createCell(0);
				cell0.setCellValue(smpKpi.getProgramSerialNum());
				//仅为了方便查看，所以使用不同名称来创建对象
				cell0.setCellStyle(style4);
				Cell cell1 = row.createCell(1);
				cell1.setCellValue(smpKpi.getProgramName());
				cell1.setCellStyle(style4);
				Cell cell2 = row.createCell(2);
				cell2.setCellValue(Optional.ofNullable(smpKpi.getStaffId()).orElse(""));
				cell2.setCellStyle(style1);
				Cell cell3 = row.createCell(3);
				cell3.setCellValue(smpKpi.getMemberName());
				cell3.setCellStyle(style1);
				Cell cell4 = row.createCell(4);
				cell4.setCellValue(smpKpi.getDate());
				cell4.setCellStyle(style3);
				Cell cell5 = row.createCell(5);
				cell5.setCellValue(Optional.ofNullable(smpKpi.getSortSign()).orElse(""));
				cell5.setCellStyle(style1);
				Cell cell6 = row.createCell(6);
				if (smpKpi.getNormalWork() == null || smpKpi.getNormalWork() == 0) {
					cell6.setCellValue("");
				}else {
					cell6.setCellValue(smpKpi.getNormalWork());
				}
				cell6.setCellStyle(style2);
				Cell cell7 = row.createCell(7);
				if (smpKpi.getExtraWork() == null || smpKpi.getExtraWork() == 0) {
					cell7.setCellValue("");
				}else {
					cell7.setCellValue(smpKpi.getExtraWork());
				}
				cell7.setCellStyle(style2);
				rownum ++ ;
			}
			outputStream.flush();
			xb.write(outputStream);
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	private void initStyle(XSSFCellStyle xStyle,Font font,XSSFColor color,boolean ifBold,short fontHeight){
		initXbookCommonStyle(xStyle);
		initFont(font, ifBold, fontHeight);
		xStyle.setFont(font);
		xStyle.setFillForegroundColor(color);
	}
}
