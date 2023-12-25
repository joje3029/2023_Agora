package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubRely {
	private int replyUniquId;
	private int answerUniqId;
	private String replyWritingTime;
	private String replyBody;
	private String replyUpdtTime;
	private String replyDeleteTime;
	private int replyDeleteEnnc;
	private int replyWrter;
	
	private String nickname;
	
}