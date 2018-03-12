package com.formssi.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.formssi.annotation.DateModel;
import com.formssi.util.DateUtil;

@Aspect
@Component
public class ModelAspect {

	@Pointcut("@annotation(com.formssi.annotation.DateModel)")
	public void modelPointCut() {
	}

	@Around("modelPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		// 执行方法
		Object result = point.proceed();
		//添加年月到model中
		addMonthAndYearToModel(point);
		return result;
	}

	private void addMonthAndYearToModel(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Optional<DateModel> smpModel = Optional.ofNullable(method.getAnnotation(DateModel.class));
		Object[] args = joinPoint.getArgs();
		Date date = new Date();
		String strDate = DateUtil.formatDate(date);
		Integer currYear = Integer.parseInt(strDate.substring(0,4));
		Integer currMonth = Integer.parseInt(strDate.substring(5,7));
		//Integer currMonth = 1;
		smpModel.ifPresent((value) -> {
			Integer specificYear = Integer.parseInt(smpModel.get().value());
			List<Integer> years = new ArrayList<Integer>();
			List<Integer> months = new ArrayList<Integer>();
			for (int year = 2016; year <= specificYear; year++) {
				years.add(year);
			}
			for (int month = 1; month < 13; month++) {
				months.add(month);
			}
			for (Object arg : args) {
				if (arg instanceof Model) {
					((Model)arg).addAttribute("years", years);
					((Model)arg).addAttribute("months", months);
					//将当前月份的上一月份对应的年月放入model中
					if (currMonth == 1) {
						((Model)arg).addAttribute("currMonthYear", currYear-1);
						((Model)arg).addAttribute("lastMonth", 12);
					}else {
						((Model)arg).addAttribute("currMonthYear", currYear);
						((Model)arg).addAttribute("lastMonth", currMonth-1);
					}
				}
			}
		});
	}
}
