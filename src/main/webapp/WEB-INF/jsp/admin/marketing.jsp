<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="head.jsp"%>
<link rel="stylesheet" href="/resource/main.css" />

		<section class="Admain">
			  <section class="listBody">
		<div class="flex justify-between">
			<h1 class="bg-green-100 inline-block text-3xl p-3">사이트 통계</h1>
		</div>
		<section class="mt-8 text-xl">
			<div class="container mx-auto px-3 border border-green-600 mb-4">
				<ul class="flex justify-around p-4">
					<li class="btn">신규 가입자 수</li>
					<li class="btn">일일 방문횟수</li>
					<li class="btn">탈퇴 회원 수</li>
				</ul>
			</div>
			<div class=" border2">
				<!--h-screen : height: 100vh; 임시 임시 대충 모양 볼려구-->
				<div class="chart-section border w-full h-screen">
					토스트 ui 차트 들어올 자리
					<!--차트는 선으로해서 시계열로 보여주면 되겠다.-->
					<!--예측은 안할꺼임 어디까지나 보여주기만 할꺼니까.-->
				</div>
			</div>

		</section>
	</section>
		</section>
	</body>
</html>

