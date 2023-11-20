package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {
	
	private MemberService memberService;
	private Rq rq;
	
	UsrMemberController(MemberService memberService, Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin( String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		if(rq.getLoginedMemberId()!=0) {
			return ResultData.from("F-L", "로그아웃 후 이용해주세요");
		}
		
		if (Util.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}
		if (Util.empty(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력해주세요");
		}
		if (Util.empty(name)) {
			return ResultData.from("F-3", "이름을 입력해주세요");
		}
		if (Util.empty(nickname)) {
			return ResultData.from("F-4", "닉네임을 입력해주세요");
		}
		if (Util.empty(cellphoneNum)) {
			return ResultData.from("F-5", "전화번호를 입력해주세요");
		}
		if (Util.empty(email)) {
			return ResultData.from("F-6", "이메일을 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member != null) {
			return ResultData.from("F-7", Util.f("이미 사용중인 아이디(%s) 입니다", loginId));
		}
		
		memberService.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		int id = memberService.getLastInsertId();
		
		return ResultData.from("S-1", "회원 가입 성공", memberService.getMemberById(id));
	}
	
	@RequestMapping("/usr/member/login")
	public String login(HttpServletRequest req) {
		
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody //애가 그려야하니까.
public String doLogin( String loginId, String loginPw) {
		
		if(rq.getLoginedMemberId()!=0) {
			return Util.jsHistroyBack("로그아웃 후 이용해주세요");
		}
		
		if (Util.empty(loginId)) {
			return Util.jsHistroyBack("아이디를 입력해주세요");
		}
		if (Util.empty(loginPw)) {
			return Util.jsHistroyBack("비밀번호를 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member == null) {
			return Util.jsHistroyBack(Util.f("%s은(는) 존재하지 않는 아이디입니다", loginId));
		}
		
		if (member.getLoginPw().equals(loginPw) == false) {
			return Util.jsHistroyBack("비밀번호를 확인해주세요");
		}
		
		
//		session.setAttribute("loginedMemberId", member.getId());
//		여기서는 더이상 session에 직접 접근하지않으니까 가져오는거 이외에는 불가능 => 가꼬오는것도 rq에서 연결했으니까 가능
//	    즉, rq에서 뭔가 해야함. 둘이 엮어서 req안의 session에 넣을 방법을.
		
		rq.login(member);
		
		
		return Util.jsReplace(Util.f("%s 회원님 환영합니다~", member.getNickname()), "/");
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		if (rq.getLoginedMemberId()==0) {
			return Util.jsHistroyBack("로그인 후 이용해주세요");
		}
		
//		session.removeAttribute("loginedMemberId");
//		login과 같은 이유. 직접 session 접근 안하니까.ㅏ
		rq.logout();
		
		return Util.jsReplace(Util.f("정상적으로 로그아웃 되었습니다"), "/");
	}
}