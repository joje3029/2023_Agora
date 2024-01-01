package com.example.demo.vo;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class Scheduler {
	
	//매달 1일 알라딘에 베스트셀러와 신간 요청
	@Scheduled(cron = "0 0 0 1 * *")
	public void updateBookScheduler() {
		log.info("베스트셀러 요청");
		//1. 베스트 셀러 요청 보내기
		//2. 요청한거를 Service에 보내서 Service가 DAo에게 말해서 책 테이블에 추가하기
		
		log.info("신간 책 요청");
		// 신간 책 요청 보내기
		log.info("책 업데이트 끝");
	}

}
