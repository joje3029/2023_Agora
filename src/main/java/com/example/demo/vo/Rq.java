package com.example.demo.vo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

// 밖에서 session에 하던일 시킬꺼임.
public class Rq {
	
	//밖에서 써야하니까 Getter 어노테이션을 붙임.
	@Getter
	private int loginedMemberId; //session으로 할때 key이름 이거였으니까 그대로 쓰는거임.

	// 밖에서 쓰고 있으니까 public 생성자
	public Rq(HttpServletRequest req){
		HttpSession session =req.getSession(); // session을 사용할꺼니까 request에 session을 꺼내서 변수로 담음
		
		int loginedMemberId = 0; // 아래서 비교해야하니까.

		if (session.getAttribute("loginedMemberId") != null) { // session의 key가 loginedMemberId가 null이 아니면
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}

		this.loginedMemberId = loginedMemberId;
	}
}
