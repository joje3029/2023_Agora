<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<c:set var="pageTitle" value="ARTICLE LIST" />
<!--  여기서 변수 선언헀으니까 haed에서 pageTitle을 title에 넣어서 사용가능.  -->

<%@ include file="../common/head.jsp"%>
<!-- 지시어 문법으로 incldue로 파일을 포함 : 포함한 파일이 공통으로 들어갈 head일뿐 -->

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type">
			<table>
					<tr>
						<th>번호</th>
						<td>${article.id }</td>
					</tr>
					<tr>
						<th>작성일</th>
						<td>${article.regDate }</td>
					</tr>
					<tr>
						<th>수정일</th>
						<td>${article.updateDate }</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${article.nickname }</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${article.title }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>${article.body }</td>
					</tr>
			</table>
			<div class="flex" >
				<button class="hover:underline hover:text-green-700 mr-16" onclick="history.back();">뒤로가기</button>
				<c:if test="${loginedMemberId!= null && loginedMemberId == article.memberId  }">
					<div class="hover:underline hover:text-green-700 mr-16"><a href="modify?id=${article.id }">수정</a></div>
					<div class="hover:underline hover:text-green-700" ><a href="doDelete?id=${article.id }" onclick="if(confirm('정말삭제하시겠습니까?') == false) return false" >삭제</a></div>
				</c:if>
			</div>
		</div>
	</div>
</section>

<%@ include file="../common/foot.jsp"%>