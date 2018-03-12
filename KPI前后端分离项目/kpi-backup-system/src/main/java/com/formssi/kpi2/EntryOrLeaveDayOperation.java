/**
 * FileName:NotEntryDayOperation.java
 * @Description:操作未入场情况
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月8日下午9:31:03
 ********************************
 *Modifycation History:
 *date:2018年1月8日
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
@Component("EntryOrLeaveDayOperation")
public class EntryOrLeaveDayOperation extends AbstractKpi2{

	private static final Logger log = LoggerFactory.getLogger(EntryOrLeaveDayOperation.class);
	@Autowired
	FormssiKpiDao formssiKpiDao;
	
	@Override
	public void operateKpi(List<FormssiKpi> formssiKpiList) {
		log.info("****** 更新未入场或已离场数据 ******");
		//根据业务需求，本处的List集合中每个工作类型一定只对应一个对象
		List<FormssiKpi> newFormssiKpiList = formssiKpiList.stream().filter(
				f -> WorkType.NotEntry == WorkType.valueOf(f.getWorkType()) || WorkType.HaveLeft == WorkType.valueOf(f.getWorkType())).collect(Collectors.toList());
		if (newFormssiKpiList.size() > 0) {
			FormssiKpi formssiKpi = newFormssiKpiList.get(0);
			if (formssiKpi.getNormalWork() != 0) {
				initKpi(formssiKpi);
				formssiKpiDao.save(formssiKpi);
				changeWorkType(formssiKpi);
				formssiKpiDao.updateNoLock(formssiKpi);
			}
		}
	}
}
