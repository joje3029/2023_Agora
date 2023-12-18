package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

@Controller
public class UsrMemberController {

	private MemberService memberService;
	private Rq rq;

	UsrMemberController(MemberService memberService, Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
	}

	// 회원가입
	@RequestMapping("/usr/member/join")
	public String join() {
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

	// 회원가입 실행하는 부분
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email, String postNum, String adress, String detailAdress, String extAdress) {

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
		if (Util.empty(postNum)) {
			return Util.jsHistoryBack("우편번호를 입력해주세요");
		}
		if (Util.empty(adress)) {
			return Util.jsHistoryBack("주소를 입력해주세요");
		}
		if (Util.empty(detailAdress)) {
			return Util.jsHistoryBack("상세주소를 입력해주세요");
		}
		if (Util.empty(extAdress)) {
			return Util.jsHistoryBack("주소 참고사항을 입력해주세요");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member != null) {
			return Util.jsHistoryBack(Util.f("이미 사용중인 아이디(%s) 입니다", loginId));
		}

		String[] splitCellphoneNum = cellphoneNum.split("-");

		cellphoneNum = splitCellphoneNum[0] + splitCellphoneNum[1] + splitCellphoneNum[2];

		// SHA 변환 후
		memberService.joinMember(loginId, Util.sha256(loginPw), name, nickname, cellphoneNum, email, postNum, adress,
				detailAdress, extAdress);

		return Util.jsReplace(Util.f("%s님의 가입이 완료되었습니다", name), "login");
	}

	// 회원정보 보여주기
	@RequestMapping("/usr/member/profile")
	public String showProfile( Model model) {

		// 연결하기 전에 DB가서 지금 로그인한 놈 정보 select로 가져와야겠네.
		Member member = memberService.getMemberById(rq.getLoginedMemberId());

		model.addAttribute("member", member);

		return "usr/member/profile";
	}

	// 회원정보 수정
	@RequestMapping("/usr/member/modify")
	public String modify( Model model) {

		Member member = memberService.getMemberById(rq.getLoginedMemberId());

		model.addAttribute("member", member);

		String tel = member.getTelno();

		String membertel = tel.substring(0, 3) + "-" + tel.substring(3, 7) + "-" + tel.substring(7, 11);

		model.addAttribute("membertel", membertel);

		return "usr/member/modify";
	}

	// 회원정보 수정하는 부분
	@RequestMapping("/usr/member/domodify")
	@ResponseBody
	public String domodify(int id, String name, String nickname, String cellphoneNum, String email, String postNum,
			String adress, String detailAdress, String extAdress) {

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
		if (Util.empty(postNum)) {
			return Util.jsHistoryBack("우편번호를 입력해주세요");
		}
		if (Util.empty(adress)) {
			return Util.jsHistoryBack("주소를 입력해주세요");
		}
		if (Util.empty(detailAdress)) {
			return Util.jsHistoryBack("상세주소를 입력해주세요");
		}
		if (Util.empty(extAdress)) {
			return Util.jsHistoryBack("주소 참고사항을 입력해주세요");
		}

		String[] splitCellphoneNum = cellphoneNum.split("-");

		cellphoneNum = splitCellphoneNum[0] + splitCellphoneNum[1] + splitCellphoneNum[2];

		memberService.modifyMemebr(id, name, nickname, cellphoneNum, email, postNum, adress, detailAdress, extAdress);

		return Util.jsReplace(Util.f("%s님의 회원정보수정이 완료되었습니다", name), "/");

	}

	// 회원탈퇴
	@RequestMapping("/usr/member/withdraw")
	public String withdraw() {
		return "usr/member/withdraw";
	}

	@RequestMapping("/usr/member/doWithdraw")
	@ResponseBody
	public String dosecede( String reason, String detailReason) {

		// 삭제 이유 withdrow_reason에 insert
		// reason : 탈퇴 이유를 어디다가 넣어서 데이터로 관리자 쪽에서 보려고
		memberService.insertReason(reason, detailReason);
		
		// 지금은 스케쥴링 안할꺼니까, 나중에 시간되면 스케줄링 하게 되면 표기랑 delet시간 insert하는 방식으로 변경하기
		memberService.deleteMember(rq.getLoginedMemberId());
	
		//그리고 여기서 rq 에 있는거 비워야겠네 로그아웃처럼. 오케
		rq.logout();
		
		return Util.jsReplace(Util.f("탈퇴 되었습니다"), "/"); // 일단 임시로 널 안나게

	}

	// 아이디찾기
	// 아이디찾기 폼으로감
	@RequestMapping("/usr/member/findId")
	public String findId() {
		return "usr/member/findId";
	}

	// 아이디 찾기 폼에서 인증 이메일 보낼 곳
//		@RequestMapping("/usr/member/doSendCertificationMail")
//		@ResponseBody
//		public String doSendCertificationMail(String loginId, String email) {
//
//			//인증메일 보내는 일 하는 아.
//			ResultData notifyTempLoginPwByEmailRd = memberService.notifyTempLoginPwByEmail(member);
//
//			return Util.jsReplace(notifyTempLoginPwByEmailRd.getMsg(), "login");
//		}

	// 아이디 찾는 일을 하는 곳
	@RequestMapping("/usr/member/doFindId")
	public String dofindId() {
		return "usr/member/foundId";// 찾은 아이디 알려주는 jsp

	}

	// 비밀번호 바꾸기전 인증할 페이지 양식
	@RequestMapping("/usr/member/IdentityVerification")
	public String showIdentityVerification() {
		return "usr/member/IdentityVerification";

	}

	// 인증할 페이지 양식이 아니라 직접 일하는거
	@RequestMapping("/usr/member/doIdentityVerification")
	@ResponseBody
	public String doIdentityVerification(String passWd) {

		// 여서 할일
		// form에서 name = passWd로 한거랑 지금 요청하는 놈 비번 일치하는지 DB를 갔다와야해요. 맞으면 비번 바꾸는데로 보내고 아니면
		// 다시 돌려보내야함.
		Member member = memberService.getMemberById(rq.getLoginedMemberId());

		String shaPassWd = Util.sha256(passWd);

		if (!member.getPasswd().equals(shaPassWd)) {
			return Util.jsReplace("본인인증에 실패했습니다.", "IdentityVerification");
		}

		return Util.jsReplace("본인인증에 성공했습니다.", "chagePw");

	}

	// 비밀번호
	@RequestMapping("/usr/member/findPw")
	public String findPW() {
		return "usr/member/findPw";
	}

	@RequestMapping("/usr/member/dofindPw")
	public String dofindPw() {
		return "/";

	}

	// 로그인
	@RequestMapping("/usr/member/login")
	public String login() {

		return "usr/member/login";
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {

		if (rq.getLoginedMemberId() != 0) {
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
		
		if (member.getSecsnEnnc() == 1) {
			return Util.jsHistoryBack("강제 탈퇴되었습니다. 더 이상 이용할수 없는 계정입니다.");
		}

		rq.login(member);
		

		return Util.jsReplace(Util.f("%s 회원님 환영합니다~", member.getNickname()), "/");
	}

	// 비밀번호 변경 양식 보여주기
	@RequestMapping("/usr/member/chagePw")
	public String chagePw() {

		return "usr/member/chagePw";
	}

	// 비밀번호 변경실제로 되는 곳.
	@RequestMapping("/usr/member/doChagePw")
	@ResponseBody
	public String doChagePw( String loginPw) {

		// DB가서 바꾸는 로직! 적거라!!
		memberService.modifyPw(rq.getLoginedMemberId(), Util.sha256(loginPw));

		return Util.jsReplace("비밀번호가 변경되었습니다.", "/");
	}

	// 로그아웃
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout() {

		if (rq.getLoginedMemberId() == 0) {
			return Util.jsHistoryBack("로그인 후 이용해주세요");
		}

		rq.logout();

		return Util.jsReplace(Util.f("정상적으로 로그아웃 되었습니다"), "/");
	}

	// 인증번호 이메일 로직
	@RequestMapping("/usr/member/doSendEmail")
	public String doSendEmail() {

		return Util.jsReplace(Util.f("인증메일이 발송되었습니다. 이메일을 확인해주세요"), "join");
	}

	// 탈퇴 비밀번호 체크
	@RequestMapping("/usr/member/passwdCheck")
	@ResponseBody
	public ResultData passwdCheck(String passWd) {

		
		if (Util.empty(passWd)) {
			return ResultData.from("F-1", "비밀번호를 입력해주세요");
		}
		
		
		Member member = memberService.getMemberBypassWd(rq.getLoginedMemberId());
		
		String shaPassWd = Util.sha256(passWd);
		
		if (!member.getPasswd().equals(shaPassWd)) {
			return ResultData.from("F-3", "비밀번호가 일치하지 않습니다.");
		}
		
		return ResultData.from("S-1", "비밀번호가 일치합니다.");
	}

}