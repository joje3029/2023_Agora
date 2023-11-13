package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {
	
	private MemberService memberService;
	
	UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
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
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData<Member> doLogin(HttpSession session, String loginId, String loginPw) {
//		유효성 검사
		if(session.getAttribute(loginId)!=null) {
			return ResultData.from("F-1", "로그아웃후 이용해주세요");
		}
		if (Util.empty(loginId)) {
			return ResultData.from("F-2", "아이디를 입력해주세요");
		}
		if (Util.empty(loginPw)) {
			return ResultData.from("F-3", "비밀번호를 입력해주세요");
		}
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member != null) {
			return ResultData.from("F-4", Util.f("(%s)는 없는 아이디 입니다", loginId));
		}
		
		
//		이제 settion에 저장
		session.setAttribute("loginId", loginId);
		return ResultData.from("S-1", Util.f("%s 로그인 되었습니다.", loginId) );
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData<Member> doLogOut(HttpSession session, String loginId) {
		if(session.getAttribute("loginId")==null) {
			return ResultData.from("F-1", "로그인 후 이용해주세요");
		}
		
		session.removeAttribute("loginId");
		return ResultData.from("S-1", Util.f("로그아웃 되었습니다.", loginId) );
		
		
	}
}