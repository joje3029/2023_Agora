package com.example.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class NeedLoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//NeedLoginInterceptor 보다 BeforActionInterceptor가 먼저 실행될꺼라서 Rq가 있으므로 사용가능
		Rq rq = (Rq) request.getAttribute("rq");
		
		//Rq에서 loginMemberid에 세팅해놓으니까.
		if (rq.getLoginedMemberId() == 0) {
			rq.jsPrintHistoryBack("로그인 후 이용해주세요");
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
}