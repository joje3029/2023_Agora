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
    // 헤더 숨김/나타남 변수들
    var didScroll = false; //스크롤 여부
    var lastScrollTop = 0; //이전스크롤 위치
    var delta = 5; //스크롤을 감지하는 정도조절
    var navbarHeight = $('header').outerHeight(); //헤더의 높이 저장. DOM 요소의 크기를 계산할때 유용
    //outerHeight()는 해당 요소의 높이를 반환. -> $('header').outerHeight() : header태그의 전체 높이 반환.

    // 스크롤 이벤트 리스너 : 스크롤이벤트가 발생할떄마다 hasScrolled함수 호출
    $(window).scroll(function () {
        didScroll = true;
        hasScrolled();
    });

    // 스크롤 이벤트 핸들러 최적화
    function hasScrolled() {
        if (!didScroll) return;
        
        var st = $(this).scrollTop(); //현재 스크롤 위치를 st에 저장.
                        //scrollTop()메서드는 jQuery에서 제공하는 메서드. 문서나 요소의 상단에서 수직으로 스크롤 된 거리를 반환함. 스크롤바가 상단으로부터 얼마나 내려갔는지 나타내는 값 제공.
        if (Math.abs(lastScrollTop - st) > delta) { //이전 스크롤 위치 - 현 스크롤 위치차 > delta(==5)
            if (st > lastScrollTop && st > navbarHeight) { //스크롤 방향 및 위치에 따라 헤더의 클래스를 추가/제거 하여 나타남/숨김
                $('#header').removeClass('nav-down').addClass('nav-up');
            } else {
                if (st + $(window).height() < $(document).height()) {
                    $('#header').removeClass('nav-up').addClass('nav-down');
                }
            }
            lastScrollTop = st; // lastScrollTop에 현 위치 담기.
        }

        didScroll = false; //did Scroll 초기화
    }

    // 창 크기 변경 이벤트가 발생하면 헤더의 클래스를 추가/제거해서 초기 상태로 복원
    $(window).on("resize", function () {
        $('#header').removeClass('nav-up').addClass('nav-down');
    });
});

</script>

<body>
	<section id="header">
		<div class="site_name" id="site_name">
			<a href="/"><span id="logo_name">Agora</span><img
				src="/resource/images/free-icon-temple-3874642.png" alt="신전" /></a>
		</div>
		<div>
			<ul class="menu_bar" id="menu_bar">
				<li class="menu_bar_item">전체메뉴</li>
				<li class="menu_bar_item">칼럼
					<ul class="dropdown-content">
						<!-- href 고치면서 controller도 고쳐야함 -->
						<li><a href="/usr/article/list">전체칼럼</a></li>
						<li><a href="/usr/article/list">구독한 작가 칼럼</a></li>
						<li><a href="/usr/article/list">좋아요 한 칼럼</a></li>
						<li><a href="/usr/article/write">칼럼 작성</a></li>
					</ul>
				</li>
				<li class="menu_bar_item">토론방
					<ul class="dropdown-content">
						<!-- href 고치면서 controller도 고쳐야함 -->
						<li><a href="/usr/discussion/list">전체 토론방</a></li>
						<li><a href="/usr/discussion/list"">추천 토론방</a></li>
						<li><a href="/usr/discussion/list">내 토론방</a></li>
						<li><a href="/usr/discussion/createroom">새로운 토론방 만들기</a></li>
					</ul>
				</li>
				<c:if  test="${rq.getLoginedMemberId() != 0 }">
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
					<li class="inline-block"><a href="/usr/member/login"><span>LOGIN</span></a></li>
				</c:if>
				<c:if test="${rq.getLoginedMemberId() != 0 }">
					<li class="inline-block"><a href="/usr/member/doLogout"><span>LOGOUT</span></a></li>
				</c:if>
			</ul>
		</div>
	</section>