/**
 * FileName:Model.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年11月6日下午9:02:38
 ********************************
 *Modifycation History:
 *date:2017年11月6日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateModel {
	String value() default "2025";
}
