 <template>
 	<div>
	<!-- 背景变暗 -->
 	<div id="ly" class="show_kpi_backgroup" >
    </div>
 	<div class="alet_container">
	    <section class="tip_text_container">
            <div class="tip_icon">
            </div>
	            <div class="well col-md-10">
				<h3 >新增人员</h3>
				<div class="input-group input-group-md">
					<span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
					<input type="text" class="form-control" disabled="true" :placeholder="userName" aria-describedby="sizing-addon1">
				</div>
				<div class="input-group input-group-md">
					<span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-edit"></i></span>
					<input type="text" class="form-control" v-model="memberName" placeholder="姓名" aria-describedby="sizing-addon1">
				</div>
				<div class="input-group input-group-md">
					<span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-edit"></i></span>
					<select class="form-control" v-model="rank" placeholder="级别" >
						<option value="">--级别--</option>
						<option v-for="item in rankList" :value="item.DATA">{{item.LABEL}}</option>
					</select>
					<!--<input id="rank" class="form-control" list="rank" v-model="rank" placeholder="级别"/>
			        <datalist id="rank" >
			            <option class="form-control" v-for="item in rankList" :value="item.DATA" >{{item.LABEL}}</option >
			        </datalist>-->
					
				</div>
				<div style="margin: 20px 0;">
					<el-button type="primary" size="small" @click="addMember" plain>确&nbsp;&nbsp;定</el-button>
					<el-button type="primary"  size="small" @click="closeAdd" plain>关&nbsp;&nbsp;闭</el-button>
				</div>
			</div>
        </section>
    </div>
   </div>
</template>

<script>
import {getStore} from '../../../common/JS/mUtils.js'
    export default {
    	data(){
            return{
            	userName:'',
                memberName: '',
                rank: '',
                value6:''
            }
        },
        created(){
      		this.userName = JSON.parse(getStore('user')).accountName;
        },
        props: ['rankList'],
        methods: {
            closeAdd(){
                this.$emit('closeAdd')
            },
            addMember(){
            	let member = {
            		department:this.userName,
            		memberName:this.memberName,
            		rank:this.rank
            	};
            	this.$emit('addMember',member);
            }
        }
    }
</script>

<style lang="scss" scoped>
    @import '../../../common/stylus/mixin.scss';
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
	
	.show_kpi_backgroup{
    	position: absolute; 
    	top: 0px; 
    	filter: alpha(opacity=60); 
    	background-color: #333;
        z-index: 2; 
        opacity: 0.75;
        left: 0px; 
        display: block; 
        width: 100%; 
        height: 100%;
    }
</style>
