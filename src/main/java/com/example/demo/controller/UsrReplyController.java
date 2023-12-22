package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReplyService;
import com.example.demo.util.Util;
import com.example.demo.vo.Rq;

@Controller
public class UsrReplyController {

	private ReplyService replyService;
	private Rq rq;

	UsrReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}
	// 댓글작성하면 이리로 옴.
	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(String reply,int columnId) {

		if (Util.empty(reply)) {
			return Util.jsHistoryBack("내용을 입력해주세요");
		}
		
		System.out.println(1);
								// 작성한 놈. 몇번째 칼럼, 댓글 내용
		replyService.writeReply(rq.getLoginedMemberId(), columnId, reply);

		System.out.println(2);
		
		int id = replyService.getLastInsertId();

		System.out.println(3);
		
		return Util.jsReplace(Util.f("%d번 댓글을 생성했습니다", id), Util.f("../article/detail?id=%d", columnId));
	}

}