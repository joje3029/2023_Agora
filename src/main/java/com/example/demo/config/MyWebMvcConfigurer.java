package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.BeforActionInterceptor;
import com.example.demo.interceptor.NeedLoginInterceptor;
import com.example.demo.interceptor.NeedLogoutInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	private BeforActionInterceptor beforeActionInterceptor;
	private NeedLoginInterceptor needLoginInterceptor;
	private NeedLogoutInterceptor needLogoutInterceptor;

	public MyWebMvcConfigurer(BeforActionInterceptor beforeActionInterceptor, NeedLoginInterceptor needLoginInterceptor,
			NeedLogoutInterceptor needLogoutInterceptor) {
		this.beforeActionInterceptor = beforeActionInterceptor;
		this.needLoginInterceptor = needLoginInterceptor;
		this.needLogoutInterceptor = needLogoutInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration ir;
		ir = registry.addInterceptor(beforeActionInterceptor);
		ir.addPathPatterns("/**");
		ir.addPathPatterns("/favicon.ico"); //이건 파비콘이니까 일단 보류
		ir.excludePathPatterns("/rosource/**");

		// 로그인이 필요한 곳 -> 엑셀 인터셉터 구분에서 주황색
		ir = registry.addInterceptor(needLoginInterceptor);
		ir.addPathPatterns("/usr/article/write"); // 칼럼 쓰기관련
		ir.addPathPatterns("/usr/article/doWrite"); // 칼럼 쓰기관련
		ir.addPathPatterns("/usr/article/modify"); // 칼럼 수정관련
		ir.addPathPatterns("/usr/article/doModify"); // 칼럼 수정관련
		ir.addPathPatterns("/usr/article/doDelete"); // 칼럼 삭제 관련
		ir.addPathPatterns("/usr/member/doLogout"); // 로그아웃
		ir.addPathPatterns("/usr/member/modify"); // 회원정보 수정 관련
		ir.addPathPatterns("/usr/member/doModify"); // 회원정보 수정 관련
		ir.addPathPatterns("/usr/member/withdraw"); // 회원 탈퇴 관련
		ir.addPathPatterns("/usr/member/dowithdraw"); // 회원 탈퇴 관련
		ir.addPathPatterns("/usr/customer/customercenter"); // 1:1 고객상담센터
		ir.addPathPatterns("/usr/discussion/createroom"); // 토론방 생성관련
		ir.addPathPatterns("/usr/discussion/chat"); // 채팅 토론방 생성관련
		ir.addPathPatterns("/usr/discussion/cam"); // 화상 토론방 생성 관련
		// 지금 댓글과 대댓글 없음 -> 기능 만들면서 경로 추가해야함.

		// 로그아웃이여야하는곳-> 엑셀 인터셉터 구분에서 초록색
		ir = registry.addInterceptor(needLogoutInterceptor);
		ir.addPathPatterns("/usr/member/join");
		ir.addPathPatterns("/usr/member/doJoin");
		ir.addPathPatterns("/usr/member/login");
		ir.addPathPatterns("/usr/member/doLogin");
		ir.addPathPatterns("/usr/member/findId");
		ir.addPathPatterns("/usr/member/dofindId");
		ir.addPathPatterns("/usr/member/findPw");
		ir.addPathPatterns("/usr/member/dofindPw");
		ir.addPathPatterns("/usr/member/login");
		ir.addPathPatterns("/usr/member/doLogin");
	}

}