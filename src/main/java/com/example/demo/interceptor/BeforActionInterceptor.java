package com.example.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BeforActionInterceptor implements HandlerInterceptor{
	//인터셉터를 쓸때는 HandlerInterceptor 를 상속받아야함. 종류등 자세한건 본인 블로그 참고.
	//커서놓고 Alt+shilf+s -> override/import method 선택
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
}
