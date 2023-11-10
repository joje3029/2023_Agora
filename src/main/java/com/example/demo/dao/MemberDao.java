package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper //Mapper는 class가 아니라 interface(100%추상클래스)가 되야함
public interface MemberDao {

	@Insert("""
			INSERT INTO `member`
			SET regDate = NOW()
				,updateDate = NOW()
				,loginId = #{loginId}
				,loginPw = #{loginPw}
				,`name` = #{name}
				,nickname = #{nickname}
				,cellphoneNum = #{cellphoneNum}
				,email = #{email }
			""")
	public void joinMember(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);
	
	@Select("""
			SELECT LAST_INSERT_ID();
			""")
	public int getlastInsetId();
	
	@Select("""
			SELECT COUNT(*)
			FROM `member`
			WHERE loginId = #{loginId}
			""")
	public int checkLoginId(String loginId);
	
}
