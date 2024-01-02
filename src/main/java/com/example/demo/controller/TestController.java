package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.WebClientServiceImpl;

@Controller
public class TestController {
	private WebClientServiceImpl webClientServiceImpl;

	TestController(WebClientServiceImpl webClientServiceImpl) {
		this.webClientServiceImpl =webClientServiceImpl;
	}

	@RequestMapping("/usr/test/test")
	public String doTest(){
		System.out.println(1);
		webClientServiceImpl.get();
		System.out.println(2);
		return "usr/home/interduce";
	}

}