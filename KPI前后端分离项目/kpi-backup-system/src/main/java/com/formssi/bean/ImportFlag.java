/**
 * FileName:ImportFlag.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年11月7日下午11:41:52
 ********************************
 *Modifycation History:
 *date:2017年11月7日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.bean;

import java.util.Date;

public class ImportFlag {

	private Long flagId;
	private boolean importFlag;
	private Date importTime;
	private Integer year;
	private Integer month;
	private String department;

	public Long getFlagId() {
		return flagId;
	}

	public void setFlagId(Long flagId) {
		this.flagId = flagId;
	}

	public boolean isImportFlag() {
		return importFlag;
	}

	public void setImportFlag(boolean importFlag) {
		this.importFlag = importFlag;
	}

	public Date getImportTime() {
		return importTime;
	}

	public void setImportTime(Date importTime) {
		this.importTime = importTime;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "ImportFlag [flagId=" + flagId + ", importFlag=" + importFlag + ", importTime=" + importTime + ", year="
				+ year + ", month=" + month + ", department=" + department + "]";
	}

}
