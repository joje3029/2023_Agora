<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- El 문법중 core를 쓰겠다고 선언. 이거 해야 c: 들 사용가능 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<!--  test 삼아 출력해보기 -->
	<div>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>작성일</th>
					<th>작성자</th>
					<th>제목</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="article" items="articles">
					<tr>
					<th>${article.id }</th>
					<th>${article.regDate }</th>
					<th>${article.memberId }</th>
					<th>${article.title }</th>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>