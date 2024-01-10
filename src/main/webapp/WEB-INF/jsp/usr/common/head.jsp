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
<!-- 공통 css -->
<link rel="stylesheet" href="/resource/common.css" />
<!-- dompurify -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/dompurify/2.3.0/purify.min.js"></script>
</head>

<script>
$(document).ready(function () {
	var didScroll;
	var lastScrollTop = 0;
	var delta = 5;
	var navbarHeight = $('#header').outerHeight();

	$(window).scroll(function(event){
	    didScroll = true;
	});

	setInterval(function() {
	    if (didScroll) {
	        hasScrolled();
	        didScroll = false;
	    }
	}, 250);

	function hasScrolled() {
	    var st = $(this).scrollTop();
	    
	    // Make sure they scroll more than delta
	    if(Math.abs(lastScrollTop - st) <= delta)
	        return;
	    
	    // If they scrolled down and are past the navbar, add class .nav-up.
	    // This is necessary so you never see what is "behind" the navbar.
	    if (st > lastScrollTop && st > navbarHeight){
	        // Scroll Down
	        $('#header').removeClass('nav-down').addClass('nav-up');
	    } else {
	        // Scroll Up
	        if(st + $(window).height() < $(document).height()) {
	            $('#header').removeClass('nav-up').addClass('nav-down');
	        }
	    }
	    
	    lastScrollTop = st;
	}
});

</script>

<body>
	<section id="header">
		<div class="site_name" id="site_name">
			<a href="/"><span id="logo_name">Agora</span>
			<img src="/resource/images/Temple4.png" alt="신전5" />
			</a>
		</div>
		<div>
			<ul class="menu_bar" id="menu_bar">
				<li class="menu_bar_item">칼럼
					<ul class="dropdown-content">
						<!-- href 고치면서 controller도 고쳐야함 -->
						<li><a href="/usr/article/list">전체칼럼</a></li>
						<c:if  test="${rq.getLoginedMemberId() != 0 }">
							<li><a href="/usr/article/list?type=2">구독한 작가 칼럼</a></li>
							<li><a href="/usr/article/list?type=3">좋아요 한 칼럼</a></li>
							<li><a href="/usr/article/list">추천 칼럼</a></li>
							<li><a href="/usr/article/write">칼럼 작성</a></li>
						</c:if>
					</ul>
				</li>
				<li class="menu_bar_item">토론방
					<ul class="dropdown-content">
						<!-- href 고치면서 controller도 고쳐야함 -->
						<li><a href="/usr/discussion/list">전체 토론방</a></li>
						<c:if  test="${rq.getLoginedMemberId() != 0 }">
						<li><a href="/usr/discussion/list"">추천 토론방</a></li>
						<li><a href="/usr/discussion/list">내 토론방</a></li>
							<li><a href="/usr/discussion/createroom">새로운 토론방 만들기</a></li>
						</c:if>
					</ul>
				</li>
				<c:if  test="${rq.getLoginedMemberId() != 0 }">
					<li class="menu_bar_item">책 검색
						<ul class="dropdown-content">
							<li><a href="/usr/book/search">책 검색</a></li>
						</ul>
					</li>
					<li class="menu_bar_item">마이페이지
						<ul class="dropdown-content">
							<li><a href="/usr/member/profile">내 회원정보</a></li>
							<li><a href="/usr/member/IdentityVerification">비밀번호 변경</a></li><!-- 이거.... 고민해야할듯. 이걸 그대로 오픈할지.말지 -> 변경전에 확인할꺼니까 상관없나?-> 본인 인증 페이지(비번 인증으로 하기) 만들어야할듯   -->
							<li><a href="/usr/member/withdraw">회원탈퇴</a></li>
						</ul>
					</li>
				</c:if>
			</ul>
		</div>
		<div class="flex-grow"></div>
		<div class="login-out-session mr-3">
			<ul>

				<c:if test="${rq.getLoginedMemberId() == 0 }">
					<li class="inline-block mr-2"><a href="/usr/member/join">회원가입</a></li>
					&nbsp;&nbsp;&nbsp;
					<li class="inline-block"><a href="/usr/member/login"><span>LOGIN</span></a></li>
				</c:if>
				<c:if test="${rq.getLoginedMemberId() != 0 }">
					<li class="inline-block"><a href="/usr/member/doLogout"><span>LOGOUT</span></a></li>
				</c:if>
			</ul>
		</div>
	</section>