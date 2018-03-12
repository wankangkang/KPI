<template>
	<div>
		<el-container>
			<el-header style="height: 41px;"><top :titleMsg="titleMsg"><span slot='logo'>logo</span></top></el-header>
			<el-main ><content-view></content-view></el-main>
			<div style="height: 0px;"><bottom></bottom></div>
		</el-container>
		<choose-member v-if="showChooseFlag" :userListData="userListData" @closeMember="closeMember"></choose-member>
	</div>
</template>

<script>
import top from '../Home/children/top'
import contentView from './children/content'
import bottom from '../Home/children/bottom'
import { getStore,setStore } from '../../common/JS/mUtils.js'
import chooseMember from './chooseMember'
import checkMember from './checkMember.js'
export default{
	data(){
		return {
			titleMsg:'发布',
			userMsg:'',
			showChooseFlag:false
		}
	},
	created(){
		/*checkMember().then(member=>{
			console.dir(member);			
		});*/
	},
	computed : {
	},
	components:{
		top,
		contentView,
		bottom,
		chooseMember
	},
	methods : {
		closeMember(){
			this.showChooseFlag = false;
		},
		
	}
}
</script>

<style>
	.el-header, .el-footer {
	    background-color: #B3C0D1;
	    color: #333;
	    text-align: center;
	    line-height: 60px;
	  }
	  
	  .el-aside {
	    background-color: #D3DCE6;
	    color: #333;
	    text-align: center;
	    line-height: 200px;
	  }
	  
	  .el-main {
	  	padding: 0px 0px 50px 0px;
	    background-color: #E9EEF3;
	    color: #333;
	    text-align: center;
	    line-height: 60px;
	  }
	  
	  body > .el-container {
	    margin-bottom: 40px;
	  }
	  
	  .el-container:nth-child(5) .el-aside,
	  .el-container:nth-child(6) .el-aside {
	    line-height: 260px;
	  }
	  
	  .el-container:nth-child(7) .el-aside {
	    line-height: 320px;
	  }
</style>