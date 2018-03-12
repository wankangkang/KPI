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

import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.formssi.bean.FormssiKpi;
import com.formssi.constans.ExcelField;

public class HssfExcelHandler extends ExcelHandler{

	@SuppressWarnings("resource")
	public void writeExcel(OutputStream outputStream,List<FormssiKpi> smpKpiList){
		final short PERSONAL_PINK = 9;
		final short PERSONAL_BLUE = 10;
		final short PERSONAL_WHITE = 11;
		HSSFWorkbook hb = new HSSFWorkbook();
		HSSFPalette palette = hb.getCustomPalette();
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
			sheet = hb.createSheet();
			//设置自定义颜色
			palette.setColorAtIndex(PERSONAL_PINK, (byte)255, (byte)153, (byte)204);
			palette.setColorAtIndex(PERSONAL_BLUE, (byte)204, (byte)255, (byte)255);
			palette.setColorAtIndex(PERSONAL_WHITE, (byte)255, (byte)255, (byte)255);
			Row row = sheet.createRow(0);
			row.setHeight((short)780);
			CellStyle style = hb.createCellStyle();
			//为第一行设置样式
			Font font1 = hb.createFont();
			initStyle(style, font1, PERSONAL_PINK, (short)11, true);
			/*initHbookCommonStyle(style);
			initFont(font1, true, (short)11);
			style.setFont(font1);
			style.setFillForegroundColor(PERSONAL_PINK);*/
			//第一行前两列的样式为左对齐
			CellStyle styleforFirst = hb.createCellStyle();
			initStyle(styleforFirst, font1, PERSONAL_PINK, (short)11, true);
			styleforFirst.setAlignment(HorizontalAlignment.LEFT);
			//设置第二种字体样式
			Font font2 = hb.createFont();
			//设置第三种字体样式
			Font font3 = hb.createFont();
			//创建背景填充为自定义粉红的样式
			CellStyle style1 = hb.createCellStyle();
			initStyle(style1, font2, PERSONAL_PINK,(short)10,false);
			//创建背景填充为自定义蓝的样式
			CellStyle style2 = hb.createCellStyle();
			initStyle(style2, font3, PERSONAL_BLUE,(short)11,false);
			style2.setAlignment(HorizontalAlignment.RIGHT);
			//创建背景填充为白色的样式
			CellStyle style3 = hb.createCellStyle();
			initStyle(style3, font2, PERSONAL_WHITE,(short)10,false);
			//创建背景填充为自定义粉红的样式左对齐的样式
			CellStyle style4 = hb.createCellStyle();
			initStyle(style4, font2, PERSONAL_PINK,(short)10,false);
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
				Cell cell0 = row.createCell(0);
				cell0.setCellValue(smpKpi.getProgramSerialNum());
				cell0.setCellStyle(style4);
				Cell cell1 = row.createCell(1);
				cell1.setCellValue(smpKpi.getProgramName());style = hb.createCellStyle();
				cell1.setCellStyle(style4);
				Cell cell2 = row.createCell(2);
				cell2.setCellValue(Optional.ofNullable(smpKpi.getStaffId()).orElse(""));
				cell2.setCellStyle(style1);
				Cell cell3 = row.createCell(3);
				cell3.setCellStyle(style1);
				cell3.setCellValue(smpKpi.getMemberName());
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
			hb.write(outputStream);
			outputStream.close();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private void initStyle(CellStyle style,Font font,short color,short fontHeight,boolean ifBold){
		initHbookCommonStyle(style);
		initFont(font, ifBold, fontHeight);
		style.setFont(font);
		style.setFillForegroundColor(color);
	}
}
