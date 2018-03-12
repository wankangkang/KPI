<template>
	<div style="overflow: scroll;">
		<mt-popup style="width: 100%;" v-model="popupVisible" popup-transition="popup-fade" position="bottom">
	  		<mt-button type="default" size="large" >删除</mt-button>
	 </mt-popup>
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
import {dateStyleStr, getStore} from '../../../common/JS/mUtils'
export default{
	data(){
		return {
			popupVisible : false,		//是否弹出更多选择
			bottomStatus: '',
			titleMsg:'我的评论',
			textarea: '',
			talkList : [],				//发帖List
			currentItemIndex : 0,		//懒加载 当前加载的位置
			loadStep : 10,				//加载更多步长
			allLoaded : false,			//false:未加载完成
			userMsg : JSON.parse(getStore('user'))
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
				step : this.loadStep,
				department : this.userMsg==null?'':this.userMsg.accountName
			}
			let vm_this = this;
			this.$http('/getSpecificDepartmentPost','get',inputVO).then((data)=>{
				if(data.postList!=null && data.postList.length > 0){
					let loadLength = data.postList.length;
					if(loadLength < vm_this.loadStep){
						//已全部加载完成
						vm_this.allLoaded = true;
					}
					data.postList.forEach((row)=>{
						row.postTime = dateStyleStr(row.postTime);
						vm_this.talkList.push(row);
					});
					this.$refs.loadmore.onBottomLoaded();	//关闭加载动画
					vm_this.currentItemIndex += loadLength;
				}else {
					vm_this.allLoaded = true;
				}
			});
		},
		toComment(item){
			this.$router.push({path:'/comment' , query:{member: JSON.stringify(item) }});
		}
	}
}
</script>

<style>

</style>