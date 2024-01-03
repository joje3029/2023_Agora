package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;
import com.example.demo.vo.Rq;

@Controller
public class UsrHomeController {
	
	private ArticleService articleService;
	private Rq rq;
	
	UsrHomeController(ArticleService articleService){
		this.articleService = articleService;
	}
	
//	메인
	@RequestMapping("/usr/home/main")
	public String showMain(Model model) {
		
		// 메인으로 가기 전에 DB에서 랭킹 정보를 가지고 가야해.
		// 우선은 칼럼만 데꼬 가자
		
		// 로그인 안했으면 : 좋아요 순으로 가꼬와야함. 
		if(rq == null) {
			// 이거 수정해야함. 
			List<Article> articles =articleService.getArticleRank();
			
			model.addAttribute("articles", articles);
			
			return "usr/home/main";
		}
		
		//로그인 했으면 추천 모듈이 일을해서 세팅
		
		// 추천 모듈씨는 되면 넣기
		// 일단 책이 어떻게 메인에 나오는지 궁금해 때리기
		
		
		System.out.println("지나가는겨?");
		
		System.out.println("2"+rq);
		
		return "usr/home/main";
	}
	
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}
	
//	사이트소개
	@RequestMapping("/usr/home/interduce")
	public String showInterduce() {
		return "usr/home/interduce";
	}


	
}