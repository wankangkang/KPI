package com.formssi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface Param {

	//需要放入map中的参数名称
	String[] key();
	
	//需要放入map中value的对应方法参数名
	String[] value();
}
