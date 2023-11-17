<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER LOGIN" />

<%@ include file="../common/head.jsp"%>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type">
			<table>
					<tr>
						<th>로그인</th>
						<td><input type="text" name="loginId" placeholder="로그인 입력해주세요"/></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="text" name="loginPw" placeholder="비밀번호 입력해주세요"/></td>
					</tr>
					<tr>
						<td colspan="2"><button>로그인</button></td>
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