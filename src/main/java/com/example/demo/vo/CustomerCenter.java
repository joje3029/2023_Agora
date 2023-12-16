package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCenter {
	private int id; // 회원ID
	private String cstmrTitle; //고객센터 제목
	private String uwerId; // 회원의 loginId
	private String cstmrBody; //고객센터 내용
	private String ricfldSndngYn; //응답 유무
	
	
}