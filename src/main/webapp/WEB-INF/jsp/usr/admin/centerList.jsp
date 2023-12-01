<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="head.jsp"%>
<link rel="stylesheet" href="/resource/main.css" />

		<section class="Admain">
		 	<section class="listBody">
		<div class="flex justify-between">
			<h1 class="backcground2 my-6">관리자 고객 상담 리스트</h1>
		</div>
		<section class="mt-8 text-xl">
			<div class="container mx-auto px-3">
			
				<div class="table-box-type">
					<table class="table table-lg">
						<thead class="text-lg">
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>고객 아이디</th>
								<th>고객 이메일</th>
								<th>문의한 일자</th>
								<th>답변 상태</th>
							</tr>
						</thead>
						<tbody>
							<!--여기 코드는 article list 코드랑 로직 다른거 없음.-->
							<!--아래는 일단 박은거-->
							<!--답변은 해당 tr을 건드리면 해당으로 넘어가는걸로-->
							<!-- c:forEach 넣어야함 : 퍼블리싱중에 넣으니까 안보여서 뻄. -->
							<tr>
								<th>1</th>
								<th>뱁새!</th>
								<th>sdf123</th>
								<th>joje123456@gmail.com</th>
								<th>2023-01-11</th>
								<th>답변했음</th>
							</tr>
						</tbody>
					</table>
				</div>
				
				<!--페이지네이션 : 이거 쓸꺼임. 지우지 마셈-->
				<div class="mt-2 flex justify-center">
					<div class="join">
						<c:set var="pageMenuLen" value="5" />
						<c:set var="startPage" value="${page - pageMenuLen >= 1 ? page - pageMenuLen : 1 }" />
						<c:set var="endPage" value="${page + pageMenuLen <= pagesCnt ? page + pageMenuLen : pagesCnt }" />
						
						<c:set var="baseUri" value="boardId=${board.id }&searchKeywordType=${searchKeywordType }&searchKeyword=${searchKeyword }" />
						
						<c:if test="${page == 1 }">
							<a class="join-item btn btn-sm btn-disabled">«</a>
						</c:if>
						<c:if test="${page > 1 }">
							<a class="join-item btn btn-sm" href="?page=1&${baseUri }">«</a>
						</c:if>
						<c:forEach begin="${startPage }" end="${endPage }" var="i">
							<a class="join-item btn btn-sm ${page == i ? 'btn-active' : '' }" href="?page=${i }&${baseUri }">${i }</a>
						</c:forEach>
						<c:if test="${page < pagesCnt }">
							<a class="join-item btn btn-sm" href="?page=${pagesCnt }&${baseUri }">»</a>
						</c:if>
						<c:if test="${page == pagesCnt }">
							<a class="join-item btn btn-sm btn-disabled">»</a>
						</c:if>
					</div>
				</div>
			</div>
		</section>
	</section>
		</section>
	</body>
</html>

