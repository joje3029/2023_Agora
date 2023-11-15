
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	int a;
	
	UsrHomeController(){
		this.a=0;
	}
	
	@RequestMapping("/usr/home/main") //요청을 보내면 여기서 받아서
//	@ResponseBody // ResponseBody는 controller에서 직접 화면을 그리려고 설정한것 그래서 jsp에게 화면을 그리게 할꺼면 넣으면 안됨.
	public String showMain() {
		return "usr/home/main"; //jsp에게 리턴 => yml에서 설정한 prefix와 suffix가 붙어서 : /WEB-INF/jsp/usr/home/main.jsp로 찾아감.
	}
}
