<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<c:set var="pageTitle" value="채팅 토론방" />

<!-- head2로 할지 head로 할지 정해야함. -->
<%@ include file="../common/head2.jsp"%>

<!-- 웹 소켓등 필요한거 때림 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<!-- sockjs-client : SockJS는 WebSocket을 시뮬레이트하는 JavaScript라이브러리. SockJS는 일반적인 웹 소켓 API와 유사한 API를 제공. WebSocket이 지원되지 않는 경우에도 안전하게 폴백 메커니즘을 통해 실시간양방향 통신을 구현할 수 있음. -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<!-- stomp.js : Stomp는 simple(or Streaming) Text Orientated messaging Protocol 의 약자. 메시지 기반 통신 프로토콜. Stomp.js는 WebSocket을 통해 Stomp 프로토콜을 사용하여 메시지를 교환하기 위한 JavaScrpt 클라이어늩 라이브러리 주로 웹 소켓을 통한 메시징 애플리케이션에서 사용됨. -->
<!-- 이 두라이브러리는 실시간 웹 애플리케이션에서 클라이언트와 서버 간의 양방향 통신을 구현하는 데 널리 사용도미. 일반적으로 spring Websocket과 같은 서버 측 라이브러리와 함꼐 사용되여 실시간 데이터 전송 및 메시징을 구현하는데 활용됨. -->
<script src="/resource/socket.js"></script><!--  웹 소켓 쓰려는거 : 누가들어왔다, 나갔다. 채팅 내용(데이지 UI 쓸꺼)등. -->


		<section class="listBody">
			<div id="connect" class="bg-gray-200 text-center text-2xl py-2">
        		연결중..
        	</div>
			<section class="top border flex justify-between items-center">
				<div class="room-name w-11/12 text-center font-semibold text-5xl  p-4">${discussionRoom.dscsnRoomNm }</div>
				<!-- <div class="w-16 border text-center"><a href="/usr/discussion/list"> 나가기</a></div>  -->
				<button id="exit-button" class="btn text-center mr-2">나가기</button>
			</section>
			<section class="personnel-section my-2 border">
				<div>
					<ul>
						<li><i class="fa-solid fa-crown"></i> ${discussionRoom.crtrId }-이건 방장 참가 인원 이름들 주르륵 <!-- innerjoin 해서 이름 가져오게 바꾸기 -->  </li>
					</ul>
				 인원 : 인원수(DB에서 받아오기)
				</div><!-- 이건 연결되기 전에 컨트롤러에서 DB갔다와서 model로 해줘야함 -->
				<div>참가 인원 닉네임 표시될 부분</div><!-- 이건 연결되기 전에 컨트롤러에서 DB갔다와서 model로 해줘야함 -->
			</section>
			
			<section class="chat-body border"> <!-- 즉 여기가 병호님으로치면 id가 chat인 곳이여  -->
			<!-- 아래 hidden input 3개 잘 넘어오는거 type text로 바꿔서 확인함 -->
				<input type="hidden" id="member-id" value="${member.id}"><!--유저놈 번호-->
				<input type="hidden" id="member-nickname" value="${member.nickname}"><!--유저놈 닉네임--, 이거 지금 안넘어옴!>
				<input type="hidden" id="host-member-id" value="${discussionRoom.crtrId}"> <!-- 방장시키 정보 => 유저가 방장이냐 아니냐 볼라고-->
				<div class="flex justify-center chat-height">
					<ul id="message-area"></ul><!-- 실제 챗 들어올 부분 -->
				</div>
			</section>
			
			<section class="in-chat">
				<div class="mt-4 border rounded-lg p-4">
					<form id="message-form ">
					 	<input type="hidden" id="roomId" name="discussionId" value="${discussionRoom.id}">
						<textarea id="message-input" class="textarea textarea-bordered w-full"
							placeholder="의견을 말해주세요!" autocomplete="off"></textarea>
						<div class="flex justify-end">
							<button class="btn btn-sm">입력</button>
						</div>
					</form>
				</div>
			</section>
		</section>
	</body>
</html>
