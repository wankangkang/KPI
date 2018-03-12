/**
 * FileName:GlobalExceptionHandler.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年10月7日上午9:22:33
 ********************************
 *Modifycation History:
 *date:2017年10月7日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.exception;

import java.util.Map;

import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	/**
	 * 全局处理运行异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	public Map<String, Object> exceptionHandler(RuntimeException e){
		log.error(e.getMessage(), e);
		return Result.error(Error.UNEXPECTED_ERROR);
	}
	
	/**
	 * 全局处理异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public Result handleException(Exception e) {
		log.error(e.getMessage(), e);
		return Result.error(Error.EXCEPTION);
	}
	
	/**
	 * @Title:handleException
	 * @Description:此处偷懒直接全局处理自定义的运行异常
	 * @param:@param e
	 * @param:@return
	 * @return:Result
	 * @throws
	 */
	@ExceptionHandler(GlobalException.class)
	public Result handleException(GlobalException e) {
		log.error(e.getMessage(), e);
		return Result.error(e.getCode(),e.getMessage());
	}
	
	@ExceptionHandler(UnauthenticatedException.class)
	public Result handleUnauthorizedException() {
		return Result.error(Error.NOT_LOGINED);
	}
}
