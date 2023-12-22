package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendPoint {
	private int id; //칼럼 id
	private int count; // 좋아요 갯수
	
	private int ownerUserId; // 구독대상
	private int guestUserId; // 구독하려는 사람
}