package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Rq;

@Controller
public class WebRTCTestController {
	
	private ArticleService articleService;
	private Rq rq;
	
	WebRTCTestController(ArticleService articleService){
		this.articleService = articleService;
	}
	
	// test
	@RequestMapping("/usr/test/index")
	public String showInterduce() {
		
		System.out.println("지나가는겨?");
		
		return "/usr/test/index";
	}


	
}