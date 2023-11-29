<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="../common/head.jsp"%>
<%@ include file="../common/floatingBanner.jsp"%>
<link rel="stylesheet" href="/resource/main.css" />
<%@ include file="mainScript.jsp"%>

<!-- 캐러셀을 일단 안쓴 버전으로 퍼블리싱을 하자 => 캐러셀같은 프론트 단때문에 시간 많이 잡아먹지 말자! -->
		<section class="main">
			<section class="main-in-section">
				<div class="title">추천 칼럼</div>
				<section class="contents">
					<div class="item">
						<div>칼럼제목1</div>
						<div>작성자1</div>
					</div>
					<div class="item">
						<div>칼럼제목2</div>
						<div>작성자2</div>
					</div>
					<div class="item">
						<div>칼럼제목3</div>
						<div>작성자3</div>
					</div>
					<div class="item">
						<div>칼럼제목4</div>
						<div>작성자4</div>
					</div>
					<div class="item">
						<div>칼럼제목5</div>
						<div>작성자5</div>
					</div>
				</section>
			</section>
		</section>

<%@ include file="../common/foot.jsp"%>