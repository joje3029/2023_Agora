<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<!--<c:set var="pageTitle" value="${board.name }" />-->

<!-- head2로 할지 head로 할지 정해야함. -->
<%@ include file="../common/head2.jsp"%>


		<section class="listBody">
			<section class="top border flex justify-between">
				<div class="room-name w-11/12 text-center">토론방 이름</div>
				<div class="w-16 border text-center">나가기</div>
			</section>
			<section class="personnel-section my-2 border">
				<div>인원 : 인원수(DB에서 받아오기)</div>
				<div>참가 인원 닉네임 표시될 부분</div>
			</section>
			<section class="chat-body border">
				<!--데이지 ui에서 class 이걸로 써서 채팅할꺼임.-->
				<!--말하는놈 이름이랑 시간이 예처럼 보여야함-->
				<!--클래스에 색 추가해서 이 칙칙한 검정이 바꿀수 있음 상큼한 초록이로 바꿀꺼야!!!-->
				<!--채팅방 배경이미지도 넣어야지!!-->
				<!--배경은 이미지 넣고 나서 위에 채팅부분이랑 배경이미지 위치를 겹치고 나서 채팅만 position relative로 한뒤에 z-index로 하면 안되나?-->
				<div class="chat chat-start">
					<font></font>
					<div class="chat-header">
						<font></font> Obi-Wan Kenobi<font></font>
						<time class="text-xs opacity-50">2 hours ago</time>
						<font></font>
					</div>
					<font></font>
					<div class="chat-bubble backcground">You were the Chosen One!</div>
					<font></font>
				</div>
				<font></font>
				</div>
				<font></font>
				<div class="chat chat-start">
					<font></font>
					<div class="chat-header">
						<font></font> Obi-Wan Kenobi<font></font>
						<time class="text-xs opacity-50">2 hour ago</time>
						<font></font>
					</div>
					<font></font>
					<div class="chat-bubble">I loved you.</div>
					<font></font>
				</div>
				<font></font>
				</div>
			</section>
			<section class="in-chat">
				<div class="mt-4 border border-gray-400 rounded-lg p-4">
					<textarea class="textarea textarea-bordered w-full" name="body"
						placeholder="의견을 말해주세요!"></textarea>
					<div class="flex justify-end">
						<button class="btn-text-color btn btn-outline btn-sm">입력</button>
					</div>
				</div>
			</section>
		</section>
	</body>
</html>
