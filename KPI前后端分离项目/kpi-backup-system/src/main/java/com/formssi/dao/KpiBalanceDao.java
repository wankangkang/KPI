/**
 * FileName:KpiBalanceDao.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月20日下午10:45:40
 ********************************
 *Modifycation History:
 *date:2018年1月20日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.dao;

import java.util.List;
import java.util.Map;

import com.formssi.bean.KpiBalanceInfo;

/**
 * @author Bill
 *
 */
public interface KpiBalanceDao {

	List<KpiBalanceInfo> getKpiBalanceInfoList(String department);
	
	int saveKpiBalanceInfo(KpiBalanceInfo kpiBalanceInfo);
	
	int updateKpiBalanceInfo(Map<String, Object>params);
	
	KpiBalanceInfo getKpiBalanceInfo(Long memberId);
	
	int deleteKpiBalanceInfo(Long memberId);
	
	int batchUpdate(List<KpiBalanceInfo> kbiList);
}
