package com.example.demo.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.example.demo.service.WebClientServiceImpl;

@Controller
@EnableScheduling
public class TestController {
	private WebClientServiceImpl webClientServiceImpl;

	TestController(WebClientServiceImpl webClientServiceImpl) {
		this.webClientServiceImpl =webClientServiceImpl;
	}
	//여기서 스케쥴러가 되게 
	
	@Scheduled(cron = "0 0 15 * * *") // 매일 오후 3시 00분
	public String doNewBookAladin(){
		System.out.println(1);
		webClientServiceImpl.get();
		System.out.println(2);
		return "usr/home/interduce";
	}

}