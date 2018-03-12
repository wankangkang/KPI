<template >
<div class="container bg-img" style="height: 617px;">
	<router-link to="/">
		<i class="el-icon-back" style="margin-top: 10px;"></i>		
	</router-link>
<div class="container row bg-img" style="margin-top:50px;">
	<div class="col-center-block col-md-6 " style="opacity: 0.8;">
		<div class="">
			<h3 >KPI管理系统</h3>
			<div class="input-group input-group-md">
				<span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
				<input type="text" class="form-control" v-model="userName" placeholder="用户名" aria-describedby="sizing-addon1">
			</div>
			<div class="input-group input-group-md">
				<span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-lock"></i></span>
				<input type="password" class="form-control" v-model="passWord" placeholder="密码" aria-describedby="sizing-addon1">
			</div>
			<!--<div class="well well-sm b-a" style="text-align:center;">
				<!--<input type="radio" v-model="loginType" name="kind" :value="1" > 个人&nbsp;&nbsp;&nbsp;&nbsp;-->
				<!--<input type="radio" v-model="loginType" name="kind" :value="2" > 事业群
			</div>-->
			<button type="submit" class="btn btn-success btn-block" @click="login()">
			登录
			</button>
			<div class="well-sm" >
				<router-link to ="/register" class="a1" >注&nbsp;&nbsp;&nbsp;册</router-link>
				<router-link to ="/changePsw" class="a2" >忘记密码</router-link>
			</div>
			<!--<button type="submit" class="btn btn-success btn-block" @click="register()">
			注册
			</button>-->
		</div>
		<div class="order_again">
            <alert-tip v-if="showAlert" @closeTip="showAlert = false" :alertText="alertText"></alert-tip> 
        </div>
	</div>
</div>
</div>
</template>

<script>
	import { MessageBox } from 'mint-ui';
	import alertTip from '../common/alertTip.vue'
	import {setStore} from '../../common/JS/mUtils.js'
	export default {
		data(){
			return {
				showAlert: false,
                alertText: null,
				userName:'',
				passWord:'',
				loginType:'2',
				fromUrl : ''
			}
		},				
		methods : {  
				login : function () {
				if(this.userName == '' || this.passWord == ''){
					this.showAlert = true;
		        	this.alertText = '账号或密码为空！';
				    return false;  
				}
				//return true;			
				
		        var params = {
		       		'userName' : this.userName,
		       		'password' : this.passWord
		        }
		        //params.append('loginType','1');
		        //this.$http.get("/login?userName="+this.userName+"&password="+this.passWord)
		        this.$http("/login",'post',params)
				  .then(data => {
				  		
				  		var d = new Date;
    					d.setTime(d.getTime() + 24*60*60*1000*2);
				  		this.token = data.token;
				  		this.cookie = "JSESSIONID="+this.token + ";path=/;expires=" + d.toGMTString();
				  		document.cookie = this.cookie;
				  		setStore('token',this.token);
				  		setStore('user',data.user);
				  		//关闭加载中，提示登录成功
					  	this.Toast({
						  message: '登录成功',
						  iconClass:'icon icon-success',
						  duration: 3000
						});
						this.$router.push((this.fromUrl==''||this.fromUrl=='/register') ? '/home' : this.fromUrl);
				  })
			},
            register : function(){
            	
            	this.$router.push('/register');
            },
            
        },
		components:{
			alertTip,
		},
		beforeRouteEnter(to, from, next){
			next(vm => {
				vm.fromUrl = from.fullPath;
			});
		},
	}
</script>

<style lang="scss" scoped>
	@import '../../common/stylus/mixin.scss';
	
    .col-center-block {  
	    float: none;  
	    display: block;  
	    margin-left: auto;  
	    margin-right: auto;  
	} 
   .bg-img{
	  background-image: url("../../assets/img3.jpg");  
	  background-position: center 0;  
	  background-repeat: no-repeat;  
	  background-attachment: fixed;  
	  background-size: cover;  
	  height: 100%;
	  -webkit-background-size: cover;  
	  -o-background-size: cover;  
	  -moz-background-size: cover;  
	  -ms-background-size: cover; 
   }
   .input-group{
	margin:10px 0px;//输入框上下外边距为10px,左右为0px
	}
	h3{
	padding:5px;
	border-bottom:1px solid #ddd;//h3字体下边框
	}
	li{
	list-style-type:square;//列表项图标为小正方形
	margin:10px 0;//上下外边距是10px
	}
	em{//强调的样式
	color:#c7254e;
	font-style: inherit;
/*	background-color: #f9f2f4;*/
	}
	.a1{
		color: white;
		float:left;
		text-decoration:none;
		}
	.a2{
		color: white;
		float:right;
		text-decoration:none;
		}
	.b-a
	{
		opacity:0.7;
		background-color: none;
	}

</style>