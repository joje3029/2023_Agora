package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
//	쌤꺼
//	private int id;
//	private String regDate;
//	private String updateDate;
//	private String relTypeCode;
//	private int relId;
//	private String body;
//
//	private String writerName;
	
	//내꺼- 일단 DB에서 만든 칼럼명은 다때림.
	private int id;
	private int writerId;
	private int columnId;
	private String answerWritingTime;
	private String answerBody;
	private String answerUpdtTime;
	private String answerDeleteTime;
	private int answerDeleteEnnc;
	
	private String writerName;
	
	
	public String getForPrintBody() {
		return this.answerBody.replaceAll("\n", "<br />");
	}
}