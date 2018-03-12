package com.formssi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.formssi.bean.Holiday;

@Mapper
public interface HolidayDao {

	//仅在每月初始导入使用，不做缓存处理
	List<Holiday> getHolidayList(Map<String, Object> param);
	
	int batchImportHoliday(List<Holiday> holidayList);
	
	int deleteHoliday(Map<String, Object> param);
}
