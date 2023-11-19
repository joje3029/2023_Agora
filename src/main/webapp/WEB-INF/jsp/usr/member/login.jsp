<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER LOGIN" />

<%@ include file="../common/head.jsp"%>

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

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type">
			<form action="doLogin" method="post" onsubmit="loginForm_onSubmit(this); return false;"> <!-- 버튼이 아닌 여기에 onsubmit으로 하는 이유: 결국 실행할 컨트롤러로 정보를 넘기는게 가능한 이유는 button이 아니라 form이니까. -->
				<table>
					<tr>
						<th>로그인</th>
						<td><input type="text" name="loginId"
							placeholder="로그인 입력해주세요" /></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="text" name="loginPw"
							placeholder="비밀번호 입력해주세요" /></td>
					</tr>
					<tr>
						<td colspan="2"><button>로그인</button></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="flex">
			<button class="hover:underline hover:text-green-700 mr-16"
				onclick="history.back();">뒤로가기</button>
		</div>
	</div>
</section>

<%@ include file="../common/foot.jsp"%>