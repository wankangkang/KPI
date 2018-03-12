/**
 * FileName:NormalWorkOperation.java
 * @Description:处理正常Kpi导入问题
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年12月26日下午9:35:11
 ********************************
 *Modifycation History:
 *date:2017年12月26日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.kpi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formssi.bean.FormssiKpi;
import com.formssi.dao.FormssiKpiDao;

/**
 * @author lenovo
 *
 */
@Service("normalWorkKpi")
public class NormalWorkKpi implements KpiOpration {

	@Autowired
	FormssiKpiDao formssiKpiDao;
	
	/* 
	 * @see com.formssi.kpi.KpiOpration#addKpi(java.util.List)
	 */
	@Override
	public void addKpi(List<FormssiKpi> formssiKpiList,List<FormssiKpi> newFormssiList) {
		formssiKpiDao.batchImport(formssiKpiList);
	}

	/* 
	 * @see com.formssi.kpi.KpiOpration#updateKpi(java.util.List)
	 */
	@Override
	public void updateKpi(List<FormssiKpi> formssiList,List<FormssiKpi> newFormssiList) {
		//do nothing
	}

	/* 
	 * @see com.formssi.kpi.KpiOpration#deleteKpi(java.util.List)
	 */
	@Override
	public void deleteKpi(List<FormssiKpi> formssiList,List<FormssiKpi> newFormssiList) {
		//do nothing
	}

}
