package com.example.demo.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.UsrSnsLoginService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.Rq;

@Controller
public class UsrSnsLoginController {
	private UsrSnsLoginService usrSnsLoginService;
	private Rq rq;

	UsrSnsLoginController(UsrSnsLoginService usrSnsLoginService, Rq rq) {
		this.usrSnsLoginService =usrSnsLoginService;
		this.rq = rq;
	}

	// 소셜로그인만 따로 할꺼임.
	// 카카오 로그인
	@RequestMapping("/usr/member/kakaoLogin")
	@ResponseBody
	public String getKakaoLoginToken(String code, Model model) throws IOException {
		// 확인용
		System.out.println("code = " + code);
        
		String access_token = usrSnsLoginService.getToken(code); 

		Map<String, Object> userInfo = usrSnsLoginService.getUserInfo(access_token);
        
		//난중에 이렇게 무식하게 짜르면 안돼 ㅠㅠ 지금은 발표해야하는데 int범위 넘어가서 여기저기서 에러떠서 일단 짜른거야. 실제로는 얘부터 다 타입이 long이어야해.
		String strId =userInfo.get("id").toString();
		strId=strId.substring(1);
		
		// 안들어가는 이유 : int 범위보다 커서 => long을 써야함. -> 
		int id = Integer.parseInt(strId); 
		System.out.println("id : "+id);
		String email = userInfo.get("age_range").toString();
		String nickname = userInfo.get("nickname").toString();
		
		Member member = new Member(id, email, nickname);
		
		
		// Db에도 member 내용이 들어가야해
		
		rq.login(member); 
		
		return Util.jsReplace(Util.f("%s 회원님 환영합니다~", member.getNickname()), "/"); // 그럼 여기를 계정 로그인 요청으로 가게?
	}

	// 네이버 로그인
	@RequestMapping("/usr/snsmember/naverLogin")
	public String naverLogin() {

		return "usr/home/main";
	}

	// 구글 로그인
	@RequestMapping("/usr/snsmember/googleLogin")
	public String googleLogin() {

		return "usr/home/main";
	}

//	// 여기서부터는 이전꺼.
//	// 회원가입
//	@RequestMapping("/usr/member/join")
//	public String join() {
//		return "usr/member/join";
//	}
//
//	// 로그인아이디 중복 체크
//	@RequestMapping("/usr/member/loginIdDupChk")
//	@ResponseBody
//	public ResultData loginIdDupChk(String loginId) {
//
//		if (Util.empty(loginId)) {
//			return ResultData.from("F-1", "아이디를 입력해주세요");
//		}
//
//		Member member = memberService.getMemberByLoginId(loginId);
//
//		if (member != null) {
//			return ResultData.from("F-2", Util.f("%s은(는) 이미 사용중인 아이디입니다", loginId));
//		}
//
//		return ResultData.from("S-1", Util.f("%s은(는) 사용 가능한 아이디입니다", loginId));
//	}

}