<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<c:set var="pageTitle" value="칼럼작성" />
<!--  여기서 변수 선언헀으니까 haed에서 pageTitle을 title에 넣어서 사용가능.  -->

<%@ include file="../common/head.jsp"%>
<%@ include file="../common/toastUiEditorLib.jsp"%>

<!-- 지시어 문법으로 incldue로 파일을 포함 : 포함한 파일이 공통으로 들어갈 head일뿐 -->



	<section class="listBody">
		<form action="doWrite" method="post"
			onsubmit="writeForm_onSubmit(this); return false;">
			<input name="body" type="hidden" />
			<div class="table-box-type">
				<table class="table table-lg">
					<tr>
						<td class="text-center" colspan="2">
						<div class="mb-2"><span class="text-red-700">*</span>컬럼 분류 설정은 필수입니다</div>
						<select id="session" name="session"
							class="select select-bordered max-w-xs select-sm">
								<option value="notSelect" disabled selected>컬럼 분류</option>
								<option value="philosophy">철학</option>
								<option value="religion">종교</option>
								<option value="socialScience">사회과학</option>
								<option value="naturalScience">자연과학</option>
								<option value="technologyScience">기술과학</option>
								<option value="art">예술</option>
								<option value="language">언어</option>
								<option value="literature">문학</option>
								<option value="history">역사</option>
						</select>
							<button class="btn btn-sm ml-1">작성</button></td>
					</tr>
						<th>제목</th>
						<td>
						<!-- 스크립트로 제목 제약 해야함. -->
							<input type="text" name="title" id="title" placeholder="제목을 입력해주세요. 50자 이내입니다." class="input input-bordered w-full max-w-xs input-sm">
						</td>
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