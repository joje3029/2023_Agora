package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.RecommendService;
import com.example.demo.vo.RecommendPoint;
import com.example.demo.vo.Rq;

@Controller
public class UsrRecommendPointController {
	
	private RecommendService recommendService;
	private Rq rq;
	
	UsrRecommendPointController(RecommendService recommendService, Rq rq){
		this.recommendService = recommendService;
		this.rq =rq;
	}
	
	// 좋아요 버튼을 누르면 ajax에서 보내는 url
	@RequestMapping("/usr/recommendPoint/doRecommendPoint")
	@ResponseBody
	public Map<String, Object> doRecommendPoint(int coulumnId, boolean recommendBtn) { //recommendBtn은 true/false가 오니까.
		
		Map<String, Object> data =new HashMap<>();
		
		if (recommendBtn) {
			recommendService.deleteRecommendPoint(rq.getLoginedMemberId(), coulumnId);
			// 좋아요 갯수 세야함
			RecommendPoint recommendPoint=recommendService.countRecommendPont(coulumnId);
			
			data.put("result", "좋아요 취소");
			data.put("recommendPoint", recommendPoint);
			
			return data;
		}
		
		recommendService.insertRecommendPoint(rq.getLoginedMemberId(), coulumnId); // 좋아요 칼럼에 insert 잘하고 있음.
		// 좋아요 갯수 세야함
		RecommendPoint recommendPoint=recommendService.countRecommendPont(coulumnId);
		
		data.put("result", "좋아요 성공");
		data.put("recommendPoint", recommendPoint);
		
		return data; // 그후 return을 좋아요로 내뱉음 
	}
	
	// 구독 버튼을 누르면 ajax에서 보내는 url
		@RequestMapping("/usr/recommendPoint/doSubscribePoint")
		@ResponseBody
		public String doSubscribePoint(int writerId, boolean recommendBtn) { //recommendBtn은 true/false가 오니까.
			if (recommendBtn) {
				recommendService.deleteSubscribePoint(rq.getLoginedMemberId(), writerId);
				return "구독 취소";
			}
			recommendService.insertSubscribePoint(rq.getLoginedMemberId(), writerId); // 좋아요 칼럼에 insert 잘하고 있음.
			return "구독 성공"; // 그후 return을 좋아요로 내뱉음 
		}
	
}