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
		this.rq =rq; // rq 의존성 주입을 하기 위해.
	}
	
	@RequestMapping("/usr/discussion/list")
	public String list(Model model, int boardId, @RequestParam(defaultValue = "1") int page, String searchKeywordType, String searchKeyword) {//파라미터를 받아야지. 파라미터를 받는 이유. 그래야 어디서부터 보여주는지 list가 알지. 그래서 요청으로 받아서 넘기는거야.
		//@RequestParam(defaultValue = "1") int page 파라미터가 없으면 기본값을 1로 할께라고 하는 것
		if (page <= 0) { // 페이지는 1부터 시작 => 이 조건식은 0/음수를 파라미터로 요청한 것. 즉 존재하지 않는걸 요청한 것.
			return rq.jsReturnOnView("페이지번호가 올바르지 않습니다");
		}
		
		
		Board board = boardService.getBoardById(boardId);
		
		int articlesCnt = discussionService.getArticlesCnt(boardId);
		
//		페이지내이션 개발에 필요한 4가지 값
//		- 총 페이지 개수 : articlesCnt
//		- 화면에 보여질 페이지 그룹 : 10, pageGroup
//		- 화면에 보여질 페이지의 첫번째 페이지 번호 : 1, 
//		- 화면에 보여질 페이지의 마지막 페이지 번호 : math.ceil(board/10)
		
		int itemsInAPage  = 10;
		
		int pagesCnt  = (int)Math.ceil((int)articlesCnt/itemsInAPage); // 이래야 소숫점까지 나오니까 올림을 할지 말지 파악 가능.
		
		// 몇번 인덱스부터 시작할꺼냐 체크할 부분. 우리가 10씩 올라가니까 그에 대한 처리를 함.
		
		int limitStart  = (page-1) * itemsInAPage;

		// 위의 것들을 이용해서 아래의 이걸 수정해야함. => Db에 limit 만큼만 스캔때리라고 하기 위해
		List<Article> articles = discussionService.getArticles(boardId, limitStart, itemsInAPage); // lastPage는 jsp에서 그려낼때 필요한 애, 데이터 베이스에서 limit으로 조회할껀 추가된 두개니까(정확하게는 연산을 해낸 결과가 필요).
		
		model.addAttribute("articles", articles);
		model.addAttribute("board", board);
		model.addAttribute("articlesCnt", articlesCnt);
		
		model.addAttribute("pagesCnt", pagesCnt);
		model.addAttribute("page", page);
		
		
		return "usr/discussion/list";
	}
	
	@RequestMapping("/usr/discussion/createroom")
	public String createroom() {
		return "usr/discussion/createroom";
	}
}
	
