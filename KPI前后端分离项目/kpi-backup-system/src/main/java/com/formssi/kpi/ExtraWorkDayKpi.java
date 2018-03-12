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
 * @Description:加班的kpi操作
 *
 */
@Service("extraWorkDayKpi")
@Deprecated
public class ExtraWorkDayKpi extends AbstractKpi{

	@Autowired
	FormssiKpiDao formssiKpiDao;
	
	@FilterKpi(workType=WorkType.ExtraWork,operation=Operation.Add,newListName = "newFormssiList",oldListName="fformssiKpiList")
	public void addKpi(List<FormssiKpi> fformssiKpiList,List<FormssiKpi> newFormssiList) {
		System.out.println("****ExtraWorkDayKpi : addKPi");
		if (newFormssiList.size() != 0) {
			initKpi(newFormssiList);
			formssiKpiDao.batchImport(newFormssiList);
		}
	}
	
	@FilterKpi(workType=WorkType.ExtraWork,operation=Operation.Update,newListName = "newFormssiList",oldListName="formssiKpiList")
	public void updateKpi(List<FormssiKpi> formssiKpiList,List<FormssiKpi> newFormssiList) {
		System.out.println("****ExtraWorkDayKpi : updateKpi");
		//假如休假从1修改为非1时需再导入正常班数据，未处理
		if (newFormssiList.size() != 0) {
			initKpi(newFormssiList);
			formssiKpiDao.updateBatch(newFormssiList);
		}
	}
	
	@FilterKpi(workType=WorkType.ExtraWork,operation=Operation.Delete,newListName = "newFormssiList",oldListName="formssiKpiList")
	public void deleteKpi(List<FormssiKpi> formssiKpiList,List<FormssiKpi> newFormssiList) {
		System.out.println("****ExtraWorkDayKpi : deleteKpi");
		if (newFormssiList.size() != 0) {
			initKpi(newFormssiList);
			formssiKpiDao.batchRemove(newFormssiList);
		}
	}
}
