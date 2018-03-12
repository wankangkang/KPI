/**
 * FileName:KpiBalance.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月20日下午10:26:16
 ********************************
 *Modifycation History:
 *date:2018年1月20日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.bean;

/**
 * @author Bill
 *
 */
public class KpiBalanceInfo {

	private Long kpiBalanceId;
	private Long memberId;
	private double kpiBalance;
	private String memberName;
	private String department;

	public Long getKpiBalanceId() {
		return kpiBalanceId;
	}

	public void setKpiBalanceId(Long kpiBalanceId) {
		this.kpiBalanceId = kpiBalanceId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public double getKpiBalance() {
		return kpiBalance;
	}

	public void setKpiBalance(double kpiBalance) {
		this.kpiBalance = kpiBalance;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
