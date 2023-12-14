package com.example.demo.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Chat.MessageType;
import com.example.demo.vo.ChatRoom;
import com.example.demo.vo.Member;

@Mapper
public interface ChatDao {
	
	@Insert("""
			INSERT INTO CHATROOMMEMBER
			SET regDate = NOW()
				, dscsnRoomId = #{discussionRoomId}
				, memberId = #{memberId}
				, sessionId = #{sessionId}
			""")
	void joinChatRoom(int discussionRoomId, int memberId, String sessionId);
	
	@Insert("""
			INSERT INTO CHAT
			SET regDate = #{regDate}
				, dscsnRoomId = #{discussionRoomId}
				, memberId = #{memberId}
				, message = #{message}
				, banMemberId = #{banMemberId}
				, messageType = #{messageType}
			""")
	void saveChat(LocalDateTime regDate, int discussionRoomId, int memberId, String message, int recipientId, int banMemberId, MessageType messageType);

	@Delete("""
			<script>
				DELETE FROM CHATROOMMEMBER
				WHERE discussionRoomId = #{discussionRoomId}
				<if test="memberId != 0">
					AND memberId = #{memberId}
				</if>
			 </script>
			""")
	void exitChatRoom(int discussionRoomId, int memberId);
	
	@Select("""
			SELECT D.* , U.nickname AS hostNickname, COUNT(CRM.id) AS currentMemberCount
				FROM DSCSN_ROOM AS D
				INNER JOIN USER_INFO AS U
				ON D.crtrId=U.id
				INNER JOIN CHATROOMMEMBER AS CRM
				ON D.id = CRM.dscsnRoomId
				WHERE D.id = #{discussionRoomId}
			""")
	ChatRoom getChatRoomById(int discussionRoomId);
	
	@Delete("""
			DELETE FROM CHATROOM
				WHERE id = #{discussionRoomId}
			""")
	void deleteChatRoom(int discussionRoomId);
	
	@Delete("""
			DELETE FROM CHAT
				WHERE discussionRoomId = #{discussionRoomId}
			""")
	void deleteChat(int discussionRoomId);

	@Select("""
			SELECT U.*, CRM.sessionId AS sessionId
				FROM USER_INFO AS U
				INNER JOIN CHATROOMMEMBER AS CRM
				ON U.id = CRM.memberId
				WHERE CRM.dscsnRoomId = #{discussionRoomId}
			""")
	List<Member> getMemberList(int discussionRoomId);

	
	@Select("""
			SELECT U.*
				FROM `USER_INFO` AS U
				INNER JOIN CHATROOMMEMBER AS CRM
				ON U.id = CRM.memberId
				WHERE CRM.sessionId = #{sessionId}
			""")
	Member getMemberBySessionId(String sessionId);

}