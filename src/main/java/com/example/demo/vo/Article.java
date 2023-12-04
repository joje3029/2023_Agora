package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	private int colmn_uniqu_id; //칼럼 id
	private String title; // 칼럼 제목
	private String writng_time; //칼럼 작성일,시간
	private String body; // 칼럼 내용
	private int  colmn_cl_setup; // 칼럼분류설정(한국 도서 십진 분류법에 따라 구분)
	private String  colmn_delete_time; // 칼럼 삭제시간
	private int  colmn_delete_ennc; // 칼럼 삭제 유무
	private String colmn_wrter; //작성자 번호
	private String ncnm; //작성자 닉네임
}