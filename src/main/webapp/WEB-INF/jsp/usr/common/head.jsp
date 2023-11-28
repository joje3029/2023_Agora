<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle }</title>
<!-- 데이지ui cdn -->
<link
	href="https://cdn.jsdelivr.net/npm/daisyui@4.4.10/dist/full.min.css"
	rel="stylesheet" type="text/css" />
<!-- 테일윈드 치트시트 -->
<script src="https://cdn.tailwindcss.com"></script>
<!-- 제이쿼리 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- 폰트어썸 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<link rel="stylesheet" href="/resource/common.css" />
</head>
<body>
	<section id="header">
		<div class="site_name" id="site_name">
			<a href="/"><span id="logo_name">Agora</span><img
				src="/resource/static.image/free-icon-temple-3874642.png" alt="신전" /></a>
		</div>
		<div>
			<ul class="menu_bar" id="menu_bar">
				<li class="menu_bar_item">전체메뉴</li>
				<li class="menu_bar_item">칼럼
					<ul class="dropdown-content">
						<!-- href 고치면서 controller도 고쳐야함 -->
						<li><a href="/usr/article/list?boardId=1">전체칼럼</a></li>
						<li><a href="/usr/article/list?boardId=2">구독한 작가 칼럼</a></li>
						<li><a href="/usr/article/list?boardId=2">좋아요 한 칼럼</a></li>
					</ul>
				</li>
				<li class="menu_bar_item">토론방
					<ul class="dropdown-content">
						<!-- href 고치면서 controller도 고쳐야함 -->
						<li><a href="/usr/discussion/list?boardId=1">전체 토론방</a></li>
						<li><a href="/usr/discussion/list?boardId=2"">추천 토론방</a></li>
						<li><a href="/usr/discussion/list?boardId=2">내 토론방</a></li>
						<li><a href="/usr/discussion/createroom">새로운 토론방 만들기</a></li>
					</ul>
				</li>
				<li class="menu_bar_item">마이페이지
					<ul class="dropdown-content">
						<li><a href="/usr/member/join">회원가입</a></li>
						<li><a href="/usr/member/modify">회원정보수정</a></li>
						<li><a href="/usr/member/findId">아이디찾기</a></li>
						<li><a href="/usr/member/findPw">비밀번호찾기</a></li>
						<li><a href="/usr/member/chagePw">비밀번호수정</a></li>
						<li><a href="/usr/member/withdraw">회원탈퇴</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<div class="flex-grow"></div>
		<div class="login_out_session">
			<ul>

				<c:if test="${rq.getLoginedMemberId() == 0 }">
					<li><a href="/usr/member/login"><span>LOGIN</span></a></li>
				</c:if>
				<c:if test="${rq.getLoginedMemberId() != 0 }">
					<li><a href="/usr/member/doLogout"><span>LOGOUT</span></a></li>
				</c:if>
			</ul>
		</div>
	</section>





	<section class="my-3 text-2xl">
		<div class="container mx-auto px-3">
			<h1>${pageTitle }</h1>
			<!-- 이게 필요한데가 있고 아닌데가 있어서. 최종적으로 보고 수정어떻게 할지 보자. -->

		</div>
	</section>