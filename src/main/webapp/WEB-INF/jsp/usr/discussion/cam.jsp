<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<c:set var="pageTitle" value="${board.name }" />

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
		<section class="cam-section">
			<div class="cam-row flex justify-around">
				<!--지금은 화상창 박아둠. 나중에는 이게 참가자 만큼 생겨야함-->
				<div class="cam-item flex flex-col">
					<div class="artboard artboard-horizontal phone-1 bg-blue-400">320×568</div>
					<div class="text-center">화상창 주인 닉네임</div>
				</div>
				<div class="cam-item flex flex-col">
					<div class="artboard artboard-horizontal phone-1 bg-yellow-400">320×568</div>
					<div class="text-center">화상창 주인 닉네임</div>
				</div>
			</div>
		</section>
		<section class="setting-section border flex justify-around mt-10">
			<div class="form-control">
				<label class="label cursor-pointer">
				  <span class="label-text">화상캠 on</span> 
				  <input type="checkbox" class="toggle" checked />
				  <span class="label-text">화상캠 off</span>
				</label>
			  </div>
			  <div class="form-control">
				<label class="label cursor-pointer">
				  <span class="label-text">마이크 on</span> 
				  <input type="checkbox" class="toggle" checked />
				  <span class="label-text">마이크 off</span>
				</label>
			  </div>
		</section>
    </section>
	</body>
</html>
