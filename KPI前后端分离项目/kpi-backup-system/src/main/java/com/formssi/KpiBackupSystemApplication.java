package com.formssi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.formssi.dao")
@EnableSwagger2
@EnableCaching
public class KpiBackupSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(KpiBackupSystemApplication.class, args);
	}
}
