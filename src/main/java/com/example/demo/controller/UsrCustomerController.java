package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsrCustomerController {
//	챗봇으로가는거
	@RequestMapping("/usr/customer/chatBot")
	public String showMain() {
		return "usr/customer/chatBot";
	}
	
}