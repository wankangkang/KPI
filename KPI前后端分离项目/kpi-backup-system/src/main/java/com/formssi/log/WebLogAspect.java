/**
 * FileName:WebLogAspect.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年10月7日下午4:01:27
 ********************************
 *Modifycation History:
 *date:2017年10月7日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.log;


import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class WebLogAspect {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Pointcut("execution(public * com.formssi.controller..*.*(..))")
	public void webLog(){
		
	}
	
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable{
		//接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		//记录下请求内容
		log.info("URL : "+request.getRequestURL().toString());
		log.info("HTTP_METHOD : "+request.getMethod());
		log.info("IP : "+request.getRemoteAddr());
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			log.info("name:{},value:{}",name,request.getParameter(name));
		}
	}
	
	@AfterReturning(returning = "ret",pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable{
		//处理完请求，返回内容
		log.info("RESPONSE : "+ret);
	}
}
