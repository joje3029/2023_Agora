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
	public ResultData<Member> doJoin( String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
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
		
//		sha 변환 전
//		memberService.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		//sha 변환 후
		memberService.joinMember(loginId, Util.sha256(loginPw), name, nickname, cellphoneNum, email);
		
		
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