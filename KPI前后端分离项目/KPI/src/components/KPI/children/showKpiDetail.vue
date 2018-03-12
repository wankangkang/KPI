 <template>
 <div>
 	<!-- 背景变暗 -->
 	<div id="ly" class="show_kpi_backgroup" >
    </div>
 	<div class="alet_container" style="z-index: 2001;">
	    <section class="tip_text_container" style="background-color: #f5f5f5;">
            <div class="tip_icon">
            	
            </div>
            <div class="table-responsive" width="100%">
			    <table class="table" style="font-size: 8px;table-layout: fixed;">
			        <thead>
			            <tr>
			                <th style="text-align:center;overflow: hidden;width:27%;">KPI</th>
			                <th style="text-align:center;overflow: hidden;width:30%;">类别</th>
			                <th style="text-align:center;overflow: hidden;width:20%;">KPI日期</th>
			                <th style="text-align:center;overflow: hidden;width:15%;">操作</th>
			            </tr>
			        </thead>
			        <tbody>
			            <tr v-for="(kpi,index) in kpiData" >
			            	<td>
			                	<el-input-number size="mini" v-model="kpi.kpiNum" :step="0.125" :min="0" :max="2" label="KPI" ></el-input-number>
			                </td>
			                <td>
								<el-select size="mini" placeholder="请选择KPI类型" :disabled="isVacation || isWeekday" v-model="kpi.kpiType" >
								    <el-option v-for="type in $store.state.kpiType" :key="type.DATA" :label="type.LABEL" :value="type.DATA"></el-option>
								    
							    </el-select>
			                	
			                </td>
			                <td >
			                	<div style="margin-top:6px ;width: 90px;font-size: 12px;">{{userMsg.currentDate}}</div>
			                </td>
			                <td >
			                	<el-button-group>
				                	<!--<el-button type="primary" size="mini" class="el-icon-plus" @click="addKpi"></el-button>
				                	<el-button type="primary" size="mini" class="el-icon-edit" @click="editKpi"></el-button>-->
				                	<el-button type="primary" size="mini" class="el-icon-delete" @click="deleteKpi(index)" :disabled="isVacation || isWeekday"></el-button>
			                	</el-button-group>
			                </td>
			            </tr>
			        </tbody>
			</table>
			</div> 
			<div style="margin: 20px 0;">
				<el-button type="primary" size="small" @click="closeTip">关&nbsp;&nbsp;闭</el-button>
				<el-button type="primary" size="small" @click="updateKpiList" plain>确&nbsp;&nbsp;定</el-button>
				<el-button type="primary"  size="small" @click="addKpi" :disabled="kpiData.length==3 || isVacation || isWeekday" plain>新&nbsp;&nbsp;增</el-button>
			</div>
        </section>
    </div>
</div>
</template>

<script>
import {Toast} from 'mint-ui'
import {mapActions} from 'vuex'
import { cloneObj } from '../../../common/JS/mUtils.js'
    export default {
    	data(){
            return{
            	isVacation : false,
            	isWeekday : false,
            	kpiData : [],
            	kpiTotal: 0
            }
        },
        created(){
        	this.kpiData = cloneObj(this.showKpiData);
        	//判断当前是否为周末
        	let nowDate = new Date(this.userMsg.currentDate);
        	if(nowDate.getDay()==0 || nowDate.getDay()==6){
        		//若是周末，只能添加加班
        		if(this.kpiData.length == 0)
        			this.addExtraWorkKpi();
        		this.isWeekday = true;
        	}
        	if(this.kpiData==undefined || this.kpiData.length==0){
        		//该天工作日，且没有KPI信息
        		this.addKpi;
        	}else if(this.kpiData.length == 1 && this.kpiData[0].vacation == '1'){
    			this.kpiData.splice(0,1);
    			this.addExtraWorkKpi();
    			this.isVacation = true;         			
        	}else {
        		let removeIndex = 0;
        		let flag = false;
        		this.kpiData.forEach((row,index) => {
        			if(row.vacation == '1'){
        				//节假日 休息
        				this.isVacation = true;
        				flag = true;
        				removeIndex = index;
        			}else if(row.vacation == '0'){
        				//节假日 补班
        				this.isVacation = false;
        				this.isWeekday = false;
        				flag = true;
        				removeIndex = index;
        			}
        		});
        		if(flag)
        			this.kpiData.splice(removeIndex,1);
        		
        		//首次进入，统计kpiTotal
        		this.kpiData.forEach((row,index) => {
							if(row.workType == 'ExtraWork'){
								this.kpiTotal += row.kpiNum
							}else{
								this.kpiTotal -= row.normalWork
							}
						})
        	}
        	console.log(this.kpiTotal);
        },
        computed:{
        	...mapActions(['updateKpi'])
        },
        props: ['showKpiData','userMsg'],
        methods: {
        	//新增kpi
        	addKpi(){
        		if(this.kpiData.length < 3){
	        		let kpiObj = {
	        			date			: this.userMsg.currentDate,
				    	department   	: this.userMsg.department,
				    	memberId		: this.userMsg.memberId,
				    	memberName		: this.userMsg.memberName,
				    	workType		: '',
				    	rank 			: this.userMsg.rank,
				    	leaveDayType	: '',
				    	normalWork		: 0,
				    	extraWork		: 0,
				    	//日历组件显示字段
				    	kpiNum 			: 0,
						kpiType 		: '',
						start 			: this.userMsg.currentDate,
						title 			: ''
	        		}
	        		this.kpiData.push(kpiObj);
        		}
        	},
        	deleteKpi(index){
        		this.kpiData.splice(index,1);
        	},
             updateKpiList(){
             	debugger
             	//修改后kpiTotal的值
             	let kpiTotalEnd = 0;
             	if(this.kpiData.length==0){
             		this.kpiData.push({
             			date : this.userMsg.currentDate,
             			department : this.userMsg.department,
             			memberId : this.userMsg.memberId,
             			memberName : this.userMsg.memberName
             		});
             	}else{
             		let returnObj = {msg:'',flag:false};		//校验标志，若为true提示并return
             		let kpiTypeStr = '';
	             	this.kpiData.forEach((row,index) => {
	             		if(!returnObj.flag){
		             		//判断kpi类型是否已选择
		             		if(row.kpiType==null || row.kpiType==''){
		             			returnObj = {msg : '请选择KPI类型',flag : true};
		             		}
		             		//当KPI类型为请假，kpiNum不能大于1
		             		if((row.kpiType=='L1' || row.kpiType=='L2' || row.kpiType=='L4') && row.kpiNum > 1){
			        			returnObj = {msg : '请假KPI值最大为1',flag : true};
			        			row.kpiNum = 1;
			        		}
		             		//同一类kpi类型只能有一个
		             		if(kpiTypeStr.indexOf(row.kpiType)!=-1){
		             			returnObj = {msg : 'kpi类型不能重复',flag : true};
		             		}
		             		kpiTypeStr = kpiTypeStr + row.kpiType;
		             		//计算kpiTotal
							if(row.kpiType == 'EW'){
								kpiTotalEnd += row.kpiNum
							}else{
								kpiTotalEnd -= row.kpiNum
							}
	             		}
					})
	             	if(returnObj.flag){
	             		Toast({message: returnObj.msg ,duration: 3000});
	             		return;
	             	}
             	}
             	//kpiData必定有值，在其[0]设置 kpi改变的差值
             	this.kpiData[0].kpiData = kpiTotalEnd - this.kpiTotal;
            	this.$store.dispatch('updateKpi',this.kpiData).then(()=>{
            		this.closeTip();    
            	});
            },
            addExtraWorkKpi(){
            	//假期，该天只能申请加班
            	let kpiVacation = {
            		date : this.userMsg.currentDate,
            		kpiType : 'EW',
            		workType : 'ExtraWork',
            		kpiNum : 0,
            		department : this.userMsg.department,
            		memberId : this.userMsg.memberId,
            		rank : this.userMsg.rank,
            		memberName : this.userMsg.memberName
            	}
            	this.kpiData.push(kpiVacation);
            	this.kpiData[0].show = true;
    			
            },
            closeTip(){
            	this.$emit('closeTip'); 
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
    .alet_container{
    	
    	position: fixed;
    	border:1px solid black;
    	top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        z-index: 200;
    }
    .tip_text_container{
        position: absolute;
        top: 20%;
        left:2.5%;
        width: 95%;
        padding: 5px;
        animation: tipMove .4s ;
        background-color: rgba(255,255,255,1);
       /* border: 1px;
        padding-top: .6rem;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        border-radius: 0.25rem;*/
        .tip_icon{
            border: 0.15rem solid #f8cb86;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            span:nth-of-type(1){
                background-color: #f8cb86;
            }
            span:nth-of-type(2){
                border: 1px;
                border-radius: 50%;
                background-color: #f8cb86;
            }
        }
        .tip_text{
            text-align: center;
        }
        .confrim{            @include sc(.5rem, #fff);
            font-weight: bold;
            
            margin-top: .8rem;
            background-color: #4cd964;
            width: 100%;
            text-align: center;
            line-height: 30px;
            border: 1px;
            border-bottom-left-radius: 0.25rem;
            border-bottom-right-radius: 0.25rem;
        }
    }
    
    //change elementUI css
    //数字input组件
    .el-input-number--mini {
	    width: 110px;
	    line-height: 26px;
	}
	//下拉选项组件
	.el-select .el-input{
    	width: 100px;
	}
</style>
