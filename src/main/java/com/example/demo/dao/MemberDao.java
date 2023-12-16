package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
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
			INSERT INTO `WITHDROW_REASON`
				SET reason = #{reason}
				, detailReason = #{detailReason}
			""")
	public void insertReason(String reason, String detailReason);
	
	@Delete("""
			DELETE FROM `USER_INFO`
				WHERE id = #{loginedMemberId}
			""")
	public void deleteMember(int loginedMemberId);
	
	@Select("""
			SELECT COUNT(*)
				FROM `USER_INFO`
			""")
	public int getMembersCnt();
	
	@Select("""
		    <script>
		        SELECT *
		        FROM `USER_INFO`
		        <where>
		            <if test="startDate != null and startDate != ''">
		                joinDate <![CDATA[ >= ]]> #{startDate}
		            </if>
		            <if test="endDate != null and endDate != ''">
		                <if test="startDate != null and startDate != ''">
		                    <![CDATA[ AND ]]>
		                </if>
		                joinDate <![CDATA[ <= ]]> #{endDate}
		            </if>
		            <if test="searchId != null and searchId != ''">
		                uwerId LIKE CONCAT('%', #{searchId}, '%')
		            </if>
		            <if test="searchNickname != null and searchNickname != ''">
		                nickname LIKE CONCAT('%', #{searchNickname}, '%')
		            </if>
		        </where>
		        ORDER BY id DESC
		        LIMIT #{limitStart}, #{itemsInAPage}
		    </script>
		""")
	public List<Member> getMembers(int itemsInAPage, int limitStart, String startDate, String endDate, String searchId, String searchNickname);
	//int itemsInAPage : 검색시 10개씩 끊어 보여줄끼다의 10개 , 
	//int limitStart : 끈을 곳, 
	//String startDate :가입일 날짜 검색 시작 일, (v) 
	//String endDate : 가입일 날짜 검색 종료 일, (v)
	//String searchId : 아이디로 검색시 ,
	//String searchNickname : 닉네임으로 검색시
	
}