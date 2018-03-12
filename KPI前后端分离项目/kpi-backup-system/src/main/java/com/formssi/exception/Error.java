/**
 * FileName:Error.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年10月31日下午7:24:47
 ********************************
 *Modifycation History:
 *date:2017年10月31日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.exception;

public enum Error {
	
	UNEXPECTED_ERROR(998,"服务器飞到九霄云外了，等等，我马上去找回来！"),
	EXCEPTION(999,"存在未知异常！"),
	NOT_LOGINED(1001,"未登陆"),
	Wrong_Verification_Code(2001,"验证码错误或失效"),
	OPERATION_FAIL(2002,"操作失败"),
	BATCH_IMPORT_FAILED(2003,"批量导入失败"),
	HAVE_BATCHED(2004,"该年月已导入"),
	UN_EXIST(2005,"不存在"),
	WRONG_HOLIDAY(2006,"存在错误的节假日"),
	WRONG_LEAVE_DAY(2007,"存在错误的休假日期>1"),
	//UNAUTHORIZED(403,"Unauthorized"),
	INVALIDE_ACCOUNT(2008,"账号不存在"),
	WRONG_PASSWORD(2009,"密码错误"),
	LOCK_ACCOUNT(2010,"密码输错十次，请10分钟后重试"),
	REGISTER_FAILED(2011,"注册失败"),
	REGISTERED(2012,"该用户名已注册"),
	FAILED_MODIFY_PASSWORD(2013,"修改密码失败"),
	FAILED_MODIFY_RANK(2014,"修改级别失败"),
	FAILED_MODIFY_EMAIL(2015,"修改邮箱失败"),
	;
	
	private int code;
	private String message;
	
	private Error(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
