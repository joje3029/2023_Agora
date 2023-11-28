package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Article;

@Mapper
public interface DiscussionDao {
	
	@Select("""
			SELECT A.*, M.nickname AS nickname
				FROM article AS A
				INNER JOIN `member` AS M
				ON A.memberId = M.id
				WHERE A.boardId = #{boardId}
				ORDER BY A.id DESC
				LIMIT #{limitStart}, #{itemsInAPage}
			""")
	public List<Article> getArticles(int boardId, int limitStart, int itemsInAPage);
	

	@Select("""
			SELECT COUNT(*)
				FROM article
				WHERE boardId = #{boardId}
			""")
	public int getArticlesCnt(int boardId);
}