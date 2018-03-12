/**
 * FileName:SmpUtil.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年11月3日下午11:45:42
 ********************************
 *Modifycation History:
 *date:2017年11月3日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.formssi.bean.Holiday;

public class DateUtil {

	
	/**
	 * 
	 * @Title:formatDate
	 * @Description:以指定格式格式化util date类型时间为String类型
	 * @param:@param date
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	public static String formatDate(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		return format.format(date);
	}
	
	/**
	 * 
	 * @Title:formatDate
	 * @Description:以指定格式格式化util date类型时间为String类型
	 * @param:@param date
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	public static String formatDateTime(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return format.format(date);
	}
	
	/**
	 * 
	 * @Title:getDates
	 * @Description:获取sql date形式的当年当月的所有工作日集合
	 * @param:@param year
	 * @param:@param month
	 * @param:@return
	 * @return:List<Date>
	 * @throws
	 */
	/*public static List<Date> getDates(int year,int month){
		List<Date> dates = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, 1);
		while (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) < month) {
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if (! (day == Calendar.SATURDAY || day == Calendar.SUNDAY)) {
				java.util.Date date = (java.util.Date) cal.getTime().clone();
				dates.add(new Date(date.getTime()));
			}
			cal.add(Calendar.DATE, 1);
		}
		return dates;
	}*/
	
	/**
	 * 
	 * @Title:stringToDate
	 * @Description:转换string类型的时间为sql的date
	 * @param:@param dateStr
	 * @param:@return
	 * @return:Date
	 * @throws
	 */
	public static Date stringToDate(String dateStr){
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date sqlDate = null;
		try {
			java.util.Date date = format.parse(dateStr);
			sqlDate = new Date(date.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlDate;
	}
    
	/**
	 * 
	 * @Title:getDates
	 * @Description:获取sql date形式的当年当月的所有工作日集合
	 * @param:@param year
	 * @param:@param month
	 * @param:@return
	 * @return:List<Date>
	 * @throws
	 */
	/*public static List<java.util.Date> getUtilDates(int year,int month){
		List<java.util.Date> dates = new ArrayList<java.util.Date>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, 1);
		while (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) < month) {
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if (! (day == Calendar.SATURDAY || day == Calendar.SUNDAY)) {
				java.util.Date date = (java.util.Date) cal.getTime().clone();
				dates.add(date);
			}
			cal.add(Calendar.DATE, 1);
		}
		return dates;
	}*/
	
	/**
	 * 
	 * @Title:stringToDate
	 * @Description:转换string类型的时间为sql的date
	 * @param:@param dateStr
	 * @param:@return
	 * @return:Date
	 * @throws
	 */
	public static java.util.Date stringToUtilDate(String dateStr){
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date date = null;
		try {
			date =  format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String formatDate(String date,String month,String year){
		String strDate = null;
		if (Integer.parseInt(date)  < 10 && (!date.startsWith("0"))) {
			strDate = year+"/" + month + "/0" + date;
		}else {
			strDate = year+"/" + month + "/" + date;
		}
		return strDate;
	}
	
	public static List<String> getDatesList(int year,int month,List<Holiday> holidayList){
		List<String> dates = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, 1);
		//筛选出补班的日期
		List<Holiday> workDayList = holidayList.stream().filter(
				h -> HolidayType.workday == HolidayType.valueOf(h.getHolidayType())).collect(Collectors.toList());
		workDayList.forEach(w -> dates.add(w.getHolidayDate()));
		//筛选出节假日
		List<Holiday> holidays = holidayList.stream().filter(
				h -> HolidayType.holiday == HolidayType.valueOf(h.getHolidayType())).collect(Collectors.toList());
		while (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) < month) {
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if (! (day == Calendar.SATURDAY || day == Calendar.SUNDAY)) {
				String date = formatDate(((Date)cal.getTime().clone())); 
				dates.add(date);
			}
			cal.add(Calendar.DATE, 1);
		}
		holidays.forEach( h -> dates.remove(h.getHolidayDate()));
		return dates;
	}
	
	public static int[] getDateArray(String date) {
		int[] dates = {};
		if (date.contains("-")) {
			dates = Arrays.stream(Optional.ofNullable(date).orElseThrow(NullPointerException::new).split("-")).mapToInt(e -> Integer.parseInt(e)).toArray();
		}else if(date.contains("/")) {
			dates = Arrays.stream(Optional.ofNullable(date).orElseThrow(NullPointerException::new).split("/")).mapToInt(e -> Integer.parseInt(e)).toArray();
		}
		return dates;
	}
	
	/*public static List<String> getDatesList(int year,int month,List<Holiday> holidayList){
		List<String> dates = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, 1);
		//筛选出补班的日期
		List<Holiday> workDayList = holidayList.stream().filter(
				h -> HolidayType.workday == HolidayType.valueOf(h.getHolidayType())).collect(Collectors.toList());
		workDayList.forEach(w -> dates.add(w.getHolidayDate()));
		//筛选出节假日
		List<Holiday> holidays = holidayList.stream().filter(
				h -> HolidayType.holiday == HolidayType.valueOf(h.getHolidayType())).collect(Collectors.toList());
		while (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) < month) {
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if (! (day == Calendar.SATURDAY || day == Calendar.SUNDAY)) {
				String date = formatDate(((Date)cal.getTime().clone())); 
				dates.add(date);
			}
			cal.add(Calendar.DATE, 1);
		}
		holidays.parallelStream().filter(h -> HolidayType.holiday.name().equals(h.getHolidayType())).forEach( holiday -> dates.remove(holiday.getHolidayDate()));
		return dates;
	}*/
}

enum HolidayType {
	
	workday,
	
	holiday,
	
	;
	
}