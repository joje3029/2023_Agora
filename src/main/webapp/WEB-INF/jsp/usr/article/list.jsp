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
		<div>게시글 갯수 : ${articlesCnt}</div>
		<div class="table-box-type">
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>작성일</th>
						<th>제목</th>
						<th>작성자</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="article" items="${articles }">
						<tr>
							<td>${article.id }</td>
							<td>${article.regDate }</td>
							<td class="hover:underline"><a
								href="detail?id=${article.id }">${article.title }</a></td>
							<td>${article.nickname }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<c:if test="${rq.getLoginedMemberId() != 0 }">
				<div class="flex justify-end">
					<div class="hover:underline hover:text-green-700 mr-16"">
						<a href="write">글쓰기</a>
					</div>
				</div>
			</c:if>
			<!-- 여기서부터 페이지네이션 부분 -->
			<div class="mt-2 flex justify-center">
				<div class="join">
					<!-- 아래에서 쓸 변수 설정 -->
					<c:set var="pageMenuLen" value="5" /> <!-- 페이지메뉴의 길이 : 앞 뒤로 5개 있게 -->
					<!-- 삼항연산자 문법 :  조건문 ? 참 일때: 거짓 일때 -->
					<c:set var="startPage"
						value="${page - pageMenuLen >= 1 ? page - pageMenuLen : 1 }" /><!-- 시작점: 현 페이지 - 5가 1보다 크거나 같나? 참이면 startpage가 현재에서 -5한 값부터. 아니면 1-->
					<c:set var="endPage"
						value="${page + pageMenuLen <= pagesCnt ? page + pageMenuLen : pagesCnt }" /><!-- 끝나는 점: 현 페이지 +5를 했을 때 총 페이지 갯수보다 작으면 현재에서 +5한 값까지. 아니면 총 페이지갯수-->

					<!-- 실제로 그려내는 부분 -->
					<!-- 맨앞이면 << 없는거 : 코드보면 해석할줄 아니까. 맨앞(1)으로 이동 -->
					<c:if test="${page == 1 }">
						<a class="join-item btn btn-sm btn-disabled">«</a>
					</c:if>
					<c:if test="${page > 1 }">
						<a class="join-item btn btn-sm"
							href="?boardId=${board.id }&page=1">«</a>
					</c:if>
					
					<!-- 페이지들 실제로 그리는 반복문 -->
					<!-- begin : 반복을 시작하는 값, end : 반복을 끝내는 값, var : 현재 반복되는 항목에 대한 이름을 정의 -->
					<!-- 위에서 어디서부터 어디까지 보일지 정한걸로 forEach 돌기 -->
					<c:forEach begin="${startPage }" end="${endPage }" var="i">
						<!-- class는 지금 페이지랑 페이지번호가 같으면 되게하는것. Daisy Ui 이용. -->
						<a class="join-item btn btn-sm ${page == i ? 'btn-active' : '' }" 
							href="?boardId=${board.id }&page=${i } ">${i }</a><!--  페이지번호 -->
					</c:forEach>
					
					<!-- 맨 뒤면 >> 없는거 맨뒤로 이동-->
					<c:if test="${page < pagesCnt }">
						<a class="join-item btn btn-sm"
							href="?boardId=${board.id }&page=${pagesCnt }">»</a>
					</c:if>
					<c:if test="${page == pagesCnt }">
						<a class="join-item btn btn-sm btn-disabled">»</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="../common/foot.jsp"%>