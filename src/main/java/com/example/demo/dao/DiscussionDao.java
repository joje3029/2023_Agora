package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
	public int getDiscussionCnt(int loginMemberId, String searchKeywordType, String searchKeyword, int type); // 갯수 세는거

	@Select("""
			<script>
			SELECT *
				FROM `DSCSN_ROOM` AS D
				INNER JOIN `USER_INFO` AS U
				ON D.crtrId = U.id
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
	public List<DiscussionRoom> getdisussionRooms(int limitStart, int itemsInAPage, String searchKeywordType, String searchKeyword, int loginMemberId, int type); // 토론방 리스트 가져오는거.
	
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
	
	@Select("""
			SELECT D.*, U.nickname 
				FROM `DSCSN_ROOM` AS D
				INNER JOIN `USER_INFO` AS U
				ON D.crtrId = U.id
				WHERE D.id =#{discussionId}
			""")
	public DiscussionRoom getDiscussionRoomById(int discussionId);
	
	@Select("""
			SELECT DR.id AS id, DR.dscsnRoomNm, COUNT(CRM.id) AS memberCount
				FROM DSCSN_ROOM AS DR
				LEFT JOIN CHATROOMMEMBER AS CRM ON DR.id = CRM.dscsnRoomId
				WHERE DR.id BETWEEN 1 AND (SELECT MAX(id) FROM DSCSN_ROOM)
				GROUP BY DR.id
				ORDER BY memberCount DESC
				Limit 5
			""")
	public List<DiscussionRoom> getRoomRank();
}