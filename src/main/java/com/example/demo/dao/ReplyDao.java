package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Reply;

@Mapper
public interface ReplyDao {

	@Insert("""
			INSERT INTO `ANSWER`
				SET `writerId` = ${loginedMemberId}
					,`columnId`= ${columnId}
					,`answerWritingTime`= NOW() 
					,`answerBody`= '${reply}'
					,`answerUpdtTime`= Null
					,`answerDeleteTime`= Null
					, answerDeleteEnnc = 0
			""")
	void writeReply(int loginedMemberId, int columnId, String reply);

	@Select("SELECT LAST_INSERT_ID()")
	int getLastInsertId();

	@Select("""
			SELECT A.*, U.nickname AS writerName
			FROM `ANSWER` AS A
			INNER JOIN `USER_INFO` AS U
			ON A.writerId = U.id
			WHERE A.columnId = ${id}
			""")
	List<Reply> getReplies(int id);

}