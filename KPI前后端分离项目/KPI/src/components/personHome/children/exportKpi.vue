<template>
	<div>
		<mt-header fixed title="KPI导入/导出">
			<router-link to="/personHome" slot="left">
			    <mt-button icon="back"></mt-button>
			</router-link>
		</mt-header>
		<div style="margin-top:40px ;">
			<mt-radio style="text-decoration:none;" title="文件后缀" v-model="fileType" :options="['xls', 'xlsx']">
			</mt-radio>
			<label class="mint-radiolist-title">选择KPI月份</label>
			<mt-field style="text-decoration:none;" readonly label="KPI月份：" placeholder="导出KPI月份" :value="kpiMonth">
				<mt-picker :visibleItemCount="visibleItemCount"  :slots="slots" @change="onValuesChange" ></mt-picker>
			</mt-field>
			<label class="mint-radiolist-title">级别对应KPI列表</label>
			<div v-if="kpiDataList!=null">
				<mt-cell style="text-decoration:none;" :title="row.rank" :value="row.totalKpi" v-for="row in kpiDataList" :key="row.rank"></mt-cell>
				<mt-cell v-show="noKpiDataFlag" style="text-decoration:none;" title="无数据" ></mt-cell>
			</div>
			
			<div style="text-align: center;">
				<mt-button style="margin-top:40px ;"  type="primary" @click.native="importNormalKpi">导入</mt-button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<mt-button style="margin-top:40px ;"  type="primary" @click.native="exportKpi">导出EXCEL</mt-button>
			</div>
		</div>
	</div>
</template>

<script>
	import { Picker,Field,Radio,MessageBox,Toast } from 'mint-ui'
	import { getStore } from '../../../common/JS/mUtils.js'
	export default {
		data(){
			return {
				slots: [
			        {
			          flex: 1,
			          values: [],
			          className: 'slot1',
			          textAlign: 'right',
			          defaultIndex : 2
			        }, {
			          divider: true,
			          content: '-',
			          className: 'slot2'
			        }, {
			          flex: 1,
			          values: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
			          className: 'slot3',
			          defaultIndex : 0,
			          textAlign: 'left'
			        }
			    ],
			    visibleItemCount:3,
			    kpiMonth:'',
			    fileType:'xls',
			    kpiDataList : [],
			    noKpiDataFlag : false,
			    userMsg : JSON.parse(getStore('user'))
			}
		},
		created(){
			//初始化年，当前年的前后两年
			let date = new Date();
			let thisYear = date.getFullYear();
			this.slots[0].values.push(thisYear-2);
			this.slots[0].values.push(thisYear-1);
			this.slots[0].values.push(thisYear);
			this.slots[0].values.push(thisYear+1);
			this.slots[0].values.push(thisYear+2);
			
			let thisMon = date.getMonth();
			this.slots[2].defaultIndex = thisMon; 
			
		},
		methods : {
			onValuesChange(picker, values) {
		      this.kpiMonth = values[0] + '-' + values[1];
		      let queryDate = values[0] + '-' + values[1] + '-01';	//指定年月的随便一个日期
		      window.setTimeout(()=>{
		      	//选择月份后5s，请求后台查询KPI列表
		      	this.$http('/sys/kpi/getKpiData','get',{date:queryDate,department:this.userMsg.accountName}).then((data)=>{
					if(data!=null){
						this.kpiDataList = data.kpiDataList;
						this.noKpiDataFlag = data.kpiDataList.length==0?true:false;
						//测试数据
						/*this.kpiBalanceList.push({memberName:'test0',kpiBalance:0})
						this.kpiBalanceList.push({memberName:'test1',kpiBalance:1})
						this.kpiBalanceList.push({memberName:'test2',kpiBalance:5})
						this.kpiBalanceList.push({memberName:'test3',kpiBalance:10})
						this.kpiBalanceList.push({memberName:'test4',kpiBalance:40})*/
					}
				});
		      },1000)
		    },
		    exportKpi()	{
		    	let exportKpiDate = this.kpiMonth + '-01';
		    	/*this.$http('/sys/kpi/generate','post',{date:exportKpiDate,department:this.userMsg.accountName,fileType:this.fileType},{responseType: 'blob'})
		    		.then((data)=>{
					    if (data) {
					      let blob = data
					      alert(blob);
					       //后台返回的名称
					      //let filename = decodeURI(res.headers['content-disposition'].split('=')[1].split('.')[0])
					      let a = document.createElement('a')
					      let url = window.URL.createObjectURL(blob)
					      //let filename = this.userMsg.accountName + '-' + exportKpiDate.replace(new RegExp('-',"gm"),'') + '.' + this.fileType
					      let filename = '123.xlsx';
					      a.href = url
					      a.download = filename
					      a.click()
					      window.URL.revokeObjectURL(url)
					    }
					});*/
					location.href="/api//sys/kpi/generate?date=" + exportKpiDate + "&department="+this.userMsg.accountName+"&fileType="+this.fileType
		    },
		    importNormalKpi(){
		    	let importKpiDate = this.kpiMonth + '-01';
		    	MessageBox.confirm('自动将个人日常KPI导入数据库，是否确认操作?').then(action => {
					this.$http('/sys/kpi/importNormalKpi','post',{date:importKpiDate,department:this.userMsg.accountName})
		    			.then((data)=>{
		    				Toast({message: 'KPI导入成功',duration: 3000});
		    			})
		    	})
		    	
		    }
		},
		
	}
</script>

<style>
	.picker-items{
		width:150px;
	}
</style>