<template>
	<div>
		<mt-header fixed title="修改邮箱">
			<router-link to="/personHome" slot="left">
			    <mt-button icon="back"></mt-button>
			</router-link>
		</mt-header>
		<div style="margin-top:40px ;">
			<!--<mt-field style="text-decoration:none;" :state="userNameState" label="用户名" placeholder="请输入用户名" v-model="userName" @change="checkUserName" :disableClear="disableClear"></mt-field>-->
			<mt-field style="text-decoration:none;" :state="emailState" label="新的邮箱" placeholder="请输入邮箱" v-model="email" @change="checkEmail" :disableClear="disableClear"></mt-field>
			<mt-field v-show="checkCodeShow" style="text-decoration:none;" :state="checkCodeState" label="验证码"  placeholder="请输入验证码" v-model="checkCode" >
				<mt-button  size="small" :disabled="!sendFlag" :type="sendFlag?'primary':'default'" @click="sendCheck">发送{{sendFlag?'':'('+count+')'}}</mt-button>
			</mt-field>
			<mt-button style="margin-top:40px ;" size="large" type="primary" @click="sendConfirm">确定</mt-button>
		</div>
	</div>
</template>

<script>
	import { Picker,Field,Radio,Toast } from 'mint-ui'
	import {getStore} from '../../../common/JS/mUtils.js'
	import {setStore} from '../../../common/JS/mUtils.js'
	export default {
		data(){
			return {
				user:JSON.parse(getStore('user')),
				userName : '',
				userNameState : '',		//用户名校验状态
				email : '',
				emailState : '',		//密码校验状态
				checkCode : '',	
				checkCodeState : '',	//验证码校验状态
				disableClear : true,	//禁止clear按钮
				checkCodeShow : false,	//校验框显示标志
				sendFlag : true,		//点击发送标志，发送后开始到计算
				count : 90			//倒计时90S
			}
		},
		created(){
			this.username = JSON.parse(getStore('user')).accountName
		},
		methods : {
		    sendCheck(){
		    	if(this.emailState == 'error'){
		    		Toast({message: '输入有误',duration: 3000});
		    		return;
		    	}
		    	this.$http('/sendVericationCode','get',{userName:this.user.accountName,email:this.email,vericationCodeType:'email'}).then((data)=>{
		    			console.dir(data)
		    			Toast({message: '验证码已发送至邮箱',duration: 3000});
						console.dir('email');
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
		    	
		    },
		    /**
		     * 一堆校验
		     */
		    checkUserName(){
		    	if(this.userName==''){
		    		Toast({message: '用户名不能为空',duration: 3000});
		    		this.userNameState = 'error';
		    		return false;
		    	}else{
		    		//发后的请求 判断用户是否存在
		    		
		    		this.userNameState = 'success';
		    		return true;
		    	}
		    },
		    checkEmail(){
		    	let emailck = new RegExp('^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$');
		    	let regFormssi=new RegExp('formssi.com' + '$'); 
		    	if(this.email=='' ||　!emailck.test(this.email)){
		    		Toast({message: '邮箱不合法',duration: 3000});
		    		this.emailState = 'error';
		    		return false;
		    	}else if(!regFormssi.test(this.email)){
		    		Toast({message: '非四方精创邮箱',duration: 3000});
		    		this.emailState = 'error';
		    		return false;
		    	}else{
		    		//发后的请求 判断用户是否存在
		    		
		    		this.emailState = 'success';
		    		this.checkCodeShow = true;
		    		return true;
		    	}
		    },
		    sendConfirm(){
		    	if(this.checkEmail() ){
		    		//发请求 ，校验 验证码是否正确(邮箱与验证码一起发送，匹配即可通过)
					this.$http('/changeEmail','get',{userName:this.username,email:this.email,verificationCode:this.checkCode}).then((data)=>{
						Toast({message: '修改邮箱成功',duration: 3000});
						this.user.email = this.email;
						setStore("user",this.user)
						this.$router.push('/personHome');
					});
		    	}
		    },
		},
		beforeRouteLeave (to, from, next) {
		    // 导航离开该组件的对应路由时调用
		    // 可以访问组件实例 `this`
		    clearInterval(this.timer);
		    this.disableClear = true;	//禁止clear按钮
			this.sendFlag = true;		//点击发送标志，发送后开始到计算
			this.count = 90;			//倒计时90S
		    next();
		}
		
	}
</script>

<style>
	.picker-items{
		width:150px;
	}
</style>