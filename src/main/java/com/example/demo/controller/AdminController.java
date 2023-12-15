package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.AdminService;
import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
	
	private AdminService adminService;
	private MemberService memberService;
	private Rq rq;
	
	AdminController(AdminService adminService,MemberService memberService, Rq rq) {
		this.adminService = adminService;
		this.memberService = memberService;
		this.rq =rq; // rq 의존성 주입을 하기 위해.
	}
	
//	관리자 메인페이지
	@RequestMapping("/admin/main")
	public String showAdMain() {
		return "admin/main";
	}
	
//	관리자쪽 로그인
	@RequestMapping("/admin/login")
	public String adlogin() {
		
		return "admin/login";
	}
	
//	고객상담 리스트로 가는 로직
	@RequestMapping("/admin/centerList")
	public String showCenterList() {
		return "admin/centerList";
}
	
//	고객상담 답변
	@RequestMapping("/admin/customercenter")
	public String customercenter() {
		
		return "admin/customercenter";
	}
	
//	회원조회
	@RequestMapping("/admin/userlist")
	public String userlist(Model model) {

//		// 멤버 총 인원 페이지 개수
//		int membersCnt = memberService.getMembersCnt();
//		//페이징 관련 병수
//		int itemsInAPage = 10;
//		int limitStart = (page - 1) * itemsInAPage;
//		int pagesCnt = (int) Math.ceil((double) membersCnt / itemsInAPage);// 화면에 보여질 페이지의 마지막 페이지 번호

		List<Member> members = memberService.getMembers();

//		Member member = memberService.getMemberById(rq.getLoginedMemberId());
		
//		model.addAttribute("member", member);
		model.addAttribute("members", members);
		
		return "admin/userlist";
	}
	
	
//	마케팅데이터가는 로직
	@RequestMapping("/admin/marketing")
	public String showmarketing() {
		
		return "admin/marketing";
	}
}