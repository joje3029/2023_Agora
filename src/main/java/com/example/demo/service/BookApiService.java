package com.example.demo.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.BookApiDao;

@Service
public class BookApiService {

	private BookApiDao bookApiDao;
	private RestTemplate restTemplate;

	public BookApiService(BookApiDao bookApiDao, RestTemplate restTemplate) {
		this.bookApiDao = bookApiDao;
		this.restTemplate = restTemplate;
	}

	public String fetchDataFromAladin() {
		
		//신간 리스트 주소
		String newBookUrl = "http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbphandom03111555001&QueryType=ItemNewAll&SearchTarget=Book&output=js&Version=20131101";
		
		//베스트셀러 리스트 주소
		String BestsellerBookUrl = "http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbphandom03111555001&QueryType=Bestseller&SearchTarget=Book&output=js&Version=20131101";
		
		//json으로 받을꺼니까. 
		HashMap<String, Object> newBook = 
		
				
		// 여기서 DB에 넘길때 파라미터 정리 잘해서 넘겨야함 
				
		bookApiDao.insert();
		
		return null;
	}

	

}