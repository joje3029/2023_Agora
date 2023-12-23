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
			INSERT INTO `COLUMN`
				SET  title = #{title}
				, writngTime = NOW()
				, `body` = #{body}
				, colmnClSetup ='1'
				, colmnModifidTime = NOW()
				, colmnDeleteTime = NOW()
				, colmnDeleteEnnc = 1
				, colmnWrter = 1

			""")
	public void writeArticle(int memberId, String title, String body);
	
	@Select("""
			<script>
			SELECT C.*, U.nickname
				FROM `COLUMN` AS C
				INNER JOIN `USER_INFO` AS U
				ON C.colmnWrter = U.id
				<if test="searchKeyword != ''">
					<choose>
						<when test="searchKeywordType == 'title'">
							WHERE C.title LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'body'">
							WHERE C.body LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'write'">
							WHERE U.nickname LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'all'">
							WHERE C.title LIKE CONCAT('%', #{searchKeyword}, '%') OR C.body LIKE CONCAT('%', #{searchKeyword}, '%') OR U.nickname LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
					</choose>
				</if>
				ORDER BY C.id DESC
				LIMIT #{limitStart}, #{itemsInAPage}
			</script>	
			""")
	public List<Article> getArticles(int limitStart, int itemsInAPage, String searchKeywordType, String searchKeyword);
	
	@Select("""
			SELECT C.*, U.nickname
				FROM `COLUMN` AS C
				INNER JOIN `USER_INFO` AS U
				ON C.colmnWrter = U.id
				WHERE c.id = #{id}
			""")
	public Article forPrintArticle(int id);
	
	@Select("""
			SELECT * 
				FROM `COLUMN`
				WHERE id = #{id}
			""")
	public Article getArticleById(int id);
	
	@Update("""
			<script>
			UPDATE `COLUMN`
				SET writngTime = NOW()
                <if test="title != null and title != ''">
						, title = #{title}
					</if>
					<if test="body != null and body != ''">
						, `body` = #{body}
					</if>
				, colmnClSetup ='1'
				, colmnModifiedTime = NOW()
			WHERE id = #{id}
			</script>
			""")
	public void modifyArticle(int id, String title, String body);
	
	@Delete("""
			DELETE FROM `COLUMN`
				WHERE id = #{id}
			""")
	public void deleteArticle(int id);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("""
			<script>
			SELECT COUNT(*)
				FROM `COLUMN` AS C
				<if test="searchKeyword != ''">
				INNER JOIN `USER_INFO` AS U
				ON C.colmnWrter = U.id
					<choose>
						<when test="searchKeywordType == 'title'">
							WHERE C.title LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'body'">
							WHERE C.body LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'write'">
							WHERE U.nickname LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'all'">
							WHERE C.title LIKE CONCAT('%', #{searchKeyword}, '%') OR C.body LIKE CONCAT('%', #{searchKeyword}, '%') OR U.nickname LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
					</choose>
				</if>
			</script>	
			""")
	public int getArticlesCnt(String searchKeywordType, String searchKeyword);
	
	@Select("""
			SELECT C.*, U.nickname 
				FROM `COLUMN` AS C
				INNER JOIN `USER_INFO` AS U
				ON C.colmnWrter = U.id
				ORDER BY clickCount DESC
				LIMIT 5;
			""")
	public List<Article> getArticleRank();
	
	@Update("""
			UPDATE `COLUMN`
				SET clickCount = clickCount + 1
				WHERE id = #{id}
			""")
	public void increaseHitCount(int id);
	
	


}