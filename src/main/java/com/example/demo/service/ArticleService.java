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
	
	public void writeArticle(String title, String body) {
		articleDao.writeArticle(title, body);
	}
	
	public List<Article> getArticles() {
		return articleDao.getArticles();
	}
	
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	
	public void modifyArticle(Article article, String title, String body) {
		int id=article.getId();
		articleDao.modifyArticle(id, title, body);
	}
	
	public void deleteArticle(Article article) {
		int id=article.getId();
		articleDao.deleteArticle(id);
	}

	public int getlastInsetId() {
		return articleDao.getlastInsetId();
	}
	
}