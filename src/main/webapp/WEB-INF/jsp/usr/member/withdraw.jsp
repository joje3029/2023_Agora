<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER LOGIN" />

<%@ include file="../common/head2.jsp"%>

<script>
	const joinForm_onSubmit = function(form){
		form.loginId.value = form.loginId.value.trim(); 
		form.loginPw.value = form.loginPw.value.trim();
		form.checkLoginPw.value = form.checkLoginPw.value.trim(); 
		form.name.value = form.name.value.trim();
		form.nickname.value = form.nickname.value.trim(); 
		form.email.value = form.email.value.trim();
		form.certification.value = form.certification.value.trim(); 
		
		console.log("test");
		
		if(form.loginId.value.length ==0){
			alert('아이디를 입력해주세요');
			form.loginId.focus();
			return
		}
		if(form.loginPw.value.length ==0){
			alert('비밀번호를 입력해주세요');
			form.loginPw.focus();
			return
		}
		if(form.checkLoginPw.value.length ==0){
			alert('확인비밀번호를 입력해주세요');
			form.checkLoginPw.focus();
			return
		}
		
		if(form.name.value.length ==0){
			alert('이름을 입력해주세요');
			form.name.focus();
			return
		}
		if(form.nickname.value.length ==0){
			alert('닉네임을 입력해주세요');
			form.nickname.focus();
			return
		}
		if(form.email.value.length ==0){
			alert('이메일을 입력해주세요');
			form.email.focus();
			return
		}
		if(form.certification.value.length ==0){
			alert('본인인증번호를 입력해주세요');
			form.certification.focus();
			return
		}
		

		form.submit();
	}
</script>
	<!-- 얘 위치 중앙인거 .login의 align-items: center; 때문임 css 수정하면서 고칠것 -->
	<section class="login">
		<h1 class="text-4xl">회원탈퇴</h1>
		<div class="phrases">
			<p>정말 탈퇴하시겠습니까?</p>
			<p>탈퇴하려는 이유를 체크해 주세요</p>
		</div>
		<div class="check_reason">
			<label>
	        <input type="checkbox" name="reason" value="apple">
	       	다른 사이트가 더 좋아서
		    </label>
		    <br>
		    <label>
		        <input type="checkbox" name="reason" value="banana">
		        사용빈도가 낮아서
		    </label>
		    <br>
		    <label>
		        <input type="checkbox" name="reason" value="orange">
		        콘텐츠 불만
		    </label>
		    <br>
		    <label class="ext">
		        <input type="checkbox" name="reason" value="orange">
		        기타
		        <!-- 기타를 체크하면 나오게 해야함 -->
		        <textarea class="textarea textarea-bordered rows="" cols="" placeholder="이유를 적어주세요"></textarea>
		    </label>
		</div>
		<div>
			<p>현재 사용중인 비밀번호를 입력하세요</p>
			<input class="input input-bordered w-full max-w-xs input-sm" type="text" placeholder="비밀번호를 입력하세요"/>
		</div>
		<div>
			<p>비밀번호를 잊으셨나요?</p>
			<div><a href="">비밀번호 재설정</a></div><!-- 비밀번호 페이지로 링크 연결하기 -->
		</div>
		<div class="button_center">
				<button class="btn">저장</button>
		</div>
	</section>
</body>
</html>