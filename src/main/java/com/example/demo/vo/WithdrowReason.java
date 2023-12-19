package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrowReason {
	private String withdrawalMonth; //탈퇴신청 날짜별 
	private String reason; //탈퇴이유 별
	private String count; // 위의 두개로 분류한거 count 
}