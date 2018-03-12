/**
 * FileName:ShiroProperties.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年10月19日上午12:48:23
 ********************************
 *Modifycation History:
 *date:2017年10月19日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="shiro.config")
public class ShiroProperties {

	private String anon;
	private String authc;
	private String logoutUrl;
	private String loginUrl;
	private String successUrl;
	private String unauthorizedUrl;
	private String algorithmName;
	private Integer hashIterations;
	public String getAlgorithmName() {
		return algorithmName;
	}
	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}
	public Integer getHashIterations() {
		return hashIterations;
	}
	public void setHashIterations(Integer hashIterations) {
		this.hashIterations = hashIterations;
	}
	public String getUnauthorizedUrl() {
		return unauthorizedUrl;
	}
	public void setUnauthorizedUrl(String unauthorizedUrl) {
		this.unauthorizedUrl = unauthorizedUrl;
	}
	public String getAnon() {
		return anon;
	}
	public void setAnon(String anon) {
		this.anon = anon;
	}
	public String getAuthc() {
		return authc;
	}
	public void setAuthc(String authc) {
		this.authc = authc;
	}
	public String getLogoutUrl() {
		return logoutUrl;
	}
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public String getSuccessUrl() {
		return successUrl;
	}
	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}
	
}
