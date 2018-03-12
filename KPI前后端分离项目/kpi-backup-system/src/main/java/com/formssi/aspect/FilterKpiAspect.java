package com.formssi.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.formssi.annotation.FilterKpi;
import com.formssi.bean.FormssiKpi;
import com.formssi.constans.Operation;
import com.formssi.constans.WorkType;
import com.formssi.exception.GlobalException;

@Aspect
@Component
public class FilterKpiAspect {

	@Pointcut("@annotation(com.formssi.annotation.FilterKpi)")
	public void filterKpiPointCut() {
	}

	@Around("filterKpiPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		//根据注解上的指定条件筛选满足的List
		filterKpi(point);
		// 执行方法
		Object result = point.proceed();
		
		return result;
	}

	@SuppressWarnings({ "unchecked"})
	private void filterKpi(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Optional<FilterKpi> filterKpi = Optional.ofNullable(method.getAnnotation(FilterKpi.class));
		String[] paramNames = ((CodeSignature)joinPoint.getStaticPart().getSignature()).getParameterNames();
		Object[] args = joinPoint.getArgs();
		String[] names = filterKpi.get().name();
		String[] values = filterKpi.get().value();
		String[] exceptNames = filterKpi.get().exceptName();
		String[] exceptValues = filterKpi.get().exceptValue();
		String newListName = filterKpi.get().newListName();
		String oldListName = filterKpi.get().oldListName();
		WorkType workType = filterKpi.get().workType();
		Operation operation = filterKpi.get().operation();
		Predicate<FormssiKpi> condition1 = fk -> WorkType.valueOf(fk.getWorkType()) == workType;
		Predicate<FormssiKpi> condition2 = fk -> Operation.valueOf(fk.getOperation()) == operation;
		List<FormssiKpi> newList = new ArrayList<FormssiKpi>();
		List<FormssiKpi> oldList = new ArrayList<FormssiKpi>();
		//新的List的位置
		int newListPos = 0;
		for (int i = 0; i < paramNames.length; i++) {
			if (newListName.equals(paramNames[i])) {
				if (args[i] instanceof List) {
					newList = (List<FormssiKpi>)args[i];
					newListPos = i;
					//如果新的List值不为空，则清空该List
					if (newList.size() != 0) {
						newList.clear();
					}
				}else {
					throw new GlobalException("注解上的参数名不是对应List类型参数");
				}
			}else if(oldListName.equals(paramNames[i])) {
				if (args[i] instanceof List) {
					oldList = (List<FormssiKpi>)args[i];
				}else {
					throw new GlobalException("注解上的参数名不是对应List类型参数");
				}
			}
		}
		Field[] fields = FormssiKpi.class.getFields();
		//如果注解上的key和value数量不一致，抛出异常
		if (exceptNames.length != exceptValues.length || names.length != values.length) {
			throw new GlobalException("Param注解上的Key和value的数量不一致");
		}
		newList = ((List<FormssiKpi>) oldList).stream().filter(condition1.and(condition2)).collect(Collectors.toList());
		if (names.length != 0) {
			for (int i = 0; i < names.length; i++) {
				for (Field field : fields) {
					if (names[i].equals(field.getName())) {
						newList = newList.stream().filter((FormssiKpi fk) ->{
							try {
								for (int j = 0; j < names.length; j++) {
									if (names[j].equals(field.getName())) {
										return (values[j].equals(field.get(fk)+""));
									}
								}
							} catch (IllegalArgumentException | IllegalAccessException e) {
								e.printStackTrace();
							}
							return false;
						}).collect(Collectors.toList());
					}
				}
			}
		}
		if (exceptNames.length != 0) {
			for (int i = 0; i < exceptNames.length; i++) {
				for (Field field : fields) {
					if (field.getName().equals(exceptNames[i])) {
						newList = newList.stream().filter((FormssiKpi fk) ->{
							try {
								for (int j = 0; j < exceptNames.length; j++) {
									if (exceptNames[j].equals(field.getName())) {
										return (!exceptValues[j].equals(field.get(fk)+""));
									}
								}
							} catch (IllegalArgumentException | IllegalAccessException e) {
								e.printStackTrace();
							}
							return false;
						}).collect(Collectors.toList());
					}
				}
			}
		}
		newList.forEach(e -> System.out.println("###########  : " + e.toString()));
		((List<FormssiKpi>) args[newListPos]).addAll(newList);
	}
}
