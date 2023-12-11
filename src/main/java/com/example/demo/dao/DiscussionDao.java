package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Article;
import com.example.demo.vo.DiscussionRoom;

@Mapper
public interface DiscussionDao {
	
	
	@Select("""
			<script>
			SELECT COUNT(*)
				FROM `DSCSN_ROOM` AS D
				<if test="searchKeyword != ''">
					<choose>
						<when test="searchKeywordType == 'roomName'"> # 토론방이름
							WHERE D.roomName LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'managerName'"> # 방장이름
							WHERE D.managerName LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'all'"> # 전체
							WHERE D.roomName LIKE CONCAT('%', #{searchKeyword}, '%') OR D.managerName LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
					</choose>
				</if>
			</script>	
			""")
	public int getDiscussionCnt(String searchKeywordType, String searchKeyword);

	@Select("""
			<script>
			SELECT *
				FROM `DSCSN_ROOM` AS D
				<if test="searchKeyword != ''">
					<choose>
						<when test="searchKeywordType == 'roomName'"> # 토론방이름
							WHERE D.roomName LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'managerName'"> # 방장이름
							WHERE D.managerName LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'all'"> # 전체
							WHERE D.roomName LIKE CONCAT('%', #{searchKeyword}, '%') OR D.managerName LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
					</choose>
				</if>
				ORDER BY D.id DESC
				LIMIT #{limitStart}, #{itemsInAPage}
			</script>	
			""")
	public List<DiscussionRoom> getdisussionRooms(int limitStart, int itemsInAPage, String searchKeywordType, String searchKeyword);
	
	@Insert("""
			INSERT INTO `DSCSN_ROOM`
				 SET dscsnRoomNm = #{roomName}
					, crtrId = #{loginedMemberId}
					, `type` = #{type}
					, dscsnRoomCreatDete = NOW()
			""")
	public void createDiscussionRoom(int loginedMemberId, String roomName, String type);
	
	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();
}