package com.example.demo.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.service.UsrSnsLoginService;
import com.example.demo.util.Util;
import com.example.demo.vo.JwtUtil;
import com.example.demo.vo.Member;
import com.example.demo.vo.Rq;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SimpleSecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;




@Controller
public class UsrSnsLoginController {
	private UsrSnsLoginService usrSnsLoginService;
	private Rq rq;
	private String googleClientId = "620485610882-nenvgg4jdejjldeq8j60dnnrf15h1iia.apps.googleusercontent.com";
	private String googleClientSecret = "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";
	private String redirectUri = "http://localhost:8081/usr/member/googleLogin";
	private String tokenEndpoint = "https://oauth2.googleapis.com/token";
	
	@Autowired
    private JwtUtil jwtUtil;
	
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
        

        return "redirect:" + url;
        
	}

	// 네이버에서 토큰 온거
	@RequestMapping("/usr/member/naverLogin")
	@ResponseBody
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
        Map<String, Object> userinfo=getUserInfo(token);
        
        // 짤라서 때려넣어. 내 DB에 그리고 자른거중에 객체 맞는건 객체화 해서 rq.로 넣어
        String uwerId=(String) userinfo.get("id");
        String nickname=(String) userinfo.get("nickname");
        String email=(String) userinfo.get("email");
        String name=(String) userinfo.get("name");
        
        // 여기서 중복 검증을 챙겨야겠네.
     	// 일단 strId를 데꼬 Db를 간다. 잘 가꼬 오는지 확인한다.
     	Member memberCheck = usrSnsLoginService.getMemberCheck(uwerId);
        
     	Member member =null;
		if(memberCheck==null) {
			// Db에 kakao에서 데꼬 온 애 insert
			usrSnsLoginService.insertNaverinfo(uwerId, nickname, email, name);
			//insert한 애 pk 값 들고오려고 던짐.
			int lastId = usrSnsLoginService.getLastId();
			//여기서 마지막에 들어간 애 행을 데꼬와서 member에 넣어서 들어가면 다른데서도 안꼬이겠다.
			member=usrSnsLoginService.getLastInsertMember(lastId);
			
		}else {
			// member 가 뭐라도 있다 -> 즉 이전 로그인 기록이 있다.
			// 이때는 insert가 아니라 이미 불러와졌고 그 기록이가 있으니까.
			member = memberCheck;
		}
        
		// 전체 동의하고 나서 오는거 -> 즉 여기서 음... 카카오씨처럼 뭔가를 해서 main으로 보내야함.
        rq.login(member);

        return Util.jsReplace(Util.f("%s 회원님 환영합니다~", member.getNickname()), "/"); // 그럼 여기를 계정 로그인 요청으로 가게?
	}

	private Map<String, Object> getUserInfo(String accessToken) {
		 //네이버 사용자 정보 요청하기
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

	// 구글 로그인 요청
		@RequestMapping("/usr/member/toGoogleLogin")
		public String toGoogleLogin() {
			
			String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
		                + "&redirect_uri=http://localhost:8081/usr/member/googleLogin&response_type=code&scope=profile";
			
			System.out.println(reqUrl);
			
			return "redirect:" + reqUrl;
			
		}
		
		@GetMapping("/usr/member/googleLogin")
		public String successGoogleLogin(@RequestParam("code") String authorizationCode){
			
		    
			System.out.println("authorizationCode : "+authorizationCode);
			
			 // Access Token 요청을 위한 데이터 설정
	        String tokenRequestBody = "code=" + authorizationCode
	                + "&client_id=" + googleClientId
	                + "&client_secret=" + googleClientSecret
	                + "&redirect_uri=" + redirectUri
	                + "&grant_type=authorization_code";
			
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Content-Type", "application/x-www-form-urlencoded");
	        
	     // RestTemplate을 사용하여 Access Token 요청
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> responseEntity = restTemplate.exchange(tokenEndpoint, HttpMethod.POST,
	                new HttpEntity<>(tokenRequestBody, headers), String.class);
	        
	     // 응답에서 Access Token 추출
	        String responseBody = responseEntity.getBody();
	        System.out.println("Access Token Response: " + responseBody); // 네 오셨어요.
	        
	        JSONParser parser = new JSONParser();
			
	        try {
	            // JSON 문자열을 JSON 객체로 파싱
	            JSONObject json = (JSONObject) parser.parse(responseBody);

	            // access_token 추출
	            String idToken = (String) json.get("id_token");
	            System.out.println("IdToken: " + idToken);

	            // RS256 디코딩을 위한 JWKSet 로드
	            JWKSet jwkSet = JWKSet.load(new URL("https://www.googleapis.com/oauth2/v3/certs"));

	            // JWTProcessor 설정
	            ConfigurableJWTProcessor<SimpleSecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
	            JWSVerificationKeySelector<SimpleSecurityContext> keySelector = new JWSVerificationKeySelector<>(JWSAlgorithm.RS256, new JWKSetKeySelector<>(jwkSet));
	            jwtProcessor.setJWSKeySelector(keySelector);

	            // JWT 디코딩 및 검증
	            JWTClaimsSet claimsSet = jwtProcessor.process(idToken, null);

	            // 여기에서 claimsSet을 사용하여 추가 작업 수행
	            System.out.println("Decoded Claims: " + claimsSet.toJSONObject());

	        } catch (ParseException | BadJOSEException | JOSEException e) {
	            e.printStackTrace();
	        }

	        return "usr/member/naverLogin2";
		}

	
	
	

	
	

}