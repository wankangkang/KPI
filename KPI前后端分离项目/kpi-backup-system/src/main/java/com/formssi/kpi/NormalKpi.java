/**
 * FileName:NormalKpi.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年12月26日下午10:24:03
 ********************************
 *Modifycation History:
 *date:2017年12月26日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.kpi;

import java.util.List;

import com.formssi.bean.FormssiKpi;

public interface NormalKpi {

	List<FormssiKpi> init(String date,String department);
}
