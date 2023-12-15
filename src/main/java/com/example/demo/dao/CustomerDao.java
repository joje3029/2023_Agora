package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerDao {
	
	@Insert("""
			INSERT INTO CSTMR_CNSLT_CNTER
			SET userUniqId = #{loginedMemberId}
				, cstmrTitle = #{title}
				, cstmrBody = #{body}
				, ricfldSndngYn = 0
			""")
	void insertReceipt(int loginedMemberId, String title, String body);
	
	
	
}