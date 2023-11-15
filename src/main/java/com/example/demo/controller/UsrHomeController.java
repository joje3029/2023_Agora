
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsrHomeController {
	int a;
	
	UsrHomeController(){
		this.a=0;
	}
	
	@RequestMapping("/usr/home/main") 
	public String showMain() {
		return "usr/home/main"; 
	}
}
