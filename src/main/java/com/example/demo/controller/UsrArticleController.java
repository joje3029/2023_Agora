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
	//현재 이런데서 다 session에 직접 접근함. 이걸 request에 들어가서 session으로 변경 => 그러면 request에 넣을때 매개변수로 일일이 안때리고 request를 추가하면 됨. 그리고 session을 꺼내 쓸때마다 이상 없는지 null인지 아닌지 확인작업을 그때그때 해야함. 그래서 변경하는것도 있음.
	public ResultData<Article> doWrite(HttpSession session, String title, String body) {
		
		if (session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-L", "로그인 후 이용해주세요");
		}
		
		if (Util.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		
		if (Util.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}
		
		articleService.writeArticle((int) session.getAttribute("loginedMemberId"), title, body);
		
		int id = articleService.getLastInsertId();
		
		return ResultData.from("S-1", Util.f("%d번 게시물을 생성했습니다", id), articleService.getArticleById(id));
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(Model model) {
		
		List<Article> articles = articleService.getArticles();
		
		model.addAttribute("articles", articles);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	//해당 게시물을 보여주기만 하면 됨.
	public String showDetail(HttpServletRequest req, Model model, int id) {
		
		Rq rq = new Rq(req);
		
//		if (session.getAttribute("loginedMemberId") == null) {
//			return Util.jsHistroyBack("로그인 후 이용해주세요");
//		}
//		이제 이일을 여기가 아니라 Rq클래스에서 해서 rq에 남기겠지
		
		Article article = articleService.getArticleById(id);
		
//		model.addAttribute("article",article);
		model.addAttribute("loginedMemberId", rq.getLoginedMemberId()); // Rq 객체 만들어질때 Rq가 검증처리 다한걸 rq에 넣은거고. 거기서 loginedMemberId라과 key이름을 지은걸 model에 추가해서 jsp에서 쓸수 있게 함.
		
		
//		if (article == null) {
//			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다", id));
//		}
		return "usr/article/detail";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(HttpSession session, int id, String title, String body) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		if ((int) session.getAttribute("loginedMemberId") != article.getMemberId()) {
			return ResultData.from("F-A", "해당 게시물에 대한 권한이 없습니다");
		}
		
		articleService.modifyArticle(id, title, body);
		
		return ResultData.from("S-1", Util.f("%d번 게시물을 수정했습니다", id));
	}
	
	@RequestMapping("/usr/article/doDelete")
	public String doDelete(HttpSession session, int id) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			//delete는 삭제되고나서 jsp가 삭제되었다는 글만 내뱉고 또 어딘가를 가느니. alert만 보여주고 이전상황으로 돌아가는게 나음. 그래서 String타입 => ResultData 방식 저건 이제 문법적으로 못씀.
			return Util.jsHistroyBack(Util.f("%d번 게시물은 존재하지 않습니다", id)); //js문법을 사용한다는 의미.
//			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		if ((int) session.getAttribute("loginedMemberId") != article.getMemberId()) {
			return Util.jsHistroyBack(Util.f("%d번 게시물은 권한이 없습니다", id));
//			return ResultData.from("F-A", "해당 게시물에 대한 권한이 없습니다");
		}
		
		articleService.deleteArticle(id);
		
		return "../home/main";
	}
	
}