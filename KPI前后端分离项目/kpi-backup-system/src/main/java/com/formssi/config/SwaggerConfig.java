/**
 * FileName:SwaggerConfig.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年10月9日下午10:10:46
 ********************************
 *Modifycation History:
 *date:2017年10月9日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket createRestApi(){
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().
				apis(RequestHandlerSelectors.basePackage("com.formssi.controller")).
				paths(PathSelectors.any()).build();
	}
	
	private ApiInfo apiInfo(){
		return new ApiInfoBuilder().title("kpi系統的Restful|APIs")
				.description("共享平台").termsOfServiceUrl("192.168.31.202:9001").version("2.0").build();
	}
}
