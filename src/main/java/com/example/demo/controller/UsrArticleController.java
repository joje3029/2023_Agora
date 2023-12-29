package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RecommendService;
import com.example.demo.service.ReplyService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.Member;
import com.example.demo.vo.RecommendPoint;
import com.example.demo.vo.Reply;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	private RecommendService recommendService;
	private ReplyService replyService;
	private MemberService memberService;
	private Rq rq;
	
	UsrArticleController(ArticleService articleService, ReplyService replyService, MemberService memberService, RecommendService recommendService, Rq rq) {
		this.articleService = articleService;
		this.replyService = replyService;
		this.memberService = memberService;
		this.recommendService = recommendService;
		this.rq =rq;
	}
	
	@RequestMapping("/usr/article/write")
	public String write() {
		return "usr/article/write";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(String title, String body, String session) {
		System.out.println(session); // 잘들어옴.
		//인터셉터에서 로그인 해야만 가능하게 설정할꺼니까 로그인했냐 안했냐도 필요없고, 
		
		if (Util.empty(title)) {
			return Util.jsHistoryBack("제목을 입력해주세요");
		}
		
		if (Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요");
		}
		
		
		// 여기서 칼럼 글로 들어온걸 숫자로 변형
		//칼럼분류설정(한국 도서 십진 분류법에 따라 구분) 철학-1 종교-2 사회과학-3 자연과학-4 기술과학-5 예술-6 언어-7 문학-8 역사-9'
		int colmnClSetup =-1;
		if(session.equals("philosophy")) {
			colmnClSetup = 1;
		}else if(session.equals("religion")) {
			colmnClSetup = 2;
		}else if(session.equals("socialScience")) {
			colmnClSetup = 3;
		}else if(session.equals("naturalScience")) {
			colmnClSetup = 4;
		}else if(session.equals("technologyScience")) {
			colmnClSetup = 5;
		}else if(session.equals("art")) {
			colmnClSetup = 6;
		}else if(session.equals("language")) {
			colmnClSetup = 7;
		}else if(session.equals("literature")) {
			colmnClSetup = 8;
		}else if(session.equals("history")) {
			colmnClSetup = 9;
		}else {
			return Util.jsHistoryBack("칼럼 분류을 입력해주세요");
		}
		
		
		articleService.writeArticle(rq.getLoginedMemberId(), title, body, colmnClSetup);
		
		int id = articleService.getLastInsertId();
		
		return Util.jsReplace(Util.f("%d번 게시물을 생성했습니다", id),  Util.f("detail?id=%d", id));
	}
	
	@RequestMapping("/usr/article/list")
	public String list(Model model, @RequestParam(defaultValue = "1") int type,@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "all") String searchKeywordType, @RequestParam(defaultValue = "") String searchKeyword) {
		if (page <= 0) {
			return rq.jsReturnOnView("페이지번호가 올바르지 않습니다");
		}
		
		// 전역 뺄꺼
		List<Article> articles= null;
		int articlesCnt =0;
		int pagesCnt = 0;
		String typeName = null;
		
		if(type ==2) { // 구독
			typeName = "구독한 작가 칼럼";
			// 총 페이지 개수
			articlesCnt = articleService.getArticlesCnt(rq.getLoginedMemberId(), searchKeywordType, searchKeyword, type);
			
			//페이징 관련 변수
			int itemsInAPage  = 10;
			int limitStart  = (page-1) * itemsInAPage;
			pagesCnt = (int) Math.ceil((double) articlesCnt / itemsInAPage); // 화면에 보여질 페이지의 마지막 페이지 번호
			
			// 위의 것들을 이용해서 아래의 이걸 수정해야함. => Db에 limit 만큼만 스캔때리라고 하기 위해
			//기존에 공부할때 이걸 만든 이유 => db에서 list 가꼬 올라고
			articles = articleService.getArticles(limitStart, itemsInAPage, searchKeywordType, searchKeyword, rq.getLoginedMemberId(), type); // lastPage는 jsp에서 그려낼때 필요한 애, 데이터 베이스에서 limit으로 조회할껀 추가된 두개니까(정확하게는 연산을 해낸 결과가 필요).

		
		}else if(type ==3) {// 좋아요
			typeName = "좋아요 한 칼럼";
			// 총 페이지 개수
			articlesCnt = articleService.getArticlesCnt(rq.getLoginedMemberId(), searchKeywordType, searchKeyword, type);
			
			//페이징 관련 변수
			int itemsInAPage  = 10;
			int limitStart  = (page-1) * itemsInAPage;
			pagesCnt = (int) Math.ceil((double) articlesCnt / itemsInAPage); // 화면에 보여질 페이지의 마지막 페이지 번호
			
			// 위의 것들을 이용해서 아래의 이걸 수정해야함. => Db에 limit 만큼만 스캔때리라고 하기 위해
			//기존에 공부할때 이걸 만든 이유 => db에서 list 가꼬 올라고
			articles = articleService.getArticles(limitStart, itemsInAPage, searchKeywordType, searchKeyword, rq.getLoginedMemberId(), type); // lastPage는 jsp에서 그려낼때 필요한 애, 데이터 베이스에서 limit으로 조회할껀 추가된 두개니까(정확하게는 연산을 해낸 결과가 필요).
		}else {
			typeName = "전체 칼럼";
			// 총 페이지 개수
			articlesCnt = articleService.getArticlesCnt(rq.getLoginedMemberId(), searchKeywordType, searchKeyword, type);
			
			//페이징 관련 변수
			int itemsInAPage  = 10;
			int limitStart  = (page-1) * itemsInAPage;
			pagesCnt = (int) Math.ceil((double) articlesCnt / itemsInAPage); // 화면에 보여질 페이지의 마지막 페이지 번호
			
			// 위의 것들을 이용해서 아래의 이걸 수정해야함. => Db에 limit 만큼만 스캔때리라고 하기 위해
			//기존에 공부할때 이걸 만든 이유 => db에서 list 가꼬 올라고
			articles = articleService.getArticles(limitStart, itemsInAPage, searchKeywordType, searchKeyword, rq.getLoginedMemberId(), type); // lastPage는 jsp에서 그려낼때 필요한 애, 데이터 베이스에서 limit으로 조회할껀 추가된 두개니까(정확하게는 연산을 해낸 결과가 필요).
		}
		
		model.addAttribute("type", type);
		model.addAttribute("typeName", typeName);
		model.addAttribute("searchKeywordType", searchKeywordType);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("articles", articles);
		model.addAttribute("articlesCnt", articlesCnt);
		model.addAttribute("pagesCnt", pagesCnt); 
		model.addAttribute("page", page); 
		
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String detail(HttpServletRequest req, HttpServletResponse resp, Model model, int id) {
		
		//선택한 칼럼의 내용을 가져옴.
		Article article = articleService.forPrintArticle(id);
		
		//선택한 칼럼의 댓글을 가져옴.
		List<Reply> replies = replyService.getReplies(id);
		
		
		// 댓글 갯수
		Reply replyCount = replyService.getReplycount(id);
		
		// 좋아요 갯수
		RecommendPoint recommendPoint = recommendService.countRecommendPont(id);
		
		//로그인 안했을때
		if(rq.getLoginedMemberId()==0) {
			model.addAttribute("article", article);
			model.addAttribute("replies", replies);
			model.addAttribute("replyCount", replyCount);
			model.addAttribute("recommendPoint", recommendPoint);
			
			
			return "usr/article/detail";
		}
		//로그인했을때
		
		//로그인한 놈의 정보를 가져옴. -> 닉네임에 이름 떠야해.
		Member member = memberService.getMemberById(rq.getLoginedMemberId());
		
		// 이 사람이 구독을 했는지 안했는지 체크 -> 했으면 뭔가를 던져서 화면에 누름이 addClass 되야해
		RecommendPoint checkRecommend = recommendService.getCheckRecommend(rq.getLoginedMemberId(), article.getColmnWrter());
		// 구독했으면 check가 1 아니면 0으로 세팅해서 넘기자
		int subscribeCheck = -1;
		if(checkRecommend==null) {
			subscribeCheck=0;
		}else {
			subscribeCheck=1;
		}
		
		//이 사람이 이 글에 좋아요를 눌렀었는지 체크 -> 위의 구독과 동일한 로직
		RecommendPoint checklike = recommendService.getChecklike(rq.getLoginedMemberId(), id);
		
		// 구독했으면 check가 1 아니면 0으로 세팅해서 넘기자
		int likeCheck = -1;
		if(checklike==null) {
			likeCheck=0;
		}else {
			likeCheck=1;
		}
		//그래야 닉네임으로 보여주니까. -> 댓글 적어놓은걸.
		model.addAttribute("nickname", member.getNickname());
		model.addAttribute("article", article);
		model.addAttribute("replies", replies);
		model.addAttribute("replyCount", replyCount);
		model.addAttribute("recommendPoint", recommendPoint);
		model.addAttribute("subscribeCheck", subscribeCheck);
		model.addAttribute("likeCheck", likeCheck);
		
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