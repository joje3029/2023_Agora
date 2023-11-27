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
	
	//회원가입
	@RequestMapping("/usr/member/join")
	public String join(HttpServletRequest req) {
		return "usr/member/join";
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
	
	//회원정보 수정
	@RequestMapping("/usr/member/modify")
	public String modify(HttpServletRequest req) {
		return "usr/member/modify";
	}
	
	@RequestMapping("/usr/member/domodify")
	public String domodify() {
		return "/";  //일단 임시로 널 안나게
		
	}
	
	//회원탈퇴
	@RequestMapping("/usr/member/withdraw")
	public String withdraw(HttpServletRequest req) {
		return "usr/member/withdraw";
	}
	
	@RequestMapping("/usr/member/dowithdraw")
	public String dosecede() {
		return "/";  //일단 임시로 널 안나게
		
	}
	
	
	//로그인
	@RequestMapping("/usr/member/login")
	public String login(HttpServletRequest req) {
		
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
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
		
		
		rq.login(member);
		
		
		return Util.jsReplace(Util.f("%s 회원님 환영합니다~", member.getNickname()), "/");
	}
	
	//로그아웃
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		if (rq.getLoginedMemberId()==0) {
			return Util.jsHistroyBack("로그인 후 이용해주세요");
		}
		
		rq.logout();
		
		return Util.jsReplace(Util.f("정상적으로 로그아웃 되었습니다"), "/");
	}
}