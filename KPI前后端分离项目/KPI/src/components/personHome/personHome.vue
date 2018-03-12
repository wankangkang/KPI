<template>
	<el-container>
		<mt-header class="mt_header" style="font-size: 20px;" :title="titleMsg"  ></mt-header>
		<el-header style="height: 130px;padding: 0px;text-align: left;line-height: 30px;background-color: #26a2ff;">
			<section class="profile-number">
				<div class="profile-link" style="">
	                <img class="privateImage" width="50" height="50" src="../../common/images/icon/qq.png" >
	                <!--<span class="privateImage" v-else>
	                    <svg class="privateImage-svg">
	                        <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#avatar-default"></use>
	                    </svg>
	                </span>-->
	                <div class="user-info">
	                    <p>{{user.accountName}}</p>
	                    <p>
	                        <span class="user-icon">
	                        	<icon style="width: 15px;height: 20px;" name="boy" ></icon>
	                        </span>
	                        <span class="icon-mobile-number">{{user.email}}</span>
	                    </p>
	                </div>
	                <span class="arrow">
	                    <svg class="arrow-svg" fill="#fff">
	                        <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#arrow-right"></use>
	                    </svg>
	                </span>
	            </div>
            </section>
		</el-header>
		<el-main style="padding: 20px 0px 50px 0px"><content-view></content-view></el-main>
		<div style="height: 0px;"><bottom></bottom></div>
	</el-container>
</template>

<script>
import top from '../Home/children/top'
import contentView from './children/content'
import bottom from '../Home/children/bottom'
import {getStoreUser} from '../../common/JS/mUtils.js'
import { MessageBox } from 'mint-ui'
export default{
	data(){
		return {
			titleMsg:'我',
			user : getStoreUser()
		}
	},
	components:{
		top,
		contentView,
		bottom
	},
	created(){
		console.dir(this.user);
	},
	beforeRouteEnter(to, from, next){
		let user = getStoreUser();
		if(null == user){
			MessageBox.confirm('是否进入登录?').then(action => {
				next(vm => {
					vm.$router.push('/login');				  				
				})
			})
		}else
			next();
	}
}
</script>

<style lang="scss" scoped>
	@import '../../common/stylus/mixin.scss';
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
	  .container{
	  	padding-left: 0px;
	  	padding-right: 0px;
	  }
	  
	  .profile-number{
	  		padding-top: 30px;
	        .profile-link{
	            display:block;
	            display:flex;
	            box-align: center;
	            -webkit-box-align: center;
	            -ms-flex-align: center;
	            align-items: center;
	            padding: .666667rem .6rem;
	            .privateImage{
	                display:inline-block;
	                width: 40px;height: 30px;
	                @include wh(2.5rem,2.5rem);
	                border-radius:50%;
	                vertical-align:middle;
	                .privateImage-svg{
	                    background:$fc;
	                    border-radius:50%;
	                    @include wh(2.5rem,2.5rem);
	                }
	            }
	            .user-info{
	                margin-left:.48rem;
	                -webkit-box-flex: 1;
	                -ms-flex-positive: 1;
	                flex-grow: 1;
	                p{
	                    font-weight:700;
	                    @include sc(.8rem,$fc);
	                    .user-icon{
	                        @include wh(0.5rem,0.75rem);
	                        display:inline-block;
	                        vertical-align:middle;
	                        line-height:0.75rem;
	                        .icon-mobile{
	                            @include wh(100%,100%);
	                        }
	                    }
	                    .icon-mobile-number{
	                        display:inline-block;
	                        @include sc(.57333rem,$fc);
	
	                    }
	                }
	
	            }
	            .arrow{
	                @include wh(.46667rem,.98rem);
	                display:inline-block;
	                svg{
	                   @include wh(100%,100%);
	                }
	            }
	        }
	   }
</style>