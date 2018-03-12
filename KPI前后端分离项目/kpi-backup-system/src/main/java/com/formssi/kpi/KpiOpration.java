package com.formssi.kpi;

import java.util.List;

import com.formssi.bean.FormssiKpi;

/**
 * @author Bill
 * @Description: kpi操作的接口
 */
public interface KpiOpration {

	void addKpi(List<FormssiKpi> formssiList,List<FormssiKpi> newFormssiList);
	
	void updateKpi(List<FormssiKpi> formssiList,List<FormssiKpi> newFormssiList);
	
	void deleteKpi(List<FormssiKpi> formssiList,List<FormssiKpi> newFormssiList);
}
