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
			<div class="flex justify-end">
				<div class="hover:underline hover:text-green-700 mr-16""><a href="write">글쓰기</a></div>
			</div>

		</div>
	</div>
</section>

<%@ include file="../common/foot.jsp"%>