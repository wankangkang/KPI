package com.formssi.service.impl;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.formssi.bean.Account;
import com.formssi.constans.AccountOperationType;
import com.formssi.dao.AccountDao;
import com.formssi.exception.Error;
import com.formssi.exception.GlobalException;
import com.formssi.exception.Result;
import com.formssi.redis.RedisClient;
import com.formssi.service.AccountService;
import com.formssi.util.MailUtil;
import com.formssi.util.PasswordUtil;
import com.formssi.util.VerificationCodeUtil;

@Service
@CacheConfig(cacheNames = "account")
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountDao accountDao;
	@Autowired
	PasswordUtil passwordHelper;
	@Autowired
	RedisClient redisClient;
	
	private static final String ENAIL_TITLE = "深圳四方精创资讯股份有限公司港中银项目";
	private static final String VERIFICATION_CODE_SUFFIX = "-verificationCode";
	
	@Override
	@Cacheable(key="#root.targetClass + #accountName", sync = true)
	public Account getAccount(String accountName) {
		return accountDao.getAccount(accountName);
	}

	@Override
	@CachePut(key="#root.targetClass + #account.accountName",condition = "#result ne null")
	public Account register(Account account,String verification) {
		if (!verification.equals(redisClient.operate(jedis -> jedis.get(account.getAccountName() + "-" + AccountOperationType.register + VERIFICATION_CODE_SUFFIX)))) {
			return null;
		}
		String emailText = "<body><p>您好," + account.getAccountName() + ":</p><br/><p>尊敬的" + account.getAccountName() + "组,您已成功注册项目组名为<Strong>" + account.getAccountName() + "</Strong>的账号</p></body>";
		account.setPassword(passwordHelper.encryptPassword(account.getAccountName(), account.getPassword()));
		int result = accountDao.saveAccount(account);
		if (result != 0) {
			//使验证码失效
			redisClient.operate(jedis -> jedis.del(account.getAccountName() + "-" + AccountOperationType.register + VERIFICATION_CODE_SUFFIX));
		}
		MailUtil.send_qqmail(account.getEmail(), ENAIL_TITLE, emailText);
		return account;
	}

	@Override
	@CacheEvict(key="#root.targetClass + #p0.get('accountName')")
	public Result modifyPasswordOrEmail(Map<String, Object> param,String vericationCode,AccountOperationType updateColum) {
		String verificationCodeInRedis = redisClient.operate(jedis -> jedis.get(param.get("accountName") + "-" + updateColum + VERIFICATION_CODE_SUFFIX));
		if (!vericationCode.equals(verificationCodeInRedis) || verificationCodeInRedis == null) {
			return Result.error(Error.Wrong_Verification_Code);
		}
		int updateResult =  accountDao.updateAccount(param);
		if (updateResult != 0) {
			//使验证码失效
			redisClient.operate(jedis -> jedis.del(param.get("accountName") + "-" + updateColum + VERIFICATION_CODE_SUFFIX));
		}else {
			return Result.error(Error.FAILED_MODIFY_PASSWORD);
		}
		return Result.success();
	}

	@Override
	public int countAccount(String userName) {
		return accountDao.countAccount(userName);
	}

	@Override
	public void sendVerificationCode(String accountName, String email ,AccountOperationType verificationType) {
		try {
			Account account = Optional.ofNullable(accountDao.getAccount(accountName)).orElseThrow(Exception::new);
			String verificationCode = VerificationCodeUtil.gernerateCode();
			if (email.length() == 0) {
				email = null;
			}
			//邮件消息
			String emailText = "<body><p>您好," + account.getAccountName() + ":</p><br/><p>尊敬的" + 
					account.getAccountName() + "组,本次的验证码为<Strong>" + verificationCode + 
					"</Strong>,此验证码30分钟内有效!请及时使用。</p></body>";
			MailUtil.send_qqmail(Optional.ofNullable(email).orElse(account.getEmail()), ENAIL_TITLE, emailText);
			//将验证码放入redis中，本工程懒得封装redis的util类，直接使用redis缓存中封装的redis模板redisTemplate
			redisClient.operate(jedis -> jedis.setex(accountName + "-" + verificationType + VERIFICATION_CODE_SUFFIX, 30*60, verificationCode));
		} catch (Exception e) {
			throw new GlobalException("账号不存在");
		}
	}

	@Override
	public void sendVerificationCodeForReg(String userName,String email,AccountOperationType verificationType) {
		try {
			String verificationCode = VerificationCodeUtil.gernerateCode();
			//邮件消息
			String emailText = "<body><p>您好," +userName + ":</p><br/><p>尊敬的" + 
					userName + "组,本次的注册的验证码为<Strong>" + verificationCode + 
					"</Strong>,此验证码30分钟内有效!请及时使用。</p></body>";
			MailUtil.send_qqmail(email, ENAIL_TITLE, emailText);
			//将验证码放入redis中，本工程懒得封装redis的util类，直接使用redis缓存中封装的redis模板redisTemplate
			redisClient.operate(jedis -> jedis.setex(userName + "-" + verificationType + VERIFICATION_CODE_SUFFIX, 30*60, verificationCode));
		} catch (Exception e) {
			throw new GlobalException("注册失败");
		}
	}
}
