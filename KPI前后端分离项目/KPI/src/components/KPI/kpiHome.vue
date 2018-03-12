<template>
	<el-container>
		<el-header style="height: 41px;"><top :titleMsg="titleMsg"></top></el-header>
		<el-main style="padding: 20px 20px 50px 20px"><content-view></content-view></el-main>
		<div style="height: 0px;"><bottom></bottom></div>
	</el-container>
</template>

<script>
import top from '../Home/children/top'
import contentView from './children/content'
import bottom from '../Home/children/bottom'
export default{
	data(){
		return {
			titleMsg:'KPI统计'
		}
	},
	components:{
		top,
		contentView,
		bottom
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