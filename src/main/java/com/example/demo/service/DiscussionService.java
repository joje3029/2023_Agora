package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.DiscussionDao;
import com.example.demo.vo.Article;

@Service
public class DiscussionService {
	
	private DiscussionDao discussionDao;
	
	public DiscussionService(DiscussionDao discussionDao) {
		this.discussionDao = discussionDao;
	}

	//냄길것
	public List<Article> getArticles(int boardId, int limitStart, int itemsInAPage) {
		return discussionDao.getArticles(boardId, limitStart, itemsInAPage);
	}
	
	//냄길것
	public int getArticlesCnt(int boardId) {
		return discussionDao.getArticlesCnt(boardId);
	}
	
}