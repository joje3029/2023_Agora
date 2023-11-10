package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.vo.Member;

@Service
public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public Member joinMember(String loginId, String loginPw, String name, String nickname, String cellphoneNum,String email) {
		return memberDao.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email);
	}

}