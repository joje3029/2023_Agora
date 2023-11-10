package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //자동으로 생성자 메서드 및 Getter, Setter 메서드 정의
@AllArgsConstructor//'모든'인자를 가지는 생성자를 구성
public class Member {
	private String loginId;
	private String loginPw;
	private String name;
	private String nickname;
	private String cellphoneNum;
	private String email;
}
