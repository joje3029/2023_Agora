<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="USER LOGIN" />

<%@ include file="../common/head2.jsp"%>

<script>
	const loginForm_onSubmit = function(form){
		form.loginId.value = form.loginId.value.trim(); // loginId는 key니까 그 키와 대칭되는 값으로 해야함.
		form.loginPw.value = form.loginPw.value.trim();
		
		const idRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z0-9]{1,30}$/;//id
		const pwRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[.,\/?!@#$%^&*])[A-Za-z0-9.,\/?!@#$%^&*]{10,50}$/; //pw

		
		//아무것도 입력 안했을 때
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
		
		//검증
	    // test메서드를 사용하여 주저진 문자열이 정규식과 맞지 않는 경우를 검사.
	     // 아이디 검증
	     if (!idRegex.test(form.loginId.value)) {
	        alert('아이디는 영문 대소문자와 숫자로 1~30자 이내여야 합니다.');
	        form.loginId.value = '';
	        form.loginId.focus();
	        return false;
	    }

	    // 비밀번호 검증
	    if (!pwRegex.test(form.loginPw.value)) {
	        alert('비밀번호는 영문 대문자, 소문자, 숫자, 특수문자를 각각 1개 이상 포함하여 10~50자 이내여야 합니다.');
	        form.loginPw.value = '';
	        form.loginPw.focus();
	        return false;
	    }
		
		form.submit();
	}
</script>

			<section class="login">
	            <h1 class="text-4xl">로그인</h1>
	            <form action="doLogin" method="post"
	                    onsubmit="loginForm_onSubmit(this); return false;">
	                <table>
	                    <tr>
	                        <th>아이디</th>
	                        <td><input type="text" name="loginId" placeholder="로그인 입력해주세요" class="input input-bordered w-full max-w-xs input-sm"></td>
	                    </tr>
	                    <tr>
	                        <th>비밀번호</th>
	                        <td><input type="text" name="loginPw" placeholder="비밀번호 입력해주세요" class="input input-bordered w-full max-w-xs input-sm"></td>
	                    </tr>
	                </table>
	                <div class="button_center">
	                    <button class="btn">로그인</button>
	                </div>
	                    
	            </form>
	            <div class="account_row">
	                <div class="account_left"><a href="/usr/member/join">회원가입</a></div>
	                <div class="account_right">
	                    <div class="loginId"><a href="">아이디찾기</a></div>
	                    <div class="passWord"><a href="">비밀번호</a></div>
	                </div>
	            </div>
	            <hr>
	            <div class="external_login">
	                <img src="/resource/static.image/kakao_login.png" alt="카카오로그인">
	                <img src="/resource/static.image/naver_login.png" alt="네이버로그인">
	                <img src="/resource/static.image/google_login.png" alt="구글로그인">
	                <div>QR코드들어올자리</div>
	            </div>
	        
	
	    </section>	
	</body>
</html>