<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 여기 페이지는 뭔가를 그리기 위해서가 아니라 안됩니다를 alert으로 보여주려고 만든 페이지: 그래서 다른 html 없음. -->
<script>
	let msg = '${msg}'.trim(); //메세지 받은걸. js 변수에 담아서
	
	//falsy : 이거 관련해서는 본인 블로그 참고
	if (msg) {
		alert(msg); //안된다 alert띄우고 
	}
	history.back();	//이전화면으로 돌림.
</script>