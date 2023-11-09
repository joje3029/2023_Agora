//-> doWrite 처리후 화면에는 생성된 객체 보여주기
//

package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.Article;

@Controller
public class UsrArticleController {
//	List<Article> artilces= new ArrayList<>(); 내가 한거. 틀린 이유 : 우리는 그냥 초기화를 바로 안하기로 했으니까. 나는 지금 그냥 바로 초기화를 하고 있으니까.
	
	private List<Article> artilces; // 선언만 한것!
	private int lastAritlceID;
//	UrArticleController(String title, String body){ // 바로 초기화 안하려고 생성자를 만들어놓고 위에 초기화를 하냐!! 그리고 파라미터를 Article이 받으면 되는거 알면서 여따가 쳐넣냐!!
//		this.artilces=new ArrayList<>();
//	}
	
	UsrArticleController(){
		this.artilces=new ArrayList<>();
		this.lastAritlceID=0;
	}
	
	@RequestMapping("/usr/ariicle/dowrite")
	@ResponseBody
	public String dowrite(String title, String body) { //그리고 dowrite 매소드가 파라미터(매개변수)를 보내야 아래의 Artilce이 받아서 쓰지 임마!! 다 아는거면서 다까묵었구만!!
		lastAritlceID++; //왜냐면 Aritlce은 0번부터 시작하지 않으니까.
		Article article = new Article(lastAritlceID, title, body);
		artilces.add(article);
		return "뱁새씨";
	}
	
	@RequestMapping("/usr/ariicle/showList")
	@ResponseBody
	public List<Article> showList() {
		//dowrite에서 한거 보여주면 됨 => articles라는 ArrayList 보여줌.
		return this.artilces;
	}
}
