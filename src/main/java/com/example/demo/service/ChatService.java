package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ChatDao;
import com.example.demo.vo.Chat.MessageType;
import com.example.demo.vo.ChatRoom;
import com.example.demo.vo.Member;

@Service
public class ChatService {
	
	private ChatDao chatDao;
	
	public ChatService(ChatDao chatDao) {
		this.chatDao = chatDao;
	}
	
	public void joinChatRoom(int discussionRoomId, int memberId, String sessionId) {
		chatDao.joinChatRoom(discussionRoomId, memberId, sessionId);
	}

	public void saveChat(LocalDateTime regDate, int discussionRoomId, int memberId, String message, int recipientId, int banMemberId, MessageType messageType) {
		chatDao.saveChat(regDate, discussionRoomId, memberId, message, recipientId, banMemberId, messageType);
	}

	public void exitChatRoom(int discussionRoomId, int memberId) {
		chatDao.exitChatRoom(discussionRoomId, memberId);
	}

	public ChatRoom getChatRoomById(int discussionRoomId) {
		return chatDao.getChatRoomById(discussionRoomId);
	}

	public void deleteChatRoom(int discussionRoomId) {
		chatDao.deleteChatRoom(discussionRoomId);
	}

	public void deleteChat(int discussionRoomId) {
		chatDao.deleteChat(discussionRoomId);
	}

	public void modifyChatRoom(int discussionRoomId) {
		List<Member> members = chatDao.getMemberList(discussionRoomId);
	}

	public Member getMemberBySessionId(String sessionId) {
		return chatDao.getMemberBySessionId(sessionId);
	}

	public List<Member> getMemberList(int discussionRoomId) {
		return chatDao.getMemberList(discussionRoomId);
	}

}