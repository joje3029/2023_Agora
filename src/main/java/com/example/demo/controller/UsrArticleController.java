package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	
	UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(String title, String body) {
		
//		Article article = articleService.writeArticle(title, body); 
//		위의 코드가 내 코드 이러면 작살남. 왜 이렇게 하려고 했는지는 알겠는데 이러면 안됨. 이친구는 그냥 DB에 insert만 하기 때문에 return 타입이 없어.
		articleService.writeArticle(title, body); 
		
		int lastId = articleService.getlastInsetId(); //이런식으로 따로 구해야함. 지금 들어간 놈은 마지막 글로 들어가니까.그리고 저 select 자체가 반환을 하니까.
		return lastId+"번 글이 생성되었습니다.";
	}
	
	@RequestMapping("/usr/article/showList")
	@ResponseBody
	public List<Article> showList() {
		return articleService.getArticles();
	}
	
	@RequestMapping("/usr/article/showDetail")
	@ResponseBody
	public Object showDetail(int id) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다";
		}
		
		return article;
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다";
		}
		
		articleService.modifyArticle(article, title, body);
		
		return id + "번 게시물을 수정했습니다";
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다";
		}
		
		articleService.deleteArticle(article);
		
		return id + "번 게시물을 삭제했습니다";
	}
	
}