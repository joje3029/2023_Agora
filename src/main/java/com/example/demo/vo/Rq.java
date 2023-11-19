package com.example.demo.vo;

import java.io.IOException;

import com.example.demo.util.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

public class Rq {
	
	@Getter
	private int loginedMemberId;
	HttpServletResponse resp;
	HttpSession session;
	HttpServletRequest req;
	
	//session에 로그인 했는지 안했는지 확인할수 있게 세팅. 안하면 loginedMemberId가 0이고 했으면 뭔가 있것지.
	public Rq(HttpServletRequest req, HttpServletResponse response) { 
		
		this.resp = response;
		this.req = req;
		
		this.session = req.getSession();
		
		int loginedMemberId = 0;
		
		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		this.loginedMemberId = loginedMemberId;
	}
	
	//로그인이 필요한데 로그인을 안한데이면 돌아가라를 해야하니까.
	public void jsPrintHistoryBack(String msg) {
		resp.setContentType("text/html; charset=UTF-8;");//response에서 보여줘서.
		
		try {
			resp.getWriter().append(Util.jsHistroyBack(msg)); // 메세지를 보이고 이전으로 돌아가게 함.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void login(Member member) { //로그인만 되면 되는거니까 굳이 뭔가 반환할 필요 없음
		this.session.setAttribute("loginedMemberId", member.getId());
		//이거 할라고 session이 전역으로 뺀거임.
	}

	public void logout() {
		this.session.removeAttribute("loginedMemberId"); //session이에서 요소를 삭제하는거니까 늘 하듯 remove
	}

}