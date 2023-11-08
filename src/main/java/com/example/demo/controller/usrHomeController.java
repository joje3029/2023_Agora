package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	int a;
	
	UsrHomeController(){
		this.a=0;
	}
	
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "뱁새씨";
	}
	
	@RequestMapping("/usr/home/main2")
	@ResponseBody
	public int showMain2() {
		return a++;
	}
}
