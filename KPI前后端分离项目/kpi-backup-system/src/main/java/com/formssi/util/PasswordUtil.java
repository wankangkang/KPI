/**
 * FileName:PasswordHelper.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年10月19日下午11:51:46
 ********************************
 *Modifycation History:
 *date:2017年10月19日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.util;

import java.util.Optional;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.formssi.shiro.ShiroProperties;

@Component
public class PasswordUtil {
	
	@Autowired
	ShiroProperties shiroProperties;
	private final static Logger log = LoggerFactory.getLogger(PasswordUtil.class);

    public String encryptPassword(String userName,String password) {
    	try {
    		String algorithmName = Optional.ofNullable(shiroProperties.getAlgorithmName()).orElseGet(() -> "md5");// 散列算法
    		int hashIterations = Optional.ofNullable(shiroProperties.getHashIterations()).orElseThrow(NullPointerException::new);// 散列的次数
    		password =  new SimpleHash(algorithmName, password,userName, hashIterations).toHex();
		} catch (Throwable e) {
			log.info("缺少算法次数");
		}
    	return password;
    }
}
