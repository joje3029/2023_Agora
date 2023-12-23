package com.example.demo.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.RecommendPoint;

@Mapper
public interface RecommendDao {
	
	@Insert("""
			INSERT INTO COLUMN_LIKE
			SET userUniqId = #{loginmemberId}
			, columnId = #{coulumnId}
			, likepoint =1
			""")
	public void insertRecommendPoint(int loginmemberId, int coulumnId);
	
	@Delete("""
			DELETE FROM COLUMN_LIKE
			WHERE userUniqId = #{loginmemberId}
				AND columnId =#{coulumnId}
			""")
	public void deleteRecommendPoint(int loginmemberId, int coulumnId);
	
	@Delete("""
			DELETE FROM EMPLYR_SBSCRB
				WHERE  ownerUserId = #{writerId}
				AND guestUserId = #{loginedMemberId}
			""")
	public void deleteSubscribePoint(int loginedMemberId, int writerId);
	
	@Insert("""
			INSERT INTO EMPLYR_SBSCRB
				SET ownerUserId = #{writerId}
				, guestUserId = #{loginedMemberId}
			""")
	public void insertSubscribePoint(int loginedMemberId, int writerId);
	
	@Select("""
			SELECT COUNT(*) AS `count`
				FROM COLUMN_LIKE
				WHERE columnId =#{coulumnId}
			""")
	public RecommendPoint countRecommendPont(int coulumnId);
	
	@Select("""
			SELECT * FROM EMPLYR_SBSCRB
				WHERE ownerUserId = ${writerId}
					AND guestUserId = ${loginedMemberId}
			""")
	public RecommendPoint getCheckRecommend(int loginedMemberId, int writerId);
	
	@Select("""
			SELECT * 
				FROM COLUMN_LIKE 
				WHERE columnId = ${id}
					AND userUniqId =${loginedMemberId}
			""")
	public RecommendPoint getChecklike(int loginedMemberId, int id);
	
	
}