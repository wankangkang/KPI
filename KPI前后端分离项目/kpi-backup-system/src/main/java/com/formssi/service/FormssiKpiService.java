/**
 * FileName:SmpKpiService.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年11月7日下午9:42:49
 ********************************
 *Modifycation History:
 *date:2017年11月7日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.service;

import java.util.List;
import java.util.Map;

import com.formssi.bean.FormssiKpi;
import com.formssi.bean.KpiBalanceInfo;
import com.formssi.exception.Result;


public interface FormssiKpiService {
	
	/**
	 * @Title:generate
	 * @Description:生成Kpi功能
	 * @param:@param params  查询参数
	 * @param:@param fileType 生成的文件类型
	 * @param:@return
	 * @return:byte[]
	 * @throws
	 */
	byte[] generate(Map<String, Object> params,String fileType);
	
	/**
	 * @Title:operateKpi
	 * @Description:操作kpi数据方法，包括假日数据，加班数据的增删查改
	 * @param:@param formssiKpiList  操作的kpi数据集合
	 * @param:@param kpiData  此次操作与版面显示的kpi的差额
	 * @return:void
	 * @throws
	 */
	void operateKpi(List<FormssiKpi> formssiKpiList,double kpiData);
	
	/**
	 * @Title:batchImport
	 * @Description:批量导入指定月份正常数据
	 * @param:@param date  指定月份的任意日期
	 * @param:@param department  部门名称
	 * @param:@return
	 * @return:Result
	 * @throws
	 */
	Result batchImport(String date,String department);
	
	/**
	 * @Title:getFormssiKpi
	 * @Description:根据条件获取kpi数据
	 * @param:@param params
	 * @param:@return
	 * @return:List<FormssiKpi>
	 * @throws
	 */
	List<FormssiKpi> getFormssiKpi(Map<String, Object> params);
	
	/**
	 * @Title:getKpiData
	 * @Description:获取kpi的统计值
	 * @param:@param department
	 * @param:@return
	 * @return:List<KpiData>
	 * @throws
	 */
	Result getKpiData(Map<String, Object> params);
	
	Result getKpiBalance(String department);
	
	Result updateKpiBalanceInfo(List<KpiBalanceInfo> kbiList);
}
