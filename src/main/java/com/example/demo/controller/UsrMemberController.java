package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;

@Controller
public class UsrMemberController {
	
	private MemberService memberService;
	
	UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email ) {
		
		memberService.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email );
		
		int lastId = memberService.getlastInsetId();
		return lastId+"번 회원이 가입 되었습니다.";
	}
		
}