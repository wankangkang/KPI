/**
 * FileName:ShiroConfig.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年10月15日下午11:16:04
 ********************************
 *Modifycation History:
 *date:2017年10月15日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.formssi.filter.ShiroLoginFilter;
import com.formssi.shiro.MyShiroRealm;
import com.formssi.shiro.SessionManager;
import com.formssi.shiro.ShiroProperties;

@Configuration
public class ShiroConfig {

	private final static Logger log = LoggerFactory.getLogger(ShiroConfig.class);

	@Autowired
	ShiroProperties shiroProperties;
	final String prefixRedis = "spring.redis.";
	@Value("${" + prefixRedis + "host}")
	String host;
	@Value("${" + prefixRedis + "port}")
	int port;
	@Value("${" + prefixRedis + "timeout}")
	int timeout;
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		log.info("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		Map<String, Filter> filterMap = new HashMap<String, Filter>();
		filterMap.put("shiroLoginFilter", shiroLoginFilter());
		shiroFilterFactoryBean.setFilters(filterMap);
		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		/*Optional<String> anon = Optional.ofNullable(shiroProperties.getAnon());
		anon.ifPresent((value) -> {
			if (anon.get().contains(",")) {
				String[] anonUrl = anon.map((annoString) -> annoString.split(",")).get();
				for (int i = 0; i < anonUrl.length; i++) {
					filterChainDefinitionMap.put(anonUrl[i], "anon");
				}
			} else {
				filterChainDefinitionMap.put(anon.get(), "anon");
			}
		});
		// 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put(Optional.ofNullable(shiroProperties.getLogoutUrl()).orElseGet(() -> "/logout"),
				"logout");
		// <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		Optional<String> authc = Optional.ofNullable(shiroProperties.getAuthc());
		authc.ifPresent((value) -> {
			if (authc.get().contains(",")) {
				String[] authcUrl = authc.map((authcString) -> authcString.split(",")).get();
				for (int i = 0; i < authcUrl.length; i++) {
					filterChainDefinitionMap.put(authcUrl[i], "authc");
				}
			} else {
				filterChainDefinitionMap.put(authc.get(), "authc");
			}
		});
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean
				.setLoginUrl(Optional.ofNullable(shiroProperties.getLoginUrl()).orElseGet(() -> "/login"));
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean
				.setSuccessUrl(Optional.ofNullable(shiroProperties.getSuccessUrl()).orElseGet(() -> "/sp"));

		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl(
				Optional.ofNullable(shiroProperties.getUnauthorizedUrl()).orElseGet(() -> "/unauthorized"));*/
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager(@Qualifier("authRealm") MyShiroRealm authRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(authRealm);
		securityManager.setSessionManager(sessionManager());
		// 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
		return securityManager;
	}
	
	/**
	 * 配置shiro redisManager 使用的是shiro-redis开源插件
	 * 
	 * @return
	 */
	
	 public RedisManager redisManager() { 
		 RedisManager redisManager = new RedisManager(); 
		 redisManager.setHost(host); redisManager.setPort(port);
		 redisManager.setExpire(36000);// 配置缓存过期时间
		 redisManager.setTimeout(timeout); // redisManager.setPassword(password);
		 return redisManager; 
	 }
	 /**
		 * cacheManager 缓存 redis实现 使用的是shiro-redis开源插件
		 * 
		 * @return
		 */
	
	 public RedisCacheManager cacheManager() { 
		 RedisCacheManager redisCacheManager = new RedisCacheManager();
		 redisCacheManager.setRedisManager(redisManager()); 
		 return redisCacheManager; 
	}
	 
	 /**
		 * RedisSessionDAO shiro sessionDao层的实现 通过redis 使用的是shiro-redis开源插件
		 */
	 
	@Bean 
	public RedisSessionDAO redisSessionDAO() { 
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager()); 
		return redisSessionDAO; 
	}
		 
	/**
	 * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
	 * 所以我们需要修改下doGetAuthenticationInfo中的代码; ）
	 * 
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		try {
			hashedCredentialsMatcher.setHashAlgorithmName(
					Optional.ofNullable(shiroProperties.getAlgorithmName()).orElseGet(() -> "md5"));// 散列算法
			hashedCredentialsMatcher.setHashIterations(
					Optional.ofNullable(shiroProperties.getHashIterations()).orElseThrow(NullPointerException::new));// 散列的次数
		} catch (Throwable e) {
			log.info("缺少算法次数");
		}

		return hashedCredentialsMatcher;
	}

	/**
	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean(name = "authRealm")
	public MyShiroRealm myAuthRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
		log.info("myShiroRealm()");
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		// 设置密码凭证匹配器
		myShiroRealm.setCredentialsMatcher(matcher); // myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());

		return myShiroRealm;
	}

	/**
	 * shiro session的管理
	 */
	
	 @Bean 
	 public DefaultWebSessionManager sessionManager() {
		 //DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		 SessionManager sessionManager = new SessionManager();
		 return sessionManager;
	 }
	 
	 @Bean(name="shiroLoginFilter")
	 public ShiroLoginFilter shiroLoginFilter() {
		 ShiroLoginFilter shiroLoginFilter = new ShiroLoginFilter();
		 return shiroLoginFilter;
	 }
}