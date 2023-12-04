package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Article;

@Mapper
public interface ArticleDao {
	
	@Insert("""
			INSERT INTO article
				SET regDate = NOW()
					, updateDate = NOW()
					, memberId = #{memberId}
					, boardId = #{boardId}
					, title = #{title}
					, `body` = #{body}
			""")
	public void writeArticle(int memberId, int boardId, String title, String body);
	
	@Select("""
			SELECT C.*, U.ncnm
				FROM `COLUMN` AS C
				INNER JOIN `USER_INFO` AS U
				ON C.colmn_wrter = U.user_uniq_id
				LIMIT #{limitStart}, #{itemsInAPage}
			""")
	public List<Article> getArticles(int limitStart, int itemsInAPage);
	
	@Select("""
			SELECT A.*, M.nickname AS nickname
				FROM article AS A
				INNER JOIN `member` AS M
				ON A.memberId = M.id
				WHERE A.id = #{id}
			""")
	public Article forPrintArticle(int id);
	
	@Select("""
			SELECT * 
				FROM article
				WHERE id = #{id}
			""")
	public Article getArticleById(int id);
	
	@Update("""
			<script>
			UPDATE article
				SET updateDate = NOW()
					<if test="title != null and title != ''">
						, title = #{title}
					</if>
					<if test="body != null and body != ''">
						, `body` = #{body}
					</if>
				WHERE id = #{id}
			</script>
			""")
	public void modifyArticle(int id, String title, String body);
	
	@Delete("""
			DELETE FROM article
				WHERE id = #{id}
			""")
	public void deleteArticle(int id);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("""
			SELECT COUNT(*) 
				FROM article
				WHERE boardId =${boardId};
			""")
	public String getArticlCount(int boardId);

	@Select("""
			SELECT COUNT(*)
				FROM article
				WHERE boardId = #{boardId}
			""")
	public int getArticlesCnt(int boardId);
}