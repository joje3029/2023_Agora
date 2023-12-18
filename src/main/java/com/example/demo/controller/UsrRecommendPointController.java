package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.RecommendService;
import com.example.demo.vo.RecommendPoint;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

@Controller
public class UsrRecommendPointController {
	
	private RecommendService recommendService;
	private Rq rq;
	
	UsrRecommendPointController(RecommendService recommendService, Rq rq){
		this.recommendService = recommendService;
		this.rq =rq;
	}
	
	@RequestMapping("/usr/recommendPoint/getRecommendPoint")
	@ResponseBody
	public ResultData<RecommendPoint> getRecommendPoint(String relTypeCode, int coulumnId) {
		
		System.out.println(1);
		
		return recommendService.getRecommendPoint(rq.getLoginedMemberId(), coulumnId);
	}
	
	@RequestMapping("/usr/recommendPoint/doRecommendPoint")
	@ResponseBody
	public String doRecommendPoint(int coulumnId, boolean recommendBtn) {
		
		if (recommendBtn) {
			recommendService.getRecommendPoint(rq.getLoginedMemberId(), coulumnId);
			return "좋아요 취소";
		}
		
		recommendService.insertRecommendPoint(rq.getLoginedMemberId(), coulumnId);
		return "좋아요 성공";
	}
	
}