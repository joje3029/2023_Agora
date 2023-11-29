package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.BoardService;
import com.example.demo.service.DiscussionService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.Board;
import com.example.demo.vo.Rq;

@Controller
public class UsrDiscussionController {
	
	private DiscussionService discussionService;
	private BoardService boardService;
	private Rq rq;
	
	UsrDiscussionController(DiscussionService discussionService, BoardService boardService, Rq rq) {
		this.discussionService = discussionService;
		this.boardService = boardService;
		this.rq =rq; 
	}
	//토론방 리스트
	@RequestMapping("/usr/discussion/list")
	public String list(Model model, int boardId, @RequestParam(defaultValue = "1") int page, String searchKeywordType, String searchKeyword) {//파라미터를 받아야지. 파라미터를 받는 이유. 그래야 어디서부터 보여주는지 list가 알지. 그래서 요청으로 받아서 넘기는거야.
		if (page <= 0) { 
			return rq.jsReturnOnView("페이지번호가 올바르지 않습니다");
		}
		
		Board board = boardService.getBoardById(boardId);
		
		int articlesCnt = discussionService.getArticlesCnt(boardId);
		
		int itemsInAPage  = 10;
		
		int pagesCnt  = (int)Math.ceil((int)articlesCnt/itemsInAPage); // 이래야 소숫점까지 나오니까 올림을 할지 말지 파악 가능.
		
		int limitStart  = (page-1) * itemsInAPage;

		List<Article> articles = discussionService.getArticles(boardId, limitStart, itemsInAPage); // lastPage는 jsp에서 그려낼때 필요한 애, 데이터 베이스에서 limit으로 조회할껀 추가된 두개니까(정확하게는 연산을 해낸 결과가 필요).
		
		model.addAttribute("articles", articles);
		model.addAttribute("board", board);
		model.addAttribute("articlesCnt", articlesCnt);
		
		model.addAttribute("pagesCnt", pagesCnt);
		model.addAttribute("page", page);
		
		
		return "usr/discussion/list";
	}
	
	//토론방 생성
	@RequestMapping("/usr/discussion/createroom")
	public String createroom() {
		return "usr/discussion/createroom";
	}
	
	//채팅방
	@RequestMapping("/usr/discussion/chat")
	public String chat() {
		return "usr/discussion/chat";
	}
	
	//화상방
	@RequestMapping("/usr/discussion/cam")
	public String cam() {
		return "usr/discussion/cam";
	}
}
	
