package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.vo.Article;

@Service
public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	public void writeArticle(int memberId, int boardId,  String title, String body) {
		articleDao.writeArticle(memberId, boardId, title, body);
	}
	
	public List<Article> getArticles(int boardId, int limitStart, int itemsInAPage) {
		return articleDao.getArticles(boardId, limitStart, itemsInAPage);
	}
	
	public Article forPrintArticle(int id) {
		return articleDao.forPrintArticle(id);
	}
	
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	
	public void modifyArticle(int id, String title, String body) {
		articleDao.modifyArticle(id, title, body);
	}
	
	public void deleteArticle(int id) {
		articleDao.deleteArticle(id);
	}

	public int getLastInsertId() {
		return articleDao.getLastInsertId();
	}

	public String getArticlCount(int boardId) {
		return articleDao.getArticlCount(boardId);
	}

	public int getArticlesCnt(int boardId) {
		return articleDao.getArticlesCnt(boardId);
	}
	
}