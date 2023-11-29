package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsrHomeController {
//	메인
	@RequestMapping("/usr/home/main")
	public String showMain() {
		return "usr/home/main";
	}
	
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}
	
//	사이트소개
	@RequestMapping("/usr/home/interduce")
	public String showInterduce() {
		return "usr/home/interduce";
	}
	
}