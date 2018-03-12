<template>
<div >
	<div>
		<span>KPI信息列表</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<el-button type="primary" size="mini"  class="el-icon-plus" @click="showAddPage()">新增</el-button>
		<el-button type="primary" size="mini"  class="el-icon-edit-outline" @click="saveKpiNum()">保存级别</el-button>
	</div>
	<!--<table class="table" style="font-size: 12px;">
        <thead>
            <tr >
                <th style="text-align: center;" width="25%" >姓名</th>
                <th style="text-align: center;" width="25%" >级别</th>
                <th style="text-align: center;" width="50%" >操作</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(item,index) in userListData.slice((currentPage-1)*pageSize,currentPage*pageSize)" >
                <td style="overflow: hidden;">{{item.memberName}}</td>
                <td style="overflow: hidden;">{{item.rank}}</td>
                <td >
                	<el-button-group>
                		<el-button type="primary" size="mini" class="el-icon-edit" @click="showEditPage(item)"></el-button>
	                	<el-button type="primary" size="mini" class="el-icon-delete" @click="deleteItem(item,index)"></el-button>
                	</el-button-group>
                </td>
            </tr>
        </tbody>
	</table>-->
		<el-row >
		  <el-col :span="7">
		  	<div >
		  	姓名
		    </div>
		  </el-col>
		  <el-col :span="9">
		  	<div >
		  	级别
		    </div>
		  </el-col>
		  <el-col :span="8">
		  	<div  >
		  	操作
		    </div>
		  </el-col>
		</el-row>
		<el-row v-for="(item,index) in userList.slice((currentPage-1)*pageSize,currentPage*pageSize)" :key="item.memberId">
		  <el-col :span="7">
		  	<div class="grid-content bg-purple">
		  	{{item.memberName}}
		    </div>
		  </el-col>
		  <el-col :span="9" @blur="blurRank(item)">
		  	<div class="grid-content-input bg-purple-light"  >
		  		<el-select @blur="changeSelected(item)" size="large" v-if="item.SELECTED" placeholder="请选择级别" v-model="item.rank" ref="seletedId">
				    <el-option v-for="type in $store.state.rankList" :key="type.DATA" :label="type.LABEL" :value="type.DATA"></el-option>
			   </el-select>
			   <div v-if="!item.SELECTED" @click="changeSelected(item)">
			   		{{item.rank}}
			   </div>
		    </div>
		  </el-col>
		  <el-col :span="8">
		  	<div class="grid-content bg-purple" >
		  	  <el-button-group>
        		<el-button type="primary" size="mini" class="el-icon-edit" @click="showEditPage(item)"></el-button>
            	<el-button type="primary" size="mini" class="el-icon-delete" @click="deleteItem(item,index)"></el-button>
        	  </el-button-group>
		    </div>
		  </el-col>
		</el-row>
	
	
	<div class="block">
	    <el-pagination
	      large
	      background
	      height="50px"
	      @size-change="handleSizeChange"
	      @current-change="handleCurrentChange"
	      :current-page.sync="currentPage"
	      :page-size="pageSize"
	      layout="prev, pager, next, jumper"
	      :total="total">
	    </el-pagination>
	</div>
	<kpi-calendar v-if="showCalendar" :userMsg="userMsg" @closeKpi="closeKpi"></kpi-calendar>
	<!-- 人员新增组件， rankList：级别参数档，addMember:新增确定后触发,member包含department memberName rank-->
	<add-user v-if="showAdd" :rankList="rankList" @addMember="addMember" @closeAdd="closeAdd"></add-user>
</div>
</template>

<script>
import kpiCalendar from './kpiCalendar'
import addUser from './addUser'
import {MessageBox} from 'mint-ui'
import {mapState,mapActions} from 'vuex'
import {getStore} from '../../../common/JS/mUtils.js'
import Vue from 'vue'
export default {
    data () {
    	return {
    		//日历显示Flag
    		showCalendar:false,
    		//传入子组件user信息
    		userMsg:'',
    		//打开新增componse Flag
    		showAdd:false,
    		//当前页码
    		currentPage:1,
    		//每页显示条数
    		pageSize:10,
    		//分页总数
    		total : 0,
			//分页数据 
			userList:[]
    	}
	},
	computed:{
		...mapState(['rankList']),
	},
	created(){
		//调接口
		this.queryAllMember();
	    
	},
	methods:{
		queryAllMember(){
			if(null == JSON.parse(getStore('user'))){
				MessageBox.confirm('是否进入登录?').then(action => {
			  			this.$router.push('/login');				  
					});
					return;
			}
			let userName = JSON.parse(getStore('user')).accountName;		
			this.$http('/member/getAllMember','post',{'accountName' : userName})
		  		.then( data => {
		  			this.userList = data.member;
				    this.total = this.userList.length;
		  		})
		},
		//点击级别，显示下拉菜单，调整级别
		changeSelected(item){
			if(item.SELECTED)
				Vue.set(item,'SELECTED',false);
			else{
				Vue.set(item,'SELECTED',true);
				//this.$refs.seletedId.onfocus();				
			}
		},
		blurRank(item){
			Vue.set(item,'SELECTED',false);
		},
		saveKpiNum(){
			let memberListStr = [];
			this.userList.forEach(row => {
				let obj = {memberId : row.memberId,rank : row.rank};
				memberListStr.push(obj);
			})
			//保存级别
			this.$http('/member/modifyMemberRank','post',{'memberListStr' : JSON.stringify(memberListStr)})
		  		.then( data => {
		  			this.Toast({message: '保存成功',duration: 3000})
		  			
		  		})
		},
		//新增人员
		addMember(member){
			let vm_this = this;
			/*this.$store.dispatch('insertMember',member);*/
			this.$http("/member/addUser",'post',member)
			  .then(data => {
		  		vm_this.queryAllMember();
			  	this.closeAdd();
			  });
		},
		//分页方法
		handleSizeChange(val) {
			this.pageSize = val;
	    },
	    handleCurrentChange(val) {
	    	this.currentPage = val;
	    },
		showEditPage(item){
			this.userMsg = item;
			this.showCalendar = true;
		},
		showAddPage(){
			this.showAdd = true;
		},
		closeKpi(){
			this.showCalendar = false;
		},
		closeAdd(){
			this.showAdd = false;
		},
		deleteItem(item,index){
			let indexNum = index + (this.currentPage - 1) * this.pageSize; 
			let vm_this = this;
			MessageBox.confirm('确定要删除吗？').then(action => {
				let inputVO = {
					memberId : item.memberId
				}
				this.$http('/member/deleteMember','get',inputVO)
					.then(response => {
					  	vm_this.userList.splice(indexNum,1);
					});
			});
		}
	},
	watch : {
		//当用户数量发生变化，改变分页参数  
		userList : function(newArrayData){
			this.total = newArrayData.length
		},
		userListData : function(newData){
			console.dir(newData)
			this.userList = newData;
		}
	},
	components:{
		kpiCalendar,
		addUser
	}
  }
</script>

<style>
	.el-row {
	    margin-bottom: 10px;
	    &:last-child {
	      margin-bottom: 0;
	    }
	  }
	  .el-col {
	    border-radius: 4px;
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
	  .grid-content {
	    border-radius: 4px;
	    min-height: 36px;
	    padding-top: 0px;
	    padding-left: 0px;
	  }
</style>