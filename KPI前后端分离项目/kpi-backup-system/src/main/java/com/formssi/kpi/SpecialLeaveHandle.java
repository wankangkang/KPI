package com.formssi.kpi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formssi.annotation.FilterKpi;
import com.formssi.bean.FormssiKpi;
import com.formssi.constans.Operation;
import com.formssi.constans.WorkType;
import com.formssi.dao.FormssiKpiDao;

/**
 * @author Bill
 * @Description: 休假天数1的kpi操作
 *
 */
@Service("specialLeaveHandle")
@Deprecated
public class SpecialLeaveHandle extends AbstractKpi {

	@Autowired
	FormssiKpiDao formssiKpiDao;
	
	@Override
	@Transactional
	@FilterKpi(workType=WorkType.Leave,name= {"normalWork"},value= {"1.0"},operation=Operation.Add,newListName = "newFormssiList",oldListName="formssiKpiList")
	public void addKpi(List<FormssiKpi> formssiKpiList,List<FormssiKpi> newFormssiList) {
		System.out.println("****SpecialLeaveHandle : addKpi");
		if (newFormssiList.size() != 0) {
			//初始化休假数据
			initKpi(newFormssiList);
			formssiKpiDao.batchImport(newFormssiList);
			for (FormssiKpi formssiKpi : newFormssiList) {
				formssiKpi.setWorkType(WorkType.NormalWork.name());
			}
			formssiKpiDao.batchRemove(newFormssiList);
		}
	}
	
	@Override
	@Transactional
	@FilterKpi(workType=WorkType.Leave,name= {"normalWork"},value= {"1.0"},operation=Operation.Update,newListName = "newFormssiList",oldListName="formssiKpiList")
	public void updateKpi(List<FormssiKpi> formssiKpiList,List<FormssiKpi> newFormssiList) {
		System.out.println("****SpecialLeaveHandle : updateKpi");
		if (newFormssiList.size() != 0) {
			//初始化休假数据
			initKpi(newFormssiList);
			formssiKpiDao.updateBatch(newFormssiList);
			for (FormssiKpi formssiKpi : newFormssiList) {
				formssiKpi.setWorkType(WorkType.NormalWork.name());
			}
			formssiKpiDao.batchRemove(newFormssiList);
		}
	}
	
	@Override
	@Transactional
	@FilterKpi(workType=WorkType.Leave,name= {"normalWork"},value= {"1.0"},operation=Operation.Delete,newListName = "newFormssiList",oldListName="formssiKpiList")
	public void deleteKpi(List<FormssiKpi> formssiKpiList,List<FormssiKpi> newFormssiList) {
		System.out.println("****SpecialLeaveHandle : deleteKpi");
		if (newFormssiList.size() != 0) {
			formssiKpiDao.batchRemove(newFormssiList);
			initKpi(changeWorkType(newFormssiList));
			formssiKpiDao.batchImport(newFormssiList);
		}
	}

}
