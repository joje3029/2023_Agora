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
	
	//지금 limit 넣으면 애가 지랄을 해서 우선 뺌. 나중에 추가해야함.
	@Select("""
			SELECT C.*, U.nickname
				FROM `COLUMN` AS C
				INNER JOIN `USER_INFO` AS U
				ON C.colmnWrter = U.id
				ORDER BY C.id DESC
			""")
	public List<Article> getArticles(int limitStart, int itemsInAPage);
	
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
				, colmnModifidTime = NOW()
				, colmnDeleteTime = NOW()
				, colmnDeleteEnnc = 1
				, colmnWrter = 1
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
			SELECT COUNT(*)
				FROM `COLUMN`
			""")
	public int getArticlesCnt();


}