<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER LOGIN" />

<%@ include file="../common/head2.jsp"%>

<!-- 주소찾기 카카오씨 쓰려면 있어야함. -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	
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
	
  //정규식님
	const spacePattern = /\s/; //공백
	const idRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z0-9]{1,30}$/;//id
	const pwRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[.,\/?!@#$%^&*])[A-Za-z0-9.,\/?!@#$%^&*]{10,50}$/; //pw
	const nameRegex = /^[가-힣A-Za-z]{2,30}$/;  //name
	const nicknameRegex = /^[가-힣A-Za-z0-9]{2,30}$/; //nickname
	const emailRegex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; //email
	const pnRegex = /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/; //전화번호
	
	// 보내는 onsubmit
    const memberModifyForm_onSubmit = function(form){
    	form.name.value = form.name.value.trim(); //이름
		form.nickname.value = form.nickname.value.trim(); //닉네임
		form.email.value = form.email.value.trim(); //이메일
		form.cellphoneNum.value = form.cellphoneNum.value.trim(); //전화번호
		form.postNum.value = form.postNum.value.trim(); //우편번호
		form.adress.value = form.adress.value.trim(); //주소
		form.detailAdress.value = form.detailAdress.value.trim(); //상세주소
		form.extAdress.value = form.extAdress.value.trim(); //기타
		
		// 이름
		if(form.name.value.length ==0){
			alert('이름을 입력해주세요');
			form.name.focus();
			return
		}
		
		//이름 검증.
		 if (!nameRegex.test(form.name.value)) {
		    alert('이름은 한글 또는 영문으로 2~30자 이내여야 합니다.');
		    form.name.value ='';
		    form.name.focus();
		    return false;
	   }
		//닉네임
		if(form.nickname.value.length ==0){
			alert('닉네임을 입력해주세요');
			form.nickname.focus();
			return
		}
		
		//닉네임 검증.
		if (!nicknameRegex.test(form.nickname.value)) {
			 alert('닉네임은 한글, 영문 대소문자, 숫자로 구성된 2~30자 이내여야 합니다.');
			 form.nickname.value ='';
		    form.nickname.focus();
		    return false;
	   }
		//이메일
		if(form.email.value.length ==0){
			alert('이메일을 입력해주세요');
			form.email.focus();
			return
		}
		//이메일 검증
		if (!emailRegex .test(form.email.value)) {
			 alert('이메일은 예시처럼 입렵해주세요.');
			form.email .value ='';
		    form.email.focus();
		    return false;
	   }
		
		//우편번호
		if(form.postNum.value.length ==0){
			alert('우편번호를 입력해주세요');
			form.postNum.focus();
			return
		}
		// 주소
		if(form.adress.value.length ==0){
			alert('주소를 입력해주세요');
			form.adress.focus();
			return
		}
		//상세주소
		if(form.detailAdress.value.length ==0){
			alert('상세주소를 입력해주세요');
			form.detailAdress.focus();
			return
		}
		//기타 주소
		if(form.extAdress.value.length ==0){
			alert('기타주소을 입력해주세요');
			form.extAdress.focus();
			return
		}
		
		form.submit();
    } 
	
	
</script>

<section class="login">
	<h1 class="text-4xl">회원정보수정</h1>
	<form action="/usr/member/domodify" method="post"
		onsubmit="memberModifyForm_onSubmit(this); return false;">
		<input type="hidden" name="id" value="${member.id }" />
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
					value="${member.name }" type="text" placeholder="이름을 입력해주세요" /></td>
			</tr>
			<tr>
				<th><span class="text-red-700">*</span>닉네임</th>
				<td><input
					class="input input-bordered w-full max-w-xs input-sm"
					value="${member.nickname } " name="nickname" type="text"
					placeholder="닉네임을 입력해주세요" /></td>
				<!-- 정규식하고나서 닉네임 중복확인은 ajax로 하기 -> 중복로그인 코드 참고 -->
				</td>
			</tr>
			<tr>
				<th><span class="text-red-700">*</span>이메일</th>
				<td><input
					class="input input-bordered w-full max-w-xs input-sm" name="email"
					value="${member.email }" type="text"
					placeholder="이메일 입력해주세요 예) asd123@gmail.com" /> <!--<div class="btn btn-sm ml-1"><a href="/usr/member/doSendEmail">이메일로 인증번호 받기</a></div> -->
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input
					class="input input-bordered w-full max-w-xs input-sm"
					value="${membertel }" name="cellphoneNum" type="text"
					placeholder="전화번호 입력해주세요 예) 010-1234-5678" /></td>
			</tr>
			<tr>
				<th>주소</th>
				<td class="address-section p-2">
							<!-- kakao에서 우편번호 서비스 제공하는거 사용함. --> 
							<input name="postNum" value="${member.postNum }" class="my-1 input input-bordered w-full max-w-xs input-sm" type="text" id="sample6_postcode" placeholder="우편번호">
							<input class="my-1 input input-bordered w-full max-w-xs input-sm" type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
							<input name="adress" value="${member.adress }" class="my-1 input input-bordered w-full max-w-xs input-sm" type="text" id="sample6_address" placeholder="주소"><br>
							<input name="detailAdress" value="${member.detailAdress }" class="my-1 input input-bordered w-full max-w-xs input-sm" type="text" id="sample6_detailAddress" placeholder="상세주소">
							<input name="extAdress" value="${member.extAdress }" class="my-1 input input-bordered w-full max-w-xs input-sm" type="text" id="sample6_extraAddress" placeholder="참고항목">
						</td>

			</tr>
			<tr>
				<td class="text-center" colspan="2"><button class="btn">수정하기</button></td>
			</tr>
		</table>
	</form>
	<div class="btns mt-2">
		<div class="flex justify-between">
			<button class="btn mr-2" onclick="history.back();">취소</button>
			<a class="btn" href="chagePw">비밀번호 변경</a>
			<!-- 이거 바로 가고 있는데 그전에 본인인증 따로 하게 해야함 -> 모든 웹사이트는 이런 중요한건 본인인증 한번 더 함. -->
		</div>
	</div>

</section>
</body>
</html>