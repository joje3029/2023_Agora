package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDao;
import com.example.demo.vo.Board;

@Service
public class BoardService {

	private BoardDao boardDao;

	public BoardService(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	public Board getBoardById(int boardId) {
		return boardDao.getBoardById(boardId);
	}

}