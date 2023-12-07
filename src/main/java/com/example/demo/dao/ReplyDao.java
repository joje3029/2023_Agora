package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReplyDao {

	@Insert("""
			INSERT INTO `ANSWER`
				SET `writerId` = ${loginedMemberId}
					,`columnId`= ${columnId}
					,`answerWritingTime`= NOW() 
					,`answerBody`= ${reply}
					,`answerUpdtTime`= Null
					,`answerDeleteTime`= Null
			""")
	void writeReply(int loginedMemberId, int columnId, String reply);

	@Select("SELECT LAST_INSERT_ID()")
	int getLastInsertId();

}