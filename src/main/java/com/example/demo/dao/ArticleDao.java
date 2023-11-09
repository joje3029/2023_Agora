package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Article;

@Mapper //Mapper는 class가 아니라 interface(100%추상클래스)가 되야함
public interface ArticleDao {
			
	public Article writeArticle(String title, String body);
	
	public List<Article> getArticles();
	
	public Article getArticleById(int id);
	
	public void modifyArticle(Article article, String title, String body);
	
	public void deleteArticle(Article article);
}
