/**
 * FileName:MyShiroRealm.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年10月15日下午11:21:13
 ********************************
 *Modifycation History:
 *date:2017年10月15日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.shiro;

import java.util.Optional;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.formssi.bean.Account;
import com.formssi.service.AccountService;

public class MyShiroRealm extends AuthorizingRealm{

	private final static Logger log = LoggerFactory.getLogger(MyShiroRealm.class);
	@Autowired
	private AccountService accountService;
	/**
	 * Title:doGetAuthorizationInfo
	 * Description:本工程无需权限管理，不做权限的处理
	 * @param principals
	 * @return
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/*SimpleAuthorizationInfo info = null;
		String userName = principals.toString();
		Member member = userService.getUser(userName);
		Set<String> roles = new HashSet<String>();
		roles.add(user.getRole());
		info = new SimpleAuthorizationInfo(roles);*/
		return null;
	}

	/**
	 * Title:doGetAuthenticationInfo
	 * Description:
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		log.info("MyShiroRealm.doGetAuthenticationInfo()");
		SimpleAuthenticationInfo authenticationInfo = null;
	    //获取用户的输入的账号.
		UsernamePasswordToken userToken = (UsernamePasswordToken)token;
	    String accountName = userToken.getUsername();
	    //通过username从数据库中查找 User对象，如果找到，没找到.
	    //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
	    try {
	    	Account account = Optional.ofNullable(accountService.getAccount(accountName)).orElseThrow(AuthenticationException :: new);
	    	ByteSource salt = ByteSource.Util.bytes(accountName);
	    	//userToken = new UsernamePasswordToken(username,userToken.getPassword());
	    	authenticationInfo = new SimpleAuthenticationInfo(accountName,account.getPassword(),salt,this.getName());
	    	// 当验证都通过后，把用户信息放在session里
	        Session session = SecurityUtils.getSubject().getSession();
	        session.setAttribute("userSession", account);
	        session.setAttribute("userSessionId", account.getAccountId());
		} catch (AuthenticationException e) {
			log.info("認證錯誤");
		} 
	    
	    return authenticationInfo;
	}

}
