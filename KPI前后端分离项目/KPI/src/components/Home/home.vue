<template>
	<el-container>
		<el-header style="height: 41px;"><top :titleMsg="titleMsg"><span slot='logo'>logo</span></top></el-header>
		<el-main style="padding: 0px 0px 50px 0px"><content-view></content-view></el-main>
		<div style="height: 0px;"><bottom></bottom></div>
	</el-container>
</template>

<script>
import top from './children/top'
import contentView from './children/content'
import bottom from './children/bottom'
export default{
	data(){
		return {
			titleMsg:'个人首页'
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
	    line-height: 41px;
	    text-align: center;
	    line-height: 40px;
	  }
	  
	  .el-aside {
	    background-color: #D3DCE6;
	    color: #333;
	    text-align: center;
	  }
	  
	  .el-main {
	    background-color: #E9EEF3;
	    color: #333;
	    text-align: center;
	  }
	  
	  body > .el-container {
	  }
	  
	  .el-container:nth-child(5) .el-aside,
	  .el-container:nth-child(6) .el-aside {
	    line-height: 260px;
	  }
	  
	  .el-container:nth-child(7) .el-aside {
	    line-height: 320px;
	  }
</style>