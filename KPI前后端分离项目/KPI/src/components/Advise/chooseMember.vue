 <template>
 	<div class="alet_container">
	    <section class="tip_text_container">
            <div class="tip_icon">
            </div>
	            <div class="well col-md-10">
				<h3 >请先确认身份</h3>
				<div class="input-group input-group-md">
					<span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-edit"></i></span>
			        <select class="form-control" id="selectMember" v-model="member">
			        	<option value="">--请选择您的姓名--</option>
			        	<option v-for="row in userList" :value="row">{{row.memberName}}</option>
			        </select>
				</div>
				<div style="margin: 20px 0;">
					<el-button type="primary" size="small" :disabled="member==undefined" @click="checkMember"  plain>确&nbsp;&nbsp;定</el-button>
					<el-button type="primary" size="small" :disabled="member==undefined" @click="closeChoose"  plain>关&nbsp;&nbsp;闭</el-button>
				</div>
			</div>
        </section>
    </div>
</template>

<script>
import {getStoreUser,setStore,cloneObj} from '../../common/JS/mUtils.js'
    export default {
    	data(){
            return{
            	member:'',
            	userList:[]
            }
        },
        created(){
        	this.userList = cloneObj(this.userListData);
        },
        props: ['userListData'],
        methods: {
            closeChoose(){
            	console.dir(this.member);
                this.$emit('closeMember')
            },
            checkMember(){
            	if(null == this.member || this.member == ''){
            		this.Toast('请选择姓名')
            		return;
            	}
            	this.member.department = getStoreUser().accountName;
            	setStore('memberMsg',this.member);
            	this.$emit('closeMember');
            }
        }
    }
</script>

<style lang="scss" scoped>
    @import '../../common/stylus/mixin.scss';
	@keyframes tipMove{
       0%   { transform: scale(1) }
       80%  { transform: scale(.9) }
       90%  { transform: scale(1.1) }
       100% { transform: scale(1) }
    }
    .alet_container{
    	position: fixed;
    	top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        z-index: 2;
    }
    .tip_text_container{
        position: absolute;
        top: 15%;
        left:2.5%;
        width: 95%;
        padding: 5px;
        animation: tipMove .4s ;
    }
    
    .col-center-block {  
	    float: none;  
	    display: block;  
	    margin-left: auto;  
	    margin-right: auto;  
	} 
   .bg-img{
	 
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
    
    .el-main {
	    background-color: #E9EEF3;
	    color: #333;
	    text-align: center;
	    line-height: 33px;
	}
</style>
