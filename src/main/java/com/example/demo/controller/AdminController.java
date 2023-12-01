package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.AdminService;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
	
	private AdminService adminService;
	private Rq rq;
	
	AdminController(AdminService adminService, Rq rq) {
		this.adminService = adminService;
		this.rq =rq; // rq 의존성 주입을 하기 위해.
	}
	
//	관리자 메인페이지
	@RequestMapping("/usr/admin/main")
	public String showAdMain() {
		return "/usr/admin/main";
	}
	
//	관리자쪽 로그인
	@RequestMapping("/usr/admin/login")
	public String adlogin(HttpServletRequest req) {
		
		return "/usr/admin/login";
	}
	
//	고객상담 리스트로 가는 로직
	@RequestMapping("/usr/admin/centerList")
	public String showCenterList(HttpServletRequest req) {
		return "/usr/admin/centerList";
}
	
//	고객상담 답변
	@RequestMapping("/usr/admin/customercenter")
	public String customercenter(HttpServletRequest req) {
		
		return "/usr/admin/customercenter";
	}
	
//	회원조회
	@RequestMapping("/usr/admin/userlist")
	public String check(HttpServletRequest req) {
		
		return "/usr/admin/userlist";
	}
	
//	마케팅데이터가는 로직
	@RequestMapping("/usr/admin/marketing")
	public String showmarketing(HttpServletRequest req) {
		
		return "/usr/admin/marketing";
	}
}