<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html data-theme=lemonade>
<head>
<meta charset="UTF-8">
<title>${pageTitle }</title>
<!-- 파비콘 -->
<link rel="shortcut icon" href="/resource/images/favicon.ico" />
<!-- 데이지ui cdn -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@4.4.10/dist/full.min.css" rel="stylesheet" type="text/css" />
<!-- 테일윈드 치트시트 -->
<script src="https://cdn.tailwindcss.com"></script>
<!-- 제이쿼리 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- 폰트어썸 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<!-- 공통 css -->
<link rel="stylesheet" href="/resource/common.css" />

<!-- dompurify -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/dompurify/2.3.0/purify.min.js"></script>
</head>

<!-- 운영자 메뉴부분 -> head -->
<body>
	<section id="admin_header">
		<div class="site_name flex justify-center mt-2" id="site_name">
			<span id="logo_name">Agora</span>
			<img class="h-10" src="/resource/images/Temple4.png" alt="신전5" />
		</div>
		<div class="menu_bar">
			<ul>
				<li class="menu_bar_item"><a href="main">관리자 메인화면</a></li>
				<li class="menu_bar_item"><a href="userlist">회원조회</a></li>
				<!--<li class="menu_bar_item"><a href="marketingNew">사이트 통계</a></li> -->
				<li class="menu_bar_item"><a href="marketingNew">사이트 통계</a></li>
				<!--마케팅 데이터 화면 plot을 붙이든. 토스트 ui 에서 제공하는 api를 쓰든 할꺼임-->
				<li class="menu_bar_item"><a href="centerList">고객상담 확인</a></li>
				<li class="menu_bar_item"><a href="dologout">로그아웃</a></li>
			</ul>
		</div>
	</section>
