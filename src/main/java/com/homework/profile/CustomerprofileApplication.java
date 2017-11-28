package com.homework.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
public class CustomerprofileApplication {



	public static void main(String[] args) {
		SpringApplication.run(CustomerprofileApplication.class, args);
	}

	@Bean
	public Docket docket() {
		ApiSelectorBuilder apiSelectorBuilder = new Docket(DocumentationType.SWAGGER_2).select();
		return apiSelectorBuilder
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/api/profile/**"))
				.build();
	}

	@Bean
	public CacheManager cacheManager() {
		CacheManager cacheManager = new ConcurrentMapCacheManager("customerProfile");
		return  cacheManager;
	}


}
