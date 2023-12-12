package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.DiscussionService;
import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.DiscussionRoom;
import com.example.demo.vo.Member;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrDiscussionController {
	private MemberService memberService;
	private DiscussionService discussionService;
	private Rq rq;
	
	UsrDiscussionController(DiscussionService discussionService, MemberService memberService, Rq rq) {
		this.discussionService = discussionService;
		this.memberService = memberService;
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
	public String doCreateroom(RedirectAttributes redirect, HttpServletRequest req, String roomName, String type ) {
		//인터셉터에서 로그인 해야만 가능하게 설정할꺼니까 로그인했냐 안했냐도 필요없고, 
		
		if (Util.empty(roomName)) {
			return Util.jsHistoryBack("토론방 이름을 입력해주세요");
		}
		
		//여기까지 되고나면 typeId 숫자가 뭐냐에 따라 만들어야함
		//1차 목표는 그거에 따라서 지금 만들어져 있는 jsp로 연결하는걸로하자.
		// typeId = 1 : 채팅 , typeId = 2 : 화상  
		
		//연결결과 잘감. 이제 갈때 정보 데꼬가게 하면 되겠네. model로
		
		//우선 채팅이 성공하면 화상도 비슷하게 하면되니까 여기서부터 생각하면 되겠네
		
		// 병호님 채팅 목록부터 한거 참고하기 -> 웹소켓님(병호님이 다대다로 해서 가능함.)
		
		if(type.equals("1")) { // 채팅이래
			// 방 만들기 : 넘길 내용. 방제목 만든놈, 방 타입
			discussionService.createDiscussionRoom(rq.getLoginedMemberId(), roomName, type);
			
			int discussionId = discussionService.getLastInsertId();
			
			System.out.println("discussionId1 + "+discussionId); // 여는 오케
			
			//리다이렉트 할때 만들어진 번호 받게 했어
			redirect.addAttribute("discussionId", discussionId);
			
			System.out.println("discussionId4 + "+discussionId);
			
			return "redirect:/usr/discussion/chat";
		}
		
		if(type.equals("2")) { // 화상이래
			return "usr/discussion/cam";
		}
		
		return null; // 없으면 뭐라하니까. 실제로는 1이냐 2만 있으면 되지만...
	}
	
	
	//채팅방
	@RequestMapping("/usr/discussion/chat")
	public String chat(Model model, @RequestParam("discussionId") int discussionId) {
		
		System.out.println("discussionId2 + "+discussionId); // 낫오케
		
		// 내가 만들든 선택하든 한놈 채팅방으로 가기 위해
		DiscussionRoom discussionRoom = discussionService.getDiscussionRoomById(discussionId);
		
		if (discussionRoom.getId() == 0) {
			return rq.jsReturnOnView("존재하지 않는 토론방 입니다");
		}
		
		//로그인한 놈 정보 같이 넘기기 위해 DB 가기
		Member member = memberService.getMemberById(rq.getLoginedMemberId());
		
		model.addAttribute("discussionRoom", discussionRoom);
	    model.addAttribute("member", member);
	    
	    System.out.println(discussionRoom);
		
		return "usr/discussion/chat";
	}
	
	//화상방
	@RequestMapping("/usr/discussion/cam")
	public String cam() {
		return "usr/discussion/cam";
	}
}
	
