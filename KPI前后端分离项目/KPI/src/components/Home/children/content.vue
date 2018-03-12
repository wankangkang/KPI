<template>
	<div style="height: 660px;">
		<!--<div style="margin-bottom: 10px;">
		  <el-input placeholder="请输入内容" v-model="input5" class="input-with-select">
		  	<div slot="prepend" >
		  		KPISYS
		  	</div>
		    <el-button slot="append" icon="el-icon-search"></el-button>
		  </el-input>
		</div>-->
		<!-- 第一部分 轮播图 -->  
		<mt-swipe @change="handleChange" :auto="4000" :showIndicators="true" style="height: 400px;">
		  <mt-swipe-item>
		  	<div class="swipe_item_div">
			  	<img src="../../../assets/indexBg.jpg"  height="400" width="708"/>
		  	</div>
		  </mt-swipe-item>
		  <mt-swipe-item>
		  	<div class="swipe_item_div">
		  		<br/>
		  		<br />
		  		<br/>
		  		<br />
		  		<br/>
		  		<br />
		  		<br/>
		  		<br />
			  	<h1 style="color: #122B40;font-weight: bold;">敬请期待</h1>
		  	</div>
		  </mt-swipe-item>
		</mt-swipe>
		
		<!-- 内容板块 -->
		<div class="home_content" >
			<el-row :gutter="0" style="line-height: 20px;">
			  <el-col :span="4" style="padding-top: 8px; padding-left: 12px; text-align: left;">说明</el-col>
			</el-row>
			<el-row :gutter="0" style="line-height: 100px;">
			  <el-col :span="6">
			  	<div class="grid-content-home">
			  		<img src="../../../assets/timg.jpg" class="home_content_img"/>
			  	</div>
			  </el-col>
			  <el-col :span="18">
			  	<div class="grid-content-home">
			  		<ul style="list-style-type: disc;">
			  		<li><span style="color:red;font-weight: bold;">1.</span>所有验证码发送到邮箱有很大概率被放入垃圾箱中，新注册项目组需要先到<span style="color:blue">我的</span>中的<span style="color:#5DAF34">项目组kpi前缀设置</span>中设置项目组生成excel所显示的项目前缀</li>
			  		<li><span style="color:red;font-weight: bold;">2.</span>到<span style="color:blue">kpi操作</span>中新增项目组成员</li>
			  		<li><span style="color:red;font-weight: bold;">3.</span>如需操作kpi，则需先到<span style="color:blue">我的</span>中的<span style="color:#5DAF34">kpi导入导出</span>中导入对应月份的数据，导入后数据不能删除，必须保证项目组成员是最新的</li>
			  		<li><span style="color:red;font-weight: bold;">4.</span>到<span style="color:blue">我的</span>中的<span style="color:#5DAF34">kpi余额</span>可以设置kpi余额数量，并会随kpi操作同步更新，可用于记录自己的kpi余额数</li>
			  	</ul>
			  	</div>
			  </el-col>
			</el-row>
			
		</div>
		
		<!--<div class="home_content" >
			<el-row :gutter="0" style="line-height: 20px;">
			  <el-col :span="2">
			  	<hr style="margin:15px 0 20px 0;text-align: left;border: solid #000000;width: 30px;"/>
			  </el-col>
			  <el-col :span="4" style="padding-top: 8px;text-align: left;">&nbsp;&nbsp;信息</el-col>
			  <el-col :span="18" style="padding-top: 8px;text-align: right;">更多</el-col>
			</el-row>
			<el-row :gutter="0" style="line-height: 100px;">
			  <el-col :span="6">
			  	<div class="grid-content-home">
			  		<img src="../../../assets/icon_QQ.png" class="home_content_img"/>
			  	</div>
			  </el-col>
			  <el-col :span="18">
			  	<div class="grid-content-home">
			  		<p>内容111111111111111111111111。。。</p>
			  		<p>内容222222222222222222222222。。。</p>
			  		<p>内容33333333333333333333333。。。</p>
			  	</div>
			  </el-col>
			</el-row>
			
		</div>-->
		
</div>
</template>

<script>
import { Swipe, SwipeItem } from 'mint-ui';
import { getStore } from '../../../common/JS/mUtils.js'
export default {
    data() {
      return {
      	loginFlag : false,
        input5 : '',
        user : (JSON.parse(getStore('user'))==null)?'':JSON.parse(getStore('user'))
      };
    },
    created(){
    	
    },
    watchs : {
    	user : (val) => {
    		console.dir(val);
    	}
    },
    methods: {
      handleChange(index){
      	
      },
    },
    components:{
    	
    },
    
  }
</script>

<style lang="scss" scoped>
	.swipe_item_div{
		height: 400px;
		width: 401px;
		background-color: #fff;
	}
	.home_content{
		font-size: medium;
		background-color: #fff;
		margin-bottom: 10px;
		margin-top: 10px;
	}
	.home_content_head{
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
	.head_left{
		display: flex;
		flex-direction: row;
		align-self: flex-start;
	}
	.home_content_body{
		display: flex;
		flex-direction: row;
		align-self: flex-start;
	}
	.home_content_img{
		width: 80px;
		height: 90px;
	}
	.grid-content-home{
	  	text-align: left;
	  	line-height: 20px;
	  	margin-top: 5px;
		margin-bottom: 5px;
	  	font-size: small;
	  	font-family: "微软雅黑";
	  	word-wrap:break-word;
	  	p {
	  		line-height: 30px;
	  		overflow-x: overlay;
	  		font-size: small;
	  		font-family: "微软雅黑";
	  	}
	  }
	  .grid-content-home li{
	  	margin: 8px;
	  }
</style>