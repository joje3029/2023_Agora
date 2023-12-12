<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER LOGIN" />

<%@ include file="../common/head.jsp"%>


<section class="login">
	<h1 class="text-4xl">내 회원정보</h1>
	<div class="width80">
		<table>
			<tr>
				<th class="w-28"><span class="text-red-700">*</span>이름</th>
				<td>${member.name}</td>
			</tr>
			<tr>
				<th><span class="text-red-700">*</span>닉네임</th>
				<td>${member.nickname}</td>
				<!-- 정규식하고나서 닉네임 중복확인은 ajax로 하기 -> 중복로그인 코드 참고 -->
			</tr>
			<tr>
				<th><span class="text-red-700">*</span>이메일</th>
				<td>${member.email}</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>${member.telno}</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${member.adress} ${member.detailAdress} ${member.extAdress}</td>
			</tr>
		</table>
	</div>
		<section class="width80 flex justify-end">
			<div class="btn">
				<a href="/">홈으로 돌아가기</a>
			</div>
			&nbsp;&nbsp;
			<div class="btn">
				<a href="modify">회원정보 수정</a>
			</div>
			&nbsp;&nbsp;
			<div class="btn">
					<a href="IdentityVerification">비밀번호 변경</a>
			</div>
		</section>

</section>
</body>
</html>