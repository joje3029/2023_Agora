package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private int id; // 회원ID // 카카오 id 때문에 형을 일단 -> long으로 바꿈.
	private String uwerId; // 아이디
	private String passwd; //비밀번호
	private String name; //이름
	private String nickname; //닉네임
	private String email; //이메일
	private String telno; //전화번호
	private String postNum; //우편번호
	private String adress; //주소
	private String detailAdress; //상세주소
	private String extAdress; //주소의 참고사항
	private String adres; //주소
	private int secsnEnnc; //탈퇴 유/무
	private String joinDate; //가입일
	private int mberAuthor; //회원권한
	private String secsnReqstdt; //탈퇴신청날짜
	

	public String delStatusStr() {
		if (secsnEnnc == 0) {
			return "활동 중";
		}
		return "탈퇴";
	}


	public Member(int id, String email, String nickname) {
		this.id = id;
        this.email = email;
        this.nickname = nickname;
		
	}

}