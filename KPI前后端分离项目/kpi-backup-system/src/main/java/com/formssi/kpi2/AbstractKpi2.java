package com.formssi.kpi2;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import com.formssi.bean.FormssiKpi;
import com.formssi.bean.KpiStyle;
import com.formssi.constans.LeaveDayType;
import com.formssi.constans.Rank;
import com.formssi.constans.WorkType;
import com.formssi.dao.KpiStyleDao;
import com.formssi.util.DateUtil;

public abstract class AbstractKpi2 implements KpiOpration2 {

	@Autowired
	KpiStyleDao kpiStyleDao;

	/**
	 * @Title:InitKpi
	 * @Description:初始化前端传入的所有Kpi的各个值
	 * @param:@param formssiKpiList
	 * @return:void
	 * @throws
	 */
	protected void initKpi(FormssiKpi formssiKpi) {
		KpiStyle kpiStyle = new KpiStyle();
		kpiStyle = kpiStyleDao.getKpiStyle(formssiKpi.getDepartment());
		
		//如果是加班或正常上班，初始化项目编号和项目名称和正常上班相同
		if (WorkType.valueOf(formssiKpi.getWorkType()) == WorkType.ExtraWork || WorkType.valueOf(formssiKpi.getWorkType()) == WorkType.NormalWork
				|| WorkType.valueOf(formssiKpi.getWorkType()) == WorkType.NotEntry || WorkType.valueOf(formssiKpi.getWorkType()) == WorkType.HaveLeft) {
			setNormalProgramDes(formssiKpi, kpiStyle);
		}else if (WorkType.valueOf(formssiKpi.getWorkType()) == WorkType.Leave) {
			setLeaveProgramDes(formssiKpi, kpiStyle);
		}
		formssiKpi.setDate(formssiKpi.getDate().replace("-", "/"));
		formssiKpi.setYear(DateUtil.getDateArray(formssiKpi.getDate())[0]);
		formssiKpi.setMonth(DateUtil.getDateArray(formssiKpi.getDate())[1]);
		formssiKpi.setVersion(1);
		formssiKpi.setVersion2(1);
	}
	
	/**
	 * @Title:setNormalProgramDes
	 * @Description:根据级别设置正常的上班或加班的项目名称和项目编号
	 * @param:@param formssiKpi  Kpi所有描述的对象
	 * @param:@param kpiStyle    具体项目组对应的Kpi项目名称和项目编号
	 * @return:void
	 * @throws
	 */
	private void setNormalProgramDes(FormssiKpi formssiKpi,KpiStyle kpiStyle) {
		Arrays.stream(Rank.values()).map(e -> Rank.valueOf(formssiKpi.getRank())).forEach(e -> 
		{
			formssiKpi.setProgramSerialNum(kpiStyle.getProgramSerialNumPrefix() + e.getEngDescription());
			formssiKpi.setProgramName(kpiStyle.getProgramNamePrefix() + e.getChiDescriptione());
		});
	}
	
	/**
	 * @Title:setNormalProgramDes
	 * @Description:根据休假类型设置休假的项目名称和项目编号
	 * @param:@param formssiKpi  Kpi所有描述的对象
	 * @param:@param kpiStyle    具体项目组对应的Kpi项目名称和项目编号
	 * @return:void
	 * @throws
	 */
	private void setLeaveProgramDes(FormssiKpi formssiKpi,KpiStyle kpiStyle) {
		Arrays.stream(LeaveDayType.values()).map(e -> LeaveDayType.valueOf(formssiKpi.getLeaveDayType())).forEach(e -> 
		{
			formssiKpi.setProgramSerialNum(kpiStyle.getProgramSerialNumPrefix() + e.getEngDescription());
			formssiKpi.setProgramName(kpiStyle.getProgramNamePrefix() + e.getChiDescriptione());
		});
	}
	
	protected FormssiKpi  changeWorkType(FormssiKpi formssiKpi) {
		formssiKpi.setWorkType(WorkType.NormalWork.name());
		formssiKpi.setNormalWork(1-formssiKpi.getNormalWork());
		return formssiKpi;
	}
}
