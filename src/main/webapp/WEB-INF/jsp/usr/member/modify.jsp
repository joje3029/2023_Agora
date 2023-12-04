<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER LOGIN" />

<%@ include file="../common/head2.jsp"%>

<!-- 주소찾기 카카오씨 쓰려면 있어야함. -->
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	const memberModifyForm_onSubmit  = function(form){
		form.loginPw.value = form.loginPw.value.trim();//비번
		form.loginPwChk.value = form.loginPwChk.value.trim();//비번화긴
		form.name.value = form.name.value.trim();//이름
		form.nickname.value = form.nickname.value.trim();//닉네임
		form.cellphoneNum.value = form.cellphoneNum.value.trim();//전화번호
		form.email.value = form.email.value.trim();//이메일
		//주소
		
		alert("지나감?");
		
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
	
	//카카오 우편주소 사용하기 위한 스크립트
	//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>

<section class="login">
	<h1 class="text-4xl">회원정보수정</h1>
	<form action="domodify" method="post"
		onsubmit="memberModifyForm_onSubmit(this); return false;">
		<table>
			<tr colspan="2">
				<p class="text-xs">
					<span class="text-red-700">*</span>표시는 필수 기입항목입니다.
				</p>
			</tr>
			<tr>
				<th class="w-28"><span class="text-red-700">*</span>이름</th>
				<td><input
					class="input input-bordered w-full max-w-xs input-sm" name="name"
					type="text" placeholder="이름을 입력해주세요" /></td>
			</tr>
			<tr>
				<th><span class="text-red-700">*</span>닉네임</th>
				<td><input
					class="input input-bordered w-full max-w-xs input-sm"
					name="nickname" type="text" placeholder="닉네임을 입력해주세요" /></td>
				<!-- 정규식하고나서 닉네임 중복확인은 ajax로 하기 -> 중복로그인 코드 참고 -->
				</td>
			</tr>
			<tr>
				<th><span class="text-red-700">*</span>이메일</th>
				<td><input
					class="input input-bordered w-full max-w-xs input-sm" name="email"
					type="text" placeholder="이메일 입력해주세요 예) asd123@gmail.com" /> <!--<div class="btn btn-sm ml-1"><a href="/usr/member/doSendEmail">이메일로 인증번호 받기</a></div> -->
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input
					class="input input-bordered w-full max-w-xs input-sm"
					name="cellphoneNum" type="text"
					placeholder="전화번호 입력해주세요 예) 010-1234-5678" /></td>
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
					type="text" id="sample4_roadAddress" placeholder="도로명주소"> <input
					class="my-1 input input-bordered w-full max-w-xs input-sm"
					type="text" id="sample4_jibunAddress" placeholder="지번주소"> <span
					id="guide" style="color: #999; display: none"></span> <input
					class="my-1 input input-bordered w-full max-w-xs input-sm"
					type="text" id="sample4_detailAddress" placeholder="상세주소">
					<input class="my-1 input input-bordered w-full max-w-xs input-sm"
					type="text" id="sample4_extraAddress" placeholder="참고항목">
				</td>
			</tr>
			<!-- 			<tr>
				<th class="w-28"><span class="text-red-700">*</span>본인인증</th>
				<td class="flex"><input type="text" name="certification"
					placeholder="본인인증번호를 입력해주세요"
					class="input input-bordered w-full max-w-xs input-sm">
					<div class="btn btn-sm ml-1">인증번호</div></td>
			</tr>
			 이메일 인증만 뒤로 미루기-->
			<tr>
				<td class="text-center" colspan="2"><button
						class="btn">수정</button></td>
			</tr>
		</table>
	</form>
	<!-- 		<div class="button_center flex">
			<div class="btn mr-2">취소</div>
			<button class="btn">저장</button>
		</div> -->
	<div class="btns mt-2">
		<div class="flex justify-between">
			<button class="btn mr-2"
				onclick="history.back();">취소</button>
			<a class="btn"
				href="/usr/member/dofindPw">비밀번호 변경</a>
		</div>
	</div>

</section>
</body>
</html>