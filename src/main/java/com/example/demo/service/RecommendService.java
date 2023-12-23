package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.RecommendDao;
import com.example.demo.vo.RecommendPoint;

@Service
public class RecommendService {

	private RecommendDao recommendDao;
	
	public RecommendService(RecommendDao recommendDao) {
		this.recommendDao = recommendDao;
	}
	// 좋아요
	public RecommendPoint countRecommendPont(int coulumnId) {
		return recommendDao.countRecommendPont(coulumnId);
	}
	
	
	public void insertRecommendPoint(int loginmemberId, int coulumnId) {
		recommendDao.insertRecommendPoint(loginmemberId, coulumnId);
	}

	public void deleteRecommendPoint(int loginmemberId, int coulumnId) {
		recommendDao.deleteRecommendPoint(loginmemberId, coulumnId);
	}
	
	//구독 관련
	public void deleteSubscribePoint(int loginedMemberId, int writerId) {
		recommendDao.deleteSubscribePoint(loginedMemberId, writerId);
		
	}
	public void insertSubscribePoint(int loginedMemberId, int writerId) {
		recommendDao.insertSubscribePoint(loginedMemberId, writerId);
	}
	public RecommendPoint getCheckRecommend(int loginedMemberId, int writerId) {
		return recommendDao.getCheckRecommend(loginedMemberId, writerId);
	}
	public RecommendPoint getChecklike(int loginedMemberId, int id) {
		return recommendDao.getChecklike(loginedMemberId, id);
	}
	
	
}