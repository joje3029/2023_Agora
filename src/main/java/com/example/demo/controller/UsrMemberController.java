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
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {

		// 이놈이 적었나 안적었나 확인 로직
		if (loginId == null || loginId.trim().length() == 0) {
			return "아이디를 입력해주세요";
		}
		if (loginPw == null || loginPw.trim().length() == 0) {
			return "비밀번호를 입력해주세요";
		}
		if (name == null || name.trim().length() == 0) {
			return "이름을 입력해주세요";
		}
		if (nickname == null || nickname.trim().length() == 0) {
			return "닉네임을 입력해주세요";
		}
		if (cellphoneNum == null || cellphoneNum.trim().length() == 0) {
			return "전화번호를 입력해주세요";
		}
		if (email == null || email.trim().length() == 0) {
			return "이메일을 입력해주세요";
		}

		//아이디 중복이 있나 없나 로직
		//1.갔다와야것네...
		int checkLoginId=memberService.checkLoginId(loginId);
		String test= Integer.toString(checkLoginId);
//		있으면 1 없으면 0
		if(checkLoginId==1) {
			return loginId+"는 이미 사용 중인 아이디 입니다.";
		}
		
		memberService.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email);

		int lastId = memberService.getlastInsetId();
		return lastId + "번 회원이 가입 되었습니다.";
	}

}