<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />


<%@ include file="../common/head.jsp"%>
<!--  플로팅 배너  -->
<%@ include file="../common/floatingBanner.jsp"%>
<!-- 공통 css -->
<link rel="stylesheet" href="/resource/main.css" />


<!-- 캐러셀을 일단 안쓴 버전으로 퍼블리싱을 하자 => 캐러셀같은 프론트 단때문에 시간 많이 잡아먹지 말자! -->
<section class="main">
	<!-- 메인 페이지 환영 캐러셀 : 안녕 동물 친구들!! -->
	<section class="main-in-section w-full main-height">
		<div class="carousel w-full ">
			<div id="slide1" class="carousel-item relative w-full main-height">
					<img src="/resource/images/Lets_Read_A_Book_Together.jpg" class="main-image-height" />
				<div class="absolute flex justify-between transform -translate-y-1/2 left-5 right-5 top-1/2">
					<a href="#slide4" class="btn btn-circle">❮</a>
					<a href="#slide2" class="btn btn-circle">❯</a>
				</div>
			</div>
			<div id="slide2" class="carousel-item relative w-full main-height">
				<img src="/resource/images/Lets_Talk_Toggether.jpg" class="main-image-height" />
				<div class="absolute flex justify-between transform -translate-y-1/2 left-5 right-5 top-1/2">
					<a href="#slide1" class="btn btn-circle">❮</a>
					<a href="#slide3" class="btn btn-circle">❯</a>
				</div>
			</div>
			<div id="slide3" class="carousel-item relative w-full main-height">
				<img src="/resource/images/Express_Your_Thoughts.jpg" class="main-image-height" />
				<div class="absolute flex justify-between transform -translate-y-1/2 left-5 right-5 top-1/2">
					<a href="#slide2" class="btn btn-circle">❮</a>
					<a href="#slide4" class="btn btn-circle">❯</a>
				</div>
			</div>
			<div id="slide4" class="carousel-item relative w-full main-height">
				<img src="/resource/images/Book_animal.jpg" class="main-image-height" />
				<div class="absolute flex justify-between transform -translate-y-1/2 left-5 right-5 top-1/2">
					<a href="#slide3" class="btn btn-circle">❮</a>
					<a href="#slide1" class="btn btn-circle">❯</a>
				</div>
			</div>
		</div>
	</section>
	<!-- 로그인 안했을때 -->	
	<c:if test="${rq.getLoginedMemberId() == 0 }">
		<section class="main-in-section">
			<div class="section-title text-2xl">랭킹 칼럼</div>
			<section class="items">
				<div class="item">
					<div class="title">칼럼제목1</div>
					<div class="writer">작성자1</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목2</div>
					<div class="writer">작성자2</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목3</div>
					<div class="writer">작성자3</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목4</div>
					<div class="writer">작성자4</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목5</div>
					<div class="writer">작성자5</div>
				</div>
			</section>
		</section>
	
			<section class="main-in-section text-2xl">
			<div class="section-title">랭킹 토론방</div>
			<section class="items">
				<div class="item">
					<div class="title">칼럼제목1</div>
					<div class="writer">작성자1</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목2</div>
					<div class="writer">작성자2</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목3</div>
					<div class="writer">작성자3</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목4</div>
					<div class="writer">작성자4</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목5</div>
					<div class="writer">작성자5</div>
				</div>
			</section>
		</section>
	
		<section class="main-in-section text-2xl">
			<div class="section-title text-2xl">분야별 칼럼</div>
			<section class="table-section">
				<div class="table-row">
					<div class="table-item">철학</div>
					<div class="table-item">종교</div>
					<div class="table-item">사회과학</div>
				</div>
				<div class="table-row">
					<div class="table-item">자연과학</div>
					<div class="table-item">기술과학</div>
					<div class="table-item">예술</div>
				</div>
				<div class="table-row">
					<div class="table-item">언어</div>
					<div class="table-item">문학</div>
					<div class="table-item">역사</div>
				</div>
			</section>
		</section>
		
	</c:if>
	
	<!-- 로그인 했을때 -->	
	<c:if test="${rq.getLoginedMemberId() != 0 }">
		<section class="main-in-section">
			<div class="section-title text-2xl">추천 칼럼</div>
			<section class="items">
				<div class="item">
					<div class="title">칼럼제목1</div>
					<div class="writer">작성자1</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목2</div>
					<div class="writer">작성자2</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목3</div>
					<div class="writer">작성자3</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목4</div>
					<div class="writer">작성자4</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목5</div>
					<div class="writer">작성자5</div>
				</div>
			</section>
		</section>
	
			<section class="main-in-section text-2xl">
			<div class="section-title">추천 토론방</div>
			<section class="items">
				<div class="item">
					<div class="title">칼럼제목1</div>
					<div class="writer">작성자1</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목2</div>
					<div class="writer">작성자2</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목3</div>
					<div class="writer">작성자3</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목4</div>
					<div class="writer">작성자4</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목5</div>
					<div class="writer">작성자5</div>
				</div>
			</section>
		</section>
	
		<section class="main-in-section">
			<div class="section-title text-2xl">분야별 칼럼</div>
			<section class="table-section">
				<div class="table-row">
					<div class="table-item">철학</div>
					<div class="table-item">종교</div>
					<div class="table-item">사회과학</div>
				</div>
				<div class="table-row">
					<div class="table-item">자연과학</div>
					<div class="table-item">기술과학</div>
					<div class="table-item">예술</div>
				</div>
				<div class="table-row">
					<div class="table-item">언어</div>
					<div class="table-item">문학</div>
					<div class="table-item">역사</div>
				</div>
			</section>
		</section>
		
		<section class="main-in-section">
			<div class="section-title text-2xl ">추천 책</div>
			<section class="items">
				<div class="item">
					<div class="title">칼럼제목1</div>
					<div class="writer">작성자1</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목2</div>
					<div class="writer">작성자2</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목3</div>
					<div class="writer">작성자3</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목4</div>
					<div class="writer">작성자4</div>
				</div>
				<div class="item">
					<div class="title">칼럼제목5</div>
					<div class="writer">작성자5</div>
				</div>
			</section>
		</section>
	</c:if>

</section>

<%@ include file="../common/foot.jsp"%>