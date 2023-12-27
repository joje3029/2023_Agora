package com.example.demo.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.UsrSnsLoginService;
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
	public String getKakaoLoginToken(String code, Model model) throws IOException {
		// 확인용
		System.out.println("code = " + code);
        
		String access_token = usrSnsLoginService.getToken(code); 
		// 확인용        
		System.out.println("code : "+code);
		// 확인용
		System.out.println("여기를 오기는 하는가 그대?"); // 여기를 오심-> 즉 404가 아님. =>
		// 확인용
		System.out.println("token : " + access_token); // 코드가 출력이 되시거든. 그럼 그냥 여기서 post url로 만들어서 요청을 보내면 안되냐?

		Map<String, Object> userInfo = usrSnsLoginService.getUserInfo(access_token);
        
		// 확인용
		System.out.println("userInfo : "+userInfo);
		System.out.println("code : "+code);
		System.out.println("access_token : "+access_token);
        System.out.println("userInfo : "+userInfo);
		
        model.addAttribute("code", code);
        model.addAttribute("access_token", access_token);
        model.addAttribute("userInfo", userInfo);
		

		return "usr/home/main"; // 그럼 여기를 계정 로그인 요청으로 가게?
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