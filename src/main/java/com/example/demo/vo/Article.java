package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	private int id; //칼럼 id
	private String title; // 칼럼 제목
	private String writngTime; //칼럼 작성일,시간
	private String body; // 칼럼 내용
	private int  colmnClSetup; // 칼럼분류설정(한국 도서 십진 분류법에 따라 구분)
	private String  colmnModifiedTime; // 칼럼 수정시간
	private String  colmnDeleteTime; // 칼럼 삭제시간
	private int  colmnDeleteEnnc; // 칼럼 삭제 유무
	private String colmnWrter; //작성자 번호
	private String nickname; //작성자 닉네임
	
	//textarea와 토스트ui의 차이때문에 있어야함.
	public String getForPrintBody() {
		return this.body.replaceAll("\n", "<br />");
	}
}