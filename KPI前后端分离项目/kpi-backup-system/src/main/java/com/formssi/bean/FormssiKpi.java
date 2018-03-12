/**
 * FileName:SmpKpi.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年11月6日下午6:23:01
 ********************************
 *Modifycation History:
 *date:2017年11月6日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.bean;

public class FormssiKpi  implements Cloneable{

	public Long id;
	public String memberName; // 姓名
	public String rank; // 级别
	public String staffId; // 员工号
	public String sortSign; // 分类标识
	public Double extraWork; // 加班时间
	public Double normalWork; // 正常上班时间
	public String programSerialNum; // 项目编号
	public String programName; // 项目名称
	public String date; // 日期
	public int year; // 年份 和月份一起新增为精准取出该年月的sql准备防止使用like造成效率低下
	public int month;
	public Long memberId;
	public int version;// 乐观锁
	public String department;
	public String workType;
	public String operation;
	public String leaveDayType;
	public int version2; //乐观锁2

	
	public Object clone() {
		FormssiKpi formssiKpi;
		try {
			formssiKpi = (FormssiKpi) super.clone();
		} catch (Exception e) {
			return null;
		}
		return formssiKpi;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getSortSign() {
		return sortSign;
	}

	public void setSortSign(String sortSign) {
		this.sortSign = sortSign;
	}

	public Double getExtraWork() {
		return extraWork;
	}

	public void setExtraWork(Double extraWork) {
		this.extraWork = extraWork;
	}

	public Double getNormalWork() {
		return normalWork;
	}

	public void setNormalWork(Double normalWork) {
		this.normalWork = normalWork;
	}

	public String getProgramSerialNum() {
		return programSerialNum;
	}

	public void setProgramSerialNum(String programSerailNum) {
		this.programSerialNum = programSerailNum;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getLeaveDayType() {
		return leaveDayType;
	}

	public void setLeaveDayType(String leaveDayType) {
		this.leaveDayType = leaveDayType;
	}

	
	public int getVersion2() {
		return version2;
	}

	public void setVersion2(int version2) {
		this.version2 = version2;
	}

	@Override
	public String toString() {
		return "FormssiKpi [id=" + id + ", memberName=" + memberName + ", rank=" + rank + ", staffId=" + staffId
				+ ", sortSign=" + sortSign + ", extraWork=" + extraWork + ", normalWork=" + normalWork
				+ ", programSerialNum=" + programSerialNum + ", programName=" + programName + ", date=" + date
				+ ", year=" + year + ", month=" + month + ", memberId=" + memberId + ", version=" + version
				+ ", department=" + department + ", workType=" + workType + ", operation=" + operation
				+ ", leaveDayType=" + leaveDayType + ", version2=" + version2 + "]";
	}

}
