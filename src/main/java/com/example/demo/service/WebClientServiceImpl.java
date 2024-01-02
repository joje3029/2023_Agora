package com.example.demo.service;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WebClientServiceImpl {

    public void get() { // 일단 새로운책으로 해서 controller에서 요청을 보냈을때 받는지 해보자. 대충 뭐 하나 jsp에서 연결해서 contorller가 일하게 하면 되니까. 
    	System.out.println(3);
    	
        String ttbkey = "ttbphandom03111555001";
        String QueryType = "ItemNewAll";
        String SearchTarget = "Book";
        String output = "js";
        String Version = "20131101";
        
        System.out.println(4);
        
        // 여기서부터 문제네. 
        // webClient 기본 설정
        WebClient webClient =
                WebClient
                        .builder()
                        .baseUrl("http://www.aladin.co.kr")
                        .build();
        
        System.out.println("webClient1 : "+webClient);
        
        // api 요청
        Map<String, Object> response =
                webClient
                        .get()
                        .uri(uriBuilder ->
                                uriBuilder
                                        .path("/ttb/api/ItemList.aspx")
                                        .queryParam("ttbkey", ttbkey)
                                        .queryParam("QueryType", QueryType)
                                        .queryParam("SearchTarget", SearchTarget)
                                        .queryParam("output", output)
                                        .queryParam("Version", Version)
                                        .build())
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                        .block();
        System.out.println("webClient2 : "+webClient); 
        
        // 결과 확인
        log.info(response.toString());// 잘 가꼬 오네
        
        System.out.println(5);
    }
}
