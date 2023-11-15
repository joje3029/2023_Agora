package com.example.demo.util;

public class Util {
	public static boolean empty(String str) {
		
		if (str == null) {
			return true;
		}
		
		return str.trim().length() == 0;
	}

	//여기저기서 형식 맞출때 쓰려고 public static
	public static String f(String format, Object... args) {
		return String.format(format, args);
		// 형식을 맞추려고 String.format을 함.
	}
	
}