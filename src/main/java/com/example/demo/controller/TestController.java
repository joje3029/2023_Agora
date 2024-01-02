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
	
	@Scheduled(cron = "0 00 15 * * *") // 매일 오후 3시 00분
	public void doNewBookAladin(){
		webClientServiceImpl.getNewBook();
	}
	
	@Scheduled(cron = "0 00 15 1 * *") // 매달 1일 오후 3시 00분
	public void doBestBook(){
		webClientServiceImpl.getBestBook();
	}

}