/**
 * FileName:MemberMapper.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年10月4日下午10:56:45
 ********************************
 *Modifycation History:
 *date:2017年10月4日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.formssi.bean.Member;

@Mapper
@CacheConfig(cacheNames = "Member")
public interface MemberDao {
	
	//本工程非分布式工程，不会出现同名bean，且姓名为唯一值，故直接使用姓名作为redis的键做查询
	@Cacheable(key="#p0+''", sync = true)
	Member getMember(Long MemberId);
	
	//由于mybatis对update和insert只会返回成功影响的条数而不能返回具体对象，因此将save操作的插入缓存放在service层处理
	int saveMember(Member Member);
	
	//由于mybatis没法返回实际操作对象，所以没办法直接更新，故对更新和删除操作均采用删除缓存操作,删除操作是在操作成功之后删除
	int updateAllMember(List<Member> memberList);
	
	int countMember(String MemberName);
	
	@CacheEvict(key="#p0+''")
	int deleteMember(Long MemberId);
	
	List<Member> list(Map<String,Object> map);
}
