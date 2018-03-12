package com.formssi.aspect;

import java.lang.reflect.Method;
import java.util.Optional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.formssi.annotation.Param;
import com.formssi.exception.GlobalException;
import com.formssi.util.ParamMap;

@Aspect
@Component
public class ParamAspect {

	@Pointcut("@annotation(com.formssi.annotation.Param)")
	public void paramPointCut() {
	}

	@Around("paramPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		// 执行方法
		Object result = point.proceed();
		//添加参数到参数中的paramMap中
		addConstructorToMap(point);
		return result;
	}

	@SuppressWarnings("unchecked")
	private void addConstructorToMap(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		String[] paramNames = ((CodeSignature)joinPoint.getStaticPart().getSignature()).getParameterNames();
		Optional<Param> paramModel = Optional.ofNullable(method.getAnnotation(Param.class));
		Object[] args = joinPoint.getArgs();
		ParamMap<String, Object> params = new ParamMap<>();
		String[] anotationKey = paramModel.get().key();
		String[] anotationValue = paramModel.get().value();
		//如果注解上的key和value数量不一致，抛出异常
		if (anotationKey.length != anotationValue.length) {
			throw new GlobalException("Param注解上的Key和value的数量不一致");
		}
		//循环查找value名对应的参数放入map，map的key为注解上的key
		for (int i = 0; i < anotationValue.length; i++) {
			for (int j = 0; j < paramNames.length; j++) {
				if (paramNames[j].equals(anotationValue[i])) {
					params.put(anotationKey[i], args[j]);
				}
			}
		}
		//如果出现放入的value数量少于注解上的，抛出异常说明多了参数
		if (params.keySet().size() < anotationValue.length) {
			throw new GlobalException("Param注解上的value未找到对应的参数");
		}
		
		//将params的整个map放入到参数的map中
		for (Object arg : args) {
			if (arg instanceof ParamMap) {
				((ParamMap<String, Object>) arg).putAll(params);
			}
		}
	}
}
