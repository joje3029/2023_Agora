<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="../common/head.jsp"%>
<%@ include file="../common/floatingBanner.jsp"%>
<link rel="stylesheet" href="/resource/main.css" />

<script>
const customerForm_onSubmit = function(form) {
	form.title.value = form.title.value.trim();
	form.body.value = form.body.value.trim();
	
	if (form.title.value.length == 0) {
		alert('제목을 입력해주세요');
		form.title.focus();
		return;
	}
	
	if (form.body.value.length == 0) {
		alert('내용을 입력해주세요');
		form.body.focus();
		return;
	}
	
	form.submit();
}
</script>

<section class="listBody">



	<div class="flex justify-center">
		<h1 class="text-3xl font-semibold">1:1 고객 상담</h1>
	</div>
	<section class="table-section flex justify-center ">
		<form action="sendcustomercenter" method="post"
			onsubmit="customerForm_onSubmit(this); return false;">
			<table>
				<tr>
					<th class="w-20"><span class="text-red-700">*</span>제목</th>
					<td><input type="text" name="title" placeholder="제목을 입력해주세요"
						class="input input-bordered w-full max-w-xs input-sm"></td>
				</tr>
				<tr>
					<th class="w-20"><span class="text-red-700">*</span>내용</th>
					<td class="flex"><textarea
							class="textarea textarea-bordered resize-none" name="body"
							cols="50" rows="10" placeholder="내용 입력해주세요"></textarea>
				</tr>
				<tr>
					<th colspan="2">
						<div class="btn">
							<a href="/">취소</a>
						</div>
						<button class="btn">확인</button>
					</th>
				</tr>
			</table>
		</form>
	</section>
</section>

<%@ include file="../common/foot.jsp"%>