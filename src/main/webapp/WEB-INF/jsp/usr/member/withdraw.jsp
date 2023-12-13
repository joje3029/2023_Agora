<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER LOGIN" />

<%@ include file="../common/head2.jsp"%>

<script>
	// 비번 일치 여부 Ajax
	const passwdCheck = function(el) {
		el.value = el.value.trim();
		
		let passwdCheck = $('#passwdCheck');
		
		passwdCheck.empty();
		
		if (el.value.length == 0) {
			passwdCheck.removeClass('text-green-500');
			passwdCheck.addClass('text-red-500');
			passwdCheck.html('<span>비밀번호를 입력 해 주세요 1</span>');
			return;
		}
		
		$.ajax({
			url: "passwdCheck",
			method: "get",
			data: {
					"passWd" : el.value
				},
			dataType: "json",
			success: function(data) {
				if (data.success) {
					passwdCheck.removeClass('text-red-500');
					passwdCheck.addClass('text-green-500');
					passwdCheck.html(`<span>\${data.msg}</span>`);
					validLoginId = el.value;
				} else {
					passwdCheck.removeClass('text-green-500');
					passwdCheck.addClass('text-red-500');
					passwdCheck.html(`<span>\${data.msg}</span>`);
					validLoginId = '';
				}
			},
			error: function(xhr, status, error) {
				console.error("ERROR : " + status + " - " + error);
			}
		})
	}
	
	
	$(document).ready(function() {
	    // "기타" 체크박스에 클릭 이벤트 리스너를 추가
	    $('input[name="reason"][value="extReaseon"]').click(function() {
	        // 기타를 선택했을 때
	        if ($(this).prop('checked')) {
	            $('.textarea').prop('disabled', false);
	        } else {
	            // 기타를 선택하지 않았을 때는 textarea 비활성화
	            $('.textarea').prop('disabled', true);
	        }
	    });

	    // 다른 이유 체크박스에 클릭 이벤트 리스너를 추가
	    $('input[name="reason"]:not([value="extReaseon"])').click(function() {
	        // 다른 이유를 선택했을 때
	        if ($(this).prop('checked')) {
	            // 기타 체크박스의 체크를 해제하고, textarea 비활성화
	            $('input[name="reason"][value="extReaseon"]').prop('checked', false);
	            $('.textarea').prop('disabled', true);
	        }
	    });

	    // 탈퇴 버튼 클릭 시 이벤트 처리
	    $('.btn').click(function() {
	        // 체크된 이유가 있는지 확인
	        var checkedReason = $('input[name="reason"]:checked').length > 0;
	        var passwdLength = $('#passwdCheck').val().length; // 비번입력하세요

	        console.log(checkedReason);
	        console.log(passwdLength);

	        // 체크된 이유가 없다면 경고 표시 및 form 제출 방지
	        if (!checkedReason) {
	            alert("탈퇴 이유를 선택해주세요.");
	            return false;
	        }

	        if (passwdLength == 0) {
	            $('#passwdCheck').removeClass('text-green-500');
	            $('#passwdCheck').addClass('text-red-500');
	            $('#passwdCheck').html('<span>비밀번호를 입력 해 주세요 2</span>');
	            return false;
	        }

	        // 폼 엘리먼트를 찾아서 변수에 할당
	        var form = $('#withdrawForm');

	        // 폼 엘리먼트를 제출합니다.
	        form.submit();
	    });
	});

</script>



	<!-- 얘 위치 중앙인거 .login의 align-items: center; 때문임 css 수정하면서 고칠것 -->
	<section class="login">
		<h1 class="text-4xl">회원탈퇴</h1>
		<div class="phrases">
			<p>정말 탈퇴하시겠습니까?</p>
			<p>탈퇴하려는 이유를 하나만 체크해 주세요</p>
		</div>
		<div class="check_reason">
			<form action="/usr/member/doWithdraw" method="post" onsubmit="withdrawForm_onSubmit(this); return false;">
			
				<label>
		        <input type="radio" name="reason" value="otherSite" class="radio radio-success radio-xs">
		       	다른 사이트가 더 좋아서
			    </label>
			    <br>
			    <label>
			        <input type="radio" name="reason" value="notUse" class="radio radio-success radio-xs">
			        사용빈도가 낮아서
			    </label>
			    <br>
			    <label>
			        <input type="radio" name="reason" value="contentsDiscontent" class="radio radio-success radio-xs">
			        콘텐츠 불만
			    </label>
			    <br>
			    <label class="ext">
			        <input type="radio" name="reason" value="extReaseon" class="radio radio-success radio-xs">
			        기타
			        <!-- 기타를 체크하면 나오게 해야함 -->
			        <textarea name="detailReason" class="textarea textarea-bordered resize-none" disabled="disabled" placeholder="이유를 적어주세요"></textarea>
			    </label>
			 </form>
		</div>
		<div>
			<p>현재 사용중인 비밀번호를 입력하세요</p>
			<!-- 여기는 Ajax로 DB갔다와야겠다. 수업때 아이디 중복을 참고하기! -->
			<input class="input input-bordered w-full max-w-xs input-sm" type="text" name="passWd" placeholder="비밀번호를 입력하세요" onblur="passwdCheck(this);"/>
			<div id="passwdCheck" class="text-sm mt-2 h-5"></div>
		</div>
		<div>
			<p>비밀번호를 잊으셨나요?</p>
			<div class="btn"><a href="/usr/member/IdentityVerification">비밀번호 재설정</a></div><!-- 비밀번호 페이지로 링크 연결하기 -->
		</div>
		<div class="button_center">
				<button class="btn">탈퇴하기</button>
		</div>
	</section>
</body>
</html>