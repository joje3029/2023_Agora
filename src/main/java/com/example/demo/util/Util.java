package com.example.demo.util;

public class Util {
	public static boolean empty(String str) {

		if (str == null) {
			return true;
		}

		return str.trim().length() == 0;
	}

	public static String f(String format, Object... args) {
		return String.format(format, args);
	}

	//이거 자체가 HistroyBack기능+ 메세지를 함.
	public static String jsHistroyBack(String msg) {
//		만들어서 하는 이유 : 유사한 상황이 많이 생길테니까.

		if (msg == null) {
			msg = ""; //null 에러 방지
		}

		return Util.f("""
					<script>
						const msg = '%s'.trim(); 

						if (msg.length > 0) {
							alert(msg);
						}

						history.back();
					</script>
				""", msg); // const msg 의 %s는 durl msg // script안에 histroy.back()을 넣어두었으므로 alert뜨고 나면 실행.
	}

	public static String jsReplace(String msg, String uri) {

		if (msg == null) {
			msg = "";
		}

		if (uri == null) {
			uri = "";
		}

		return Util.f("""
					<script>
						const msg = '%s'.trim();

						if (msg.length > 0) {
							alert(msg);
						}

						location.replace('%s');
					</script>
				""", msg, uri);
	}

}