<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="head.jsp"%>
<link rel="stylesheet" href="/resource/main.css" />

<script>
const customerForm_onSubmit = function(form) {
	form.body.value = form.body.value.trim();
	
	if (form.body.value.length == 0) {
		alert('내용을 입력해주세요');
		form.body.focus();
		return;
	}
	
	form.submit();
}
</script>

		<section class="Admain">
		 	<section class="listBody">
		<div class="flex justify-between mb-6">
			<h1 class="bg-green-100 inline-block text-3xl p-3">관리자 고객 상담 답변</h1>
		</div>
		<!-- 여기는 고객 질문 상세보기 -->
		<div class="border p-2 inline-block">유저가 보낸 내용 상세보기</div>
		<section class="table-section flex justify-center">
			<table class="w-6/12">
				<tr>
					<th class="w-24">유저 아이디</th>
					<td><div class="input input-bordered w-full max-w-xs input-sm">${customerCenter.uwerId}</div></td>
				</tr>
				<tr>
					<th class="w-24">제목</th>
					<td><div class="input input-bordered w-full max-w-xs input-sm">${customerCenter.cstmrTitle}</div></td>
				</tr>
				<tr>
					<th class="w-24">내용</th>
					<td><div class="textarea textarea-bordered resize-none">${customerCenter.cstmrBody}</div>
				</tr>
			</table>
		</section>
		
		<hr>
		
		<!-- 여기는 답하는거 -->
		<div class="border p-2 inline-block">유저 메일로 답 보내기</div>
		<section class="table-section flex justify-center">
			<form action="sendCustomerAnswer" method="post"
				onsubmit="customerForm_onSubmit(this); return false;">
				<input type="hidden" name="id" value="${customerCenter.id}">
				<!--나중에 기능할떄 여기서는 보낸 놈(나한테 의뢰온놈) email주소가 iput으로 들어와서 같이 전달 되야함.-->
				<table >
					<tr>
						<th class="w-20"><span class="text-red-700">*</span>내용</th>
						<td class="flex"><textarea class="textarea textarea-bordered resize-none"
								name="body" cols="50" rows="10" placeholder="내용 입력해주세요"></textarea>
					</tr>
					<!-- 첨부파일은 난중에 하기
					<tr>
						<th>첨부파일</th>
						<td><input type="file" name="file" placeholder="첨부사진 입력해주세요"
							class="input w-full max-w-xs">
					</tr>-->
					<tr>
						<th colspan="2">
							<div class="btn">
								<a href="/">취소</a>
							</div>
							<button class="btn">확인</button>
						</th>
					</tr>
				</table>
		</section>
	</section>
		</section>
	</body>
</html>

