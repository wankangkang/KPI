/**
 * FileName:VerificationCodeUtil.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月1日下午9:15:34
 ********************************
 *Modifycation History:
 *date:2018年1月1日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.util;

/**
 * @author Bill
 *
 */
public class VerificationCodeUtil {

	public static String gernerateCode() {
		String verificationCode = (1 + (int)(Math.random()*999999))+"";
		return verificationCode;
	}
	
}
