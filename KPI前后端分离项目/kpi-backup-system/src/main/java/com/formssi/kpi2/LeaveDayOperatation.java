/**
 * FileName:LeaveDayOperate.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年12月30日下午10:39:05
 ********************************
 *Modifycation History:
 *date:2017年12月30日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.kpi2;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.formssi.bean.FormssiKpi;
import com.formssi.constans.WorkType;
import com.formssi.dao.FormssiKpiDao;


/**
 * @author Bill
 *
 */
@Component("leaveDayOperation")
public class LeaveDayOperatation extends AbstractKpi2 {

	private static final Logger log = LoggerFactory.getLogger(LeaveDayOperatation.class);
	@Autowired
	FormssiKpiDao formssiKpiDao;
	
	@Override
	public void operateKpi(List<FormssiKpi> formssiKpiList) {
		log.info("****** 更新休假数据 ******");
		List<FormssiKpi> newFormssiKpiList = formssiKpiList.stream().filter(
				f -> WorkType.Leave == WorkType.valueOf(f.getWorkType())).collect(Collectors.toList());
		if (newFormssiKpiList.size() > 0) {
			newFormssiKpiList.parallelStream().forEach(formssiKpi -> {
				if (formssiKpi.getNormalWork() != 0) {
					initKpi(formssiKpi);
				}
			});
			formssiKpiDao.batchImport(newFormssiKpiList);
			List<Double> normalWorkList = newFormssiKpiList.parallelStream().map(FormssiKpi ::getNormalWork).collect(Collectors.toList());
			double normalWorkValue = 1;
			for (Double normalWork : normalWorkList) {
				normalWorkValue = normalWorkValue - normalWork;
			}
			FormssiKpi formssiKpi = newFormssiKpiList.get(0);
			changeWorkType(formssiKpi);
			formssiKpi.setNormalWork(normalWorkValue);
			formssiKpiDao.updateNoLock(formssiKpi);
		}
	}
}
