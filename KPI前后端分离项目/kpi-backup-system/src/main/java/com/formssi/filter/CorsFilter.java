package com.formssi.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;  
  
/** 
 *  
 *  跨域过滤器 
 */  
@Component
@ConfigurationProperties(prefix="response")
public class CorsFilter implements Filter {  
  
	private Map<String, String> headerMap;
	
	public Map<String, String> getHeaderMap() {
		return headerMap;
	}
	public void setHeaderMap(Map<String, String> headerMap) {
		this.headerMap = headerMap;
	} 
	
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {  
        HttpServletResponse response = (HttpServletResponse) res;
        for (Map.Entry<String, String> entry : getHeaderMap().entrySet()) {
			response.setHeader(entry.getKey(), entry.getValue());
		}
        
        //前端使用cookie时需配置返回头的来源域为具体域名，否则使用*
       /* response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081"); 
        //response.setHeader("Access-Control-Allow-Origin", "http://192.168.31.99:8081"); 
        //response.setHeader("Access-Control-Allow-Origin", "*");
        //配置允许请求的方法类型
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        //设置超时过期时间
        response.setHeader("Access-Control-Max-Age", "3600");  
        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type");
        //设置cookie认证为允许
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        response.setHeader("Accss-Control-Expose-Headers", "Content-Disposition");*/
        chain.doFilter(req, res);  
    }  
    public void init(FilterConfig filterConfig) {}  
    public void destroy() {}
 /*   
    @Component
    @ConfigurationProperties(prefix="response")
    public class ResponseHeaderConfig {
    	
    	private Map<String, String> headerMap;
    	
    	public Map<String, String> getHeaderMap() {
    		return headerMap;
    	}
    	public void setHeaderMap(Map<String, String> headerMap) {
    		this.headerMap = headerMap;
    	} 
    }*/
}
