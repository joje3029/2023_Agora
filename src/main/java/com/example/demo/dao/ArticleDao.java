package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Article;

@Mapper //Mapper는 class가 아니라 interface(100%추상클래스)가 되야함
public interface ArticleDao {
	
	@Insert("""
			INSERT INTO article
			SET regDate = NOW()
			,updateDate = NOW()
			,title = #{title}
			,`body` = #{body}
			""")
	public Article writeArticle(String title, String body);
	
	@Select("""
			SELECT * 
			FROM article 
			ORDER BY id DESC
			""")
	public List<Article> getArticles();
	
	@Select("""
			SELECT * 
			FROM article 
			WHERE id=#{id};
			""")
	public Article getArticleById(int id);
	
	@Update("""
			UPDATE article
			SET updateDate = NOW()
			,title = #{title}
			,`body` = #{body}
			WHERE id=#{id}
			""")
	public void modifyArticle(Article article, String title, String body);

	@Select("""
			SELECT LAST_INSERT_ID();
			""")
	public void deleteArticle(Article article);
}
