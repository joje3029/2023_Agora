package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
				, `postNum` = #{postNum}
				, `adress` = #{adress}
				, `detailAdress` = #{detailAdress}
				, `extAdress` = #{extAdress}
				, `telno` = #{cellphoneNum}
				, `joinDate` = NOW()
				, `secsnReqstdt` = NULL
			""")
	public void joinMember(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email, String postNum, String adress, String detailAdress, String extAdress);
	
	@Select("""
			SELECT * 
				FROM `USER_INFO`
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
				FROM `USER_INFO`
				WHERE `name` = #{name}
				AND email = #{email}
				AND cellphoneNum = #{cellphoneNum}
			""")
	public Member getMemberByNameAndEmailAndCell(String name, String email, String cellphoneNum);
	
	@Update("""
			<script>
			UPDATE `USER_INFO`
			 SET modifyDate = NOW()
			 <if test="name != null and name != ''">
			 	 ,`name` = #{name}
			 	 </if>
				<if test="nickname != null and nickname != ''">
				 , `nickname` =  #{nickname}
				</if>
				<if test="email != null and email != ''">
				 , `email` =  #{email}
				</if>
				<if test="cellphoneNum != null and cellphoneNum != ''">
				 , `telno` =  #{cellphoneNum}
				</if>
				<if test="postNum != null and postNum != ''">
				 , `postNum` =  #{postNum}
				</if>
				<if test="adress != null and adress != ''">
				 , `adress` =  #{adress}
				</if>
				<if test="detailAdress != null and detailAdress != ''">
				 , `detailAdress` =  #{detailAdress}
				</if>
				<if test="extAdress != null and extAdress != ''">
				 , `extAdress` =  #{extAdress}
				</if>	
			 WHERE id =  #{id}
			 </script>
			""")
	public void modifyMemebr(int id, String name, String nickname, String cellphoneNum, String email, String postNum, String adress, String detailAdress, String extAdress);

	@Update("""
			UPDATE `USER_INFO`
				SET passwd = #{loginPw}
			WHERE id =  #{loginedMemberId}
			""")
	public void modifyPw(int loginedMemberId, String loginPw);
	
	@Select("""
			SELECT *
			FROM `USER_INFO`
			WHERE nickname = #{recipientNickname}
			""")
	public Member getMemberByNickname(String recipientNickname);
	
	@Select("""
			SELECT *
			FROM `USER_INFO`
			WHERE id = #{loginedMemberId}
			""")
	public Member getMemberBypassWd(int loginedMemberId);
	
	@Insert("""
			
			""")
	public void insertReason(String reason);
}