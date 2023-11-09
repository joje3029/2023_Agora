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
	
	public Article writeArticle(String title, String body) {
		return articleDao.writeArticle(title, body);
	}
	
	public List<Article> getArticles() {
		return articleDao.getArticles();
	}
	
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	
	public void modifyArticle(Article article, String title, String body) {
		articleDao.modifyArticle(article, title, body);
	}
	
	public void deleteArticle(Article article) {
		articleDao.deleteArticle(article);
	}
	
}