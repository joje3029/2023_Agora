package com.example.demo.vo;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.example.demo.util.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;


@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // 요청이 들어왔을때 여기에 만들어질 친구의 생명주기 같은거. 이 어노테이션이 생기면 : http 요청이 들어올 때마다 새로 만듦. 단, 새로 만들어졌지만 이전꺼를 물려받는식으로 계속 가져감.
//이거를 안하면 beforaction에서 채갈때마다 새로운 객체를 생성. 충돌가능성 발생. 그래서 새로 생길때 이전놈이 가진건 물려받되. 이전놈을 죽이는거임.
public class Rq {

	@Getter
	private int loginedMemberId;
	HttpServletResponse resp;
	HttpSession session;
	HttpServletRequest req;

	public Rq(HttpServletRequest req, HttpServletResponse response) {

		this.resp = response;
		this.req = req;

		this.session = req.getSession();

		int loginedMemberId = 0;

		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		this.loginedMemberId = loginedMemberId;
		
		this.req.setAttribute("rq", this); //this => 여기 이 객체를 보내는 거임. 왜인지는 this.에서 .을 생각하면 쟤가 뭔지 알꺼여. 
		//new Rq라고 해서 객체를 만드는 과정이 없어짐으로. beforaction에서 하던일이지만 이제는 인터셉터에서 그일을 안하니까.
	}

	// 로그인이 필요한데 로그인을 안한데이면 돌아가라를 해야하니까.
	public void jsPrintHistoryBack(String msg) {
		resp.setContentType("text/html; charset=UTF-8;");// response에서 보여줘서.

		try {
			resp.getWriter().append(Util.jsHistoryBack(msg)); // 메세지를 보이고 이전으로 돌아가게 함.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void login(Member member) { // 로그인만 되면 되는거니까 굳이 뭔가 반환할 필요 없음
		this.session.setAttribute("loginedMemberId", member.getId());
		// 이거 할라고 session이 전역으로 뺀거임.
	}

	public void logout() {
		this.session.removeAttribute("loginedMemberId"); // session이에서 요소를 삭제하는거니까 늘 하듯 remove
	}

	public String jsReturnOnView(String msg) { //얘는 controller에서 return rq.jsReturnOnView(Util.f("%d번 게시물은 존재하지 않습니다", id)); 형식으로 msg를 보내면 
		req.setAttribute("msg", msg); //키명을 msg로 해서 request에 넣고

		return "usr/common/js"; // 이 jsp로 보냄.
	}

	public void init() {
		// 말짱하게 쓰기 위해서 한번 쓰는게 목적이라서 이게 다임.
	}


}