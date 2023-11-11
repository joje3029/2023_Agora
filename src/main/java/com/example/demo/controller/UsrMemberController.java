package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;

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
		
		if (Util.isEmpty(loginId)) {
			return "아이디를 입력해주세요";
		}
		
		if (Util.isEmpty(loginPw)) {
			return "비밀번호를 입력해주세요";
		}
		if (Util.isEmpty(name)) {
			return "이름을 입력해주세요";
		}
		if (Util.isEmpty(nickname)) {
			return "닉네임을 입력해주세요";
		}
		if (Util.isEmpty(cellphoneNum)) {
			return "전화번호를 입력해주세요";
		}
		if (Util.isEmpty(email)) {
			return "이메일을 입력해주세요";
		}

		//아이디 중복이 있나 없나 로직

		int checkLoginId=memberService.checkLoginId(loginId);
//		있으면 1 없으면 0
		if(checkLoginId==1) {
			return loginId+"는 이미 사용 중인 아이디 입니다.";
		}
//		비밀번호는 중복 오케이
//		닉네임이랑 전화번호 이메일은 아니잖아.
//		근데 이러면 이걸 그때 그떄 DB를 가야하는데.. 이럴빠에야 한번 갔다오고 나서 그걸 객체로 받은걸 까는게 빠르지 않나? 아닌가? 월요일 차쌤 묻기
//		이게 이래도 되는건지 서비스시에 잘 모르겠어.
		int checkNickname=memberService.checkNickname(nickname);
//		있으면 1 없으면 0
		if(checkNickname==1) {
			return nickname+"는 이미 사용 중인 닉네임 입니다.";
		}
		
		int checkCellphoneNum=memberService.checkCellphoneNum(cellphoneNum);
//		있으면 1 없으면 0
		if(checkCellphoneNum==1) {
			return cellphoneNum+"는 이미 사용 중인 전화번호 입니다.";
		}
		
		int checkEmail=memberService.checkEmail(email);
//		있으면 1 없으면 0
		if(checkEmail==1) {
			return email+"는 이미 사용 중인 이메일 입니다.";
		}
		
		
		memberService.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email);

		int lastId = memberService.getlastInsetId();
		return lastId + "번 회원이 가입 되었습니다.";
	}

	

}