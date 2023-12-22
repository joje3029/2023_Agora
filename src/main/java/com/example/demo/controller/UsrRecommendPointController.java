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
	
	// 좋아요 버튼을 누르면 ajax에서 보내는 url
	@RequestMapping("/usr/recommendPoint/doRecommendPoint")
	@ResponseBody
	public String doRecommendPoint(int coulumnId, boolean recommendBtn) { //recommendBtn은 true/false가 오니까.
		System.out.println("coulumnId : "+coulumnId); // 칼럼 id잘 들어오고
		System.out.println("recommendBtn : "+recommendBtn); //false임
		System.out.println("좋아요 지나가나요?"); // 지나갑니다!
		if (recommendBtn) {
			System.out.println("true이면 여기인가요?");
			
			recommendService.getRecommendPoint(rq.getLoginedMemberId(), coulumnId);
			return "좋아요 취소";
		}
		
		System.out.println("false이면 여기인가요?");
		
		recommendService.insertRecommendPoint(rq.getLoginedMemberId(), coulumnId); // 좋아요 칼럼에 insert 잘하고 있음.
		return "좋아요 성공"; // 그후 return을 좋아요로 내뱉음 
	}
	
}