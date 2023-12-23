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
	
	public void writeArticle(int memberId, String title, String body) {
		articleDao.writeArticle(memberId, title, body);
	}
	
	public List<Article> getArticles(int limitStart, int itemsInAPage, String searchKeywordType, String searchKeyword) {
		return articleDao.getArticles(limitStart, itemsInAPage, searchKeywordType, searchKeyword);
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
	
	public int getArticlesCnt(String searchKeywordType, String searchKeyword) {
		return articleDao.getArticlesCnt(searchKeywordType, searchKeyword);
	}

	public List<Article> getArticleRank() {
		return articleDao.getArticleRank();
	}

	public void increaseHitCount(int id) {
		articleDao.increaseHitCount(id);
	}
	
}