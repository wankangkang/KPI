/**
 * FileName:DescripType.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年11月3日下午10:58:50
 ********************************
 *Modifycation History:
 *date:2017年11月3日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.constans;

public enum DescripType {

	SA("BOCHK-HK-OPEN-SMP-SA","中银香港-香港-开放系统-销售流程管理系统-高级"),
	AP("BOCHK-HK-OPEN-SMP-AP","中银香港-香港-开放系统-销售流程管理系统-中级"),
	PR("BOCHK-HK-OPEN-SMP-PR","中银香港-香港-开放系统-销售流程管理系统-初级"),
	T1("BOCHK-HK-OPEN-SMP-T1","中银香港-香港-开放系统-销售流程管理系统-实训"),
	L1("BOCHK-HK-OPEN-SMP-L1","中银香港-香港-开放系统-销售流程管理系统-年假"),
	L2("BOCHK-HK-OPEN-SMP-L2","中银香港-香港-开放系统-销售流程管理系统-事假"),
	L4("BOCHK-HK-OPEN-SMP-L4","中银香港-香港-开放系统-销售流程管理系统-陪产假"),
	;
	
	private String programSerialNum;
	private String programName;
	
	private DescripType(String programSerialNum, String programName) {
		this.programSerialNum = programSerialNum;
		this.programName = programName;
	}
	
	public String getProgramSerialNum() {
		return programSerialNum;
	}
	
	public void setProgramSerialNum(String programSerialNum) {
		this.programSerialNum = programSerialNum;
	}
	
	public String getProgramName() {
		return programName;
	}
	
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
}
