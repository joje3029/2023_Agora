<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER JOIN" />

<%@ include file="../common/head.jsp"%>

<script>
	let validLoginId = '';

	const joinForm_onSubmit = function(form) {
		form.loginId.value = form.loginId.value.trim();
		form.loginPw.value = form.loginPw.value.trim();
		form.loginPwChk.value = form.loginPwChk.value.trim();
		form.name.value = form.name.value.trim();
		form.nickname.value = form.nickname.value.trim();
		form.cellphoneNum.value = form.cellphoneNum.value.trim();
		form.email.value = form.email.value.trim();

		if (form.loginId.value.length == 0) {
			alert('아이디를 입력해주세요');
			form.loginId.focus();
			return;
		}

		if (form.loginId.value != validLoginId) {
			alert(form.loginId.value + '은(는) 사용할 수 없는 아이디입니다');
			form.loginId.value = '';
			form.loginId.focus();
			return;
		}

		if (form.loginPw.value.length == 0) {
			alert('비밀번호를 입력해주세요');
			form.loginPw.focus();
			return;
		}

		if (form.loginPwChk.value.length == 0) {
			alert('비밀번호확인을 입력해주세요');
			form.loginPwChk.focus();
			return;
		}

		if (form.loginPw.value != form.loginPwChk.value) {
			alert('비밀번호가 일치하지 않습니다');
			form.loginPw.value = '';
			form.loginPwChk.value = '';
			form.loginPw.focus();
			return;
		}

		if (form.name.value.length == 0) {
			alert('이름을 입력해주세요');
			form.name.focus();
			return;
		}

		if (form.nickname.value.length == 0) {
			alert('닉네임을 입력해주세요');
			form.nickname.focus();
			return;
		}

		if (form.cellphoneNum.value.length == 0) {
			alert('전화번호를 입력해주세요');
			form.cellphoneNum.focus();
			return;
		}

		if (form.email.value.length == 0) {
			alert('이메일을 입력해주세요');
			form.email.focus();
			return;
		}

		form.submit();
	}

	const loginIdDupChk = function(el) {
		el.value = el.value.trim();

		let loginIdDupChkMsg = $('#loginIdDupChkMsg');

		loginIdDupChkMsg.empty();

		if (el.value.length == 0) {
			loginIdDupChkMsg.removeClass('text-green-500');
			loginIdDupChkMsg.addClass('text-red-500');
			loginIdDupChkMsg.html('<span>아이디는 필수 입력 정보입니다</span>');
			return;
		}

		$.ajax({
			url : "loginIdDupChk",
			method : "get",
			data : {
				"loginId" : el.value
			},
			dataType : "json",
			success : function(data) {
				if (data.success) {
					loginIdDupChkMsg.removeClass('text-red-500');
					loginIdDupChkMsg.addClass('text-green-500');
					loginIdDupChkMsg.html(`<span>\${data.msg}</span>`);
					validLoginId = el.value;
				} else {
					loginIdDupChkMsg.removeClass('text-green-500');
					loginIdDupChkMsg.addClass('text-red-500');
					loginIdDupChkMsg.html(`<span>\${data.msg}</span>`);
					validLoginId = '';
				}
			},
			error : function(xhr, status, error) {
				console.error("ERROR : " + status + " - " + error);
			}
		})
	}
</script>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<form action="doJoin" method="post"
			onsubmit="joinForm_onSubmit(this); return false;">
			<div class="table-box-type">
				<table class="table table-lg">
					<tr">
						<th><span class="text-red-700">*</span>아이디</th>
						<td><input class="input input-bordered input-primary w-9/12"
							name="loginId" type="text" placeholder="아이디를 입력해주세요"
							onblur="loginIdDupChk(this);" />
							<div id="loginIdDupChkMsg" class="text-sm mt-2 h-5"></div></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input class="input input-bordered w-full max-w-xs input-sm"
							name="loginPw" type="text" placeholder="비밀번호를 입력해주세요" /></td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td><input class="input input-bordered w-full max-w-xs input-sm"
							name="loginPwChk" type="text" placeholder="비밀번호 확인을 입력해주세요" /></td>
					</tr>
					<tr>
						<th><span class="text-red-700">*</span>이름</th>
						<td><input class="input input-bordered w-full max-w-xs input-sm"
							name="name" type="text" placeholder="이름을 입력해주세요" /></td>
					</tr>
					<tr>
						<th><span class="text-red-700">*</span>닉네임</th>
						<td><input class="input input-bordered w-full max-w-xs input-sm"
							name="nickname" type="text" placeholder="닉네임을 입력해주세요" /></td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td><input class="input input-bordered w-full max-w-xs input-sm"
							name="cellphoneNum" type="text" placeholder="전화번호 입력해주세요 예) 010-1234-5678" /></td>
					</tr>
					<tr>
						<th><span class="text-red-700">*</span>이메일</th>
						<td><input class="input input-bordered w-full max-w-xs input-sm"
							name="email" type="text" placeholder="이메일 입력해주세요 예) asd123@gmail.com" /></td>
					</tr>

					<tr>
						<th>주소</th>
						<td class="address-section p-2">
							<!-- kakao에서 우편번호 서비스 제공하는거 사용함. --> <input
							class="my-1 input input-bordered w-full max-w-xs input-sm"
							type="text" id="sample4_postcode" placeholder="우편번호"> <input
							class="my-1 btn btn-sm ml-1" type="button"
							onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
							<input class="my-1 input input-bordered w-full max-w-xs input-sm"
							type="text" id="sample4_roadAddress" placeholder="도로명주소">
							<input class="my-1 input input-bordered w-full max-w-xs input-sm"
							type="text" id="sample4_jibunAddress" placeholder="지번주소">
							<span id="guide" style="color: #999; display: none"></span> <input
							class="my-1 input input-bordered w-full max-w-xs input-sm"
							type="text" id="sample4_detailAddress" placeholder="상세주소">
							<input class="my-1 input input-bordered w-full max-w-xs input-sm"
							type="text" id="sample4_extraAddress" placeholder="참고항목">
						</td>
					</tr>

					<tr>
						<th><span class="text-red-700">*</span>본인인증</th>
						<td class="flex"><input type="text" name="certification"
							placeholder="본인인증번호를 입력해주세요"
							class="input input-bordered w-full max-w-xs input-sm">
							<div class="btn btn-sm ml-1">인증번호 확인</div></td>
					</tr>

					<!-- 회원가입 -->
					<tr>
						<td class="text-center" colspan="2"><button
								class="btn btn-wide">회원가입</button></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</section>

<%@ include file="../common/foot.jsp"%>