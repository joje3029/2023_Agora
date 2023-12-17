package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCenter {
	private int id; // 고객센터Pk
	private int userUniqId; //회원ID
	private String cstmrTitle; //고객센터 제목
	private String cstmrBody; //고객센터 내용
	private int ricfldSndngYn; //응답 유무
	private String uwerId; // 회원의 loginId
	
	
	public String ricfldStausStr() {
		if (ricfldSndngYn == 0) {
			return "미응답함";
		}
		return "응답함";
	}
	
}