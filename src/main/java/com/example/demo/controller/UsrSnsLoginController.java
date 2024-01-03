package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

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
		
		String strId =userInfo.get("id").toString();
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

	// 네이버 로그인 요청 보내기
	@RequestMapping("/usr/member/toNaverLogin")
	public String toNaverLogin() { // 버튼을 누르면 이리로 옴. -> 여기서 요청을 보내고 그거가 redirect를 아래의 naverLogin으로 하게 하자.
		
		 // state용 난수 생성
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString(32);
        
        // redirect
        StringBuffer url = new StringBuffer();
        url.append("https://nid.naver.com/oauth2.0/authorize?");
        url.append("client_id=sre3apwylaef28oEMZxP&state");
        url.append("&response_type=code");
        url.append("&redirect_uri=http://localhost:8081/usr/member/naverLogin");
        url.append("&state=" + state);
        
        System.out.println("url : "+url);

        return "redirect:" + url;
        
	}

	// 네이버에서 토큰 온거
	@RequestMapping("/usr/member/naverLogin")
	public String naverLogin(@RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "state", required = false) String state) {
		
		String clientId = "sre3apwylaef28oEMZxP";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "rwYLDjLKwK";//애플리케이션 클라이언트 시크릿값";
		
		// 네이버에 요청 보내기
        WebClient webclient = WebClient.builder()
            .baseUrl("https://nid.naver.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

        JSONObject response = webclient.post()
            .uri(uriBuilder -> uriBuilder
                .path("/oauth2.0/token")
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("grant_type", "authorization_code")
                .queryParam("state", state)
                .queryParam("code", code)
                .build())
            .retrieve().bodyToMono(JSONObject.class).block();
            
        // 네이버에서 온 응답에서 토큰을 추출
        String token = (String) response.get("access_token");
		
        System.out.println(token); //AAAAO8veEihf4pm0qQFSYVCKhfF3HTz30TAAeasdIuDluisP8siBKafPJ9NiWrwzVFjqibrNuQz1zrkGLWSp5sK8RHc
        Map<String, Object> member=getUserInfo(token);
		
		// 전체 동의하고 나서 오는거 -> 즉 여기서 음... 카카오씨처럼 뭔가를 해서 main으로 보내야함.
		
        return Util.jsReplace(Util.f("%s 회원님 환영합니다~", member.getNickname()), "/"); // 그럼 여기를 계정 로그인 요청으로 가게?
	}

	private Map<String, Object> getUserInfo(String accessToken) {
		 // 사용자 정보 요청하기
        WebClient webclient = WebClient.builder()
            .baseUrl("https://openapi.naver.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

        JSONObject response = webclient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/v1/nid/me")
                .build())
            .header("Authorization", "Bearer " + accessToken)
            .retrieve()
            .bodyToMono(JSONObject.class).block();
        
        // 원하는 정보 추출하기
        Map<String, Object> res = (Map<String, Object>) response.get("response");

        return res;
	}

	// 구글 로그인
	@RequestMapping("/usr/member/googleLogin")
	public String googleLogin() {
		
	
		
		System.out.println(3);

		return "usr/home/main";
	}

}