package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.CustomerService;
import com.example.demo.util.Util;
import com.example.demo.vo.Rq;

@Controller
public class UsrCustomerController {
	
	private CustomerService customerService;
	private Rq rq;
	
	UsrCustomerController(CustomerService customerService, Rq rq) {
		this.customerService = customerService;
		this.rq = rq;
	}

	
//	챗봇으로가는거
	@RequestMapping("/usr/customer/chatBot")
	public String showChatBot() {
		return "usr/customer/chatBot";
	}
	
//	고객센터로 가는거
	@RequestMapping("/usr/customer/customercenter")
	public String showcustomercenter() {
		return "usr/customer/customercenter";
	}
	
//	고객센터에서 내용 보내기
	@RequestMapping("/usr/customer/sendcustomercenter")
	@ResponseBody
	public String sendCustomercenter(String title, String body) {
		
		// 고객센터 테이블에 넣어야함. 넣을때 제목 주소도 넣고 지금 로그인한 놈 id도 넣어야함 그래야 관리자쪽에서 join으로 묶어서 처리 가능.
		customerService.insertReceipt(rq.getLoginedMemberId(), title, body);
		
		return Util.jsReplace("접수되었습니다.", "/usr/home/main" );
	}
}