<template>
<div>
	<mt-navbar v-model="chooseBar">
	    <mt-tab-item id="review" >讨论</mt-tab-item>
	    <mt-tab-item id="myTalk" >我的评论</mt-tab-item>
	    <mt-tab-item id="adviseOur" >建议</mt-tab-item>
	</mt-navbar>
	<div >
		<router-view></router-view>
	</div>
</div>

</template>

<script>
import {MessageBox} from 'mint-ui'
import { Badge } from 'mint-ui'
import {getStoreUser} from '../../../common/JS/mUtils.js'
export default {
    data () {
    	return {
    		userMsg : getStoreUser(),
    		chooseBar : 'review',
    		selected : '1'
    	}
	},
	computed:{
		
	},
	created(){
		//先确定人员，只有选择人员才可进行评论
		this.$router.push({path:'/advise/review',query:{userMsg:this.userMsg}});
		//首先进入讨论界面
	},
	methods:{
		
	},
	watch : {
		chooseBar: function(viewName){
			if(null==viewName || viewName==''){
				viewName = 'review';
			}
			this.$router.push({path:'/advise/'+viewName,query:{userMsg:this.userMsg}});
		}
	}
  }
</script>

<style>
	
</style>