<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="../common/head.jsp"%>
<%@ include file="../common/floatingBanner.jsp"%>
<link rel="stylesheet" href="/resource/main.css" />

<script>
	// li에 뭘 선택했는지에 따라.....  chatbot-bubble에 보여줘야함
	// controller가 있어야겠네. 요청 보낼 url이랑
	
</script>

		<section class="listBody">
			<div class="flex justify-between">
				<h1 class="text-4xl">고객상담 챗봇</h1>
				<button class="btn"
					onclick="history.back();">뒤로가기</button>
			</div>
			<section class="chat-contents flex backcground2 h-screen">
				<nav class="chat-question mr-4 ">
					<ui>
					<li class="question-item"><a href="#">계정 관리</a></li>
					<li class="question-item"><a href="#">칼럼</a></li>
					<li class="question-item"><a href="#">토론방</a></li>
					<li class="question-item"><a href="#">구독</a></li>
					<li class="question-item"><a href="#">탈퇴</a></li>
					<c:if  test="${rq.getLoginedMemberId() != 0 }">
						<li class="question-item"><a href="customercenter">그 외</a></li>
					</c:if>
					</ui>
				</nav>
				<div class="chatbot-bubble border border-2 border-green-700 w-full p-6">
					
				</div>
			</section>
		</section>

<%@ include file="../common/foot.jsp"%>