<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="head.jsp"%>
<link rel="stylesheet" href="/resource/main.css" />

		<section class="Admain">
			<section class="listBody">
				<div class="flex justify-between">
					<h1 class="backcground2 my-6">관리자 회원 조회</h1>
				</div>
				<section class="mt-8 text-xl">
					<div class="container mx-auto px-3 border border-green-600 ">
						<table class="table table-lg  w-full">
							<tr>
								<th class="mr-4">가입일</th>
								<!--네이버 같은데 달력은 시간 되면 하고. 형태에 맞게 때리면 db에서 조회하게 하자-->
								<td><input type="text" placeholder="입력 형태 :2022-12-25">~<input
									type="text" placeholder="입력 형태 :2023-12-25"></td>
							</tr>
							<tr>
								<th class="mr-4">아이디</th>
								<!--이거는 db에서 like 로 조회 => 그래야 제대로 된 놈을 찾기 편하지-->
								<td><input type="text" placeholder="조회할 아이디 입력"></td>
							</tr>
							<tr>
								<th class="mr-4">닉네임</th>
								<!--닉네임은 중복안되게 했으니까 조회 가능-->
								<td><input type="text" placeholder="조회할 닉네임 입력"></td>
							</tr>
						</table>
						<div class="w-full flex justify-center"><button class="btn">조회</button></div>
					</div>
					<div class="table-box-type border2">
						<table class="table table-lg">
							<thead class="text-lg">
								<tr>
									<th>번호</th>
									<th>아이디</th>
									<th>이름</th>
									<th>가입일</th>
									<th>상태</th>
									<!--지가 탈퇴했다/강제탈퇴다(요청들어와서 신고받은 시키 운영에서 없앤거)/휴먼상태이다.-->
									<th>강제탈퇴하기</th>
								</tr>
							</thead>
							<tbody>
								<!--여기 코드는 article list 코드랑 로직 다른거 없음.-->
								<!--아래는 일단 박은거-->
								<!--답변은 해당 tr을 건드리면 해당으로 넘어가는걸로-->
								<!--이거 있으면 퍼블리싱이 안보여서 일단 주석처리함. 개발 단계일 때  c:for 써야함.-->
								<!-- <c:forEach var="article" items="${articles }"> </c:forEach> -->
								<th>1</th>
								<th>sdf123</th>
								<th>김뱁새</th>
								<th>2023-11-12</th>
								<th>활동 중</th>
								<th><button>탈퇴시키기</button></th>
								</tr>
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
		
				</section>
			</section>
		</section>
	</body>
</html>

