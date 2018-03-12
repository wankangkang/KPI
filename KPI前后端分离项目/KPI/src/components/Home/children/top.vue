<template>
	<div style="background-color: #fff;">
		<mt-header style="font-size: 20px;" class="mt_header" :title="titleMsg"  fixed>
		  <div slot="left" >
		    <!--<mt-button icon="back">退出</mt-button>-->
		    <div style="font-size: 14px;">
		  		<router-link style="color: #FFFFFF;" to="/login" v-if="null == user || user==''">登录</router-link>
		  		<span @click="logout" style="color: #FFFFFF;" v-else-if="null != user && user != ''">欢迎 {{user.accountName}}</span>
		  	</div>
		  </div>
		  <div slot="right">
		  	<mt-button icon="more" @click="showMore"></mt-button>	
		  </div>
		</mt-header>
	  	<mt-popup style="width: 100%;" v-model="popupVisible" popup-transition="popup-fade" position="bottom">
	  		<div id="nativeShare">
	  		</div>
	  	</mt-popup>
	</div>
	
</template>

<script>
	import { removeStore,getStore } from '../../../common/JS/mUtils.js'
	import {MessageBox} from 'mint-ui'
	import soshm from 'soshm' 
	export default{
		data(){
			return {
				user : (JSON.parse(getStore('user'))==null)?'':JSON.parse(getStore('user')),
				popupVisible : false,		//是否弹出更多选择
				share_obj : ''
			}
		},
		props:{
			titleMsg:String
		},
		created(){
			
		},
		methods : {
			logout(){
				MessageBox.confirm('确定退出?').then( ()=>{
					this.$http("/logout",'get',{}).then(data => {
						this.user = null;
						//退出，清空用户信息 转至登录
						removeStore('user');
						removeStore('memberMsg');
						removeStore('token');
						this.$router.push('/');						
					})
				})
			},
			showMore(){
				/*soshm('#nativeShare', {
		          // 分享的链接，默认使用location.href
		          url: '',
		          // 分享的标题，默认使用document.title
		          title: '',
		          // 分享的摘要，默认使用<meta name="description" content="">content的值
		          digest: '',
		          // 分享的图片，默认获取本页面第一个img元素的src
		          pic: '',
		          // 默认显示的网站为以下六个个,支持设置的网站有
		          // weixin,weixintimeline,qq,qzone,yixin,weibo,tqq,renren,douban,tieba
		          sites: ['weixin', 'weixintimeline', 'yixin', 'weibo', 'qq', 'qzone']
		        });*/
		       soshm.popIn({
		       		url : 'http://120.79.198.75/#/home',
		            title: 'KPI管理系统',
		            pic : '../../../assets/indexBg.jpg',
		            sites: ['weixin', 'weixintimeline', 'weibo', 'yixin', 'qzone', 'tqq', 'qq']
		          });
				//this.popupVisible = true;
			},
		}
	}
</script>

<style type="text/css">
	.el-select .el-input {
	    width: 130px;
	  }
	
	//分享样式
	#nativeShare {
	    font-size: 13px;
	}
	
	#nativeShare .label {
	    font-size: 20px;
	}
	
	#nativeShare .list {
	    width: 90%;
	    margin: 0 auto;
	}
	
	#nativeShare .list span {
	    width: 30%;
	    display: inline-block;
	    text-align: center;
	    margin: 10px 0;
	}
	
	#nativeShare .list span i {
	    width: 40px;
	    height: 40px;
	    display: block;
	    margin: 0 auto;
	    margin-bottom: 5px;
	}
	
	#nativeShare .weibo i {
	    background-image: url('../../../common/images/icon/weibo.png');
	    background-size: cover;
	}
	
	#nativeShare .weixin i {
	    background-image: url('../../../common/images/icon/weixin_friend.png');
	    background-size: cover;
	}
	
	#nativeShare .weixin_timeline i {
	    background-image: url('../../../common/images/icon/weixin.png');
	    background-size: cover;
	}
	
	#nativeShare .qq i {
	    background-image: url('../../../common/images/icon/qq.png');
	    background-size: cover;
	}
	
	#nativeShare .qzone i {
	    background-image: url('../../../common/images/icon/qqzone.png');
	    background-size: cover;
	}
	
	#nativeShare .more i {
	    background-image: url('../../../common/images/icon/more.png');
	    background-size: cover;
	}
 	
</style>