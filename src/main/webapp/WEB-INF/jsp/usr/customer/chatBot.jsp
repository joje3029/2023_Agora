<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="../common/head.jsp"%>
<%@ include file="../common/floatingBanner.jsp"%>
<link rel="stylesheet" href="/resource/main.css" />

		<section class="listBody border">
			<div class="flex justify-between">
				<h1 class="backcground2 my-6">고객상담 챗봇</h1>
				<button class="hover:underline hover:text-green-700 mr-16"
					onclick="history.back();">뒤로가기</button>
			</div>
			<section class="chat-contents flex backcground2 h-screen">
				<nav class="chat-question mr-4 ">
					<ui>
					<li class="question-item">챗봇 질문1</li>
					<li class="question-item">챗봇 질문2</li>
					<li class="question-item">챗봇 질문3</li>
					<li class="question-item">챗봇 질문4</li>
					<li class="question-item">챗봇 질문5</li>
					<li class="question-item"><a href="customercenter">그 외</a></li>
					</ui>
				</nav>
				<div
					class="chatbot-bubble border border-2 border-green-700 w-full p-6">
					<!--데이지 ui 퍼블리싱임으로 이런식이다 박음-->
					<!--질문은 클릭하는 방식으로 하자-->
					<div class="chat chat-start">
						<div class="chat-image avatar">
							<div class="w-28 rounded-full border border-green-700 p-4">
								<img
									src="https://cdn.pixabay.com/photo/2017/12/03/14/31/kawaii-2995014_1280.png" />
							</div>
						</div>
						<div class="chat-bubble">It was said that you would, destroy
							the Sith, not join them.</div>
					</div>
					<div class="chat chat-start">
						<div class="chat-image avatar">
							<div class="w-28 rounded-full border  border-green-700 p-4">
								<img
									src="https://cdn.pixabay.com/photo/2017/12/03/14/31/kawaii-2995014_1280.png" />
							</div>
						</div>
						<div class="chat-bubble">It was said that you would, destroy
							the Sith, not join them.</div>
					</div>
					<div class="chat chat-start">
						<div class="chat-image avatar">
							<div class="w-28 rounded-full border  border-green-700 p-4">
								<img
									src="https://cdn.pixabay.com/photo/2017/12/03/14/31/kawaii-2995014_1280.png" />
							</div>
						</div>
						<div class="chat-bubble">It was said that you would, destroy
							the Sith, not join them.</div>
					</div>
				</div>
			</section>
		</section>

<%@ include file="../common/foot.jsp"%>