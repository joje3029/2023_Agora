package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.AdminService;
import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.CustomerCenter;
import com.example.demo.vo.Member;
import com.example.demo.vo.NewMember;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;
import com.example.demo.vo.WithdrowReason;

@Controller
public class AdminController {

	private AdminService adminService;
	private MemberService memberService;
	private Rq rq;

	AdminController(AdminService adminService, MemberService memberService, Rq rq) {
		this.adminService = adminService;
		this.memberService = memberService;
		this.rq = rq; // rq 의존성 주입을 하기 위해.
	}

//	관리자 메인페이지
	@RequestMapping("/admin/main")
	public String main() {
		return "admin/main";
	}

//	관리자 로그인
	@RequestMapping("/admin/login")
	public String login() {
		return "admin/login";
	}

// 관리자 로그인 DB랑 확인하고 메인페이지로 넘기기(관리자 페이지의 모든 서비스는 관리자 로그인에서 고르인이 되야함.-> 다 되면 intercepter에 다른경로 다 추가할꺼야!!)	
// 여기는 Db랑 비교할때 단순 있다 없다외에도 관리자 권한도 따져야함. 
	@RequestMapping("admin/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {

		if (rq.getLoginedMemberId() != 0) {
			return Util.jsHistoryBack("로그아웃 후 이용해주세요");
		}

		if (Util.empty(loginId)) {
			return Util.jsHistoryBack("아이디를 입력해주세요");
		}
		if (Util.empty(loginPw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요");
		}

		// 여기서는
		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return Util.jsHistoryBack(Util.f("%s은(는) 존재하지 않는 아이디입니다", loginId));
		}

		// 여기서 권한이 일반인지 아닌지 확인, 여기까지 왔다는 아이디가 존재는 하신다는 이야기니까.
		// 권한이 없는데 비번이 맞는지 아닌지 먼저 확인할껀 아니니까.
		if (member.getMberAuthor() != 2) {
			return Util.jsHistoryBack(Util.f("%s은(는) 관리자 권한이 없는 아이디입니다", loginId));
		}

		if (member.getPasswd().equals(Util.sha256(loginPw)) == false) {
			return Util.jsHistoryBack("비밀번호를 확인해주세요");
		}

		rq.login(member);

		return Util.jsReplace(Util.f("%s 관리자님 환영합니다.", loginId), "main");
	}

	// 관리자 로그아웃
	@RequestMapping("/admin/dologout")
	@ResponseBody
	public String dologout() {

		if (rq.getLoginedMemberId() == 0) {
			return Util.jsHistoryBack("로그인 후 이용해주세요");
		}

		rq.logout();

		return Util.jsReplace(Util.f("정상적으로 로그아웃 되었습니다"), "login");

	}

//	고객상담 리스트로 가는 로직
	@RequestMapping("/admin/centerList")
	public String showCenterList(Model model, @RequestParam(defaultValue = "1") int page) {

		if (page <= 0) {
			return rq.jsReturnOnView("페이지번호가 올바르지 않습니다");
		}

		// 고객센터 글 전체 개수 가져오기
		int CustomerlistCnt = adminService.getCustomerlistCnt();

		// 페이징 관련 변수
		int itemsInAPage = 10;
		int limitStart = (page - 1) * itemsInAPage;
		int pagesCnt = (int) Math.ceil((double) CustomerlistCnt / itemsInAPage);// 화면에 보여질 페이지의 마지막 페이지 번호

		List<CustomerCenter> customerCenters = adminService.getCustomerCenterList(itemsInAPage, limitStart);

		model.addAttribute("customerCenters", customerCenters);
		model.addAttribute("page", page);
		model.addAttribute("pagesCnt", pagesCnt);
		model.addAttribute("CustomerlistCnt", CustomerlistCnt);

		return "admin/centerList";
	}

//	고객상담 답변 양식
	@RequestMapping("/admin/customercenter")
	public String customercenter(Model model, int id) {
		// Db가서 해당 id의 글 넘겨줘야함. 그래야 디테일하게 보지

		CustomerCenter customerCenter = adminService.getdetailCustomer(id);

		System.out.println(customerCenter);

		model.addAttribute(customerCenter);

		return "admin/customercenter";
	}

//	고객상담 답변 이메일로 보내고 고객상담 리스트로 가야함.
	@RequestMapping("/admin/sendCustomerAnswer")
	@ResponseBody
	public String sendCustomerAnswer(int id, String body) {

		// 유저 이메일로 직접 가야함. 즉, 저쪽 문서 번호 받아야함.
		CustomerCenter customerCenter = adminService.getdetailCustomer(id);

		// 그래서 유저 이메일을 db에서 찾아서
		String customerEmail = customerCenter.getEmail();

		// 이메일을 보내는 로직
		ResultData notifyTempLoginPwByEmailRd = memberService.notifyTempUserAnswerByEmail(customerEmail, body);

		// 보내고 나면 보내졌다는 확인과 함께 고객센터 리스트로 가야함.
		// 그리고 그전에 보냈다고로 바꿔야함-> 일단 이건 보내는거 확인되고나서 하자

		String failmsg = "메일 발송 실패하였습니다.";

		if (notifyTempLoginPwByEmailRd.getMsg().equals(failmsg)) {
			// 메일 발송 실패시
			adminService.modifyRicFail(id);
		}
		// 메일 발송 성공시
		adminService.modifyRicSucess(id);

		return Util.jsReplace(notifyTempLoginPwByEmailRd.getMsg(), "centerList");

	}

//	회원조회
	@RequestMapping("/admin/userlist")
	public String userlist(Model model, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "") String startDate, @RequestParam(defaultValue = "") String endDate,
			@RequestParam(defaultValue = "") String searchId, @RequestParam(defaultValue = "") String searchNickname) {

		if (page <= 0) {
			return rq.jsReturnOnView("페이지번호가 올바르지 않습니다");
		}

//		// 멤버 총 인원 페이지 개수
		int membersCnt = memberService.getMembersCnt();
//		//페이징 관련 변수
		int itemsInAPage = 10;
		int limitStart = (page - 1) * itemsInAPage;
		int pagesCnt = (int) Math.ceil((double) membersCnt / itemsInAPage);// 화면에 보여질 페이지의 마지막 페이지 번호

		List<Member> members = memberService.getMembers(itemsInAPage, limitStart, startDate, endDate, searchId,
				searchNickname);

		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("searchId", searchId);
		model.addAttribute("searchNickname", searchNickname);
		model.addAttribute("membersCnt", membersCnt);
		model.addAttribute("pagesCnt", pagesCnt);
		model.addAttribute("page", page);

		model.addAttribute("members", members);

		return "admin/userlist";
	}

//	관리자가 회원 강제탈퇴시키는거.
	@RequestMapping("/admin/doDeleteMembers")
	@ResponseBody
	public String doDeleteMembers(@RequestParam(name = "ids", required = false) List<String> ids) {

		if (ids == null) {
			return Util.jsHistoryBack("선택한 회원이 없습니다");
		}

		for (String idStr : ids) {
			if (idStr.equals("1")) {
				return Util.jsHistoryBack("관리자 계정은 삭제할 수 없습니다");
			}
		}

		memberService.modifyMemberssecnEnnc(ids);

		return Util.jsReplace("선택한 회원이 강제탈퇴처리 되었습니다", "userlist");
	}

//	탈퇴회원수 마케팅데이터가는 로직
	@RequestMapping("/admin/marketingwithDrow")
	public String showMarketingwithDrow(Model model) {

		// 3번씨는 탈퇴 회원 수와 이유 -> 즉 가져와야하는 정보 테이블 WITHDROW_REASON
		List<WithdrowReason> withdrowReason = adminService.getWithdrowReason();

		// 새로운 리스트 생성
		List<String> withdrawDateList = new ArrayList<>();
		List<String> otherSiteCountList = new ArrayList<>();
		List<String> notUseCountList = new ArrayList<>();
		List<String> contentsDiscontentCountList = new ArrayList<>();
		List<String> extReaseonCountList = new ArrayList<>();

		// withdrowReason 리스트 순회
		for (WithdrowReason reason : withdrowReason) {
			// 각각의 속성을 새로운 리스트에 추가

			if (reason.getReason().equals("otherSite")) { // 다른 사이트 이용
				withdrawDateList.add(reason.getWithdrawalMonth());
				otherSiteCountList.add(reason.getCount());

			} else if (reason.getReason().equals("notUse")) { // 사용 안함
				notUseCountList.add(reason.getCount());

			} else if (reason.getReason().equals("contentsDiscontent")) { // 컨텐츠 부족
				contentsDiscontentCountList.add(reason.getCount());

			} else { // 기타 이유
				extReaseonCountList.add(reason.getCount());

			}
		}

		Map<String, List<String>> dataMap = new HashMap<>();
		dataMap.put("WithdrawDateList", withdrawDateList);
		dataMap.put("OtherSiteCountList", otherSiteCountList);
		dataMap.put("NotUseCountList", notUseCountList);
		dataMap.put("ContentsDiscontentCountList", contentsDiscontentCountList);
		dataMap.put("ExtReaseonCountList", extReaseonCountList);

		model.addAttribute("dataMap", dataMap);

		return "admin/marketingwithDrow";

	}

//	신규가입자수 마케팅데이터가는 로직
	@RequestMapping("/admin/marketingNew")
	public String showmarketing(Model model) {

		// 신규가입자수 -> 즉 가져와야하는 정보 테이블 WITHDROW_REASON
		List<NewMember> newMembers=adminService.getUserJoinDate();
		
		System.out.println(newMembers);
		
		// 새로운 리스트 생성
		List<Integer> monthList = new ArrayList<>();
		List<Integer> countList = new ArrayList<>();

		// members 리스트 순회
		for (NewMember member : newMembers) {
			monthList.add(member.getMonth());
			countList.add(member.getCount());
		}

		Map<String, List<Integer>> dataMap = new HashMap<>();
		dataMap.put("monthList", monthList);
		dataMap.put("countList", countList);

		model.addAttribute("dataMap", dataMap);

		return "admin/marketingNew";

	}
	
//	마케팅데이터가는 로직-차쌤 조언 도전!
	@RequestMapping("/admin/marketing")
	public String showMarketing(@RequestParam(defaultValue = "1") int type,Model model) {

		if (type <= 0) {
			return rq.jsReturnOnView("분류가 올바르지 않습니다");
		}
		
		//if로 type 파라미터로 들어온거에 따라 나뉘어져야함. 우선은 3번만
		
		if(type ==2) {
			
			//2번씨는 탈퇴 회원 수와 이유 -> 즉 가져와야하는 정보 테이블 WITHDROW_REASON
			List<WithdrowReason> withdrowReason=adminService.getWithdrowReason();
			
			
			// 새로운 리스트 생성
			List<String> withdrawDateList = new ArrayList<>();
			List<String> otherSiteCountList = new ArrayList<>();
			List<String> notUseCountList = new ArrayList<>();
			List<String> contentsDiscontentCountList = new ArrayList<>();
			List<String> extReaseonCountList = new ArrayList<>();
			
			
			// withdrowReason 리스트 순회
			for (WithdrowReason reason : withdrowReason) {
			    // 각각의 속성을 새로운 리스트에 추가
				
				if (reason.getReason().equals("otherSite")) { // 다른 사이트 이용
					withdrawDateList.add(reason.getWithdrawalMonth());
					otherSiteCountList.add(reason.getCount());

				}else if (reason.getReason().equals("notUse")) { // 사용 안함
					notUseCountList.add(reason.getCount());
					

				}else if (reason.getReason().equals("contentsDiscontent")) { //컨텐츠 부족
					contentsDiscontentCountList.add(reason.getCount());
					

				}else { // 기타 이유
					extReaseonCountList.add(reason.getCount());

				}
			}
			
			Map<String, Object> otherMap = new HashMap<>();
			otherMap.put("name", "다른사이트가 더 좋아서");
			otherMap.put("data", otherSiteCountList);
			
			Map<String, Object> notUseMap = new HashMap<>();
			notUseMap.put("name", "사용빈도가 낮아서");
			notUseMap.put("data", otherSiteCountList);
			
			Map<String, Object> contentsMap = new HashMap<>();
			contentsMap.put("name", "콘텐츠 불만");
			contentsMap.put("data", otherSiteCountList);
			
			Map<String, Object> extMap = new HashMap<>();
			extMap.put("name", "기타");
			extMap.put("data", otherSiteCountList);
			
			List<Object> objectList = new ArrayList<>(); // 리스트 초기화
			
			objectList.add(otherMap);
			objectList.add(notUseMap);
			objectList.add(contentsMap);
			objectList.add(extMap);
			
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("series", objectList);
			dataMap.put("categories", withdrawDateList);
			
			model.addAttribute("data", dataMap);
			
			
			
//			dataMap.put("WithdrawDateList", withdrawDateList);
//			dataMap.put("OtherSiteCountList", otherSiteCountList);
//			dataMap.put("NotUseCountList", notUseCountList);
//			dataMap.put("ContentsDiscontentCountList", contentsDiscontentCountList);
//			dataMap.put("ExtReaseonCountList", extReaseonCountList);
//			model.addAttribute("dataMap", dataMap);
			
			return "admin/marketing";
			
		}
		
		System.out.println(5);
		
		return "admin/marketing";

	}
	
	
}