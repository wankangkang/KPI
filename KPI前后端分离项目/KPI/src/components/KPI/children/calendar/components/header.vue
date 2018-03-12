<template>
	<!--
    	作者：WKK
    	时间：2017-12-13
    	描述：基于fullCalendar的二次开发-> header部分
    -->
	
	<el-row type="flex" class="row-bg-header" justify="center" >
	  <el-col :span="6"><div class="grid-content-head bg-purple-header header-left">
	    <slot name="header-left">
      </slot>
	  </div></el-col>
	  <el-col :span="12"><div class="grid-content-head bg-purple-light-header header-center">
	      <span class="prev-month" @click.stop="goPrev"><i class="el-icon-arrow-left"></i></span>
	      <span class="title">{{title}}</span>
	      <span class="next-month" @click.stop="goNext"><i class="el-icon-arrow-right"></i></span>
	  </div></el-col>
	  <el-col :span="6"><div class="grid-content-head bg-purple-header header-right">
	  	<slot name="header-right">
      	</slot>
	  </div></el-col>
	</el-row>

</template>
<script type="text/babel">
  import dateFunc from './dateFunc'

  export default {
    created () {
      this.dispatchEvent()
    },
    props : {
      currentDate : {},
      titleFormat : {},
      firstDay    : {},
      monthNames  : {}
    },
    data () {
      return {
        title      : '',
        leftArrow  : '<',
        rightArrow : '>',
        headDate : new Date()
      }
    },
    watch : {
      currentDate (val) {
        if (!val) return
        this.headDate = val
        console.log('currentDate', val)
        // this.headDate = JSON.parse(JSON.stringify(val))
      }
    },
    methods : {
      goPrev () {
        //this.headDate = this.changeMonth(this.headDate, -1)
        let changeMonDate = new Date(this.headDate).setDate(1);
        this.headDate = this.changeMonth(changeMonDate, -1)
        this.dispatchEvent()
      },
      goNext () {
      	let changeMonDate = new Date(this.headDate).setDate(1);
        this.headDate = this.changeMonth(changeMonDate, 1)
        this.dispatchEvent()
      },
      changeMonth (date, num) {
        let dt = new Date(date)
        return new Date(dt.setMonth(dt.getMonth() + num))
      },
      dispatchEvent() {
        this.title = dateFunc.format(this.headDate, this.titleFormat, this.monthNames)

        let startDate = dateFunc.getStartDate(this.headDate)
        let curWeekDay = startDate.getDay()

        // 1st day of this monthView
        let diff = parseInt(this.firstDay) - curWeekDay
        if (diff) diff -= 7
        startDate.setDate(startDate.getDate() + diff) 

        // the month view is 6*7
        let endDate = dateFunc.changeDay(startDate, 41)

        // 1st day of current month
        let currentDate = dateFunc.getStartDate(this.headDate)

        this.$emit('change', 
          dateFunc.format(startDate, 'yyyy-MM-dd'),
          dateFunc.format(endDate, 'yyyy-MM-dd'),
          dateFunc.format(currentDate,'yyyy-MM-dd'),
          this.headDate
        )
      }
    }
  }
</script>
<style lang="scss">
	
  
  
  .bg-purple-header {
    background: #f9fafc;
  }
  .bg-purple-light-header {
    background: #e5e9f2;
  }
  .grid-content-head {
  	text-indent: 0em;
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg-header {
    padding: 10px 0;
    line-height: 60px;
    background-color: #f9fafc;
  }
  
.full-calendar-header{
  display: flex;
  align-items: center;
  .header-left,.header-right{
    flex:1;
    height: 20px;
  }
  .header-center{
    flex:3;
    text-align:center;
    .title{
      margin: 0 20px;
    }
    .prev-month,.next-month{
      cursor: pointer;
    }
  }
}
</style>
