<template>
	<div>
		<mt-header fixed title="项目组KPI前缀设置">
			<router-link to="/personHome" slot="left">
			    <mt-button icon="back"></mt-button>
			</router-link>
		</mt-header>
		<div style="margin-top:40px ;padding-top: 5px;">
			<el-row>
				<el-col :span="24">
					<el-switch  v-model="value" active-text="修改"></el-switch>
				</el-col>
			</el-row>
			<mt-field style="text-decoration:none;"  label="项目编号" placeholder="请输入项目编号" v-model="kpiStyle.programSerialNumPrefix" :disabled="!value"></mt-field>
			<mt-field style="text-decoration:none;"  label="项目名称" placeholder="请输入项目名称" v-model="kpiStyle.programNamePrefix" :disabled="!value"></mt-field>
			<mt-button style="margin-top:40px ;" size="large" type="primary" @click="sendConfirm">保存</mt-button>
		</div>
	</div>
</template>

<script>
	import { getStore } from '../../../common/JS/mUtils.js'
	export default {
		data(){
			return {
				value : false,
				kpiStyle : {},
				userMsg : JSON.parse(getStore('user'))
			}
		},
		created(){
			//请求获取kpiStyle
			this.$http('/getKpiStyle','get',{department:null==this.userMsg?'':this.userMsg.accountName}).then((data)=>{
				if(data!=null){
					this.kpiStyle = data.kpiStyle;
				}
			});
		},
		methods : {
		    sendConfirm(){
		    	//校验不能为空
		    	if(undefined == this.kpiStyle.programSerialNumPrefix || this.kpiStyle.programSerialNumPrefix == ''){
		    		this.Toast({message: '项目编号不能为空',duration: 3000});
		    		return;
		    	};
		    	if(undefined == this.kpiStyle.programNamePrefix || this.kpiStyle.programNamePrefix == ''){
		    		this.Toast({message: '项目名称不能为空',duration: 3000});
		    		return;
		    	};
	    		let inputVO = {
	    			department : this.userMsg.accountName,
	    			programSerialNumPrefix : this.kpiStyle.programSerialNumPrefix,
	    			programNamePrefix : this.kpiStyle.programNamePrefix
	    		}
		    	//请求接口 保存kpiStyle
		    	this.$http('/saveKpiStyle','post',inputVO).then(data => {
		    		this.Toast({message: '修改成功',duration: 3000});
		    	});
		    	
		    }
		},
		beforeRouteLeave (to, from, next) {
		    // 导航离开该组件的对应路由时调用
		    // 可以访问组件实例 `this`
		    next();
		}
		
	}
</script>

<style>
	.picker-items{
		width:150px;
	}
</style>