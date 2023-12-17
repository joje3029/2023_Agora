<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="head.jsp"%>
<link rel="stylesheet" href="/resource/main.css" />

<section class="Admain">
	<section class="listBody">
		<div class="flex justify-between">
			<h1 class="bg-green-100 inline-block text-3xl p-3">관리자 고객 상담 리스트</h1>
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
								<th>답변 상태</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="customerCenter" items="${customerCenters }">
								<tr class="hover">
									<td><a href="customercenter?id=${customerCenter.id }">${customerCenter.cstmrTitle }</a></td>
									<td>${customerCenter.uwerId }</td>
									<td>${customerCenter.ricfldStausStr() }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<!--페이지네이션 : 이거 쓸꺼임. 지우지 마셈-->
				<div class="mt-2 flex justify-center">
					<div class="join">
						<c:set var="pageMenuLen" value="5" />
						<c:set var="startPage"
							value="${page - pageMenuLen >= 1 ? page - pageMenuLen : 1 }" />
						<c:set var="endPage"
							value="${page + pageMenuLen <= pagesCnt ? page + pageMenuLen : pagesCnt }" />

						<c:set var="baseUri"
							value="boardId=${board.id }&searchKeywordType=${searchKeywordType }&searchKeyword=${searchKeyword }" />

						<c:if test="${page == 1 }">
							<a class="join-item btn btn-sm btn-disabled">«</a>
						</c:if>
						<c:if test="${page > 1 }">
							<a class="join-item btn btn-sm" href="?page=1&${baseUri }">«</a>
						</c:if>
						<c:forEach begin="${startPage }" end="${endPage }" var="i">
							<a class="join-item btn btn-sm ${page == i ? 'btn-active' : '' }"
								href="?page=${i }&${baseUri }">${i }</a>
						</c:forEach>
						<c:if test="${page < pagesCnt }">
							<a class="join-item btn btn-sm"
								href="?page=${pagesCnt }&${baseUri }">»</a>
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

