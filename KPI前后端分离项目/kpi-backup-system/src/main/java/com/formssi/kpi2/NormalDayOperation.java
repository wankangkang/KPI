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
package com.formssi.kpi2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.formssi.bean.FormssiKpi;
import com.formssi.dao.FormssiKpiDao;

/**
 * @author Bill
 *
 */
@Component("normalDayOperation")
public class NormalDayOperation implements KpiOpration2 {

	private static final Logger log = LoggerFactory.getLogger(LeaveDayOperatation.class);
	@Autowired
	FormssiKpiDao formssiKpiDao;
	
	@Override
	public void operateKpi(java.util.List<FormssiKpi> formssiKpiList) {
		log.info("****** 导入正常数据 ******");
		formssiKpiDao.batchImport(formssiKpiList);
	}


}
