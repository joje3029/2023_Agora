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
	
	//session에 로그인 했는지 안했는지 확인할수 있게 세팅. 안하면 loginedMemberId가 0이고 했으면 뭔가 있것지.
	public Rq(HttpServletRequest req, HttpServletResponse response) { 
		
		this.resp = response;
		
		HttpSession session = req.getSession();
		
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
}