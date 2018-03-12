import Vue from 'vue'
import Vuex from 'vuex'
import actions from './actions'
import getters from './getters'
import mutations from './mutations'
Vue.use(Vuex)
const state = {
	kpiList:[],
	holidayList:[],
	userListData:[],
	kpiType:[
		{LABEL:'加班',DATA:'EW'},			//加班	第一位：L：Leave休假 ，N：NormalWork正常工作， E：ExtraWork加班， NE:NotEntry未入场, LE:HaveLeft
		{LABEL:'年假',DATA:'L1'},			//年假	第二位：Leave休假：0：年假 1：事假 2：陪产假4
		{LABEL:'事假',DATA:'L2'},			//事假
		{LABEL:'陪产假',DATA:'L4'},		//陪产假
		{LABEL:'未入场',DATA:'NE'},		//未入场
		{LABEL:'已离场',DATA:'HE'}		//已离场
	],
	rankList:[
		{LABEL:'SA',DATA:'SA'},
		{LABEL:'AP',DATA:'AP'},
		{LABEL:'PR',DATA:'PR'},
		{LABEL:'T1',DATA:'T1'}
	],
	statisticKpi : ''
}

export const store = new Vuex.Store({
	strict : true,
	state,
	actions,
	getters,
	mutations
})
