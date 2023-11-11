package com.example.demo.util;

//클래스를 따로 뺀 이유 : 걍 필요하면 이클래스든 저클래스든 쓸라구
public class Util {
//		static 인 이유 : 선언하기 귀찮아서
	public static boolean isEmpty(String str) {
//			근데 trim 은 string 타입만 되니까. 형변환해야하는데 어자피 지금다 string인데..
		if (str == null || str.trim().length() == 0) {
//				애가 비었니를 묻고 있음. 여기는 비었다는 이야기
			return true;
		}
		return false;
	}

}
