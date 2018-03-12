/**
 * FileName:KpiData.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月20日下午8:42:08
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
public class KpiData {

	private String rank;
	private double normal;
	private double extra;
	private double totalKpi;

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public double getNormal() {
		return normal;
	}

	public void setNormal(double normal) {
		this.normal = normal;
	}

	public double getExtra() {
		return extra;
	}

	public void setExtra(double extra) {
		this.extra = extra;
	}

	public double getTotalKpi() {
		return totalKpi;
	}

	public void setTotalKpi(double totalKpi) {
		this.totalKpi = totalKpi;
	}

	@Override
	public String toString() {
		return "KpiData [rank=" + rank + ", normal=" + normal + ", extra=" + extra + ", totalKpi=" + totalKpi + "]";
	}

}
