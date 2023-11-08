//Usr Article Controller를 새로 만들고
//
///usr/article/dowrite 라는 요청을 받았을 때
//id, title, body로 이루어진 게시물 객체 생성
//title, body는 파라미터로 처리
//-> doWrite 처리후 화면에는 생성된 객체 보여주기
//articles라는 리스트에 게시물 객체 저장
//
///usr/article/showList 라는 요청을 받았을 때 doWrite로 추가한 게시물들 출력
//
//Ariticle 클래스는 com.koreaIT.demo.vo라는 패키지 안에 만들어서 진행
//Article 클래스 안의 전역변수들은 모두 private을 걸고 롬복을 활용해서 처리.

package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.Article;

@Controller
public class UsrArticleController {
	int a;
	UsrArticleController(){
		Article article = new Article();
		this.a=0;
	}
	
	@RequestMapping("/usr/ariicle/dowrite")
	@ResponseBody
	public String showMain() {
		return "뱁새씨";
	}
	
	@RequestMapping("/usr/home/main2")
	@ResponseBody
	public int showMain2() {
		return a++;
	}
}
