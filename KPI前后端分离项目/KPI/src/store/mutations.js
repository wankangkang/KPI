import {
	ALERT_ERROR_MSG,
	KPI_LIST_SUCCESS,
	UPDATE_KPI_NUM
} from './mutations_type'



export default{
	KPI_LIST_SUCCESS : (state, kpiList )=> {
		state.kpiList = kpiList;
		
	},
	HOLIDAY_LIST_SUCCESS : (state, holidayList)=> {
		state.holidayList = holidayList;
		
	},
	ALERT_ERROR_MSG : (state,alertErrorMsg) => {
		state.alertErrorMsg = alertErrorMsg;
	},
	UPDATE_KPI_NUM : (state,statisticKpi) =>{
		state.statisticKpi = statisticKpi;
	}
	
	
}
