/**
 * FileName:FormssiKpiServiceImpl.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年11月7日下午9:44:17
 ********************************
 *Modifycation History:
 *date:2017年11月7日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formssi.bean.FormssiKpi;
import com.formssi.bean.Holiday;
import com.formssi.bean.ImportFlag;
import com.formssi.bean.KpiStyle;
import com.formssi.bean.Member;
import com.formssi.constans.Rank;
import com.formssi.constans.WorkType;
import com.formssi.dao.FormssiKpiDao;
import com.formssi.dao.HolidayDao;
import com.formssi.dao.ImportFlagDao;
import com.formssi.dao.KpiStyleDao;
import com.formssi.dao.MemberDao;
import com.formssi.excel.ExcelFactory;
import com.formssi.exception.Error;
import com.formssi.exception.GlobalException;
import com.formssi.exception.Result;
import com.formssi.kpi.KpiOpration;
import com.formssi.util.DateUtil;
import com.formssi.util.ParamMap;
import com.formssi.util.ParamUtil;

@Service("formssiKpiServiceImpl")
//使用另一套方案处理kpi，此类舍弃
@Deprecated
public class FormssiKpiServiceImpl{

	@Autowired
	FormssiKpiDao formssiKpiDao;
	
	@Autowired
	ImportFlagDao importFalgDao;
	
	@Autowired
	@Qualifier("leaveDayKpi")
	KpiOpration leaveDayKpi;
	
	@Autowired
	@Qualifier("extraWorkDayKpi")
	KpiOpration extraWorkDayKpi;
	
	@Autowired
	@Qualifier("normalWorkKpi")
	KpiOpration normalWorkKpi;
	
	@Autowired
	@Qualifier("specialLeaveHandle")
	KpiOpration specialLeaveHandle;
	
	@Autowired
	HolidayDao holidayDao;
	
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	ParamUtil<String, Object> paramUtil;
	
	@Autowired
	KpiStyleDao kpiStyleDao;
	
	/**
	 * Title:generate
	 * Description:
	 * @param params
	 * @param fileType
	 * @return
	 * @see com.formssi.service.FormssiKpiService#generate(java.util.Map, java.lang.String)
	 */
	public byte[] generate(Map<String, Object> params, String fileType) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			List<FormssiKpi> formssiKpiList = Optional.ofNullable(formssiKpiDao.list(params)).orElseThrow(Exception::new);
			ExcelFactory.getExcelHandler(fileType).writeExcel(outputStream, formssiKpiList);
			//ExcelUtil.writeExcel(fileType, outputStream, formssiKpiList);
		} catch (Exception e) {
			throw new GlobalException("数据库中未有数据！");
		}
		return outputStream.toByteArray();
	}
	
	@Deprecated
	@Transactional
	public void operateKpi(List<FormssiKpi> formssiKpiList) {
		List<FormssiKpi> newFormssiKpiList = new ArrayList<>();
		operateAllKpi(formssiKpiList,newFormssiKpiList,leaveDayKpi,specialLeaveHandle,extraWorkDayKpi);
	}

	/* 
	 * @see com.formssi.service.FormssiKpiService#batchImport(java.lang.String, java.lang.String)
	 */
	public Result batchImport(String date, String department) {
		ParamMap<String, Object> params = new ParamMap<>();
		int year = DateUtil.getDateArray(date)[0];
		int month = DateUtil.getDateArray(date)[1];
		if(importFalgDao.count(paramUtil.getParamMap(params,year, month, department,null,null)) > 0) {
			return Result.error(Error.HAVE_BATCHED);
		}else {
			List<FormssiKpi> formssiList = Optional.ofNullable(init(date, department)).orElse(new ArrayList<>());
			operateAllKpi(formssiList, null, normalWorkKpi);
			ImportFlag importFlag = new ImportFlag();
			initImportFlag(importFlag, year, month);
			importFalgDao.saveFlag(importFlag);
		}
		return Result.success();
	}
	
	@Transactional
	private void operateAllKpi(List<FormssiKpi> formssiList,List<FormssiKpi> newFormssiKpiList,KpiOpration... kpiOprations) {
		for (KpiOpration kpiOpration : kpiOprations) {
			kpiOpration.addKpi(formssiList,newFormssiKpiList);
			kpiOpration.updateKpi(formssiList,newFormssiKpiList);
			kpiOpration.deleteKpi(formssiList,newFormssiKpiList);
		}
	}

	private List<FormssiKpi> init(String date, String department) {
		final String DEFAULT = "default";
		int year = DateUtil.getDateArray(date)[0];
		int month = DateUtil.getDateArray(date)[1];
		List<FormssiKpi> formssiKpiList = new ArrayList<>();
		ParamMap<String, Object> params = new ParamMap<>();
		List<Member> memberList = memberDao.list(paramUtil.getParamMap(params, null, department,null));
		KpiStyle kpiStyle = Optional.ofNullable(kpiStyleDao.getKpiStyle(department)).orElse(kpiStyleDao.getKpiStyle(DEFAULT));
		List<Holiday> holidayList = Optional.ofNullable(holidayDao.getHolidayList(
				paramUtil.getParamMap(params, year, month,null,null,null))).orElse(new ArrayList<>());
		List<String> dates = DateUtil.getDatesList(year, month, holidayList);
		memberList.forEach(member -> {
			dates.forEach(strDate ->{
				FormssiKpi formssiKpi = new FormssiKpi();
				formssiKpi.setDate(strDate);
				formssiKpi.setMemberName(member.getMemberName());
				formssiKpi.setRank(member.getRank());
				formssiKpi.setNormalWork(1.0);
				Arrays.stream(Rank.values()).map(rank -> Rank.valueOf(member.getRank())).forEach(rank ->{
					formssiKpi.setProgramSerialNum(kpiStyle.getProgramSerialNumPrefix() + rank.getEngDescription());
					formssiKpi.setProgramName(kpiStyle.getProgramNamePrefix() + rank.getChiDescriptione());
				});
				formssiKpi.setYear(year);
				formssiKpi.setMonth(month);
				formssiKpi.setMemberId(member.getMemberId());
				formssiKpi.setVersion(1);
				formssiKpi.setDepartment(department);
				formssiKpi.setWorkType(WorkType.NormalWork.name());
				formssiKpiList.add(formssiKpi);
			});
		});
		return formssiKpiList;
	}
	
	private void initImportFlag(ImportFlag importFlag,Integer year,Integer month){
		importFlag.setImportFlag(true);
		importFlag.setImportTime(new Date());
		importFlag.setMonth(month);
		importFlag.setYear(year);
	}

	public List<FormssiKpi> getFormssiKpi(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}