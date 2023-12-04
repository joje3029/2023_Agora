package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

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
	public String doJoin( String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		if (Util.empty(loginId)) {
			return Util.jsHistoryBack("아이디를 입력해주세요");
		}
		if (Util.empty(loginPw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요");
		}
		if (Util.empty(name)) {
			return Util.jsHistoryBack("이름을 입력해주세요");
		}
		if (Util.empty(nickname)) {
			return Util.jsHistoryBack("닉네임을 입력해주세요");
		}
		if (Util.empty(cellphoneNum)) {
			return Util.jsHistoryBack("전화번호를 입력해주세요");
		}
		if (Util.empty(email)) {
			return Util.jsHistoryBack("이메일을 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member != null) {
			return Util.jsHistoryBack(Util.f("이미 사용중인 아이디(%s) 입니다", loginId));
		}
		
		//SHA 변환 후
		memberService.joinMember(loginId, Util.sha256(loginPw), name, nickname, cellphoneNum, email);
		
		return Util.jsReplace(Util.f("%s님의 가입이 완료되었습니다", name), "login");
	}
	
	//회원정보 수정
	@RequestMapping("/usr/member/modify")
	public String modify(HttpServletRequest req) {
		return "usr/member/modify";
	}
	
	@RequestMapping("/usr/member/domodify")
	public String domodify() {
		
		//일단 일로는 들어옴. 404내용보니까 return을 못찾아서 그렇지
		
		return "/usr/main/home";  //일단 임시로 null 안나게
		
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
	
	//아이디찾기
		@RequestMapping("/usr/member/findId")
		public String findId(HttpServletRequest req) {
			return "usr/member/findId";
		}
		
		
		@RequestMapping("/usr/member/dofindId")
		public String dofindId() {
			return "usr/member/foundId";
			
		}
	//비밀번호
		@RequestMapping("/usr/member/findPw")
		public String findPW(HttpServletRequest req) {
			return "usr/member/findPw";
		}
		
		
		@RequestMapping("/usr/member/dofindPw")
		public String dofindPw() {
			return "/";
			
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
			return Util.jsHistoryBack("로그아웃 후 이용해주세요");
		}
		
		if (Util.empty(loginId)) {
			return Util.jsHistoryBack("아이디를 입력해주세요");
		}
		if (Util.empty(loginPw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member == null) {
			return Util.jsHistoryBack(Util.f("%s은(는) 존재하지 않는 아이디입니다", loginId));
		}
		
		if (member.getLoginPw().equals(Util.sha256(loginPw)) == false) {
			return Util.jsHistoryBack("비밀번호를 확인해주세요");
		}
		
		
		rq.login(member);
		
		
		return Util.jsReplace(Util.f("%s 회원님 환영합니다~", member.getNickname()), "/");
	}
	
	//비밀번호수정
	@RequestMapping("/usr/member/chagePw")
	public String chagePw(HttpServletRequest req) {
		
		return "usr/member/chagePw";
	}
	
	//로그아웃
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		if (rq.getLoginedMemberId()==0) {
			return Util.jsHistoryBack("로그인 후 이용해주세요");
		}
		
		rq.logout();
		
		return Util.jsReplace(Util.f("정상적으로 로그아웃 되었습니다"), "/");
	}
	
	//인증번호 이메일 로직
	@RequestMapping("/usr/member/doSendEmail")
	public String doSendEmail(HttpServletRequest req) {
		
		
		return Util.jsReplace(Util.f("인증메일이 발송되었습니다. 이메일을 확인해주세요"), "join");
	}
	
	
	
}