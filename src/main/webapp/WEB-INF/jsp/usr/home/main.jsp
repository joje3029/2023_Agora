<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />


<%@ include file="../common/head.jsp"%>
<!--  플로팅 배너  -->
<%@ include file="../common/floatingBanner.jsp"%>
<!-- 공통 css -->
<link rel="stylesheet" href="/resource/main.css" />

 <style>
    @keyframes crossfadeImages {
      0%, 100% {
        opacity: 0;
      }
      25% {
        opacity: 1;
      }
      75% {
        opacity: 0;
      }
    }

    .image-container {
      width: 100%;
      height: 100%;
      position: relative;
      overflow: hidden;
    }

    .image-container img {
	  width: auto;
	  height: 100%;
	  position: absolute;
	  top: 0;
	  left: 50%;
	  transform: translateX(-50%);
	  opacity: 0;
	  animation: crossfadeImages 30s infinite;
	}

    .image-container img:nth-child(1) {
      animation-delay: 0s;
    }

    .image-container img:nth-child(2) {
      animation-delay: 7.5s;
    }

    .image-container img:nth-child(3) {
      animation-delay: 15s;
    }

    .image-container img:nth-child(4) {
      animation-delay: 22.5s;
    }
  </style>


<section class="main">
	<!--  메인페이지 환영 안녕 동물 친구들을 이미지 변환으로 변경 -->
	<section class="main-in-section w-full main-height">
		<div class="image-container">
			<img src="/resource/images/Lets_Read_A_Book_Together.jpg" alt="책같이읽자" />
			<img src="/resource/images/Lets_Talk_Toggether.jpg" alt="같이이야기하자" />
			<img src="/resource/images/Express_Your_Thoughts.jpg" alt="너의생각은어때?" />
			<img src="/resource/images/Book_animal.jpg" alt="책읽는동물친구들" />
		</div>
	</section>


	<!-- 로그인 안했을때 -->	
	<c:if test="${rq.getLoginedMemberId() == 0 }">
		<section class="main-in-section">
			<div class="section-title text-2xl">랭킹 칼럼</div>
			<section class="items">
				<c:forEach var="article" items="${articles}">
					<div class="item">
					<a href="/usr/article/detail?id=${article.id}"> 
						<div class="title">${article.title}</div>
						<div class="writer">${article.nickname}</div>
						<div class="likeCount"><i class="fa-solid fa-heart"></i>${article.count}</div>
					</a>
					</div>
				</c:forEach>
			</section>
		</section>
		
		<section class="main-in-section text-2xl">
			<div class="section-title">랭킹 토론방</div>
			<section class="items">
				<c:forEach var="discussionRoom" items="${discussionRooms}">
					<div class="item">
					<a href="usr/discussion/chat?discussionId=${discussionRoom.id}"> 
						<div class="title">${discussionRoom.dscsnRoomNm}</div>
						<div class="likeCount"><i class="fa-solid fa-people-group"></i> ${discussionRoom.memberCount}</div>
					</a>
					</div>
				</c:forEach>
			</section>
		</section>
	
		<section class="main-in-section text-2xl">
			<div class="section-title text-2xl">분야별 칼럼</div>
			<section class="table-section">
				<div class="table-row">
					<div class="table-item"><a href="/usr/article/list?type=4&session=1">철학</a></div>
					<div class="table-item"><a href="/usr/article/list?type=4&session=2">종교</a></div>
					<div class="table-item"><a href="/usr/article/list?type=4&session=3">사회과학</a></div>
				</div>
				<div class="table-row">
					<div class="table-item"><a href="/usr/article/list?type=4&session=4">자연과학</a></div>
					<div class="table-item"><a href="/usr/article/list?type=4&session=5">기술과학</a></div>
					<div class="table-item"><a href="/usr/article/list?type=4&session=6">예술</a></div>
				</div>
				<div class="table-row">
					<div class="table-item"><a href="/usr/article/list?type=4&session=7">언어</a></div>
					<div class="table-item"><a href="/usr/article/list?type=4&session=8">문학</a></div>
					<div class="table-item"><a href="/usr/article/list?type=4&session=9">역사</a></div>
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
					<a href="">
						<div class="title">칼럼제목1</div>
						<div class="writer">작성자1</div>
					</a>
				</div>
				<div class="item">
					<a href="">
						<div class="title">칼럼제목2</div>
						<div class="writer">작성자2</div>
					</a>
				</div>
				<div class="item">
					<a href="">
						<div class="title">칼럼제목3</div>
						<div class="writer">작성자3</div>
					</a>
				</div>
				<div class="item">
					<a href="">
						<div class="title">칼럼제목4</div>
						<div class="writer">작성자4</div>
					</a>
				</div>
				<div class="item">
					<a href="">
						<div class="title">칼럼제목5</div>
						<div class="writer">작성자5</div>
					</a>
				</div>
			</section>
		</section>
	
			<section class="main-in-section text-2xl">
			<div class="section-title">추천 토론방</div>
			<section class="items">
				<div class="item">
					<a href="">
						<div class="title">칼럼제목1</div>
						<div class="writer">작성자1</div>
					</a>
				</div>
				<div class="item">
					<a href="">
						<div class="title">칼럼제목2</div>
						<div class="writer">작성자2</div>
					</a>
				</div>
				<div class="item">
					<a href="">
						<div class="title">칼럼제목3</div>
						<div class="writer">작성자3</div>
					</a>
				</div>
				<div class="item">
					<a href="">
						<div class="title">칼럼제목4</div>
						<div class="writer">작성자4</div>
					</a>
				</div>
				<div class="item">
					<a href="">
						<div class="title">칼럼제목5</div>
						<div class="writer">작성자5</div>
					</a>
				</div>
			</section>
		</section>
	
		<section class="main-in-section text-2xl">
			<div class="section-title text-2xl">분야별 칼럼</div>
			<section class="table-section">
				<div class="table-row">
					<div class="table-item"><a href="/usr/article/list?type=4&session=1">철학</a></div>
					<div class="table-item"><a href="/usr/article/list?type=4&session=2">종교</a></div>
					<div class="table-item"><a href="/usr/article/list?type=4&session=3">사회과학</a></div>
				</div>
				<div class="table-row">
					<div class="table-item"><a href="/usr/article/list?type=4&session=4">자연과학</a></div>
					<div class="table-item"><a href="/usr/article/list?type=4&session=5">기술과학</a></div>
					<div class="table-item"><a href="/usr/article/list?type=4&session=6">예술</a></div>
				</div>
				<div class="table-row">
					<div class="table-item"><a href="/usr/article/list?type=4&session=7">언어</a></div>
					<div class="table-item"><a href="/usr/article/list?type=4&session=8">문학</a></div>
					<div class="table-item"><a href="/usr/article/list?type=4&session=9">역사</a></div>
				</div>
			</section>
		</section>
		
		<section class="main-in-section">
			<div class="section-title text-2xl ">추천 책</div>
			<section class="items">
				<div class="item">
					<a href="">
						<div class="title">칼럼제목1</div>
						<div class="writer">작성자1</div>
					</a>
				</div>
				<div class="item">
					<a href="">
						<div class="title">칼럼제목2</div>
						<div class="writer">작성자2</div>
					</a>
				</div>
				<div class="item">
					<a href="">
						<div class="title">칼럼제목3</div>
						<div class="writer">작성자3</div>
					</a>
				</div>
				<div class="item">
					<a href="">
						<div class="title">칼럼제목4</div>
						<div class="writer">작성자4</div>
					</a>
				</div>
				<div class="item">
					<a href="">
						<div class="title">칼럼제목5</div>
						<div class="writer">작성자5</div>
					</a>
				</div>
			</section>
		</section>
	</c:if>

</section>

<%@ include file="../common/foot.jsp"%>