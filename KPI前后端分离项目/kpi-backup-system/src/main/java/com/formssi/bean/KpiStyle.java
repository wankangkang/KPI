package com.formssi.bean;

import java.util.Date;

public class KpiStyle {

	private Long styleId;
	private String department;
	private String programSerialNumPrefix;
	private String programNamePrefix;
	private Date createTime;
	private Date modifyTime;

	public Long getStyleId() {
		return styleId;
	}

	public void setStyleId(Long styleId) {
		this.styleId = styleId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProgramSerialNumPrefix() {
		return programSerialNumPrefix;
	}

	public void setProgramSerialNumPrefix(String programSerialNumPrefix) {
		this.programSerialNumPrefix = programSerialNumPrefix;
	}

	public String getProgramNamePrefix() {
		return programNamePrefix;
	}

	public void setProgramNamePrefix(String programNamePrefix) {
		this.programNamePrefix = programNamePrefix;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "KpiStyle [styleId=" + styleId + ", department=" + department + ", programSerialNumPrefix="
				+ programSerialNumPrefix + ", programNamePrefix=" + programNamePrefix + ", createTime=" + createTime
				+ ", modifyTime=" + modifyTime + "]";
	}

}
