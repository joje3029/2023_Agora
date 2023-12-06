<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="USER LOGIN" />

<%@ include file="../common/head2.jsp"%>

<script>
		
		$(document).ready(function() {
		    const loginIdInput = $('#loginId');
		    const loginPwInput = $('#loginPw');

		    loginIdInput.on('input', function() {
		        const inputValue = $(this).val();
		        
		        if (inputValue.length > 30) {
		        	 $(this).val('');// 내용 비움.
		            alert('아이디는 영문 대소문자와 숫자로 1~30자 이내여야 합니다.');
		        }
		    });
		    
		    loginPwInput.on('input', function() {
		    	const inputValue = $(this).val();
				        
				if (inputValue.length > 50) {
					$(this).val('');// 내용 비움.
				    alert('비밀번호는 영문 대문자, 소문자, 숫자, 특수문자를 각각 1개 이상 포함하여 10~50자 이내여야 합니다.');
					}
				});
		});
		
		// 아무것도 입력 안했을 때
			const loginForm_onSubmit = function(form) {
				form.loginId.value = form.loginId.value.trim();
				form.loginPw.value = form.loginPw.value.trim();
				
				if (form.loginId.value.length == 0) {
					alert('아이디를 입력해주세요');
					form.loginId.focus();
					return;
				}
				
				if (form.loginPw.value.length == 0) {
					alert('비밀번호를 입력해주세요');
					form.loginPw.focus();
					return;
				}
			// 글자수 제한
				
				form.submit();
			}
		</script>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<form action="doLogin" method="post"
			onsubmit="loginForm_onSubmit(this); return false;">
			<div class="table-box-type">
				<table class="table table-lg">
					<tr>
						<th>아이디</th>
						<td><input id="loginId"
							class="input input-bordered input-primary w-9/12" name="loginId"
							type="text" placeholder="아이디를 입력해주세요" /></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input id="loginPw"
							class="input input-bordered input-primary w-9/12" name="loginPw"
							type="text" placeholder="비밀번호를 입력해주세요" /></td>
					</tr>
					<tr>
						<td class="text-center" colspan="2"><button
								class="btn-text-color btn btn-wide btn-outline">로그인</button></td>
					</tr>
				</table>
			</div>
		</form>

		<div class="btns mt-2">
			<button class="btn-text-color btn btn-outline btn-sm"
				onclick="history.back();">뒤로가기</button>
		</div>
	</div>
</section>
</body>
</html>