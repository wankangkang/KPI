package com.formssi.dao;

import java.util.Map;

import com.formssi.bean.Account;

public interface AccountDao {

		Account getAccount(String accountName);
		
		int saveAccount(Account account);
		
		int updateAccount(Map<String, Object> param);
		
		int countAccount(String accountName);
}
