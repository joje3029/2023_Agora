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
		String access_token = usrSnsLoginService.getToken(code); 
		Map<String, Object> userInfo = usrSnsLoginService.getUserInfo(access_token);
		
		// 안들어가는 이유 : int 범위보다 커서 => long을 써야함. -> 
		String strId =userInfo.get("id").toString();
		//난중에 이렇게 무식하게 짜르면 안돼 ㅠㅠ 지금은 발표해야하는데 int범위 넘어가서 여기저기서 에러떠서 일단 짜른거야. 실제로는 얘부터 다 타입이 long이어야해.
		String email = userInfo.get("age_range").toString();
		String nickname = userInfo.get("nickname").toString();
		
		// 여기서 중복 검증을 챙겨야겠네.
		// 일단 strId를 데꼬 Db를 간다. 잘 가꼬 오는지 확인한다.
		Member memberCheck = usrSnsLoginService.getMemberCheck(strId);
		
		Member member =null;
		if(memberCheck==null) {
			// Db에 kakao에서 데꼬 온 애 insert
			usrSnsLoginService.insertKakaoinfo(strId, email, nickname);
			//insert한 애 pk 값 들고오려고 던짐.
			int lastId = usrSnsLoginService.getLastId();
			//여기서 마지막에 들어간 애 행을 데꼬와서 member에 넣어서 들어가면 다른데서도 안꼬이겠다.
			member=usrSnsLoginService.getLastInsertMember(lastId);
			
		}else {
			// member 가 뭐라도 있다 -> 즉 이전 로그인 기록이 있다.
			// 이때는 insert가 아니라 이미 불러와졌고 그 기록이가 있으니까.
			member = memberCheck;
		}
		
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

}