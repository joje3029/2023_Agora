package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chatrommember {
	private int id;
	private String regDate;
	private int dscsnRoomId;
	private int memberId;
	private String sessionId;
	private String nickname;
	
}