//-> doWrite 처리후 화면에는 생성된 객체 보여주기
//

package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.Article;

@Controller//어노테이션이 달림과 동시에 UsrArticleController 객체를 스프링이 만듦 : DI, IOC
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
		
		//테스트 데이터를 만들고 그 테스트 데이터를 생성자 안에서 호출 : 왜냐면 생성자는 생성이 됨과 동시에 만들어지니까. 그만 잊어먹어 지지배야!
		TestData();
	}
	
	//테스트데이터 스스로 만듦. 잘했어! 성공도 했어! 오오!
	private void TestData() {
		int limitNum=10;
		for(int i=1; i<=limitNum; i++) {
			String title = "제목"+i;
			String body = "내용"+i;
			addArticle(title,body);
		}
		
}
//쌤 버전은 추가되는것도 코드가 중복되니까 메서드로 뺌. => 이점 : 관리하기가 좋음.
	private Article addArticle(String title, String body) {
		lastAritlceID++; //왜냐면 Aritlce은 0번부터 시작하지 않으니까.
		Article article = new Article(lastAritlceID, title, body);
		this.artilces.add(article);
		return article;
		
	}

	@RequestMapping("/usr/article/dowrite")
	@ResponseBody
	public Article dowrite(String title, String body) { //그리고 dowrite 매소드가 파라미터(매개변수)를 보내야 아래의 Artilce이 받아서 쓰지 임마!! 다 아는거면서 다까묵었구만!!
		Article article = addArticle(title,body);
		return article;
	}
	
	@RequestMapping("/usr/article/showList")
	@ResponseBody
	public List<Article> showList() {
		//dowrite에서 한거 보여주면 됨 => articles라는 ArrayList 보여줌.
		return this.artilces;
	}
}
