<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="head.jsp"%>
<link rel="stylesheet" href="/resource/main.css" />

		<section class="Admain">
			<section class="listBody">
				<div>
					<h1 class="bg-green-100 inline-block text-3xl p-3">관리자 회원 조회</h1>
				</div>
				<section class="mt-8 text-xl">
					<div class="container mx-auto p-3 border border-green-600 ">
						<form> <!-- 이건 조회하는 아 == action 없어도 됨. -->
							<table class="table table-lg  w-full">
								<tr>
									<th class="mr-4">가입일</th>
									<!--네이버 같은데 달력은 시간 되면 하고. 형태에 맞게 때리면 db에서 조회하게 하자-->
									<td><input type="date" name="startDate" class="input input-bordered max-w-xs input-sm w-44" > ~ 
									<input type="date" name="endDate" class="input input-bordered max-w-xs input-sm w-44" ></td>
								</tr>
								<tr>
									<th class="mr-4">아이디</th>
									<!--이거는 db에서 like 로 조회 => 그래야 제대로 된 놈을 찾기 편하지-->
									<td><input type="text" name="searchId" placeholder="조회할 아이디 입력" class="input input-bordered"></td>
								</tr>
								<tr>
									<th class="mr-4">닉네임</th>
									<!--닉네임은 중복안되게 했으니까 조회 가능-->
									<td><input type="text" name="searchNickname" placeholder="조회할 닉네임 입력" class="input input-bordered"></td>
								</tr>
							</table>
							<div class="w-full flex justify-center"><button class="btn">조회</button></div>
						</form>
					</div>
					<div class="table-box-type border2">
						<!--  이 form이 삭제하는 일을 함.  그래서 얘는 actin이 있어야함.-->
						<form action="doDeleteMembers" method="POST">
							<div class="mt-2 flex justify-end">
								<button class="btn btn-delete-selected-members">회원 강제 탈퇴</button>
							</div>
							<table class="table table-lg">
								<thead class="text-lg">
									<tr>
										<th><input type="checkbox" class="checkbox-all-member-id" /></th>
										<th>번호</th>
										<th>아이디</th>
										<th>이름</th>
										<th>닉네임</th>
										<th>가입일</th>
										<!-- 난중에 탈퇴일도 넣을까.. -->
										<th>상태</th>
										<!--지가 탈퇴했다/강제탈퇴다(요청들어와서 신고받은 시키 운영에서 없앤거)/휴먼상태이다.-->
									</tr>
								</thead>
								<tbody>
									<!--여기 코드는 article list 코드랑 로직 다른거 없음.-->
									<!--아래는 일단 박은거-->
									<!--답변은 해당 tr을 건드리면 해당으로 넘어가는걸로-->
									<!--이거 있으면 퍼블리싱이 안보여서 일단 주석처리함. 개발 단계일 때  c:for 써야함.-->
									<!-- <c:forEach var="article" items="${articles }"> </c:forEach> -->
									<c:forEach var="member" items="${members }">
												<tr class="hover">
													<td><input type="checkbox" class="checkbox-member-id" name="ids" value="${member.id }" /></td>
													<td>${member.id }</td>
													<td>${member.uwerId }</td>
													<td>${member.name }</td>
													<td>${member.nickname }</td>
													<td>${member.joinDate.substring(0,10) }</td>
													<td>${member.delStatusStr()}</td>
												</tr>
										</c:forEach>
								</tbody>
							</table>
						</form>
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
		
		<script>
	$('.checkbox-all-member-id').change(function() {
		const allCheck = $(this);
		const allChecked = allCheck.prop('checked');
		$('.checkbox-member-id').prop('checked', allChecked);
		$('.checkbox-member-id:is(:disabled)').prop('checked', false);
	})
	
	$('.checkbox-member-id').change(function() {
		const checkboxMemberIdCount = $('.checkbox-member-id').length;
		const checkboxMemberIdCheckedCount = $('.checkbox-member-id:checked').length;
		const checkboxDisabledCount = $('.checkbox-member-id:is(:disabled)').length;
		const allChecked = (checkboxMemberIdCount - checkboxDisabledCount) == checkboxMemberIdCheckedCount;
		$('.checkbox-all-member-id').prop('checked', allChecked);
	})
</script>
	</body>
</html>

