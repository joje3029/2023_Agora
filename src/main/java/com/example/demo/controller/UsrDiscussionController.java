package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.DiscussionService;
import com.example.demo.util.Util;
import com.example.demo.vo.DiscussionRoom;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrDiscussionController {
	
	private DiscussionService discussionService;
	private Rq rq;
	
	UsrDiscussionController(DiscussionService discussionService, Rq rq) {
		this.discussionService = discussionService;
		this.rq =rq; 
	}
	//토론방 리스트
	@RequestMapping("/usr/discussion/list")
	public String list(Model model, @RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "all") String searchKeywordType, @RequestParam(defaultValue = "") String searchKeyword) {
		if (page <= 0) {
			return rq.jsReturnOnView("페이지번호가 올바르지 않습니다");
		}
		
		// 총 페이지 개수 -> 총 토론방 개수
		int discussionCnt = discussionService.getDiscussionCnt(searchKeywordType, searchKeyword);
		
		//페이징 관련 변수
		int itemsInAPage  = 10;
		int limitStart  = (page-1) * itemsInAPage;
		int pagesCnt = (int) Math.ceil((double) discussionCnt / itemsInAPage); // 화면에 보여질 페이지의 마지막 페이지 번호
		
		// 위의 것들을 이용해서 아래의 이걸 수정해야함. => Db에 limit 만큼만 스캔때리라고 하기 위해
		//기존에 공부할때 이걸 만든 이유 => db에서 list 가꼬 올라고
		List<DiscussionRoom> disussionRooms = discussionService.getdisussionRooms(limitStart, itemsInAPage, searchKeywordType, searchKeyword); // lastPage는 jsp에서 그려낼때 필요한 애, 데이터 베이스에서 limit으로 조회할껀 추가된 두개니까(정확하게는 연산을 해낸 결과가 필요).
		
		model.addAttribute("searchKeywordType", searchKeywordType);
		model.addAttribute("disussionRooms", disussionRooms);
		model.addAttribute("articlesCnt", discussionCnt);
		model.addAttribute("pagesCnt", pagesCnt);
		model.addAttribute("page", page);
		
		
		return "usr/discussion/list";
	}
	
	//토론방 생성 폼으로 연결
	@RequestMapping("/usr/discussion/createroom")
	public String showCreateroom() {
		
		return "usr/discussion/createroom";
	}
	// 토론방 생성하는 아
	@RequestMapping("/usr/discussion/docreateroom")
	@ResponseBody
	public String doCreateroom(HttpServletRequest req, String roomName, int typeId ) {
		//인터셉터에서 로그인 해야만 가능하게 설정할꺼니까 로그인했냐 안했냐도 필요없고, 
		
		if (Util.empty(roomName)) {
			return Util.jsHistoryBack("토론방 이름을 입력해주세요");
		}
		
		//여기까지 되고나면 typeId 숫자가 뭐냐에 따라 만들어야함
		//1차 목표는 그거에 따라서 지금 만들어져 있는 jsp로 연결하는걸로하자.
		// typeId = 1 : 채팅 , typeId = 2 : 화상  
		
		
		return null;
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
	
