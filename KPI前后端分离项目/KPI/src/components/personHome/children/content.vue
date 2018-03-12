<template>
<div class='' style="height: 617px;">
	<div>
		
	</div>
	<mt-cell to="/changePsw" style="text-align: left;text-decoration:none;" title="修改密码"  is-link >
	  <span style="color: green"></span>
	  <img slot="icon" src="../../../assets/pwd.png" width="20" height="20" />
	</mt-cell>
	<mt-cell to="/changeEmail" style="text-align: left;margin-bottom: 10px;text-decoration:none;" title="修改邮箱"  is-link >
	  <span style="color: green"></span>
	  <img slot="icon" src="../../../assets/mail.png" width="20" height="20" />
	</mt-cell>
	<mt-cell to="/exportKpi" style="text-align: left;text-decoration:none;" title="KPI导入/导出" is-link>
	  <span style="color: green"></span>
	  <img slot="icon" src="../../../assets/importEx.png" width="20" height="20" />
	</mt-cell>
	<mt-cell to="/kpiBalance" style="text-align: left;text-decoration:none;" title="KPI余额" is-link>
	  <span style="color: green"></span>
	  <img slot="icon" src="../../../assets/balance.png" width="20" height="20" />
	</mt-cell>
	<mt-cell to="/itemPrefix" style="text-align: left;;margin-bottom: 10px;text-decoration:none;" title="项目组KPI前缀设置" is-link>
	  <span style="color: green"></span>
	  <img slot="icon" src="../../../assets/department.png" width="20" height="20" />
	</mt-cell>
	
	<mt-cell to="mqqwpa://im/chat?chat_type=wpa&uin=840734811&version=1&src_type=web&web_src=oicqzone.com" style="text-align: left;text-decoration:none;" title="联系我们" is-link>
	  <span style="color: green"></span>
	  <img slot="icon" src="../../../assets/icon_QQ.png" width="20" height="20" />
	</mt-cell>
</div>
</template>

<script>
import {MessageBox,Cell} from 'mint-ui'
import {mapState,mapActions} from 'vuex'
import {getStore} from '../../../common/JS/mUtils.js'
export default {
    data () {
    	return {
			selected:''
    	}
	},
	computed:{
		
	},
	created(){
		
	},
	methods:{
		
	},
	watch : {
		
	},
	components:{
		
	}
  }
</script>

<style>
	
</style>