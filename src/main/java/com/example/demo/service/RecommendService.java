package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.RecommendDao;
import com.example.demo.vo.RecommendPoint;
import com.example.demo.vo.ResultData;

@Service
public class RecommendService {

	private RecommendDao recommendDao;
	
	public RecommendService(RecommendDao recommendDao) {
		this.recommendDao = recommendDao;
	}

	public ResultData<RecommendPoint> getRecommendPoint(int loginmemberId, int coulumnId) {
		
		System.out.println(2);
		
		RecommendPoint recommendPoint = recommendDao.getRecommendPoint(loginmemberId, coulumnId);
		
		System.out.println(3);
		
		if (recommendPoint == null) {
			
			System.out.println(4);
			
			return ResultData.from("F-1", "좋아요 기록 없음");
		}
		
		System.out.println(5);
		return ResultData.from("S-1", "좋아요 기록 있음", recommendPoint);
	}

	public void insertRecommendPoint(int loginmemberId, int coulumnId) {
		recommendDao.insertRecommendPoint(loginmemberId, coulumnId);
	}

	public void deleteRecommendPoint(int loginmemberId, int coulumnId) {
		recommendDao.deleteRecommendPoint(loginmemberId, coulumnId);
	}

}