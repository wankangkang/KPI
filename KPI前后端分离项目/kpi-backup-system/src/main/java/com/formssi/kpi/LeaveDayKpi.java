package com.formssi.kpi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formssi.annotation.FilterKpi;
import com.formssi.bean.FormssiKpi;
import com.formssi.constans.Operation;
import com.formssi.constans.WorkType;
import com.formssi.dao.FormssiKpiDao;

/**
 * @author Bill
 * @Description: 休假天数非1的kpi操作
 *
 */
@Service("leaveDayKpi")
@Deprecated
public class LeaveDayKpi extends AbstractKpi{

	@Autowired
	FormssiKpiDao formssiKpiDao;
	
	@FilterKpi(workType=WorkType.Leave,exceptName= {"normalWork"},exceptValue= {"1.0"},operation=Operation.Add,newListName = "newFormssiList",oldListName="formssiKpiList")
	public void addKpi(List<FormssiKpi> formssiKpiList,List<FormssiKpi> newFormssiList) {
		System.out.println("****LeaveDayKpi : addKPi");
		if (newFormssiList.size() != 0) {
			//初始化休假数据
			initKpi(newFormssiList);
			//批量导入
			formssiKpiDao.batchImport(newFormssiList);
			//初始化正常上班数据
			initKpi(changeWorkType(newFormssiList));
			//批量更新
			formssiKpiDao.updateBatch(newFormssiList);
		}
	}
	
	@FilterKpi(workType=WorkType.Leave,exceptName= {"normalWork"},exceptValue= {"1.0"},operation=Operation.Update,newListName = "newFormssiList",oldListName="formssiKpiList")
	public void updateKpi(List<FormssiKpi> formssiKpiList,List<FormssiKpi> newFormssiList) {
		System.out.println("****LeaveDayKpi : updateKpi");
		if (newFormssiList.size() != 0) {
			//初始化休假数据
			initKpi(newFormssiList);
			//批量休假数据
			formssiKpiDao.updateBatch(newFormssiList);
			//初始化正常上班数据
			initKpi(changeWorkType(newFormssiList));
			//批量更新
			formssiKpiDao.updateBatch(newFormssiList);
		}
	}
	
	@FilterKpi(workType=WorkType.Leave,exceptName= {"normalWork"},exceptValue= {"1.0"},operation=Operation.Delete,newListName = "newFormssiList",oldListName="formssiKpiList")
	public void deleteKpi(List<FormssiKpi> formssiKpiList,List<FormssiKpi> newFormssiList) {
		System.out.println("****LeaveDayKpi : deleteKpi");
		if (newFormssiList.size() != 0) {
			//批量删除数据
			formssiKpiDao.batchRemove(newFormssiList);
			//初始化需导回的正常上班时间
			initKpi(changeWorkType(newFormssiList));
			formssiKpiDao.batchImport(newFormssiList);
		}
	}

}
