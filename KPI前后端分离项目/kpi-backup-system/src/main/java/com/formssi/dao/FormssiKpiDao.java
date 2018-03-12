package com.formssi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.formssi.bean.FormssiKpi;
import com.formssi.bean.KpiData;

@Mapper
public interface FormssiKpiDao {

	FormssiKpi get(Map<String,Object> map);
	
	List<FormssiKpi> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(FormssiKpi formssiKpi);
	
	int update(FormssiKpi formssiKpi);
	
	int updateNoLock(FormssiKpi formssiKpi);
	
	int remove(FormssiKpi formssiKpi);
	
	int batchImport(List<FormssiKpi> list);
	
	int updateBatch(List<FormssiKpi> list);
	
	int batchRemove(List<FormssiKpi> formssiKPiList);
	
	int removeNotNormal(Map<String, Object> params);
	
	List<KpiData> getKpiData(Map<String, Object> params);
	
	double getPersonalKpi(Map<String, Object> params);
	
	List<FormssiKpi> getAllNotLeftData(Map<String,Object> map);
}
