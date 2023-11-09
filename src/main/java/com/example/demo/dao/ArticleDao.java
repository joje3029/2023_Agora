package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.vo.Article;

@Component
public class ArticleDao {
	
	private List<Article> articles;
	private int lastArticleId;
	
	public ArticleDao() {
		this.articles = new ArrayList<>();
		this.lastArticleId = 0;
		
		makeTestData();
	}
	
	public void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			String title = "제목" + i;
			String body = "내용" + i;
			
			writeArticle(title, body);
		}
	}
	
	public Article writeArticle(String title, String body) {
		this.lastArticleId++;
		
		Article article = new Article(lastArticleId, title, body);
		
		this.articles.add(article);
		
		return article;
	}
	
	public List<Article> getArticles() {
		return this.articles;
	}
	
	public Article getArticleById(int id) {
		for (Article article : this.articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	
	public void modifyArticle(Article article, String title, String body) {
		article.setTitle(title);
		article.setBody(body);
	}
	
	public void deleteArticle(Article article) {
		this.articles.remove(article);
	}
}
