<template >
<div class="container bg-img" style="height: 617px;">
<div class="container row bg-img" style="margin-top:50px;">
	<div class="col-center-block col-md-6" style="opacity: 0.8;">
		<div class="well col-md-10">
			<h3 >用户注册</h3>
			<div class="input-group input-group-md">
				<span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
				<input type="text" class="form-control" v-model="userName" placeholder="用户名" aria-describedby="sizing-addon1" @blur="usernamecheck()">				
			</div>
			<p class="p1" v-show="showflag">{{note}}</p>
			<div class="input-group input-group-md">
				<span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-lock"></i></span>
				<input type="password" class="form-control" v-model="passWord" placeholder="密码" aria-describedby="sizing-addon1" @blur="passwordcheck()">
			</div>
			<p class="p1" v-show="showflag1">{{note1}}</p>
			<div class="input-group input-group-md">
				<span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-lock"></i></span>
				<input type="password" class="form-control" v-model="passWord1" placeholder="确认密码" aria-describedby="sizing-addon1" @blur="passwordcheckagain()">
			</div>
			<p class="p1" v-show="showflag2">{{note2}}</p>
			<div class="input-group input-group-md">
				<span class="input-group-addon" id="sizing-addon1"><i class="el-icon-message" aria-hidden="true"></i></span>
				<input type="email" class="form-control" v-model="mail" placeholder="邮箱" aria-describedby="sizing-addon1" @blur="emailcheck()">
			</div>
			<p class="p1" v-show="showflag3">{{note3}}</p>
			<!--<div class="input-group input-group-md">
				<span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i></span>
				<input type="text" class="form-control" v-model="Tel" placeholder="联系电话" aria-describedby="sizing-addon1" @blur="phonecheck()">
			</div>-->
			<mt-field v-show="checkCodeShow" style="text-decoration:none;" label="验证码"  placeholder="请输入验证码" v-model="checkCode">
				<mt-button  size="small" :disabled="!sendFlag" :type="sendFlag?'primary':'default'" @click="sendCheck">发送{{sendFlag?'':'('+count+')'}}</mt-button>
			</mt-field>
			<p class="p1" v-show="showflag4">{{note4}}</p>
			<!--<div class="well well-sm" style="text-align:center;">
				<input type="checkbox" v-model="registerType" name="kind" :value="1" >&nbsp&nbsp&nbsp我已阅读并同意用户协议
			</div>-->
			<div class="well well-sm" style="text-align:center;">
			<button type="submit" class="btn btn-success btn-a" @click="register">
			注册
			</button>
			<button type="submit" class="btn btn-success btn-a" @click="login">
			返回
			</button>
			</div>
		</div>
		<div class="order_again">
            <alert-tip v-if="showAlert" @closeTip="showAlert = false" :alertText="alertText"></alert-tip> 
        </div>
	</div>
</div>
</div>
</template>

<script>
	import { MessageBox,Field,Toast } from 'mint-ui';
	import alertTip from '../common/alertTip.vue'
	import {setStore} from '../../common/JS/mUtils.js'
	export default {
		data(){
			return {
				showAlert: false,
        alertText: null,
				userName:'',
				passWord:'',
				passWord1:'',
				mail:'',
				loginType:'1',
				Tel:'',
				showflag:false,
				showflag1:false,
				showflag2:false,
				showflag3:false,
				showflag4:false,
				usernameCheckFlag:false,
				note :'',
				note1 :'',
				note2 :'',
				note3 :'',
				note4 :'',
				checkCodeShow : false , 		//验证码显示标志
				checkCode : '',					//验证码
				sendFlag : true,
				count : 90
			}
		},
		
		
		methods : { 
            login : function(){ 
                this.$router.push('/login');
            },
          register : function(){       		
						if(this.checkCode == '' && !this.checkCodeShow){
							this.checkCodeShow = true
		          Toast({message: '请输入\n验证码',duration: 3000});
							return ;            		
		        }else{
          		if(this.usernamecheck() && this.passwordcheck() && this.passwordcheckagain() && 
          			this.emailcheck() )
          		{
          			let account = {
          				accountName : this.userName,
          				password : this.passWord,
          				email : this.mail
          			}
          			this.$http('/register','post',{accountStr : JSON.stringify(account), verificationCode : this.checkCode}).then((data)=>{
					    		Toast({message: data.msg,duration: 3000});
					    		this.$router.push('/login');
								})
          		}            		
          	}
					
            },
            usernamecheck : function(){
				if(this.userName == ''){
                	this.showflag = true;
                	this.note = '用户名为空';
					return false;
				}else{
					this.$http('/existUser','get',{userName : this.userName}).then((data)=>{
		            	this.showflag = false; 
						this.usernameCheckFlag =  true;
					}).catch(data => {
						Toast({message: data.msg,duration: 3000});
						this.usernameCheckFlag = false;
					})
					
					return this.usernameCheckFlag
	            }
           },
           passwordcheck : function(){          	
           		if(this.passWord == ''){
                	this.showflag1 = true;
                	this.note1 = '密码为空';
                	return false;                
	            }else if(this.passWord.length < 6){
	            	this.showflag1 = true;
                	this.note1 = '密码至少为6位';
                	return false;
	            }else{
	            	this.showflag1 = false;  
					return true;	            	
	            }
           },
           passwordcheckagain : function(){          	
           		if(this.passWord1 == ''){
                	this.showflag2 = true;
                	this.note2 = '密码为空';
                	return false;               
	            }else if(this.passWord1 != this.passWord){
                	this.showflag2 = true;
                	this.note2 = '两次输入的密码不一致';	
                	return false;
	            }else{
	            	this.showflag2 = false;  
					return true;	            	
	            }
           },
           emailcheck : function(){
           		let emailck = new RegExp('^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$');
           		let regFormssi=new RegExp('formssi.com' + '$');     
           		let C = this.mail;
           		if(C=="")
           		{
           			this.showflag3 = true;
                	this.note3 = '邮箱为空';
                	return false;
           		}else{
	           		let result = emailck.test(C);
	           		if(!result){
	                	this.showflag3 = true;
	                	this.note3 = '邮箱格式不正确';
	                	return false;
	           		}else if(!regFormssi.test(C)){
	           			this.showflag3 = true;
	                	this.note3 = '请使用公司内部邮箱，如：XXX@formssi.com';
	           		}else {
	           			this.showflag3 = false;  
	           			this.checkCodeShow = true;
						return true;	           			
	           		}
           		}
           },
           sendCheck () {
           	if(this.userName == ''){
           		this.showflag = true;
              this.note = '用户名为空';
							return;
           	}else if(!this.usernameCheckFlag){
           		this.note = '该用户名已注册';
           		this.showflag = true;
           		return
           	}else if(!this.passwordcheck()){
           		return
           	}else if(!this.passwordcheckagain()){
           		return
           	}else if(!this.emailcheck()){
		    		return;
		    	}
				//请求获取验证码，验证码将发送到邮箱		    	
		    	this.$http('/sendVericationCodeForReg','get',{userName:this.userName,email:this.mail,vericationCodeType:'register'}).then((data)=>{
		    		console.dir(data)
		    		Toast({message: '验证码已发送至邮箱',duration: 3000});
						this.sendFlag = false;
				    	//倒计时90S
				    	clearInterval(this.timer);
		                this.timer = setInterval(() => {
		                    this.count --;
		                    if (this.count == 0) {
		                        clearInterval(this.timer);
		                        Toast({
								  message: '操作超时',
								  duration: 3000
								});
								this.count = 90;
		                        this.sendFlag = true;
		                    }
		                }, 1000);
					})
           }
        },
		components:{
			alertTip,
		},
		beforeRouteLeave (to, from, next) {
		    // 导航离开该组件的对应路由时调用，关闭验证码倒计时。
		    // 可以访问组件实例 `this`
		    clearInterval(this.timer);
			this.sendFlag = true;		//点击发送标志，发送后开始到计算
			this.count = 90;			//倒计时90S
		    next();
		}
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
	  background-image: url("../../assets/Lighthouse.jpg");  
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
	background-color: #f9f2f4;
	}
	.p1{
		margin-left:42px;
		color: red;
		font-family : 微软雅黑;
	}
	.btn-a
	{
		width:100px;
		height: 30px;
	}
    
</style>