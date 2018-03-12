import {setStore} from '../common/JS/mUtils.js'
import {
	ALERT_ERROR_MSG,
	ADD_MEMBER,
	DELETE_MEMBER,
	KPI_LIST_SUCCESS,
	UPDATE_KPI_NUM
} from './mutations_type.js'
import Vue from 'vue'
import axiosUtil from '../common/JS/axiosUtil.js'
import {getStore,DateFormate} from '../common/JS/mUtils.js'
import { Indicator,Toast } from 'mint-ui';
export default {
	//获取某员工KPI数据
	setKpiList : ({commit},kpiQueryCondition) => {
      axiosUtil("/sys/kpi/getPersonalKpiandHoliday",'post',kpiQueryCondition)
		.then(data => {
	  		let holidayList = data.holidays;
	  		let kpiListData = data.formssiKpiList;
	  		let statisticKpi = data.statisticKpi;
	  		commit(KPI_LIST_SUCCESS , kpiListData);
	  		commit('HOLIDAY_LIST_SUCCESS' , holidayList);
	  		commit(UPDATE_KPI_NUM , statisticKpi);
			  	
		});
	},
	//更新个人KPI
	updateKpi : ({commit,dispatch},kpiList) => {
		debugger;
		//对kpiList对象处理，符合后端接口需要的参数
		let kpiInputVO = [];
		//
		if (kpiList.length == 1 && kpiList[0].rank === undefined){
			kpiInputVO.push(kpiList[0]);			
		}else{
			kpiList.forEach(row => {
				//kpiType处理
				switch(row.kpiType){
					case 'EW':	//加班
						row.extraWork = row.kpiNum;
						row.normalWork = null;
						row.workType = 'ExtraWork';
					break;
					case 'L1':	//年假
						row.normalWork = row.kpiNum;
						row.extraWork = null;
						row.workType = 'Leave';
						row.leaveDayType = 'L1';
					break;
					case 'L2':	//事假
						row.normalWork = row.kpiNum;
						row.extraWork = null;
						row.workType = 'Leave';
						row.leaveDayType = 'L2';
					break;
					case 'L4':	//陪产假
						row.normalWork = row.kpiNum;
						row.extraWork = null;
						row.workType = 'Leave';
						row.leaveDayType = 'L4';
					break;
					case 'NE':	//未入场
						row.normalWork = row.kpiNum;
						row.extraWork = null;
						row.workType = 'NotEntry';
					break;
					case 'HE':	//未入场
						row.normalWork = row.kpiNum;
						row.extraWork = null;
						row.workType = 'HaveLeft';
					break;
				}
				let inputObj = {
					date : row.date,								//日期
					department : row.department,					//部门ID，登录用户名
					leaveDayType : row.leaveDayType===undefined ? '':row.leaveDayType,	//休假类型
					extraWork : row.extraWork===undefined ? '':row.extraWork,			//加班天数
					memberId : row.memberId,						//成员ID
					memberName : row.memberName,					//成员名称
					normalWork : row.normalWork===undefined ? '':row.normalWork,	//休假天数
					rank : row.rank,								//成员级别
					workType : row.workType							//工作类型
				}
				kpiInputVO.push(inputObj);
			})
		}
		console.dir(JSON.stringify(kpiInputVO));
	  	//请求接口
        axiosUtil("/sys/kpi/operateKpi",'get',{formssiKpiListStr:JSON.stringify(kpiInputVO) , kpiData:kpiList[0].kpiData})
			  .then(response => {
			  		let kpiQueryCondition = {
			  			date : kpiInputVO[0].date,
			  			memberName : kpiInputVO[0].memberName,
			  			department : kpiInputVO[0].department,
			  		};
            		dispatch('setKpiList',kpiQueryCondition);
			  });
	},
} 
