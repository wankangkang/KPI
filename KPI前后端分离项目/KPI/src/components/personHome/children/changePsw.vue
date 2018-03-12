<template>
	<div>
		<mt-header fixed title="修改密码">
			<router-link to="/personHome" slot="left">
			    <mt-button icon="back"></mt-button>
			</router-link>
		</mt-header>
		<div style="margin-top:40px ;">
			<mt-field style="text-decoration:none;" :state="userNameState" label="用户名" placeholder="请输入用户名" v-model="userName" @change="checkUserName" :disableClear="disableClear"></mt-field>
			<mt-field style="text-decoration:none;" :state="passWordState" label="新的密码" type="password" placeholder="请输入新密码" v-model="passWord" @change="checkPassWord" :disableClear="disableClear"></mt-field>
			<mt-field v-show="checkCodeShow" style="text-decoration:none;" :state="checkCodeState" label="验证码"  placeholder="请输入验证码" v-model="checkCode" >
				<mt-button  size="small" :disabled="!sendFlag" :type="sendFlag?'primary':'default'" @click="sendCheck">发送{{sendFlag?'':'('+count+')'}}</mt-button>
			</mt-field>
			<mt-button style="margin-top:40px ;" size="large" type="primary" @click="sendConfirm">确定</mt-button>
		</div>
	</div>
</template>

<script>
	import { Picker,Field,Radio,Toast } from 'mint-ui'
	export default {
		data(){
			return {
				userName : '',
				userNameState : '',		//用户名校验状态
				passWord : '',
				passWordState : '',		//密码校验状态
				checkCode : '',	
				checkCodeState : '',	//验证码校验状态
				disableClear : true,	//禁止clear按钮
				checkCodeShow : false,	//校验框显示标志
				sendFlag : true,		//点击发送标志，发送后开始到计算
				count : 90			//倒计时90S
			}
		},
		created(){
			
		},
		methods : {
		    sendCheck(){
		    	if(this.userNameState == 'error' ){
		    		Toast({message: '用户名不能为空',duration: 3000});
		    		return;
		    	}
				//请求获取验证码，验证码将发送到邮箱		    	
		    	this.$http('/sendVericationCode','get',{userName:this.userName,email:'',vericationCodeType:'password'}).then((data)=>{
		    			console.dir(data)
		    			Toast({message: '验证码已发送至邮箱',duration: 3000});
						console.dir('123');
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
		    		this.checkCodeShow = true;
		    		return true;
		    	}
		    },
		    checkPassWord(){
		    	if(this.passWord=='' ||　this.passWord.length < 6){
		    		Toast({message: '密码至少为6为',duration: 3000});
		    		this.passWordState = 'error';
		    		return false;
		    	}else{
		    		//发后的请求 判断用户是否存在
		    		
		    		this.passWordState = 'success';
		    		return true;
		    	}
		    },
		    sendConfirm(){
		    	if(this.checkUserName() && this.checkPassWord() ){
		    		//发请求 ，校验 验证码是否正确(邮箱与验证码一起发送，匹配即可通过)
					this.$http('/modifyPassword','post',{userName:this.userName,password:this.passWord,verificationCode:this.checkCode}).then((data)=>{
						Toast({message: '修改密码成功',duration: 3000});
						this.$router.push('/personHome');
					});
		    	}
		    },
		},
		beforeRouteLeave (to, from, next) {
		    // 导航离开该组件的对应路由时调用，关闭验证码倒计时。
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