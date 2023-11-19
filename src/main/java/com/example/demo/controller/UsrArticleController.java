package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	
	UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData<Article> doWrite(HttpServletRequest req, String title, String body) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		if (Util.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		
		if (Util.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}
		
		articleService.writeArticle(rq.getLoginedMemberId(), title, body);
		
		int id = articleService.getLastInsertId();
		
		return ResultData.from("S-1", Util.f("%d번 게시물을 생성했습니다", id), articleService.getArticleById(id));
	}
	
	@RequestMapping("/usr/article/list")
	public String list(Model model) {
		
		List<Article> articles = articleService.getArticles();
		
		model.addAttribute("articles", articles);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String detail(HttpServletRequest req, Model model, int id) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.forPrintArticle(id);
		
		model.addAttribute("article", article);
		model.addAttribute("loginedMemberId", rq.getLoginedMemberId());
		
		return "usr/article/detail";
	}
	
	@RequestMapping("/usr/article/modify")
	//여기는 이 mapping이 하는 일 특성상 @ResponseBody 를 붙일수 없음 : 여기가 답을하거나 화면을 할꺼가 아니라. modify 화면을 그리는 jsp 로 일을 넘기는 역할이므로.
	public String modify(HttpServletRequest req, Model model, int id) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.forPrintArticle(id);
		
		//화면을 그리고 나서가 아니라 들어갈때 이사람이 권한이 있는지 없는지 존재하지 않는지도 한번 거르면 더 좋겠다. => 이걸 하더라도 저런 문구를 위의 이유로 return 못함.
		if (article == null) {
			return rq.jsReturnOnView(Util.f("%d번 게시물은 존재하지 않습니다", id)); //그래서 rq에 jsReturnOnView 라는 메소드를 만들어서 해결하는 것임.
		}
		
		if (rq.getLoginedMemberId() != article.getMemberId()) {
			return rq.jsReturnOnView("해당 게시물에 대한 권한이 없습니다");
		}
		
		model.addAttribute("article", article);
		model.addAttribute("loginedMemberId", rq.getLoginedMemberId());
		
		return "usr/article/modify";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(HttpServletRequest req, int id, String title, String body) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			
			return Util.jsHistroyBack("게시물은 존재하지 않습니다");
		}
		
		if(rq.getLoginedMemberId()!= article.getMemberId()) {
			
			return Util.jsHistroyBack("해당 게시물에 대한 권한이 없습니다");
		}
		
		articleService.modifyArticle(id, title, body);
		
		return Util.jsReplace(Util.f("%d번 게시물을 수정했습니다", id), Util.f("detail?id=%d", id));
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return Util.jsHistroyBack(Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		if (rq.getLoginedMemberId() != article.getMemberId()) {
			return Util.jsHistroyBack("해당 게시물에 대한 권한이 없습니다");
		}
		
		articleService.deleteArticle(id);
		
		return Util.jsReplace(Util.f("%d번 게시물을 삭제했습니다", id), "list");
	}
	
}