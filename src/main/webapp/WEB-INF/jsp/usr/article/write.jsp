<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<c:set var="pageTitle" value="칼럼작성" />
<!--  여기서 변수 선언헀으니까 haed에서 pageTitle을 title에 넣어서 사용가능.  -->

<%@ include file="../common/head.jsp"%>
<%@ include file="../common/toastUiEditorLib.jsp"%>

<!-- 지시어 문법으로 incldue로 파일을 포함 : 포함한 파일이 공통으로 들어갈 head일뿐 -->

<script>
	const writeForm_onSubmit = function(form){
		form.title.value = form.title.value.trim();
		form.body.value = form.body.value.trim();
		
		if(form.title.value.length ==0){
			alret('제목을 입력해주세요');
			form.title.focus();
			return
		}
		if(form.body.value.length ==0){
			alret('내용을 입력해주세요');
			form.body.focus();
			return
		}
		
		form.submit(); // 여기서 보내고 나서 아래에서 return false하는거라 이미 보낸 상태이기 때문에 괜찮음.
	}
</script>

	<section class="listBody">
		<form action="doWrite" method="post"
			onsubmit="submitForm(this); return false;">
			<input name="body" type="hidden" />
			<div class="table-box-type">
				<table class="table table-lg">
					<tr>
						<td class="text-center" colspan="2">
							<p>
								<span class="text-red-700">*</span>컬럼 분류 설정은 필수입니다
							</p>
						</td>
					</tr>
					<tr>
						<td class="text-center" colspan="2"><select
							class="select select-bordered max-w-xs select-sm">
								<option disabled selected>컬럼 분류</option>
								<option>철학</option>
								<option>종교</option>
								<option>사회과학</option>
								<option>자연과학</option>
								<option>기술과학</option>
								<option>예술</option>
								<option>언어</option>
								<option>문학</option>
								<option>역사</option>
						</select> <!-- 임시저장은 안되면 빼야해서 일단 div. submit꼬이는거 싫어 -->
							<div class="btn btn-sm ml-1">임시저장</div>
							<button class="btn btn-sm ml-1">작성</button></td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<div class="toast-ui-editor"></div>
						</td>
					</tr>
				</table>
		</form>
	
		<div class="btns mt-2">
			<button class="btn btn-sm ml-1" onclick="history.back();">뒤로가기</button>
		</div>
		</div>
	</section>
<%@ include file="../common/foot.jsp"%>