package com.example.demo.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.example.demo.service.BookApiService;

@Controller
public class BookApiController {

    private BookApiService bookApiService;

    public BookApiController(BookApiService bookApiService) {
        this.bookApiService = bookApiService;
    }

    // 매달 1일 00시에 실행되는 스케줄링 메서드
    @Scheduled(cron = "0 0 0 1 * *")
    public void fetchDataFromAladin() {
        // Aladin API 호출 및 데이터 수신
        String aladinApiResponse = bookApiService.fetchDataFromAladin();

        // 수신된 데이터를 DB에 저장
        bookApiService.saveDataToDatabase(aladinApiResponse);
    }
}
