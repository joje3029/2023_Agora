package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Reply;
import com.example.demo.vo.SubRely;

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
	public void writeReply(int loginedMemberId, int columnId, String reply);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("""
			SELECT A.*, U.nickname AS writerName
				FROM `ANSWER` AS A
				INNER JOIN `USER_INFO` AS U
				ON A.writerId = U.id
				WHERE A.columnId = ${id}
			""")
	public List<Reply> getReplies(int id);
	
	@Select("""
			SELECT COUNT(*) AS `count`
				FROM `ANSWER`
				WHERE columnId = ${id}
			""")
	public Reply getReplycount(int id);
	
	@Insert("""
			INSERT INTO REPLY
				SET answerUniqId =${replyId}
				, replyWritingTime = NOW()
				, replyBody = '${reply}'
				, replyDeleteEnnc = 0
				, replyWrter = ${loginedMemberId}
			""")
	public void doSubRely(int loginedMemberId, int replyId, String reply);
	
	@Select("""
			SELECT R.*, U.nickname
				FROM REPLY AS R
				INNER JOIN USER_INFO AS U
				ON R.replyWrter = U.id
				WHERE R.answerUniqId = ${replyId}
			""")
	public List<SubRely> getSubReles(int replyId);

}