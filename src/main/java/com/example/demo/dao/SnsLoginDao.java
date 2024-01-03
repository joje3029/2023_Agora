package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Member;

@Mapper
public interface SnsLoginDao {
	
	@Insert("""
			 INSERT INTO USER_INFO (uwerId, email, nickname)
			 	VALUES (#{strId}, #{email}, #{nickname})
			""")
	public void insertKakaoinfo(String strId, String email, String nickname);
	
	@Select("""
			SELECT LAST_INSERT_ID()
			""")
	public int getLastId();
	
	@Select("""
			SELECT * 
				FROM USER_INFO 
				WHERE id = #{lastId}
			""")
	public Member getLastInsertMember(int lastId);
	
	@Select("""
			SELECT * 
				FROM USER_INFO 
				WHERE uwerId = #{strId}
			""")
	public Member getMemberCheck(String strId);
	
	@Insert("""
			 INSERT INTO USER_INFO (uwerId, email, nickname, name)
			 	VALUES (#{uwerId}, #{email}, #{nickname}, #{name})
			""")
	public void insertNaverinfo(String uwerId, String nickname, String email, String name);
	
}