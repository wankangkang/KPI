<template>
	<div style="overflow: scroll;">
		<mt-popup style="width: 100%;" v-model="popupVisible" popup-transition="popup-fade" position="bottom">
	  		<mt-button type="default" size="large" >删除</mt-button>
	  	</mt-popup>
		<!--<div class="comment_label" >
			<mt-badge style="margin: 3px;" size="large" color="red">最新</mt-badge>
			<mt-badge style="margin: 3px;" size="large" color="red">最热</mt-badge><br />
			<mt-badge style="margin: 3px;" size="large" color="blue">只看组内</mt-badge>
			<mt-badge style="margin: 3px;" size="large" >点赞最多</mt-badge>
			<mt-badge style="margin: 3px;" size="large" >一起吐槽</mt-badge>
		</div>-->
		<div style="margin-bottom:10px ;margin-top:10px ;">
			<el-input type="textarea" :rows="7" placeholder="请输入内容" 
			  v-model="textarea">
			</el-input>
			<el-row :gutter="0" style="line-height: 20px;">
				<el-col :span="23" style="text-align: right;"><el-button size="mini" type="primary" @click="addReview">提交</el-button></el-col>
			</el-row>
		</div>
		
		<mt-loadmore :bottomDistance="70" :bottom-method="loadBottom" :bottom-all-loaded="allLoaded"  @bottom-status-change="handleBottomChange" ref="loadmore">
			<div class="talk_content" v-for="item in talkList">
				<el-row :gutter="0" style="line-height: 20px;">
				  <el-col :span="4">
				  	<icon class="icon_style_advise" name="boy" >
				  	</icon>
				  </el-col>
				  <el-col :span="8" style="padding-top: 8px;text-align: left;"><font >{{item.member.department}}-{{item.member.memberName}}</font></el-col>
				  <el-col :span="12" style="text-align: right;">
				  	<mt-button icon="more" class="my_mt_button" @click="showMore"></mt-button>
				  	
				  </el-col>
				</el-row>
				<el-row :gutter="0" style="line-height: 15px;">
				  <el-col :span="8" style="padding-left:20px;text-align: left;"><div class="light_font_style">{{item.postTime}}</div ></el-col>
				</el-row>
				<el-row :gutter="0" style="line-height: 100px;">
				  <el-col :span="24">
				  	<div class="grid-content-review ">
				  		{{item.postContent}}
				  	</div>
				  </el-col>
				  
				</el-row>
				<el-row :gutter="0" style="line-height: 30px;">
				  <el-col :span="4"><div class="light_font_style">浏览次数</div></el-col>
				  <el-col :span="20" style="text-align: right;padding-right: 20px;" >
				  	<mt-badge class="light_badge" size="small" style="background-color: #FFFFFF;" @click.native="toComment(item)"><icon  name="comment" ></icon>&nbsp;评论({{item.commentSize}})</mt-badge>
				  </el-col>
				</el-row>
				
			</div>
			
		</mt-loadmore>
	
	
	
	</div>
</template>

<script>
import { Loadmore } from 'mint-ui'
import checkMember from '../checkMember.js'
import {dateStyleStr} from '../../../common/JS/mUtils'
export default{
	data(){
		return {
			popupVisible : false,		//是否弹出更多选择
			bottomStatus: '',
			titleMsg:'评论',
			textarea: '',
			talkList : [],				//发帖List
			currentItemIndex : 0,		//懒加载 当前加载的位置
			loadStep : 10,				//加载更多步长
			allLoaded : false			//false:未加载完成
		}
	},
	created(){
		//请求接口，
		this.getNewestPost();
	},
	computed : {
		
	},
	methods:{
		showMore(){
			this.popupVisible = true;
		},
		loadBottom () {
			//下拉至底部，加载更多
			this.getNewestPost();
		},
		handleBottomChange (status) {
			//alert(status);
	        this.bottomStatus = status;
	   },
		getNewestPost(){
			let inputVO = {
				start : this.currentItemIndex,
				step : this.loadStep
			}
			let vm_this = this;
			this.$http('/getNewestPost','get',inputVO).then((data)=>{
				console.dir(data.postList);
				if(data.postList!=null && data.postList.length > 0){
					let loadLength = data.postList.length;
					if(loadLength < vm_this.loadStep){
						//已全部加载完成
						vm_this.allLoaded = true;
					}
					data.postList.forEach((row)=>{
						row.postTime = dateStyleStr(row.postTime);
						vm_this.talkList.push(row);
						//console.log(dateStyleStr(row.postTime))
					});
					this.$refs.loadmore.onBottomLoaded();	//关闭加载动画
					vm_this.currentItemIndex += loadLength;
					console.log('currentItemIndex:' + vm_this.currentItemIndex )
				}else {
					vm_this.allLoaded = true;
				}
			});
		},
		addReview(){
			let vm_this = this;
			//校验用户是否登录
			checkMember().then(member => {
				console.dir(member);
				if(null != member){
					//已选择身份，发布帖子
					let inputVO = {
						postContent : vm_this.textarea,
						memberId : member.memberId
					}
					vm_this.$http('/createPost','post',inputVO).then(data => {
						if(undefined!=data.post){
							data.post.postTime = dateStyleStr(data.post.postTime);
							data.post.commentSize = 0;
							vm_this.talkList.unshift(data.post);	//向数组开头添加一个元素
							this.Toast({message: '发布成功' ,duration: 3000});
							vm_this.textarea = '';
						}
					})
				}
			})
		},
		getReview(){
			//调接口，查询
		},
		toComment(item){
			this.$router.push({path:'/comment' , query:{member: JSON.stringify(item) }});
		}
	}
}
</script>

<style>
	.comment_label{
		line-height: 10px;
		text-align: left;
		margin-top: 20px;
		margin-bottom: 20px;
	}
	.talk_content{
		font-size: medium;
		background-color: #fff;
		margin-bottom: 10px;
		margin-top: 10px;
	}
	//elementUI
	.el-row {
	    margin-bottom: 0px;
	    &:last-child {
	      margin-bottom: 0;
	    }
	  }
	.el-col {
	    
	  }
	  .bg-purple-dark {
	    background: #99a9bf;
	  }
	  .bg-purple {
	    background: #d3dce6;
	  }
	  .bg-purple-light {
	    background: #e5e9f2;
	  }
	  .icon_style_advise{
			width: 60px;
			height: 30px;
			width:100%;
			margin-top: .3rem;
			fill: #ccc;
	  }
	  .row-bg {
	    padding: 10px 0;
	    background-color: #f9fafc;
	  }
	  .grid-content-review{
	  	text-align: left;
	  	line-height: 20px;
	  	margin-top: 5px;
		margin-bottom: 5px;
	  	font-size: small;
	  	padding: 15px;
	  	text-indent: 2em;
	  	font-family: "微软雅黑";
	  	word-wrap:break-word
	  }
	  .light_badge{
	  	box-shadow: 0 0 1px #b8bbbf;
	    line-height: 15px;
	    color: #000000;
	  }
	  .my_mt_button{
	  	background-color:inherit;
	  	height: 31px;
	  	border-radius: 0px;
	  	box-shadow: 0 0 0px #b8bbbf;
	  }
	  .light_font_style{
	  	font-size:smaller ;
	  	font-weight: 100;
	  	color:#99A9BF;
	  	font-family: "微软雅黑";
	  }
</style>