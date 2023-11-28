<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER LOGIN" />

<%@ include file="../common/head2.jsp"%>

<script>
	const loginForm_onSubmit = function(form){
		form.loginId.value = form.loginId.value.trim(); // loginId는 key니까 그 키와 대칭되는 값으로 해야함.
		form.loginPw.value = form.loginPw.value.trim();
		
		if(form.loginId.value.length ==0){
			alret('아이디를 입력해주세요');
			form.loginId.focus();
			return
		}
		if(form.loginPw.value.length ==0){
			alret('비밀번호를 입력해주세요');
			form.loginPw.focus();
			return
		}
		
		form.submit(); // 여기서 보내고 나서 아래에서 return false하는거라 이미 보낸 상태이기 때문에 괜찮음.
	}
</script>

		<section class="login">
			<h1 class="text-4xl">비밀번호 찾기</h1>
			<form action="doLogin" method="post"
				onsubmit="loginForm_onSubmit(this); return false;">
				<table>
					<tr colspan="2"><p class="text-xs"><span class="text-red-700">*</span>새로운 비밀번호를 설정해 주세요.</p></tr>
					<tr>
						<th><span class="text-red-700">*</span>새 비밀번호</th>
						<td><input type="text" name="loginPw" placeholder="새 비밀번호 입력해주세요"
							class="input input-bordered w-full max-w-xs input-sm"></td>
					</tr>
					<tr>
						<th><span class="text-red-700">*</span>새 비밀번호 확인</th>
						<td><input type="text" name="checkLoginPw"
							placeholder="새 확인 비밀번호 입력해 주세요"
							class="input input-bordered w-full max-w-xs input-sm"></td>
					</tr>
					<tr>
						<th colspan="2">
							<button class="btn">비밀번호 변경하기</button>
						</th>
					</tr>
				</table>
			</form>
		</section>
	</body>
</html>