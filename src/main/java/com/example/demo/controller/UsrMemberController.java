package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrMemberController {
	
	private MemberService memberService;
	private Rq rq;
	
	UsrMemberController(MemberService memberService, Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
	}
	
	//회원가입
	@RequestMapping("/usr/member/join")
	public String join(HttpServletRequest req) {
		return "usr/member/join";
	}
	
	// 로그인아이디 중복 체크
	@RequestMapping("/usr/member/loginIdDupChk")
	@ResponseBody
	public ResultData loginIdDupChk(String loginId) {

		if (Util.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member != null) {
			return ResultData.from("F-2", Util.f("%s은(는) 이미 사용중인 아이디입니다", loginId));
		}

		return ResultData.from("S-1", Util.f("%s은(는) 사용 가능한 아이디입니다", loginId));
	}
	
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin( String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email, String streetAdres, String detailAdres, String extAdres) {
		
		String adres = streetAdres+detailAdres+extAdres;
		
		if (Util.empty(loginId)) {
			return Util.jsHistoryBack("아이디를 입력해주세요");
		}
		if (Util.empty(loginPw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요");
		}
		if (Util.empty(name)) {
			return Util.jsHistoryBack("이름을 입력해주세요");
		}
		if (Util.empty(nickname)) {
			return Util.jsHistoryBack("닉네임을 입력해주세요");
		}
		if (Util.empty(cellphoneNum)) {
			return Util.jsHistoryBack("전화번호를 입력해주세요");
		}
		if (Util.empty(email)) {
			return Util.jsHistoryBack("이메일을 입력해주세요");
		}
		if (Util.empty(adres)) {
			return Util.jsHistoryBack("주소를 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member != null) {
			return Util.jsHistoryBack(Util.f("이미 사용중인 아이디(%s) 입니다", loginId));
		}
		
		String[] splitCellphoneNum = cellphoneNum.split("-");
		
		cellphoneNum = splitCellphoneNum[0]+splitCellphoneNum[1]+splitCellphoneNum[2];
		
		//SHA 변환 후
		memberService.joinMember(loginId, Util.sha256(loginPw), name, nickname, cellphoneNum, email, adres);
		
		return Util.jsReplace(Util.f("%s님의 가입이 완료되었습니다", name), "login");
	}
	
	//회원정보 수정
	@RequestMapping("/usr/member/modify")
	public String modify(HttpServletRequest req) {
		return "usr/member/modify";
	}
	
	@RequestMapping("/usr/member/domodify")
	public String domodify() {
		
		//일단 일로는 들어옴. 404내용보니까 return을 못찾아서 그렇지
		
		return "/usr/main/home";  //일단 임시로 null 안나게
		
	}
	
	//회원탈퇴
	@RequestMapping("/usr/member/withdraw")
	public String withdraw(HttpServletRequest req) {
		return "usr/member/withdraw";
	}
	
	@RequestMapping("/usr/member/dowithdraw")
	public String dosecede() {
		return "/";  //일단 임시로 널 안나게
		
	}
	
	//아이디찾기
	//아이디찾기 폼으로감
		@RequestMapping("/usr/member/findId")
		public String findId(HttpServletRequest req) {
			return "usr/member/findId";
		}
	
	//아이디 찾기 폼에서 인증 이메일 보낼 곳
		@RequestMapping("/usr/member/doSendCertificationMail")
		@ResponseBody
		public String doSendCertificationMail(String loginId, String email) {

			//인증메일 보내는 일 하는 아.
			ResultData notifyTempLoginPwByEmailRd = memberService.notifyTempLoginPwByEmail(member);

			return Util.jsReplace(notifyTempLoginPwByEmailRd.getMsg(), "login");
		}
		
		
	// 아이디 찾는 일을 하는 곳
		@RequestMapping("/usr/member/doFindId")
		public String dofindId() {
			return "usr/member/foundId";// 찾은 아이디 알려주는 jsp 
			
		}
	//비밀번호
		@RequestMapping("/usr/member/findPw")
		public String findPW(HttpServletRequest req) {
			return "usr/member/findPw";
		}
		
		
		@RequestMapping("/usr/member/dofindPw")
		public String dofindPw() {
			return "/";
			
		}
		
	
	//로그인
	@RequestMapping("/usr/member/login")
	public String login(HttpServletRequest req) {
		
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
public String doLogin( String loginId, String loginPw) {
		
		if(rq.getLoginedMemberId()!=0) {
			return Util.jsHistoryBack("로그아웃 후 이용해주세요");
		}
		
		if (Util.empty(loginId)) {
			return Util.jsHistoryBack("아이디를 입력해주세요");
		}
		if (Util.empty(loginPw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member == null) {
			return Util.jsHistoryBack(Util.f("%s은(는) 존재하지 않는 아이디입니다", loginId));
		}
		
		if (member.getPasswd().equals(Util.sha256(loginPw)) == false) {
			return Util.jsHistoryBack("비밀번호를 확인해주세요");
		}
	
		rq.login(member);
		
		
		return Util.jsReplace(Util.f("%s 회원님 환영합니다~", member.getNickname()), "/");
	}
	
	//비밀번호수정
	@RequestMapping("/usr/member/chagePw")
	public String chagePw(HttpServletRequest req) {
		
		return "usr/member/chagePw";
	}
	
	//로그아웃
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		if (rq.getLoginedMemberId()==0) {
			return Util.jsHistoryBack("로그인 후 이용해주세요");
		}
		
		rq.logout();
		
		return Util.jsReplace(Util.f("정상적으로 로그아웃 되었습니다"), "/");
	}
	
	//인증번호 이메일 로직
	@RequestMapping("/usr/member/doSendEmail")
	public String doSendEmail(HttpServletRequest req) {
		
		
		return Util.jsReplace(Util.f("인증메일이 발송되었습니다. 이메일을 확인해주세요"), "join");
	}
	
	
	
}