package com.formssi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.formssi.constans.Operation;
import com.formssi.constans.WorkType;

@Retention(RUNTIME)
@Target(METHOD)
public @interface FilterKpi {

	String[] name() default {};
	
	String[] value() default {};
	
	String[] exceptName() default {};
	
	String[] exceptValue() default {};
	
	WorkType workType();
	
	Operation operation();
	
	String newListName();
	
	String oldListName();
}
