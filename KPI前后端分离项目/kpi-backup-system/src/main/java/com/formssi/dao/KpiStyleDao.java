package com.formssi.dao;

import org.apache.ibatis.annotations.Mapper;

import com.formssi.bean.KpiStyle;

@Mapper
public interface KpiStyleDao {

	int saveKpiStyle(KpiStyle kpiStyle);
	
	int updateKpiStyle(KpiStyle kpiStyle);
	
	KpiStyle getKpiStyle(String department);
}
