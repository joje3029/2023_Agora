<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<c:set var="pageTitle" value="ARTICLE Modify" />
<!--  여기서 변수 선언헀으니까 haed에서 pageTitle을 title에 넣어서 사용가능.  -->

<%@ include file="../common/head.jsp"%>
<%@ include file="../common/toastUiEditorLib.jsp"%>

<script>
	$(document).ready(function() {
	    const titleInput = $('#title');
	    const maxLength = 50;
	
	    titleInput.on('input', function() {
	        const inputValue = $(this).val();
	        
	        if (inputValue.length > maxLength) {
	            $(this).val(inputValue.slice(0, maxLength)); // 최대 길이까지만 잘라냄
	            alert('제목은 50자 이내여야 합니다.');
	        }
	    });
	});


	const submitForm = function(form) {
		form.title.value = form.title.value.trim();
		form.body.value = form.body.value.trim();
		
		if(form.title.value.length ==0){
			alert('제목을 입력해주세요');
			form.title.focus();
			return
		}
		if(form.body.value.length ==0){
			alert('내용을 입력해주세요');
			form.body.focus();
			return
		}
		
		form.submit();
	}
</script>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type">
		<form action="doModify" method="get" onsubmit="submitForm(this); return false;">
			<input type="hidden" name="id" value="${article.id }" />
			<input name="body" type="hidden" />
			<table>
					<tr>
						<th>번호</th>
						<td>${article.id }</td>
					</tr>
					<tr>
						<th>작성일</th>
						<td>${article.writngTime }</td>
					</tr>
					<tr>
						<th>수정일</th>
						<td>${article.colmnModifiedTime }</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${article.nickname }</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" id="title" value="${article.title }" placeholder="제목을 입력해주세요" /></td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<div name="body" class="toast-ui-editor">
								<script type="text/x-template">${article.body }</script>
							</div>
						</td>
					</tr>
					<tr>
						<td class="text-center" colspan="2"><button class="btn btn-wide">수정</button></td>
					</tr>
			</table>
		</form>
			<div class="flex" >
				<button class="hover:underline hover:text-green-700 mr-16" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</div>
</section>

<%@ include file="../common/foot.jsp"%>