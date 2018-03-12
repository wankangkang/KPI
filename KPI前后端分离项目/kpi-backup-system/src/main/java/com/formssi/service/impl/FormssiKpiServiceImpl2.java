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
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formssi.bean.FormssiKpi;
import com.formssi.bean.Holiday;
import com.formssi.bean.ImportFlag;
import com.formssi.bean.KpiBalanceInfo;
import com.formssi.bean.KpiData;
import com.formssi.bean.KpiStyle;
import com.formssi.bean.Member;
import com.formssi.constans.Rank;
import com.formssi.constans.WorkType;
import com.formssi.dao.FormssiKpiDao;
import com.formssi.dao.HolidayDao;
import com.formssi.dao.ImportFlagDao;
import com.formssi.dao.KpiBalanceDao;
import com.formssi.dao.KpiStyleDao;
import com.formssi.dao.MemberDao;
import com.formssi.excel.ExcelFactory;
import com.formssi.exception.Error;
import com.formssi.exception.GlobalException;
import com.formssi.exception.Result;
import com.formssi.kpi2.KpiOpration2;
import com.formssi.service.FormssiKpiService;
import com.formssi.util.DateUtil;
import com.formssi.util.ParamMap;
import com.formssi.util.ParamUtil;

@Service("formssiKpiServiceImpl2")
public class FormssiKpiServiceImpl2 implements FormssiKpiService{

	@Autowired
	FormssiKpiDao formssiKpiDao;
	
	@Autowired
	ImportFlagDao importFalgDao;
	
	@Autowired
	@Qualifier("extraDayOperation")
	KpiOpration2 extraDayOperation;
	
	@Autowired
	@Qualifier("leaveDayOperation")
	KpiOpration2 leaveDayOperation;
	
	@Autowired
	@Qualifier("normalDayOperation")
	KpiOpration2 normalDayOperation;
	
	@Autowired
	@Qualifier("EntryOrLeaveDayOperation")
	KpiOpration2 entryOrLeaveDayOperation;
	
	@Autowired
	ParamUtil<String, Object> paramUtil;
	
	@Autowired
	KpiStyleDao kpiStyleDao;
	
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	HolidayDao holidayDao;
	
	@Autowired
	KpiBalanceDao kpiBalanceDao;
	
	@Override
	public byte[] generate(Map<String, Object> params, String fileType) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			List<FormssiKpi> formssiKpiList = Optional.ofNullable(formssiKpiDao.getAllNotLeftData(params)).orElseThrow(Exception::new);
			ExcelFactory.getExcelHandler(fileType).writeExcel(outputStream, formssiKpiList);
			//ExcelUtil.writeExcel(fileType, outputStream, formssiKpiList);
		} catch (Exception e) {
			throw new GlobalException("数据库中未有数据！");
		}
		return outputStream.toByteArray();
	}
	
	@Override
	@Transactional
	public void operateKpi(List<FormssiKpi> formssiKpiList,double kpiData) {
		ParamMap<String, Object> params = new ParamMap<>();
		FormssiKpi formssiKpi = Optional.ofNullable(formssiKpiList.get(0)).orElse(new FormssiKpi());
		String date = formssiKpi.getDate();
		String department = formssiKpi.getDepartment();
		Long memberId = formssiKpi.getMemberId();
		int year = DateUtil.getDateArray(date)[0];
		int month = DateUtil.getDateArray(date)[1];
		if (importFalgDao.count(paramUtil.getParamMap(params,year, month, department,null,null)) == 0) {
			throw new GlobalException("该年月尚未数据未导入");
		}
		//删除数据重新添加复用为后续sql准备数据
		params.clear();
		formssiKpiDao.removeNotNormal(paramUtil.getKpiParamMap(params, date.replace("-", "/"), formssiKpiList.get(0).getMemberName(), department));
		//如果存在正常工作日数据，还原该数据
		FormssiKpi formssiKpiGetByDB = formssiKpiDao.get(params);
		if (formssiKpiGetByDB != null) {
			formssiKpiGetByDB.setNormalWork(1.0);
			formssiKpiDao.update(formssiKpiGetByDB);
		}
		params.clear();
		double kpiBalance = kpiBalanceDao.getKpiBalanceInfo(memberId).getKpiBalance();
		if ((kpiBalance+kpiData) < 0) {
			kpiBalanceDao.updateKpiBalanceInfo(paramUtil.getKpiBalanceParamMap(params, memberId, 0));
		}else {
			kpiBalanceDao.updateKpiBalanceInfo(paramUtil.getKpiBalanceParamMap(params, memberId, kpiBalance+kpiData));
		}
		//判断没有删除操作时执行其他操作，注：删除时前端默认不传工作类型
		if (formssiKpiList.stream().filter(
				f -> f.getWorkType()==null || "".equals(f.getWorkType())).collect(Collectors.toList()).size() == 0) {
			operateAllKpi(formssiKpiList,extraDayOperation,leaveDayOperation,entryOrLeaveDayOperation);
		}
	}

	/* 
	 * @see com.formssi.service.FormssiKpiService#batchImport(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public Result batchImport(String date, String department) {
		ParamMap<String, Object> params = new ParamMap<>();
		int year = DateUtil.getDateArray(date)[0];
		int month = DateUtil.getDateArray(date)[1];
		if(importFalgDao.count(paramUtil.getParamMap(params,year, month, department,null,null)) > 0) {
			return Result.error(Error.HAVE_BATCHED);
		}else {
			List<FormssiKpi> formssiList = Optional.ofNullable(init(date, department)).orElse(new ArrayList<>());
			operateAllKpi(formssiList, normalDayOperation);
			ImportFlag importFlag = new ImportFlag();
			initImportFlag(importFlag, year, month,department);
			importFalgDao.saveFlag(importFlag);
		}
		return Result.success();
	}

	@Override
	public List<FormssiKpi> getFormssiKpi(Map<String, Object> params) {
		return formssiKpiDao.list(params);
	}
	
	@Override
	public Result getKpiData(Map<String, Object> params){
		List<KpiData> kpiDataList = Optional.ofNullable(formssiKpiDao.getKpiData(params)).orElse(new ArrayList<>());
		kpiDataList.parallelStream().forEach(kpiData -> kpiData.setTotalKpi(kpiData.getExtra()+kpiData.getNormal()));
		return Result.returnObj("kpiDataList", kpiDataList);
	}
	
	@Override
	public Result getKpiBalance(String department) {
		return Result.returnObj("kpiBalanceInfoList", kpiBalanceDao.getKpiBalanceInfoList(department));
	}
	
	@Override
	public Result updateKpiBalanceInfo(List<KpiBalanceInfo> kbiList) {
		
		return kpiBalanceDao.batchUpdate(kbiList) == 0 ? Result.error(Error.OPERATION_FAIL):Result.success();
	}
	
	//@Transactional
	private void operateAllKpi(List<FormssiKpi> formssiKpiList, KpiOpration2... kpiOpration2) {
		for (KpiOpration2 kpiOpration : kpiOpration2) {
			kpiOpration.operateKpi(formssiKpiList);
		}
	}

	public List<FormssiKpi> init(String date, String department) {
		final String DEFAULT = "default";
		int year = DateUtil.getDateArray(date)[0];
		int month = DateUtil.getDateArray(date)[1];
		FormssiKpi formssiKpi = new FormssiKpi();
		List<FormssiKpi> formssiKpiList = new ArrayList<>();
		ParamMap<String, Object> params = new ParamMap<>();
		List<Member> memberList = memberDao.list(paramUtil.getParamMap(params, null, department,null));
		KpiStyle kpiStyle = Optional.ofNullable(kpiStyleDao.getKpiStyle(department)).orElse(kpiStyleDao.getKpiStyle(DEFAULT));
		List<Holiday> holidayList = Optional.ofNullable(holidayDao.getHolidayList(
				paramUtil.getParamMap(params, year, month,null,null,null))).orElse(new ArrayList<>());
		List<String> dates = DateUtil.getDatesList(year, month, holidayList);
		memberList.forEach(member -> {
			dates.forEach(strDate ->{
				FormssiKpi formssiKpiClone = (FormssiKpi) formssiKpi.clone();
				formssiKpiClone.setDate(strDate);
				formssiKpiClone.setMemberName(member.getMemberName());
				formssiKpiClone.setRank(member.getRank());
				formssiKpiClone.setNormalWork(1.0);
				Arrays.stream(Rank.values()).map(rank -> Rank.valueOf(member.getRank())).forEach(rank ->{
					formssiKpiClone.setProgramSerialNum(kpiStyle.getProgramSerialNumPrefix() + rank.getEngDescription());
					formssiKpiClone.setProgramName(kpiStyle.getProgramNamePrefix() + rank.getChiDescriptione());
				});
				formssiKpiClone.setYear(year);
				formssiKpiClone.setMonth(month);
				formssiKpiClone.setMemberId(member.getMemberId());
				formssiKpiClone.setVersion(1);
				formssiKpiClone.setDepartment(department);
				formssiKpiClone.setWorkType(WorkType.NormalWork.name());
				formssiKpiClone.setVersion2(1);
				formssiKpiList.add(formssiKpiClone);
			});
		});
		return formssiKpiList;
	}
	
	private void initImportFlag(ImportFlag importFlag,Integer year,Integer month,String department){
		importFlag.setImportFlag(true);
		importFlag.setImportTime(new Date());
		importFlag.setMonth(month);
		importFlag.setYear(year);
		importFlag.setDepartment(department);
	}

}