package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.BeforActionInterceptor;
import com.example.demo.interceptor.NeedLoginInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	//interceter로 만든거 두개 설정
	private BeforActionInterceptor beforeActionInterceptor;
	private NeedLoginInterceptor needLoginInterceptor;

	public MyWebMvcConfigurer(BeforActionInterceptor beforeActionInterceptor,
			NeedLoginInterceptor needLoginInterceptor) {
		this.beforeActionInterceptor = beforeActionInterceptor;
		this.needLoginInterceptor = needLoginInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//경로 설정
		//beforeActionInterceptor의 경로 설정
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/rosource/**");

		//needLoginInterceptor의 경로 설정
		registry.addInterceptor(needLoginInterceptor).addPathPatterns("/usr/article/doWrite")
				.addPathPatterns("/usr/article/doDelete").addPathPatterns("/usr/article/doModify");
	}

}