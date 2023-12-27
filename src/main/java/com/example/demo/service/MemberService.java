package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Service
public class MemberService {

	private MemberDao memberDao;
	private EmailService emailService;
	@Value("${custom.siteName}")
	private String siteName;
	@Value("${custom.siteMainUri}")
	private String siteMainUri;

	public MemberService(MemberDao memberDao, EmailService emailService) {
		this.memberDao = memberDao;
		this.emailService= emailService;
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

	//고객센터 답장 이메일로 갈 것!!
	public ResultData notifyTempUserAnswerByEmail(String customerEmail, String body) { 

		String subject = "[" + siteName + "] 1:1 고객센터으로 문의주신 답변입니다."; // 이메일 제목 - [2023_11_SB_AM] 임시 패스워드 - 발송 이렇게 지금 옴.
		
		//아래 해보고 성공하면 쓸대 없는거니까 지우기
		//String tempPassword = Util.createTempPassword(); // 임시 패스워드 랜덤으로 만들고 변수에 담음
		
		// 메일 내용으로 나오는 부분
		String text = "<p>" + body + "</p>"; // 임시 패스워드 h1 태그로 해서 나오는 부분 

		ResultData sendRd = emailService.send(customerEmail, subject, text); // 이메일 서비스씨 안의 send가 이메일 보냄. member이메일주소로 제목이란 컨텐츠 위에 한것대로 해서.

		if (sendRd.isFail()) { // 메일 보내는게 실패했으면
			return sendRd;
		}
		//아래 해보고 성공하면 쓸대 없는거니까 지우기
		//setTempPassword(member, tempPassword);// 비번 바꾸는거

		return ResultData.from("S-1", "계정의 이메일주소로 답변이 발송되었습니다");
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

	public int getMembersCnt() {
		return memberDao.getMembersCnt();
	}


	public List<Member> getMembers(int itemsInAPage, int limitStart, String startDate, String endDate, String searchId,
			String searchNickname) {
		return memberDao.getMembers(itemsInAPage, limitStart, startDate, endDate, searchId, searchNickname);
	}

	public void modifyMemberssecnEnnc(List<String> ids) {
		for (String idStr : ids) {
			Member member = getMemberById(Integer.parseInt(idStr));

			if (member != null) {
				modifyMemberSecsnEnnc(member.getId());
			}
		}
	}

	private void modifyMemberSecsnEnnc(int id) {
		memberDao.modifyMemberSecsnEnnc(id);
	}

	public ResultData notifyTempLoginPwByEmail(Member member) {
		String subject = "[" + siteName + "] 임시 패스워드 발송";
		String tempPassword = Util.createTempPassword();
		String text = "<h1>임시 패스워드 : " + tempPassword + "</h1><br>";
		text += "<a style='display:inline-block;padding:10px;border-radius:10px;border:5px solid black;font-size:4rem;color:inherit;text-decoration:none;' href=\"" + siteMainUri + "/usr/member/login\" target=\"_blank\">로그인 하러가기</a>";

		ResultData sendRd = emailService.send(member.getEmail(), subject, text);

		if (sendRd.isFail()) {
			return sendRd;
		}

		setTempPassword(member, tempPassword);

		return ResultData.from("S-1", "계정의 이메일주소로 임시 패스워드가 발송되었습니다");
	}

	private void setTempPassword(Member member, String tempPassword) {
		memberDao.doPasswordModify(member.getId(), Util.sha256(tempPassword));
	}


	
	

}