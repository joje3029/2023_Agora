package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

@Controller
public class UsrSnsLoginController {

	private Rq rq;

	UsrSnsLoginController(Rq rq) {
		this.rq = rq;
	}

	// 소셜로그인만 따로 할꺼임.
	// 카카오 로그인
	@RequestMapping("/usr/member/kakaoLogin")
	public String getKakaoLoginToken(String code) throws IOException {

		// 인가코드로 토큰 받기
		String host = "https://kauth.kakao.com/oauth/token";
		URL url = new URL(host);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); // Java에서 URL을 통해 HTTP 연결을 수행하는 데 사용되는 부분. url.openConnection()을 호출하여 URLConnections 객체를 얻은 후, 이를 HTTPURLConnection으로 캐스팅.
		// openConnection() : URL에 대한 연결을 엽. 이 메서드는 URLConnection 객체를 반환함.
		String token = "";

		try {
			urlConnection.setRequestMethod("POST"); // http 요청을 post로 보내겠다.
			urlConnection.setDoOutput(true); // HttpURLConnection이 출력 스트림을 사용할 것임을 설정하는 메서드. 이 설정을 통해 애플리케이션은 해당 연결에
												// 데이터를 출력할 수 있게 됨.

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			// 1. BufferedWriter : 문자 기반 출력 스트림을 버퍼링하는 역할. 데이터를 버퍼에 임시로 저장해두었다가 일정량이 쌓이면 한
			// 번에 출력하는 역할. 주로 FileWriter/OutputStreamWriter와 함께 사용되어 파일이나 네트워크와 같은 출력 대상에
			// 데이터를 쓸 때 성능을 향상시키기 위해 활용됨.
			// 2. OutputStreamWriter : 바이트 출력 스트림에서 문자 출력 스트림으로의 다리 역할을 함. 문자를 바이트로 변환할 때
			// 인코딩을 지정.
			// 3. StringBuilder : 문자열을 담기 위한 가변성을 가진 클래스. 문자열의 변경이 자주 일어날때 String보다
			// 효율적(String: 불변, StringBuilder : 가변)
			
			// 즉 위에서 StringBuilder를 이용해서 postUrl을 가변으로 때림!! kakao가 요청한거니까.
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=b17693f42efb99ea5feb3e636ab6eb1b");
			sb.append("&redirect_uri=http://localhost:8081/usr/member/kakaoLogin"); // 이건 해보고 오케이 되면 바꿔야 함.
			sb.append("&code=" + code);
			
			bw.write(sb.toString()); // 위에서 URL로 만든거 넣음
            bw.flush(); // 내부 버퍼에 있는 모든 데이터를 출력 스트림으로 보내는 역할 : 위에서 말한거처럼 입출력 연산이 많으면 매번 바로 출력하지 않고 일정량의 데이터를 모아서 출력하면 성능이 향상되는데 그 이유.
            
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            // 1. BufferedReader : 입력 스트림으로부터 텍스트를 읽어올 때 버퍼링을 제공하는 클래스. read() 메서드로 하나의 문자열을 읽는 대신. 한 번에 여러 문자를 읽어와서 버퍼에 저장한 후 프로그램이 필요할 때마다 버퍼에서 읽게함 -> 입출력이 빠르고 효율적으로 이루어짐.
            // 2. InputStreamReader : 바이트 기반의 InputStream을 문자 기반의 Reader로 변환하는 역할을 함.
            // => BufferedReader : 읽어온 데이터를 버퍼에 저장, InputStreamReader : 해당 바이트 스트림을 문자 스트림으로 변환
            
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) { // br.readLine()이 BufferedReader로 부터 한줄의 문자열을 읽어옴. line 변수에 현재 읽은 라인이 할당됨. line이 null이 아닐 동안 루프 실행.
                result += line; // 각 라인을 result에 추가
            }
            System.out.println("result = " + result); // 여기서 result된 결과 한번 찍어보기
            
         // json parsing
            JSONParser parser = new JSONParser();
            JSONObject elem = (JSONObject) parser.parse(result); // 문자열 result를 파싱하여 JsonObject로 변환. 변환한건 elem에 들어감.
            
            String access_token = elem.get("access_token").toString(); // 파싱한거에서 키가 access_token을 꺼내서 타입을 String으로 바꿔서 access_token에 넣음.
            String refresh_token = elem.get("refresh_token").toString();
            System.out.println("refresh_token = " + refresh_token); // 잘 들어갔는지 확인
            System.out.println("access_token = " + access_token);
            
            token = access_token; // try전에 선언한 token에 백업
            
            //자원 다썼으니까 끄기
            br.close(); 
            bw.close();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		return token;
		
		System.out.println("code : "+code);

		System.out.println("여기를 오기는 하는가 그대?"); // 여기를 오심-> 즉 404가 아님. =>

		System.out.println("token : " + token); // 코드가 출력이 되시거든. 그럼 그냥 여기서 post url로 만들어서 요청을 보내면 안되냐?

		// 그럼 여기서 get 요청을 Kakao AuthServer로 보내야함 : /oauth/authorize
		// 그럼 kakao Auth Server에서 client에서 카카오 계정 로그인 요청을 해야함.

		// 인가 코드받기 get - url : https://kauth.kakao.com/oauth/authorize

		return "usr/member/kakaoLogin"; // 그럼 여기를 계정 로그인 요청으로 가게?
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