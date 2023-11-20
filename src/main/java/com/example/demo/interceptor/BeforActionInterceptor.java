package com.example.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BeforActionInterceptor implements HandlerInterceptor{
	
	//Rq 클래스 의존받아야하니까. 근데 이러기만 하면 제대로 못씀. 의존받은건 한번은 써야하는데 지금 안쓰잖아.
	private Rq rq;
	
	public BeforActionInterceptor(Rq rq) {
		this.rq =rq;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
//		Rq rq = new Rq(request, response);// 현재는 여기서 새로 만들고 있는데 이러면 의존성 받은 놈과 아닌놈의 객체가 따로 생기므로 없애고 Rq클래스에 @scope라는 어노테이션을 추가로 담.
//		
//		request.setAttribute("rq", rq); 
//		그리고 의존성으로 Rq 클래스를 sts에 맡겼으니까 여기는 이제 필요 없음. 오히려 있으면 객체가 따로 생겨서 문제임.
		
//		즉, 여기서는 Rq 의존성을 받기만 할꺼임. 의존성 받는 Rq 객체는 Rq 클래스에서 만들어지니까 Rq 클래스에서 생성되고 나서 set되고 넘어옴.
		
		rq.init(); //이건 순수하게 의존받은걸 사용할때 말짱하게 잘쓰기위해서 한번 사용하기 위한것. 즉 안에 아무것도 없는거임. 걍 쓰는거(부르는게) 목적이니까.
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
}
