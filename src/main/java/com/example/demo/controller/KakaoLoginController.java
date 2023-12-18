package com.example.demo.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class KakaoLoginController {

    @RequestMapping(value = "/kakao-login", method = RequestMethod.GET)
    public String kakaoLogin() {
        // 카카오 REST API 호출 예시
        String apiUrl = "https://kapi.kakao.com/v2/user/me";
        String accessToken = "YOUR_ACCESS_TOKEN"; // 사용자의 액세스 토큰

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(apiUrl, String.class);

        // 결과 처리
        // ...

        return result;
    }
}
