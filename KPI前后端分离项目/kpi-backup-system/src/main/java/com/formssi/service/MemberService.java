/**
 * FileName:MemberService.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年10月6日下午1:38:33
 ********************************
 *Modifycation History:
 *date:2017年10月6日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.service;

import java.util.List;
import java.util.Map;

import com.formssi.bean.Member;

public interface MemberService {
	
	/**
	 * @Title:getMember
	 * @Description:获取指定成员信息
	 * @param:@param memberId 成员ID
	 * @param:@return
	 * @return:Member
	 * @throws
	 */
	Member getMember(Long memberId);
	
	/**
	 * @Title:saveMember
	 * @Description:保存成员信息
	 * @param:@param user
	 * @param:@return
	 * @return:Member
	 * @throws
	 */
	Member saveMember(Member user);
	
	/**
	 * @Title:updateMember
	 * @Description:更新成员资料
	 * @param:@param param
	 * @param:@return
	 * @return:int
	 * @throws
	 */
	int updateAllMember(List<Member> memberList);
	
	/**
	 * @Title:countMember
	 * @Description:判断成员是否存在
	 * @param:@param userName
	 * @param:@return
	 * @return:int
	 * @throws
	 */
	int countMember(String userName);
	
	/**
	 * @Title:deleteMember
	 * @Description:删除指定成员
	 * @param:@param memberId 指定成员ID
	 * @param:@return
	 * @return:int
	 * @throws
	 */
	int deleteMember(Long memberId);
	
	/**
	 * @Title:list
	 * @Description:获取成员集
	 * @param:@param map
	 * @param:@return
	 * @return:List<Member>
	 * @throws
	 */
	List<Member> list(Map<String, Object> map);
}