<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER LOGIN" />

<%@ include file="../common/head2.jsp"%>

<script>
	const pwRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[.,\/?!@#$%^&*])[A-Za-z0-9.,\/?!@#$%^&*]{10,50}$/; //pw

	const chagePwForm_onSubmit = function(form){
		
		
		form.loginPw.value = form.loginPw.value.trim(); // 새 비번
		form.checkLoginPw.value = form.checkLoginPw.value.trim(); // 새 비번 확인

		console.log(form.loginPw.value);
		console.log(form.checkLoginPw.value);
		
		if(form.loginPw.value==0){
			alert('새비밀번호를 입력해주세요');
			form.loginPw.focus();
			return;
		}
		
		if(!pwRegex.test(form.checkLoginPw.value)){
			alert('비밀번호는 영문 대문자, 소문자, 숫자, 특수문자를 각각 1개 이상 포함하여 10~50자 이내여야 합니다.');
			form.checkLoginPw.value = '';
			form.checkLoginPw.focus();
			return;
		}
		
		if(form.checkLoginPw.value==0){
			alert('새비밀번호 확인을 입력해주세요');
			form.checkLoginPw.focus();
			return;
		}
		
		if(form.loginPw.value !== form.checkLoginPw.value){
			alert('비밀번호가 일치하지 않습니다');
			form.loginPw.value = '';
			form.checkLoginPw.value ='';
			form.loginPw.focus();
			return;
		}
		
		form.submit();
	}
</script>

		<section class="login">
			<h1 class="text-4xl">비밀번호 변경</h1>
			<form action=doChagePw method="post"
				onsubmit="chagePwForm_onSubmit(this); return false;">
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