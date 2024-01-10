package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class BookSearchController {
	
	private final String NAVER_API_URL = "https://openapi.naver.com/v1/search/book.json";

    private final String clientId = "sre3apwylaef28oEMZxP";
    private final String clientSecret = "rwYLDjLKwK";
	
	
	BookSearchController() {
	}

	@RequestMapping("/usr/book/search")
	public String showBookSearch() {
		return "usr/book/search";
	}
	
	@RequestMapping("/usr/book/doBookSearch")
	@ResponseBody
	public Map<String, Object> doBookSearch(String bookTitle) {
		
		Map<String, Object> data =new HashMap<>();
		
		// 여기서 할 짓 bookTitle로 네이버검색에 보냄. 결과 받은거가 json으로 오니까 그거 retrun
		RestTemplate restTemplate = new RestTemplate();
		
		// HttpHeaders 생성 및 사용자 지정 헤더 추가
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Naver-Client-Id", clientId);
		headers.set("X-Naver-Client-Secret", clientSecret);

		// URL 파라미터 설정
		String url = String.format("%s?query=%s", NAVER_API_URL, bookTitle);

		// 사용자 지정 헤더를 포함하여 요청 보내기
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        
		try {
            // JSON 파싱
            JSONParser parser = new JSONParser();
            JSONObject jsonResponse = (JSONObject) parser.parse(responseEntity.getBody());
            JSONArray itemsArray = (JSONArray) jsonResponse.get("items");

            // items를 새로운 Map에 담기
            List<Map<String, Object>> itemsList = new ArrayList<>();
            for (Object item : itemsArray) {
                JSONObject itemObject = (JSONObject) item;
                Map<String, Object> itemMap = new HashMap<>();
                itemMap.put("title", itemObject.get("title"));// 책 제목
                itemMap.put("link", itemObject.get("link")); // 구매 링크
                itemMap.put("image", itemObject.get("image")); // 책 이미지
                itemMap.put("author", itemObject.get("author")); // 작가
                itemMap.put("description", itemObject.get("description")); // 책 소개내용
                itemMap.put("discount", itemObject.get("discount")); // 가격
                itemMap.put("publisher", itemObject.get("publisher")); // 출판사
                itemMap.put("pubdate", itemObject.get("pubdate")); // 출판일
                // 필요한 다른 필드들도 추가할 수 있음
                itemsList.add(itemMap);
            }

            // 결과 데이터에 items 추가
            data.put("items", itemsList);
        } catch (Exception e) {
            e.printStackTrace();
            data.put("error", "Error parsing JSON response");
        }

        return data;
	}

}