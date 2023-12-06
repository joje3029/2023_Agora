package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	//삭제 대상
//	private String regDate; //삭제 대상
//	private String updateDate; //삭제 대상
//	private String loginId; //삭제 대상
//	private String loginPw; //삭제 대상
//	private int authLevel; //삭제 대상
//	private String cellphoneNum; //삭제 대상
//	private int delStatus; //삭제 대상
//	private String delDate; //삭제 대상
	
	//여서부터 내꺼. 위는 sts가 뭐라하는게 싫어서 일단 내비둔거.
	private int id; // 회원ID
	private String uwerId; // 아이디
	private String passwd; //비밀번호
	private String name; //이름
	private String nickname; //닉네임
	private String email; //이메일
	private String telno; //전화번호
	private String adres; //주소
	private int secsnEnnc; //탈퇴 유/무
	private String joinDate; //가입일
	private int mberAuthor; //회원권한
	private String secsnReqstdt; //탈퇴신청날짜
	
	
}