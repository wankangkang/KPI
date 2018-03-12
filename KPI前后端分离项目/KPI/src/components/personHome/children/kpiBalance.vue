<template>
	<div>
		<div style="position: absolute;z-index: 2;">
			<mt-header fixed title="KPI余额">
				<router-link to="/personHome" slot="left">
				    <mt-button icon="back"></mt-button>
				</router-link>
			</mt-header>
		</div>
		<div class="kpi_kpiBalance_list">
			<el-row>
				<el-col :span="24">
					<el-switch  v-model="value" active-text="编辑"></el-switch>
				</el-col>
			</el-row>
			<el-row v-for="row in kpiBalanceList" :key="row.memberName">
			  <el-col :span="7"><div class="grid-content bg-purple">
			  	{{row.memberName}}
			  </div></el-col>
			  <el-col :span="9"><div class="grid-content-input bg-purple-light">
			  	<!--<el-input size="small" placeholder="请输入KPI余额" v-model="input1" :disabled="!value"></el-input>-->
			  	<el-input-number style="width: 150px;" v-model="row.kpiBalance" controls-position="right" :min="0" :max="100" size="small" :disabled="!value"></el-input-number>
			  </div></el-col>
			  <el-col :span="8"><div class="grid-content bg-purple" >
			  	<div v-if="row.kpiBalance==undefined">-</div>
			  	<div v-else-if="row.kpiBalance == 0">
			  		<icon class="icon_style_kpiBalance" name="kpi_level1" ></icon><span>入门</span>
			  	</div>
			  	<div v-else-if="row.kpiBalance <= 3">
			  		<icon class="icon_style_kpiBalance" name="kpi_level2" ></icon>初级
			  	</div>
			  	<div v-else-if="row.kpiBalance <= 7">
			  		<icon class="icon_style_kpiBalance" name="kpi_level3" ></icon>高级
			  	</div>
			  	<div  v-else-if="row.kpiBalance <= 15">
			  		<icon class="icon_style_kpiBalance" name="kpi_level4" ></icon>史诗级
			  	</div>
			  	<div v-else-if="row.kpiBalance > 15">
			  		<icon class="icon_style_kpiBalance" name="kpi_level5" ></icon>变态级
			  	</div>
			  </div></el-col>
			</el-row>
			<mt-button style="margin-top:40px ;" size="large" type="primary" @click.native="sendConfirm">保存</mt-button>
		</div>
	</div>
</template>

<script>
	import { Picker,Field,Radio,Toast } from 'mint-ui'
	import { getStore } from '../../../common/JS/mUtils.js'
	export default {
		data(){
			return {
				value:'',
				input1:'',
				kpiBalanceList : [],
				userMsg : JSON.parse(getStore('user'))
			}
		},
		created(){
			//请求接口，获取人员KPI余额LIST
			this.$http('/sys/kpi/getKpiBalanceInfo','get',{department:this.userMsg.accountName}).then((data)=>{
				if(data!=null){
					this.kpiBalanceList = data.kpiBalanceInfoList;
				}
			});
		},
		methods : {
		    sendConfirm(){
	    		//发请求 ，校验 验证码是否正确(邮箱与验证码一起发送，匹配即可通过)
				this.$http('/sys/kpi/updateKpiBalance','post',{kpiBalanceListStr : JSON.stringify(this.kpiBalanceList)}).then((data)=>{
					Toast({message: '修改成功',duration: 3000});
				});
		    },
		}
	}
</script>

<style>
	.picker-items{
		width:150px;
	}
	.kpi_kpiBalance_list{
		margin-top: 50px;
	}
	.el-row {
	    margin-bottom: 10px;
	    &:last-child {
	      margin-bottom: 0;
	    }
	  }
	  .el-col {
	    border-radius: 4px;
	  }
	  .bg-purple-dark {
	    background: #99a9bf;
	  }
	  .bg-purple {
	    background: #d3dce6;
	  }
	  .bg-purple-light {
	    background: #e5e9f2;
	  }
	  .grid-content {
	    border-radius: 4px;
	    min-height: 36px;
	    padding-top: 8px;
	    padding-left: 10px;
	  }
	  .grid-content-input {
	    border-radius: 4px;
	    min-height: 36px;
	    padding-top: 2px;
	  }
	  .row-bg {
	    padding: 10px 0;
	    background-color: #f9fafc;
	  }
	  
	  .icon_style_kpiBalance{
			width: 60px;
			height: 25px;
	  }
	  
</style>