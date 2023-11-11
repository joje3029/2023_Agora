package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;

@Service
public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void joinMember(String loginId, String loginPw, String name, String nickname, String cellphoneNum,String email) {
		memberDao.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email);
	}

	public int getlastInsetId() {
		return memberDao.getlastInsetId();
	}

	public int checkLoginId(String loginId) {
		return memberDao.checkLoginId(loginId);
	}

	public int checkNickname(String nickname) {
		return memberDao.checkNickname(nickname);
	}

	public int checkCellphoneNum(String cellphoneNum) {
		return memberDao.checkCellphoneNum(cellphoneNum);
	}

	public int checkEmail(String email) {
		return memberDao.checkEmail(email);
	}

}