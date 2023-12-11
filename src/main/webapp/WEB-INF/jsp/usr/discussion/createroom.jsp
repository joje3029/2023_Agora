<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<c:set var="pageTitle" value="토론방 만들기" />

<%@ include file="../common/head2.jsp"%>

<!-- 웹 소켓등 필요한거 때림 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<!-- sockjs-client : SockJS는 WebSocket을 시뮬레이트하는 JavaScript라이브러리. SockJS는 일반적인 웹 소켓 API와 유사한 API를 제공. WebSocket이 지원되지 않는 경우에도 안전하게 폴백 메커니즘을 통해 실시간양방향 통신을 구현할 수 있음. -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<!-- stomp.js : Stomp는 simple(or Streaming) Text Orientated messaging Protocol 의 약자. 메시지 기반 통신 프로토콜. Stomp.js는 WebSocket을 통해 Stomp 프로토콜을 사용하여 메시지를 교환하기 위한 JavaScrpt 클라이어늩 라이브러리 주로 웹 소켓을 통한 메시징 애플리케이션에서 사용됨. -->
<!-- 이 두라이브러리는 실시간 웹 애플리케이션에서 클라이언트와 서버 간의 양방향 통신을 구현하는 데 널리 사용도미. 일반적으로 spring Websocket과 같은 서버 측 라이브러리와 함꼐 사용되여 실시간 데이터 전송 및 메시징을 구현하는데 활용됨. -->
<script src="/resource/socket.js"></script><!--  웹 소켓 쓰려는거 : 누가들어왔다, 나갔다. 채팅 내용(데이지 UI 쓸꺼)등. -->


<script>

	$(document).ready(function() {
	    const roomNameInput = $('#roomName');
	    const maxLength = 50;
	
	    roomNameInput.on('input', function() {
	    	
	    	const roomNameInput = $(this).val();
	        
	        if (roomNameInput.length > maxLength) {
	            $(this).val(roomNameInput.slice(0, maxLength)); // 최대 길이까지만 잘라냄
	            alert('제목은 50자 이내여야 합니다.');
	        }
	    });
	});

	const createForm_onSubmit = function(form){
		form.roomName.value = form.roomName.value.trim();
		
		if(form.roomName.value.length ==0){
			alert('토론방이름을 입력해주세요');
			form.roomName.focus();
			return
		}
		
		form.submit(); 
	}
</script>

		<section class="login border">
	        <h1 class="text-4xl">토론방 만들기</h1>
	        <form action="docreateroom" method="post" onsubmit="createForm_onSubmit(this); return false;">
	            <table class="border">
	                <tr class="border">
	                    <th class="border">토론방 이름</th>
	                    <td><input id="roomName" name="roomName" class="input w-full max-w-xs input-sm" type="text" placeholder="토론방 이름을 입력해주세요"></td>
	                </tr>
	                <tr class="border">
	                    <th colspan="2">
	                        <label>
	                            <input type="radio" name="type" value="1" class="radio radio-success radio-xs" checked/>
	                            채팅 토론
	                        </label>
	                        &nbsp;
	                        <label>
	                            <input type="radio" name="type" value="2" class="radio radio-success radio-xs ">
	                            화상 토론
	                        </label>
	                    </th>
	                </tr>
	                <tr>
	                    <th colspan="2"><button class="btn border m-1">만들기</button></th>
	                </tr>
	            </table>
	        </form>
	    </section>
	</body>
</html>    
