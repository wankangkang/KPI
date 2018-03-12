package com.formssi.service;

import com.formssi.bean.KpiStyle;

public interface KpiStyleService {

	/**
	 * @Title:saveKpiStyle
	 * @Description:保存项目信息
	 * @param:@param kpiStyle
	 * @param:@return
	 * @return:KpiStyle
	 * @throws
	 */
	KpiStyle saveKpiStyle(KpiStyle kpiStyle);
	
	/**
	 * @Title:getKpiStyle
	 * @Description:获取指定部门的项目名称和项目编号
	 * @param:@param department  部门名称
	 * @param:@return
	 * @return:KpiStyle
	 * @throws
	 */
	KpiStyle getKpiStyle(String department);
}
