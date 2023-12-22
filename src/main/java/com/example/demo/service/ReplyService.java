package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ReplyDao;
import com.example.demo.vo.Reply;

@Service
public class ReplyService {

	private ReplyDao replyDao;

	public ReplyService(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	public void writeReply(int loginedMemberId, int columnId, String reply) {
		replyDao.writeReply(loginedMemberId, columnId, reply );
	}

	public int getLastInsertId() {
		return replyDao.getLastInsertId();
	}

	public List<Reply> getReplies(int id) {
		return replyDao.getReplies(id);
	}

	public Reply getReplycount(int id) {
		return replyDao.getReplycount(id);
	}

}