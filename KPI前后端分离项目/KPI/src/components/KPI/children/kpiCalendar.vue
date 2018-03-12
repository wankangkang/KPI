<template>
<div id="calendar" class="alet_container">
	<section class="tip_text_container">
	 <full-calendar 
	  :events="fcEvents" 
      first-day='1' lang="zh"
      @changeMonth="changeMonth"
      @eventClick="eventClick"
      @dayClick="dayClick"
      @moreClick="moreClick">
	 	<div slot="fc-header-left" >
	 		<a @click="downLoad">下载</a>
	 	</div>
	 	<div slot="fc-header-right" style="line-height: 25px;padding-top: 5px;">
	 		本月kpi总计：{{statisticKpi}}
	 	</div>
	 </full-calendar>

	<el-button type="primary" size="small" @click="closeKpi">关&nbsp;&nbsp;闭</el-button>
	</section>
   	<show-kpi-detail v-if="showKpiFlag" @closeTip="closeTip" :showKpiData="showKpiData" :userMsg="userMsg"></show-kpi-detail>
</div>
</template>

<script>
import fullCalendar from './calendar/fullCalendar'
import showKpiDetail from './showKpiDetail'
import {mapState,mapActions} from 'vuex'
import {getStore,DateFormate,cloneObj} from '../../../common/JS/mUtils.js'

	export default {
		data () {
			return {
				//kpi header
				chooseKpiDate:"",
				pickerOptions1: {
                	shortcuts: [{
			            text: '今天',
			            onClick(picker) {
			              picker.$emit('pick', new Date());
			            }
			          }, {
			            text: '昨天',
			            onClick(picker) {
			              const date = new Date();
			              date.setTime(date.getTime() - 3600 * 1000 * 24);
			              picker.$emit('pick', date);
			            }
			          }, {
			            text: '一周前',
			            onClick(picker) {
			              const date = new Date();
			              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
			              picker.$emit('pick', date);
			            }
			          }],
		          	disabledDate(time) {
		            	return time.getTime() > Date.now();
		          	}
		        },
				showKpiFlag : false,
	            personMsg : '',
	            kpiData : '',
	            fcEvents : []
	        }
		},
		computed : {
			...mapActions(['setKpiList']),
			...mapState(['kpiType','kpiList','holidayList','statisticKpi'])
		},
		props:['userMsg'],
		created(){
		},
		watch : {
			kpiList : function(newList){
				//异常kpi计算，先清0
				if(null==newList ){
					return;
				}
				let eventList = cloneObj(this.fcEvents);
				this.fcEvents = [];
				if(null!=eventList && eventList.length>0){
					//先清空当天所有非假期kpi
					for(let index=0; index<eventList.length; index++){
						let row = eventList[index];
						if(row.vacation == undefined){
							eventList.splice(index,1);
							index--;
						}
					}					
				}
				let vm_this = this;
				//给kpiList添加属性，使其在日历组件显示
				console.dir(newList);
				newList.forEach((row)=>{
					if(row.vacation == undefined){
						switch(row.workType){
							case 'ExtraWork':
								row.kpiType = 'EW';
								row.title = '加班' + row.extraWork;	
							break;
							case 'Leave' : 
								row.kpiType = row.programSerialNum.substr(row.programSerialNum.length - 2,2);
								this.kpiType.forEach(kpiTypeRow=>{
									if(kpiTypeRow.DATA == row.kpiType){
										row.title = kpiTypeRow.LABEL + row.normalWork;
									}
								})
							break;
							case 'NotEntry':
								row.kpiType = 'NE';
								row.title = '未入场' + row.normalWork;
							break;
							case 'HaveLeft':
								row.kpiType = 'HE';
								row.title = '已离场' + row.normalWork;
							break;
						}
						row.kpiNum = row.workType=='ExtraWork'?row.extraWork:row.normalWork;
						row.start = row.date;
						eventList.push(row);
					}
				});
				this.fcEvents = eventList;
			},
			//假期添加到日历
			holidayList : function(vacationDate){
				if(null!=this.fcEvents && this.fcEvents.length>0){
					//先删除holiday 
					for(let index=0; index < this.fcEvents.length; index++){
			  			let row = this.fcEvents[index]
			  			if(row.vacation !== undefined){
			  				this.fcEvents.splice(index,1);
			  				index--;
			  			}
			  		}
		    	}
				vacationDate.forEach( row => { 
					row.start = row.holidayDate.replace(new RegExp('/',"gm"),'-');
					this.fcEvents.push(row);
				});
			}
		},
	  	methods : {
	  		'downLoad' () {
	  			
	  		},
	  		//日历上的事件
	    	'changeMonth' (start, end, current) {
	    		//setKpiList 请求后端接口，获取KPI List
				let kpiQueryCondition = {
					date : current,
					memberName : this.userMsg.memberName,
					department : this.userMsg.department
				}
				this.$store.dispatch('setKpiList',kpiQueryCondition).then(()=>{
				});
	    		
		    },
		    'eventClick' (events, jsEvent, pos) {
		    	let currentDate = events[0].start;
		    	this.showKpiDetail(events,currentDate);
		    },
		    'dayClick' (day, events, jsEvent) {
		    	let currentDate = DateFormate(day);
		    	this.showKpiDetail(events,currentDate);
		    },
		    'moreClick' (day, events, jsEvent) {
		    	this.showKpiDetail(events);
		    },
		    
		    //日历事件触发，显示KPI详情
		    showKpiDetail (events,currentDate) {
		    	console.dir(events);
		    	this.userMsg.currentDate = currentDate;
		    	this.showKpiData = events;
		    	this.showKpiFlag = true;
		    },
		    closeTip(){
	                this.showKpiFlag = false;
	        },
	        closeKpi(){
	                this.$emit('closeKpi')
	        },
	        
	        //获取某月共有多少个工作日
	       /* getMonthWorkDay(year,month){ 
				 var date = new Date();
				 if (year.length > 0 && month.length > 0 ) 
				  {
				  date = new Date(year,month-1,1);
				  } else {
				  date = new Date(date.getFullYear(),date.getMonth(),1);
				  }
				  
				 var week = new Object();
				 week.week1 = new Object();
				 week.week2 = new Object();
				 week.week3 = new Object();
				 week.week4 = new Object();
				 week.week5 = new Object();
				 
				 //本月第一天是周几 
				 week.today = date.getDay();
				 if (week.today == 0) 
				 {
				  date.setDate(date.getDate()+1);
				  week.today = date.getDay();
				 }
				 
				 //本月第一周工作日
				 week.week1.workdays = 5-week.today+1;
				 if (week.week1.workdays<0) week.week1.workdays=0;
				 //本月第一周起始日期
				 week.week1.start = date.getDate()+"/"+(date.getMonth()+1);
				 //本月第一周结束日期
				 date.setDate(date.getDate()+(1+week.week1.workdays));
				 week.week1.end = date.getDate()+"/"+(date.getMonth()+1);
				
				 
				        //本月第二周起始日期
				 date.setDate(date.getDate()+1);
				 week.week2.workdays = 5;
				 week.week2.start = date.getDate()+"/"+(date.getMonth()+1);
				 //本月第二周结束日期
				 date.setDate(date.getDate()+(1+week.week2.workdays));
				 week.week2.end = date.getDate()+"/"+(date.getMonth()+1);
				
				 
				 //本月第三周起始日期
				 date.setDate(date.getDate()+1);
				 week.week3.workdays = 5;
				 week.week3.start = date.getDate()+"/"+(date.getMonth()+1);
				 //本月第三周结束日期
				 date.setDate(date.getDate()+(1+week.week3.workdays));
				 week.week3.end = date.getDate()+"/"+(date.getMonth()+1);
				
				
				 //本月第四周起始日期
				 date.setDate(date.getDate()+1);
				 week.week4.workdays = 5;
				 week.week4.start = date.getDate()+"/"+(date.getMonth()+1);
				 //本月第四周结束日期
				 date.setDate(date.getDate()+(1+week.week4.workdays));
				 week.week4.end = date.getDate()+"/"+(date.getMonth()+1);
				
				
				 date.setDate(date.getDate()+1);
				 week.week5.start = date.getDate()+"/"+(date.getMonth()+1);
				 
				 //计算月底日期 
				        var nextmonth = new Date(date.getFullYear(),date.getMonth()+1,1);
				 var monthlastday = new Date(nextmonth-86400000); 
				 
				 date.setDate(date.getDate()+6);
				      if (date <= monthlastday)
				 {
				  week.week5.workdays = 5;
				  week.week5.end = date.getDate()+"/"+(date.getMonth()+1); 
				  if (date < monthlastday) {
				   week.week6 = new Object();
				   date.setDate(date.getDate()+1);
				   week.week6.start = date.getDate()+"/"+(date.getMonth()+1);
				   week.week6.end = monthlastday.getDate()+"/"+(date.getMonth()+1);
				   week.week6.workdays = monthlastday.getDay();
				  }
				 } else {
				  week.week5.end = monthlastday.getDate()+"/"+(monthlastday.getMonth()+1);
				  week.week5.workdays = monthlastday.getDay();
				  if (week.week5.workdays >5 ) week.week5.workdays = 5;   
				 }
				 return week.week1.workdays + week.week2.workdays + week.week3.workdays + week.week4.workdays + week.week5.workdays;
				}*/
	  },
	  components : {
	    fullCalendar,
	  	showKpiDetail
	  }
	}
</script>

<style lang="scss" scoped>
	@keyframes tipMove{
       0%   { transform: scale(1) }
       80%  { transform: scale(.9) }
       90%  { transform: scale(1.1) }
       100% { transform: scale(1) }
    }
	.alet_container{
    	position: fixed;
    	top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        z-index: 200;
    }
    .tip_text_container{
        position: absolute;
        border:1px solid black;
        top: 5%;
        left:2.5%;
        width: 95%;
        padding: 5px;
        animation: tipMove .4s ;
        background-color: rgba(255,255,255,1);
    }
    
    //change elementUI
    .el-date-editor.el-input, .el-date-editor.el-input__inner {
    	width: 100px;
	}
</style>