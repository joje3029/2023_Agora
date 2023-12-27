package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReplyService;
import com.example.demo.util.Util;
import com.example.demo.vo.Rq;
import com.example.demo.vo.SubRely;

@Controller
public class UsrReplyController {

	private ReplyService replyService;
	private Rq rq;

	UsrReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}
	// 댓글 관련
	// 댓글작성하면 이리로 옴.
	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(String reply,int columnId) {

		if (Util.empty(reply)) {
			return Util.jsHistoryBack("내용을 입력해주세요");
		}
		
		replyService.writeReply(rq.getLoginedMemberId(), columnId, reply);

		
		int id = replyService.getLastInsertId();

		
		return Util.jsReplace(Util.f("%d번 댓글을 생성했습니다", id), Util.f("../article/detail?id=%d", columnId));
	}
	
	// 대댓글 관련
	// 대댓글 insert
	@RequestMapping("/usr/reply/doSubRely")
	@ResponseBody
	public String doSubRely(String reply,int columnId, int replyId) {

		if (Util.empty(reply)) {
			return Util.jsHistoryBack("내용을 입력해주세요");
		}
		// 대댓글을 대댓글 Db에 insert
		replyService.doSubRely(rq.getLoginedMemberId(), replyId, reply);
		
		
		int id = replyService.getLastInsertId();

		
		return Util.jsReplace(Util.f("대댓글을 생성했습니다", id), Util.f("../article/detail?id=%d", columnId));
	}
	//대댓글 보여줄 ajax 요청 : 우선은 오는지만 확인
	@RequestMapping("/usr/reply/showSubRely")
	@ResponseBody
	public Map<String, Object> showSubRely(int replyId) {
		List<SubRely> subReles = replyService.getSubReles(replyId);
		
		Map<String, Object> data =new HashMap<>();
		
		data.put("data", subReles);
		
		return data;
	}
	
}