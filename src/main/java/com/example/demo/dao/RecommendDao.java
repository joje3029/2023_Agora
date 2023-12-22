package com.example.demo.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.RecommendPoint;

@Mapper
public interface RecommendDao {
	
	@Select("""
			SELECT * 
			FROM COLUMN_LIKE
			where userUniqId =#{loginedMemberId}
			and columnId =#{coulumnId}
			""")
	public RecommendPoint getRecommendPoint(int loginedMemberId, int coulumnId);
	
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
	
//	@Insert("""
//			INSERT INTO EMPLYR_SBSCRB
//				SET ownerUserId = #{writerId}
//				, guestUserId = #{loginmemberId}
//			""")
//	public void insertSubscribePoint(int loginedMemberId, int writerId);
//	
//	@Delete("""
//			DELETE FROM EMPLYR_SBSCRB
//				WHERE  ownerUserId = #{writerId}
//				AND guestUserId = #{loginmemberId}
//			""")
//	public void deleteSubscribePoint(int loginedMemberId, int writerId);
	
	

}