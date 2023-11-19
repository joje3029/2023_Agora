package com.example.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BeforActionInterceptor implements HandlerInterceptor{
	//인터셉터를 쓸때는 HandlerInterceptor 를 상속받아야함. 종류등 자세한건 본인 블로그 참고.
	//커서놓고 Alt+shilf+s -> override/import method 선택
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Rq rq = new Rq(request, response);
		
		 // request에 Rq 객체를 attribute로 설정
		request.setAttribute("rq", rq); 
		//HttpServletRequest의 attribute로 설정하는 부분. 이렇게 설정하면 해당 요청에 대한 정보를 Rq 객체에 담아 컨트롤러에서 사용가능.
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
}
