package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Service
public class MemberService {

	private MemberDao memberDao;

	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void joinMember(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email, String postNum, String adress, String detailAdress, String extAdress) {
		memberDao.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email, postNum, adress, detailAdress, extAdress);
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public int getLastInsertId() {
		return memberDao.getLastInsertId();
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}
	
	public Member getMemberByNameAndEmailAndCell(String name, String email, String cellphoneNum) {
		return memberDao.getMemberByNameAndEmailAndCell(name, email, cellphoneNum);
	}

	public ResultData notifyTempLoginPwByEmail(Member member) {
		//이 안에 로직을 알면 내가 보내야하는 것들이 보이것지... -> 빡치면 아이디는 인증번호 말고 전번까지 해서 단순 로직으로 바꿀꺼야.

		String subject = "[" + siteName + "] 임시 패스워드 발송"; // 이메일 제목 - [2023_11_SB_AM] 임시 패스워드 - 발송 이렇게 지금 옴.
		String tempPassword = Util.createTempPassword(); // 임시 패스워드 랜덤으로 만들고 변수에 담음
		// 메일 내용으로 나오는 부분
		String text = "<h1>임시 패스워드 : " + tempPassword + "</h1><br>"; // 임시 패스워드 h1 태그로 해서 나오는 부분 
		text += "<a style='display:inline-block;padding:10px;border-radius:10px;border:5px solid black;font-size:4rem;color:inherit;text-decoration:none;' href=\"" + siteMainUri + "/usr/member/login\" target=\"_blank\">로그인 하러가기</a>"; // 이메일 내용 로그인 하러가기 

		ResultData sendRd = emailService.send(member.getEmail(), subject, text); // 이메일 서비스씨 안의 send가 이메일 보냄. member이메일주소로 제목이란 컨텐츠 위에 한것대로 해서.

		if (sendRd.isFail()) { // 메일 보내는게 실패했으면
			return sendRd;
		}

		setTempPassword(member, tempPassword);

		return ResultData.from("S-1", "계정의 이메일주소로 임시 패스워드가 발송되었습니다");
	}
	
	// 메일받은 놈 아이디로 찾아서 그놈 비번을 임시 번호로 변경하는 로직
	private void setTempPassword(Member member, String tempPassword) {
		memberDao.doPasswordModify(member.getId(), Util.sha256(tempPassword));
	}

	public void modifyMemebr(int id, String name, String nickname, String cellphoneNum, String email, String postNum, String adress, String detailAdress, String extAdress) {
		memberDao.modifyMemebr(id, name, nickname, cellphoneNum, email, postNum, adress, detailAdress, extAdress);
		
	}

	public void modifyPw(int loginedMemberId, String loginPw) {
		memberDao.modifyPw(loginedMemberId, loginPw);
		
	}

	public Member getMemberByNickname(String recipientNickname) {
		return memberDao.getMemberByNickname(recipientNickname);
	}

	public Member getMemberBypassWd(int loginedMemberId) {
		return memberDao.getMemberBypassWd(loginedMemberId);
	}

	

	public void insertReason(String reason, String detailReason) {
		memberDao.insertReason(reason, detailReason);
		
	}

	public void deleteMember(int loginedMemberId) {
		memberDao.deleteMember(loginedMemberId);
		
	}

}