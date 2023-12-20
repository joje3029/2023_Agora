package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.service.MemberService;
import com.example.demo.service.ReplyService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.Member;
import com.example.demo.vo.Reply;
import com.example.demo.vo.Rq;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	private ReplyService replyService;
	private MemberService memberService;
	private Rq rq;
	
	UsrArticleController(ArticleService articleService, ReplyService replyService, MemberService memberService,  Rq rq) {
		this.articleService = articleService;
		this.replyService = replyService;
		this.memberService = memberService;
		this.rq =rq;
	}
	
	@RequestMapping("/usr/article/write")
	public String write() {
		return "usr/article/write";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(String title, String body) {
		//인터셉터에서 로그인 해야만 가능하게 설정할꺼니까 로그인했냐 안했냐도 필요없고, 
		
		if (Util.empty(title)) {
			return Util.jsHistoryBack("제목을 입력해주세요");
		}
		
		if (Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요");
		}
		
		articleService.writeArticle(rq.getLoginedMemberId(), title, body);
		
		int id = articleService.getLastInsertId();
		
		return Util.jsReplace(Util.f("%d번 게시물을 생성했습니다", id),  Util.f("detail?id=%d", id));
	}
	
	@RequestMapping("/usr/article/list")
	public String list(Model model, @RequestParam(defaultValue = "1") int type,@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "all") String searchKeywordType, @RequestParam(defaultValue = "") String searchKeyword) {
		if (page <= 0) {
			return rq.jsReturnOnView("페이지번호가 올바르지 않습니다");
		}
		
		// 총 페이지 개수
		int articlesCnt = articleService.getArticlesCnt(searchKeywordType, searchKeyword);
		
		//페이징 관련 변수
		int itemsInAPage  = 10;
		int limitStart  = (page-1) * itemsInAPage;
		int pagesCnt = (int) Math.ceil((double) articlesCnt / itemsInAPage); // 화면에 보여질 페이지의 마지막 페이지 번호
		
		// 위의 것들을 이용해서 아래의 이걸 수정해야함. => Db에 limit 만큼만 스캔때리라고 하기 위해
		//기존에 공부할때 이걸 만든 이유 => db에서 list 가꼬 올라고
		List<Article> articles = articleService.getArticles(limitStart, itemsInAPage, searchKeywordType, searchKeyword); // lastPage는 jsp에서 그려낼때 필요한 애, 데이터 베이스에서 limit으로 조회할껀 추가된 두개니까(정확하게는 연산을 해낸 결과가 필요).
		
		model.addAttribute("searchKeywordType", searchKeywordType);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("articles", articles);
		model.addAttribute("articlesCnt", articlesCnt);
		model.addAttribute("pagesCnt", pagesCnt);
		model.addAttribute("page", page);
		
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String detail(Model model, int id) {
		
		//선택한 칼럼의 내용을 가져옴.
		Article article = articleService.forPrintArticle(id);
		
		//선택한 칼럼의 댓글을 가져옴.
		List<Reply> replies = replyService.getReplies(id);
		
		//로그인 안했을때
		if(rq.getLoginedMemberId()==0) {
			model.addAttribute("article", article);
			model.addAttribute("replies", replies);
			
			return "usr/article/detail";
		}
		//로그인했을때
		
		//로그인한 놈의 정보를 가져옴. -> 닉네임에 이름 떠야해.
		Member member = memberService.getMemberById(rq.getLoginedMemberId());
		
		
		//그래야 닉네임으로 보여주니까. -> 댓글 적어놓은걸.
		model.addAttribute("nickname", member.getNickname());
		model.addAttribute("article", article);
		model.addAttribute("replies", replies);
		
		return "usr/article/detail";
	}
	
	@RequestMapping("/usr/article/modify")
	public String modify(Model model, int id) {
		
		Article article = articleService.forPrintArticle(id);
		
		if (article == null) {
			return rq.jsReturnOnView(Util.f("%d번 게시물은 존재하지 않습니다", id)); 
		}
		
		if (rq.getLoginedMemberId() != article.getColmnWrter()) {
			return rq.jsReturnOnView("해당 게시물에 대한 권한이 없습니다");
		}
		
		model.addAttribute("article", article);
		model.addAttribute("loginedMemberId", rq.getLoginedMemberId());
		
		return "usr/article/modify";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			
			return Util.jsHistoryBack("게시물은 존재하지 않습니다");
		}
		
		if(rq.getLoginedMemberId()!= article.getColmnWrter()) {
			
			return Util.jsHistoryBack("해당 게시물에 대한 권한이 없습니다");
		}
		
		//여기서부터 문제네
		
		articleService.modifyArticle(id, title, body);
		
		return Util.jsReplace(Util.f("%d번 게시물을 수정했습니다", id), Util.f("detail?id=%d", id));
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete( int id) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return Util.jsHistoryBack(Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		if (rq.getLoginedMemberId() != article.getColmnWrter()) {
			return Util.jsHistoryBack("해당 게시물에 대한 권한이 없습니다");
		}
		
		articleService.deleteArticle(id);
		
		return Util.jsReplace(Util.f("%d번 게시물을 삭제했습니다", id), "list");
	}
	
}