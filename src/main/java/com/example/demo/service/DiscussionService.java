package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.DiscussionDao;
import com.example.demo.vo.Article;
import com.example.demo.vo.DiscussionRoom;

@Service
public class DiscussionService {
	
	private DiscussionDao discussionDao;
	
	public DiscussionService(DiscussionDao discussionDao) {
		this.discussionDao = discussionDao;
	}

	public int getDiscussionCnt(String searchKeywordType, String searchKeyword) {
		return discussionDao.getDiscussionCnt(searchKeywordType, searchKeyword);
	}

	public List<DiscussionRoom> getdisussionRooms(int limitStart, int itemsInAPage, String searchKeywordType, String searchKeyword) {
		return discussionDao.getdisussionRooms(limitStart,itemsInAPage, searchKeywordType, searchKeyword);
	}

	public void createDiscussionRoom(int loginedMemberId, String roomName, String type) {
		discussionDao.createDiscussionRoom(loginedMemberId, roomName, type);
	}

	public int getLastInsertId() {
		return discussionDao.getLastInsertId();
	}

	public DiscussionRoom getDiscussionRoomById(int discussionId) {
		return discussionDao.getDiscussionRoomById(discussionId);
	}



	
	
}