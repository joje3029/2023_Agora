package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscussionRoom {
	private int id; //토론방 id
	private String dscsnRoomNm; // 토론방 이름
	private int  crtrId; // 토론방장(만든놈)
	private String  dscsnRoomCreatDete; // 토론방생성일자
	private String  dscsnRoomDeleteDete; // 토론방삭제일자
	
}