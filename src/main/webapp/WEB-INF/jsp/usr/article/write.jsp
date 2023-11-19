<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<c:set var="pageTitle" value="ARTICLE Modify" />
<!--  여기서 변수 선언헀으니까 haed에서 pageTitle을 title에 넣어서 사용가능.  -->

<%@ include file="../common/head.jsp"%>
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

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type">
		<form action="doWrite" method="get" onsubmit="writeForm_onSubmit(this); return false;">
			<table>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" value="${article.title }" placeholder="제목을 입력해주세요" /></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="body" cols="30" rows="10" value="${article.body }" placeholder="내용을 입력해주세요"></textarea></td>
					</tr>
					<tr>
						<td colspan="2"><button>글 저장</button></td>
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