<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="../common/head.jsp"%>
<link rel="stylesheet" href="/resource/main.css" />

<section class="login">
	<h1 class="text-4xl bg-green-100 px-3">책으로 소통을!!</h1>
	<hr class="border-green-100 mb-2">
	<div class="content-section">
		<h2 class="text-2xl mb-2 px-3">Agora를 만들게 된 이유</h2>
		<p>Agora는</p>
		<p>
			<i class="fa-regular fa-circle-1"></i>현존하는 book 커뮤니티 서비스들의 공간적, 시간적
			제약을 깨고 싶다.
		</p>
		<p>
			<i class="fa-regular fa-circle-2"></i>신작 / 대중적인 책이 아닌 마이너 책로 이야기할수
			없을까?
		</p>
		<p>
			<i class="fa-regular fa-circle-3"></i>사람들이 더 편하게 많은 품을 안들이고도 관심사가 맞는
			사람과 대화를 할수 었을까?
		</p>
		<p>라는 생각에서 시작되었습니다.</p>
	</div>
	<div class="content-section">
		<h2 class="text-2xl mb-2 px-3">Agora가 하는 일</h2>
		<p>
			<i class="fa-regular fa-circle-1"></i>칼럼을 읽고 쓸 수 있습니다 아래에 댓글을 달아 그
			칼럼에 대한 생각을 달 수 있어요
		</p>
		<p>
			<i class="fa-regular fa-circle-2"></i>토론방을 개설할수 있습니다. 채팅 / 화상으로 토론방을
			만들수 있어요! 시간적 공간적 제약 없이 일정한 토론 모임을 하실수 있습니다
		</p>
		<p>
			<i class="fa-regular fa-circle-3"></i>당신의 관심사인 책/ 칼럼 /토론방을 추천해요! 많은
			사람, 더 다양한 책을 추천할께요! 더 많이 즐겨주세요!
		</p>
	</div>
	<div class="content-section">
		<h2 class="text-2xl mb-2 px-3">개발자이자 주인장</h2>
		<p>2023년 3월부터 개발공부를 시작한 이제막 부화한 병아리 개발자입니다!</p>
		<p>앞으로 많은걸 배워서 더 다양하고 편리한 서비스를 만들고 싶습니다!</p>
		<p>아고라가 그 시작입니다!</p>
		<div class="avatar">
			<div class="w-24 rounded-full">
				<img class="rotate-center" src="/resource/static.image/mascot.png"
					alt="뱁새_마스코트" />
			</div>
		</div>
		<p class="scale-up-center">박소민</p>
		<div class="img-div">
			<img class="chick-img"
				src="https://cdn.pixabay.com/photo/2014/03/25/15/22/tracks-296631_1280.png"
				alt="병아리 발자국">
		</div>
		<div class="border">test
			<img src="/resource/static.image/favicon.ico" alt="파비콘 테스트" />
		</div>
	</div>
</section>
<%@ include file="../common/foot.jsp"%>