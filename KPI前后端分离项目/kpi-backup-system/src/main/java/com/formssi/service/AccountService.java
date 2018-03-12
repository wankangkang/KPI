package com.formssi.service;

import java.util.Map;

import com.formssi.bean.Account;
import com.formssi.constans.AccountOperationType;
import com.formssi.exception.Result;

public interface AccountService {
	
	
	/**
	 * @Title:getAccount
	 * @Description:获取账户信息
	 * @param:@param accountName 用户名
	 * @param:@return
	 * @return:Account
	 * @throws
	 */
	Account getAccount(String accountName);
	
	/**
	 * @Title:saveAccount
	 * @Description:保存账户信息
	 * @param:@param account  账户信息
	 * @param:@return
	 * @return:Account
	 * @throws
	 */
	Account register(Account account,String verificationCode);
	
	/**
	 * @Title:countAccount
	 * @Description:判断账户是否存在
	 * @param:@param userName
	 * @param:@return
	 * @return:int
	 * @throws
	 */
	int countAccount(String userName);
	
	/**
	 * @Title:sendVerificationCode
	 * @Description:发送验证码
	 * @param:@param accountName  用户名
	 * @return:void
	 * @throws
	 */
	void sendVerificationCode(String accountName, String email ,AccountOperationType verificationCodeType);
	
	void sendVerificationCodeForReg(String userName,String email,AccountOperationType verificationCodeType);
	
	/**
	 * @Title:modifyPassword
	 * @Description:修改账户数据
	 * @param:@param param
	 * @param:@param verificationCode 验证码
	 * @param:@return
	 * @return:Result
	 * @throws
	 */
	Result modifyPasswordOrEmail(Map<String, Object> param,String verificationCode,AccountOperationType updateColum);
}
