<template>
	<div>
		<mt-header fixed title="评论">
			<router-link to="/advise/review" slot="left">
			    <mt-button icon="back"></mt-button>
			</router-link>
		</mt-header>
		<!-- 评论对象  发帖内容-->
		<div class="talk_content" style="margin-top: 50px;">
			<el-row :gutter="0" style="line-height: 20px;">
			  <el-col :span="4">
			  	<icon class="icon_style_advise" name="boy" >
			  	</icon>
			  </el-col>
			  <el-col :span="8" style="padding-top: 8px;"><font >{{member.member.department}}-{{member.member.memberName}}</font></el-col>
			  <el-col :span="12" style="text-align: right;">
			  	<mt-button icon="more" class="my_mt_button" ></mt-button>
			  </el-col>
			</el-row>
			<el-row :gutter="0" style="line-height: 15px;text-align: right;">
			  <el-col :span="8" style="padding-left:20px;text-align: left;"><div class="light_font_style">{{member.postTime}}</div ></el-col>
			</el-row>
			<el-row :gutter="0" style="line-height: 100px;">
			  <el-col :span="24">
			  	<div class="grid-content-comment ">
			  		{{member.postContent}}
			  	</div>
			  </el-col>
			  
			</el-row>
			
		</div>
		
		<!-- 评论 回复区 -->
		<div style="margin-bottom: 41px;">
		<el-row :gutter="0" style="line-height: 20px;">
			  <el-col :span="2">
			  	<icon class="icon_style_advise" name="message" >
			  	</icon>
			  </el-col>
			  <el-col :span="22" style="padding-top: 8px;font-size: 18px;">全部评论({{member.commentSize}})</el-col>
		</el-row>
		<mt-loadmore :bottomDistance="70" :bottom-method="loadBottom" :bottom-all-loaded="allLoaded"  @bottom-status-change="handleBottomChange" ref="loadmore">
			<div class="talk_content" style="margin-top: 5px;" v-for="comment in commentList">
				<div style="background-color: #fff;">
				<el-row :gutter="0" style="line-height: 20px;">
				  <el-col :span="4">
				  	<icon class="icon_style_advise" name="girl" >
				  	</icon>
				  </el-col>
				  <el-col :span="8" style="padding-top: 8px;"><font >{{comment.commentMember.department}}-{{comment.commentMember.memberName}}</font></el-col>
				  <el-col :span="12" style="text-align: right;">
				  	<mt-button icon="more" class="my_mt_button" ></mt-button>
				  </el-col>
				</el-row>
				<el-row :gutter="0" style="line-height: 15px;text-align: right;">
				  <el-col :span="8" style="padding-left:20px;text-align: left;"><div class="light_font_style">{{comment.commentTime}}</div ></el-col>
				</el-row>
				<el-row :gutter="0" style="line-height: 100px;">
				  <el-col :span="24" @click.native="toReply(comment)">
				  	<div class="grid-content-comment ">
				  		{{comment.commentContent}}
				  	</div>
				  </el-col>
				</el-row>
				</div>
				<!-- 对该评论的回复 -->
				<div style="background-color: #fff;padding-left:30px;" v-for="replyComment in comment.replyComment">
					<hr width="90%" style="align-content: center;margin: 0px;" />
					<el-row :gutter="0" style="line-height: 20px;">
					  <el-col :span="4">
					  	<icon class="icon_style_advise" name="girl" >
					  	</icon>
					  </el-col>
					  <el-col :span="8" style="padding-top: 8px;"><font >{{replyComment.replyMember.department}}-{{replyComment.replyMember.memberName}}</font></el-col>
					  <el-col :span="12" style="text-align: right;">
					  	<mt-button icon="more" class="my_mt_button" ></mt-button>
					  </el-col>
					</el-row>
					<el-row :gutter="0" style="line-height: 15px;text-align: right;">
					  <el-col :span="8" style="padding-left:20px;text-align: left;"><div class="light_font_style">{{replyComment.replyTime}}</div ></el-col>
					</el-row>
					<el-row :gutter="0" style="line-height: 100px;">
					  <el-col :span="24">
					  	<div class="grid-content-comment ">
					  		{{replyComment.replyMember.memberName}}
					  		<font color="blue">回复</font>
					  		{{replyComment.replyedMember.memberName}}<br />
					  		{{replyComment.replyContent}}
					  	</div>
					  </el-col>
					</el-row>
				</div>
			</div>
		</mt-loadmore>
		</div>
		<!-- 底部 -->
		<section id="comment_guide">
			<section class="comment_item">
				<mt-button size="large" type="primary" @click="toComment">
					评论
				</mt-button>
				<input type="" v-model="commentContent" ref="commentContent"/>
				<input type="" v-model="replyContent" ref="replyContent"/>
			</section>
		</section>
	</div>
</template>

<script>
import { Loadmore,MessageBox } from 'mint-ui'
import checkMember from '../checkMember.js'
import {dateStyleStr} from '../../../common/JS/mUtils'
export default{
	data(){
		return {
			member : JSON.parse(this.$route.query.member),
			reviewInfo : '',
			currentCommentIndex : 0,
			commentList : [],
			commentContent : '',
			replyContent : '',	
			bottomStatus: '',
			loadStep : 5,				//加载更多步长
			allLoaded : false			//false:未加载完成
		}
	},
	created(){
		this.getSpecificPostInfo();
		
	},
	methods : {
		loadBottom () {
			//下拉至底部，加载更多
			this.getSpecificPostInfo();
		},
		handleBottomChange (status) {
			//改变状态
	        this.bottomStatus = status;
	   },
		getSpecificPostInfo () {
			let inputVO = {
				start : this.currentCommentIndex,
				step : this.loadStep,
				postId : this.member.postId
			}
			let vm_this = this
			this.$http('getSpecificPostInfo','get',inputVO).then(data => {
				//debugger;
				if(undefined!=data.postInfo && data.postInfo.commentList.length > 0){
					let loadLength = data.postInfo.commentList.length;
					if(loadLength < vm_this.loadStep){
						//全部加载完成
						vm_this.allLoaded = true;
					}
					vm_this.reviewInfo = data.postInfo.post;
					data.postInfo.commentList.forEach((row)=>{
						row.commentTime = dateStyleStr(row.commentTime);
						vm_this.commentList.push(row);
						//回复的时间 设置为dateStyleStr
						row.replyComment.forEach((reply)=>{
							reply.replyTime = dateStyleStr(reply.replyTime); 
						})
					});
					console.dir(vm_this.commentList);
					vm_this.$refs.loadmore.onBottomLoaded();	//关闭加载动画
					vm_this.currentCommentIndex += loadLength;
				}else {
					vm_this.allLoaded = true;
				}
			})
		},
		/**  发布评论  **/
		toComment(){
			let vm_this = this;
			//校验用户是否登录
			checkMember().then(member => {
				if(null != member){
					//已选择身份，发起评论
					MessageBox.prompt('评论' + vm_this.member.member.memberName + '...','').then(obj => {
						let inputVO = {
							commentMemberId : member.memberId,
							commentContent : obj.value,
							postId : vm_this.member.postId
						}
						console.dir(inputVO);
						vm_this.$http('/comment','post',inputVO).then(data => {
							console.dir(data);
							if(undefined!=data.comment){
								data.comment.commentTime = dateStyleStr(data.comment.commentTime);
								vm_this.commentList.unshift(data.comment);	//向数组开头添加一个元素
								this.Toast({message: '评论成功' ,duration: 3000});
							}
						})
						
					})
					
					//alert(this.commentContent);
				}
			})
		},
		/**  对评论回复  **/
		toReply(commentObj){
			let vm_this = this;
			console.dir(commentObj);
			//校验用户是否登录
			checkMember().then(member => {
				if(null != member){
					//已选择身份，发起评论
					MessageBox.prompt('回复' + commentObj.commentMember.memberName + '...','').then(obj => {
						let inputVO = {
							commentId : commentObj.commentId,
							replyMemberId : member.memberId,
							replyContent : obj.value,
							replyedMemberId : commentObj.commentMember.memberId
						}
						console.dir(inputVO);
						vm_this.$http('/replyComment','post',inputVO).then(data => {
							console.dir(data);
							if(undefined!=data.reply){
								data.reply.replyTime = dateStyleStr(data.reply.replyTime); 
								if(undefined == commentObj.replyComment){
									commentObj.replyComment = []
								}
								commentObj.replyComment.unshift(data.reply);	//向数组开头添加一个元素
								this.Toast({message: '回复成功' ,duration: 3000});
							}
						})
						
					})
					
					//alert(this.commentContent);
				}
			})
		}
	}
}
</script>

<style lang="scss" scoped>
    @import '../../../common/stylus/mixin.scss';

    #comment_guide{
        background-color: #fff;
        position: fixed;
        z-index: 100;
        left: 0;
        right: 0;
        bottom: 0;
        @include wh(100%, 1.58rem);
        display: flex;
        box-shadow: 0 -0.026667rem 0.053333rem rgba(0,0,0,.1);
    }
    .comment_item{
    	flex: 1;
    	text-align: center;
    	flex-direction: column;
    	align-items: center;
    }
	 .talk_content{
		background-color: #fff;
		margin-bottom: 10px;
		margin-top: 10px;
	}
	//elementUI
	
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
	  .grid-content-comment{
	  	text-align: left;
	  	line-height: 20px;
	  	margin-top: 5px;
		margin-bottom: 5px;
	  	font-size: small;
	  	padding: 15px;
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