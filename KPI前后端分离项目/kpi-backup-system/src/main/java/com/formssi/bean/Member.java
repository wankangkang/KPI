/**
 * FileName:User.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年10月4日下午10:55:15
 ********************************
 *Modifycation History:
 *date:2017年10月4日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;

@ApiModel
public class Member implements Serializable{
	/**
	 * @FieldserialVersionUID:TODO
	 */
	private static final long serialVersionUID = 1L;
	

	private Long memberId;
	private String memberName;
	private String rank;
	private String department;
	private Date createTime;
	private Date modifyTime;
	
	public Member() {
		super();
	}
	
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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
		return "Member [memberId=" + memberId + ", memberName=" + memberName + ", rank=" + rank + ", department=" + department + ", createTime=" + createTime + ", modifyTime="
				+ modifyTime + "]";
	}

}
