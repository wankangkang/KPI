package com.formssi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formssi.bean.KpiBalanceInfo;
import com.formssi.bean.Member;
import com.formssi.dao.KpiBalanceDao;
import com.formssi.dao.MemberDao;
import com.formssi.service.MemberService;
import com.formssi.util.PasswordUtil;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberDao userDao;
	@Autowired
	PasswordUtil passwordHelper;
	@Autowired
	KpiBalanceDao kpiBalanceDao;
	public Member getMember(Long memberId) {
		return userDao.getMember(memberId);
	}

	@Override
	@Transactional
	@CachePut(value = "user",key="#user.memberName",unless = "#user eq null")
	public Member saveMember(Member user) {
		user.setModifyTime(new Date());
		user.setCreateTime(new Date());
		userDao.saveMember(user);
		KpiBalanceInfo kpiBalanceInfo = new KpiBalanceInfo();
		kpiBalanceInfo.setDepartment(user.getDepartment());
		kpiBalanceInfo.setMemberId(user.getMemberId());
		kpiBalanceInfo.setMemberName(user.getMemberName());
		kpiBalanceInfo.setKpiBalance(0);
		kpiBalanceDao.saveKpiBalanceInfo(kpiBalanceInfo);
		return user;
	}

	@Override
	@CacheEvict(value="Member", allEntries=true,beforeInvocation=true)
	public int updateAllMember(List<Member> memberList) {
		return userDao.updateAllMember(memberList);
	}

	@Override
	public int countMember(String memberName) {
		return userDao.countMember(memberName);
	}

	@Override
	@Transactional
	public int deleteMember(Long memberId) {
		kpiBalanceDao.deleteKpiBalanceInfo(memberId);
		return userDao.deleteMember(memberId);
	}
	
	@Override
	public List<Member> list(Map<String, Object> map) {
		return userDao.list(map);
	}
}
