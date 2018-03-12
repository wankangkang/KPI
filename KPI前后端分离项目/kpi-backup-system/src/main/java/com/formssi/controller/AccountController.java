package com.formssi.controller;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.websocket.server.PathParam;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formssi.bean.Account;
import com.formssi.constans.AccountOperationType;
import com.formssi.exception.Error;
import com.formssi.exception.Result;
import com.formssi.redis.RedisClient;
import com.formssi.redis.RedisUtil;
import com.formssi.service.AccountService;
import com.formssi.util.JsonUtil;
import com.formssi.util.ParamMap;
import com.formssi.util.ParamUtil;
import com.formssi.util.PasswordUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


// http://192.168.31.202:9001/login?userName=smp&&password=123456&&method=post
@RestController
public class AccountController {

	@Autowired
	AccountService accountService;
	@Autowired
	PasswordUtil passwordHelper;
	@Autowired
	RedisTemplate<String, String> redisTemplate;
	@Autowired
	ParamUtil<String, Object> paramUtil;
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	RedisClient redisClient;
	//登錄功能
	@ApiOperation("用戶登錄")
	@PostMapping("/login")
	public Result login(/*@ApiParam(name="password",value = "密碼",required = true)@PathParam(value = "password") String password,
			@ApiParam(name="userName",value = "用户名",required = true)@PathParam(value = "userName") String userName*/
			@ApiParam(name="password",value = "密碼",required = true)@RequestParam String password,
			@ApiParam(name="userName",value = "用户名",required = true)@RequestParam String userName) {
		//String userName = map.get("userName");
		//String password = map.get("password");
		final String login_suffix = "-login";
		Subject currentUser = SecurityUtils.getSubject();
		Result result = new Result();
		final int maxErrorTimes = 10;
		int count = Integer.parseInt(Optional.ofNullable(redisTemplate.opsForValue().get(userName + login_suffix)).orElse("0"));
		if (currentUser.isAuthenticated() == false) {
			UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
			try {
				if (count >= maxErrorTimes) {
					throw new DisabledAccountException();
				}else {
					currentUser.login(token);
					// 返回sessionId作为token  
					result.put("token",currentUser.getSession().getId());
					result.put("user", accountService.getAccount(userName));
					if (count != 0) {
						//jedis.del(userName);
						redisTemplate.delete(userName + login_suffix);
					}
					return result;
				}
			} catch (UnknownAccountException e) {
				//error = Error.INVALIDE_ACCOUNT;
				return Result.error(Error.INVALIDE_ACCOUNT);
			}catch (IncorrectCredentialsException e) {
				count ++;
				if (count < 10) {
					//两小时内总计输错十次则锁定十分钟
					redisTemplate.opsForValue().set(userName + login_suffix, count+"", 2, TimeUnit.HOURS);
				}else {
					redisTemplate.opsForValue().set(userName + login_suffix, count+"", 10, TimeUnit.MINUTES);
				}
				//jedis.setex(userName, locakTime, count+"");
				return Result.error(Error.WRONG_PASSWORD);
			}catch (DisabledAccountException e) {
				return Result.error(Error.LOCK_ACCOUNT);
			}
		}
		return Result.returnObj("user", accountService.getAccount(userName));
	}
	
	//註冊功能
	@ApiOperation("用戶注册")
	@ApiResponses(@ApiResponse(code=0,message="Success"))
	@PostMapping("/register")
	public Result register(@ApiParam(name = "account",value="注册的用户数据",required = true)@PathParam(value="account") String accountStr,
			@ApiParam(name = "verificationCode",value="验证码",required = true)@PathParam(value="verificationCode") String verificationCode
			/*@ApiParam(name = "account",value="注册的用户数据",required = true)@RequestParam Account account*/) {
		Account account = JsonUtil.strToObj(accountStr, Account.class);
		if (accountService.register(account, verificationCode) == null) {
			return Result.error(Error.Wrong_Verification_Code);
		}
		//如果没有被异常捕获，则操作成功
		return Result.message(200, "注册成功");
	}
	
	//修改密码功能
	@ApiOperation("修改密码功能")
	@ApiResponses(@ApiResponse(code=0,message="Success"))
	@PostMapping("/modifyPassword")
	public Result modifyPassword(/*@ApiParam(name="userName",value = "用戶名",required = true)@PathParam(value="userName")String userName,
			@ApiParam(name="password",value = "密码",required = true)@PathParam(value="password") String password,
			@ApiParam(name="verificationCode",value = "验证码",required = true)@PathParam(value="verificationCode") String verificationCode*/
			@ApiParam(name="userName",value = "用戶名",required = true)@RequestParam(value="userName") String userName,
			@ApiParam(name="password",value = "密码",required = true)@RequestParam(value="password") String password,
			@ApiParam(name="verificationCode",value = "验证码",required = true)@RequestParam String verificationCode) {
		ParamMap<String, Object> param = new ParamMap<>();
		password = passwordHelper.encryptPassword(userName, password);
		param = paramUtil.getParamMap(param, userName, password);
		//param.put("accountName", userName);
		//param.put("password", password);
		return accountService.modifyPasswordOrEmail(param, verificationCode,AccountOperationType.password);
		//return accountService.updateAccount(param)== 0?Result.error(Error.FAILED_MODIFY_PASSWORD):Result.success();
	}
	
	//用户是否存在
	@ApiOperation("判断用户是否存在")
	@GetMapping("/existUser")
	public Result existUser(@ApiParam(name="userName",value = "用户名",required = true)@RequestParam String userName) {
		return accountService.countAccount(userName)==0?Result.success():Result.error(Error.REGISTERED);
	}
	
	//登出
	@ApiOperation("登出")
	@GetMapping("/logout")
	public Result logout() {
		SecurityUtils.getSubject().logout();
		return Result.success();
	}
	
	//修改密码时的验证码发送
	@ApiOperation("发送验证码到邮箱")
	@GetMapping("/sendVericationCode")
	public Result sendVericationCode(@ApiParam(name="userName",value = "用户名",required = true)@RequestParam String userName,
			@ApiParam(name="email",value = "邮箱")@RequestParam String email,
			@ApiParam(name="vericationCodeType",value = "验证码类型，值有email(代表此次修改的为邮箱),password(代表此次修改的为密码),register(注册)",required = true)@RequestParam AccountOperationType vericationCodeType) {
		accountService.sendVerificationCode(userName, email ,vericationCodeType);
		return Result.success();
	}
	
	//修改密码时的验证码发送
	@ApiOperation("注册发送验证码到邮箱")
	@GetMapping("/sendVericationCodeForReg")
	public Result sendVericationCodeForReg(@ApiParam(name="userName",value = "用户名",required = true)@RequestParam String userName,
			@ApiParam(name="email",value = "邮箱",required = true)@RequestParam String email,
			@ApiParam(name="vericationCodeType",value = "验证码类型，值有email(代表此次修改的为邮箱),password(代表此次修改的为密码),register(注册)",required = true)@RequestParam AccountOperationType vericationCodeType) {
		accountService.sendVerificationCodeForReg(userName, email,vericationCodeType);
		return Result.success();
	}
		
	//更换邮箱
	@ApiOperation("更换邮箱")
	@GetMapping("/changeEmail")
	public Result changeMail(@ApiParam(name="userName",value = "用户名",required = true)@RequestParam String userName,
			@ApiParam(name="email",value = "新的用户邮箱",required = true)@RequestParam String email,
			@ApiParam(name="verificationCode",value = "验证码",required = true)@RequestParam String verificationCode) {
		ParamMap<String, Object> param = new ParamMap<>();
		param = paramUtil.getEmailParamMap(param, userName, email);
		return accountService.modifyPasswordOrEmail(param, verificationCode, AccountOperationType.email);
	}
}
