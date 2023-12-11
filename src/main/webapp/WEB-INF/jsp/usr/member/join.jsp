<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER JOIN" />

<%@ include file="../common/head2.jsp"%>

<!-- 주소찾기 카카오씨 쓰려면 있어야함. -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	let validLoginId = '';
	
	//정규식님
	const spacePattern = /\s/; //공백
	const idRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z0-9]{1,30}$/;//id
	const pwRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[.,\/?!@#$%^&*])[A-Za-z0-9.,\/?!@#$%^&*]{10,50}$/; //pw
	const nameRegex = /^[가-힣A-Za-z]{2,30}$/;  //name
	const nicknameRegex = /^[가-힣A-Za-z0-9]{2,30}$/; //nickname
	const emailRegex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; //email
	const pnRegex = /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/; //전화번호
	
	//onsubmit 부분
	const joinForm_onSubmit = function(form) {
		form.loginId.value = form.loginId.value.trim();//id
		form.loginPw.value = form.loginPw.value.trim();//비번
		form.loginPwChk.value = form.loginPwChk.value.trim();//비번확인
		form.name.value = form.name.value.trim();//이름
		form.nickname.value = form.nickname.value.trim();//닉네임
		form.cellphoneNum.value = form.cellphoneNum.value.trim();//전화번호
		form.email.value = form.email.value.trim();//이메일
		//주소
		
		//아이디
		if (form.loginId.value.length == 0) {
			alert('아이디를 입력해주세요');
			form.loginId.focus();
			return;
		}
		
		//아이디 검증. 일잘하고 있고.
		if (!idRegex.test(form.loginId.value)) { 
	        alert('아이디는 영문 대소문자와 숫자로 1~30자 이내여야 합니다.');
	        form.loginId.value ='';
	        form.loginId.focus();
	        return false;
	    }
		
		//ajax로 확인후 보여주는 부분
		if (form.loginId.value != validLoginId) {
			alert(form.loginId.value + '은(는) 사용할 수 없는 아이디입니다');
			form.loginId.value = '';
			form.loginId.focus();
			return;
		}
	
		//비밀번호
		if (form.loginPw.value.length == 0) {
			alert('비밀번호를 입력해주세요');
			form.loginPw.focus();
			return;
		}
		// 비밀번호 검증.일잘하고 있고.
	    if (!pwRegex.test(form.loginPw.value)) {
	        alert('비밀번호는 영문 대문자, 소문자, 숫자, 특수문자를 각각 1개 이상 포함하여 10~50자 이내여야 합니다.');
	        form.loginPw.value ='';
	        form.loginPw.focus();
	        return;
	    }
	
		//비밀번호확인
		if (form.loginPwChk.value.length == 0) {
			alert('비밀번호확인을 입력해주세요');
			form.loginPwChk.focus();
			return;
		}
		
		//비밀번호 확인은 정규식 설정 안해.어자피 안맞으면 비밀번호 안맞는다고 하니까.
		if (form.loginPw.value !== form.loginPwChk.value) {
			alert('비밀번호가 일치하지 않습니다');
			form.loginPw.value = '';
			form.loginPwChk.value = '';
			form.loginPw.focus();
			return;
		}
		//이름
		if (form.name.value.length == 0) {
			alert('이름을 입력해주세요');
			form.name.focus();
			return;
		}
		
		//이름 검증.
		 if (!nameRegex.test(form.name.value)) {
		    alert('이름은 한글 또는 영문으로 2~30자 이내여야 합니다.');
		    form.name.value ='';
		    form.name.focus();
		    return false;
	   }
		
		//닉네임
		if (form.nickname.value.length == 0) {
			alert('닉네임을 입력해주세요');
			form.nickname.focus();
			return;
		}
		//닉네임 검증.
		if (!nicknameRegex.test(form.nickname.value)) {
			 alert('닉네임은 한글, 영문 대소문자, 숫자로 구성된 2~30자 이내여야 합니다.');
			 form.nickname.value ='';
		    form.nickname.focus();
		    return false;
	   }
		
		//전화번호
		if (form.cellphoneNum.value.length == 0) {
			alert('전화번호를 입력해주세요');
			form.cellphoneNum.focus();
			return;
		}

		//전화번호 검증
		if (!pnRegex.test(form.cellphoneNum.value)) {
			 alert('전화번호는 예시처럼 입력해주세요.');
			form.cellphoneNum.value ='';
		    form.cellphoneNum.focus();
		    return false;
	   }
		
		//이메일
		if (form.email.value.length == 0) {
			alert('이메일을 입력해주세요');
			form.email.focus();
			return;
		}
		
		//이메일 검증
		if (!emailRegex .test(form.email.value)) {
			 alert('이메일은 예시처럼 입력해주세요.');
			form.email .value ='';
		    form.email.focus();
		    return false;
	   }
		

		form.submit();
	}
	
	//카카오 우편주소 사용하기 위한 스크립트
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
	
	
	//아이디 ajax 부분
	const loginIdDupChk = function(el) {
		el.value = el.value.trim();

		let loginIdDupChkMsg = $('#loginIdDupChkMsg'); // 확인하고 메세지 보일 부분.

		loginIdDupChkMsg.empty(); // 메세지 비우기 -> 안그러면조짐.

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

<section class="login">
	<div class="container">
		<form action="doJoin" method="post"
			onsubmit="joinForm_onSubmit(this); return false;">
			<div class="table-box-type">
				<table class="table table-lg">
					<tr">
						<th><span class="text-red-700">*</span>아이디</th>
						<td><input class="input input-bordered w-full max-w-xs input-sm"
							name="loginId" type="text" placeholder="아이디를 입력해주세요"
							onblur="loginIdDupChk(this);" />
							<div id="loginIdDupChkMsg" class="text-sm mt-2 h-5"></div></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input
							class="input input-bordered w-full max-w-xs input-sm"
							name="loginPw" type="text" placeholder="비밀번호를 입력해주세요" /></td>
					</tr>
					<tr>
						<th class="w-36">비밀번호 확인</th>
						<td><input
							class="input input-bordered w-full max-w-xs input-sm"
							name="loginPwChk" type="text" placeholder="비밀번호 확인을 입력해주세요" /></td>
					</tr>
					<tr>
						<th><span class="text-red-700">*</span>이름</th>
						<td><input
							class="input input-bordered w-full max-w-xs input-sm" name="name"
							type="text" placeholder="이름을 입력해주세요" /></td>
					</tr>
					<tr>
						<th><span class="text-red-700">*</span>닉네임</th>
						<td><input
							class="input input-bordered w-full max-w-xs input-sm"
							name="nickname" type="text" placeholder="닉네임을 입력해주세요" /></td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td><input
							class="input input-bordered w-full max-w-xs input-sm"
							name="cellphoneNum" type="text"
							placeholder="전화번호 입력해주세요 예) 010-1234-5678" /></td>
					</tr>
					<tr>
						<th><span class="text-red-700">*</span>이메일</th>
						<td><input
							class="input input-bordered w-full max-w-xs input-sm"
							name="email" type="text"
							placeholder="이메일 입력해주세요 예) asd123@gmail.com" />
							<!--<div class="btn btn-sm ml-1"><a href="/usr/member/doSendEmail">이메일로 인증번호 받기</a></div> -->
							</td>
					</tr>

					
					<tr>
						<th>주소</th>
						<td class="address-section p-2">
							<!-- kakao에서 우편번호 서비스 제공하는거 사용함. --> 
							<input name="postNum" class="my-1 input input-bordered w-full max-w-xs input-sm" type="text" id="sample6_postcode" placeholder="우편번호">
							<input class="my-1 input input-bordered w-full max-w-xs input-sm" type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
							<input name="adress" class="my-1 input input-bordered w-full max-w-xs input-sm" type="text" id="sample6_address" placeholder="주소"><br>
							<input name="detailAdress" class="my-1 input input-bordered w-full max-w-xs input-sm" type="text" id="sample6_detailAddress" placeholder="상세주소">
							<input name="extAdress" class="my-1 input input-bordered w-full max-w-xs input-sm" type="text" id="sample6_extraAddress" placeholder="참고항목">
						</td>
						
					</tr>

<!--					<tr>
						<th><span class="text-red-700">*</span>본인인증</th>
						<td class="flex"><input type="text" name="certification"
							placeholder="본인인증번호를 입력해주세요"
							class="input input-bordered w-full max-w-xs input-sm">
							<div class="btn btn-sm ml-1">인증번호 확인</div></td>
					</tr>
					이메일로 인증번호 받기를 순위에서 미루면서 우선 주석. -->

					<!-- 회원가입 -->
					<tr>
						<td class="text-center" colspan="2">
							<button class="btn btn-wide">회원가입</button>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</section>
</body>
</heml>