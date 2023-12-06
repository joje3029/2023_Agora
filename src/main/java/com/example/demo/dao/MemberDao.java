package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Member;

@Mapper
public interface MemberDao {
	
	@Insert("""
			INSERT INTO `USER_INFO`
				SET  `uwerId` = #{loginId}
				, `passwd` = #{loginPw}
				, `name` = #{name}
				, `nickname` = #{nickname}
				, `email` = #{email}
				, `adres` = #{adres}
				, `telno` = #{cellphoneNum}
				, `joinDate` = NOW()
				, `secsnReqstdt` = NULL
			""")
	public void joinMember(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email, String adres);
	
	@Select("""
			SELECT * 
				FROM `member`
				WHERE id = #{id}
			""")
	public Member getMemberById(int id);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("""
			SELECT *
				FROM `USER_INFO`
				WHERE uwerId = #{loginId}
			""")
	public Member getMemberByLoginId(String loginId);
	
	@Select("""
			SELECT *
				FROM `member`
				WHERE `name` = #{name}
				AND email = #{email}
				AND cellphoneNum = #{cellphoneNum}
			""")
	public Member getMemberByNameAndEmailAndCell(String name, String email, String cellphoneNum);
}