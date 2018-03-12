package com.formssi.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.servlet.AdviceFilter;
import org.springframework.beans.factory.annotation.Value;

import com.formssi.bean.Account;
import com.formssi.exception.Error;
import com.formssi.exception.Result;

public class ShiroLoginFilter extends AdviceFilter  {

	@Value("${shiro.config.anon}")
	String anon;
	 @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        System.out.println("***** 请求的数据类型是 ：" + request.getContentType());
        System.out.println("***** 请求的编码格式" + request.getCharacterEncoding());
        System.out.println("***** 请求的URL是： " + httpServletRequest.getRequestURI());
        for (@SuppressWarnings("rawtypes") Map.Entry entry : request.getParameterMap().entrySet()) {
			System.out.println("请求的参数名为 ：" + entry.getKey() + " ,请求的参数值为 ： " + request.getParameter((String) entry.getKey()));
		}
        boolean returnFlag = false;
        List<String> anonUrlList = new ArrayList<>();
        Account account = (Account)httpServletRequest.getSession().getAttribute("userSession");
        if (anon.isEmpty()) {
        	//对于/login一定不做拦截
        	anonUrlList.add("/login");
		}else {
			anonUrlList = Arrays.stream(anon.split(",")).collect(Collectors.toList());
		}
        if (account == null) {
			for (String anonUrl : anonUrlList) {
				//遍历查看URL是否含有关键字
				if (!httpServletRequest.getRequestURI().contains(anonUrl)) {
					returnFlag = false;
				}else {
					//如果含有不拦截关键字，则直接通过并跳出循环
					returnFlag = true;
					break;
				}
			}
		}else {
			//已登陆用户不用判断直接通过
			returnFlag = true;
		}
        if (! returnFlag) {
        	httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write(Result.error(Error.NOT_LOGINED).toString());
		}
        return returnFlag;
    }
}
