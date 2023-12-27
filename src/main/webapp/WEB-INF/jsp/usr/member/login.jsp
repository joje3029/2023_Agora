<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="USER LOGIN" />

<%@ include file="../common/head2.jsp"%>
<!-- 네이버 로그인을 위한 cdn-->
<script
	src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js"
	charset="utf-8"></script>

<!-- 구글 로그인을 위해 -->
<meta name="google-signin-client_id"
	content="156368883589-0hdsi099embd282knv5uhadn8t93sem9.apps.googleusercontent.com">

<script>
		// 로그인이랑 비밀번호 글자 제한
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
				
				form.submit();
			}
		</script>

		<section class="login boder">
			<h1 class="text-4xl">로그인</h1>
			<div class="container">
				<form action="doLogin" method="post"
					onsubmit="loginForm_onSubmit(this); return false;">
					<div class="table-box-type">
						<table>
							<tr>
								<th>아이디</th>
								<td><input type="text" name="loginId"
									placeholder="로그인 입력해주세요"
									class="input input-bordered w-full max-w-xs input-sm"></td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td><input type="text" name="loginPw"
									placeholder="비밀번호 입력해주세요"
									class="input input-bordered w-full max-w-xs input-sm"></td>
							</tr>
							<tr>
								<td class="text-center" colspan="2"><button class="btn">로그인</button></td>
							</tr>
						</table>
					</div>
				</form>
			</div>
			<div class="account_row">
				<div class="account_left">
					<a href="/usr/member/join">회원가입</a>
				</div>
				<div class="account_right">
					<div class="loginId">
						<a href="/usr/member/findId">아이디찾기</a>
					</div>
					<div class="passWord">
						<a href="/usr/member/findPw">비밀번호</a>
					</div>
				</div>
			</div>
			<hr>
			<div class="external_login">
				<div>
					<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=b17693f42efb99ea5feb3e636ab6eb1b&redirect_uri=http://localhost:8081/usr/member/kakaoLogin&prompt=select_account">
						<img src="/resource/images/kakao_login.png" alt="카카오로그인">
			        </a>
	            </div>
				
				<div>
					<a href="/usr/snsmember/naverLogin"> <img
						src="/resource/images/naver_login.png" alt="네이버로그인">
					</a>
				</div>
				<div>
					<a href="/usr/snsmember/googleLogin"> <img
						src="/resource/images/google_login.png" alt="구글로그인">
					</a>
				</div>
				<div>QR코드들어올자리</div>
			</div>
		</section>
	</body>
</html>