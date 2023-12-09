<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<c:set var="pageTitle" value="${board.name }" />

<%@ include file="../common/head2.jsp"%>

<script>

	$(document).ready(function() {
	    const roomNameInput = $('#roomName');
	    const maxLength = 50;
	
	    roomNameInput.on('input', function() {
	    	
	    	const roomNameInput = $(this).val();
	        
	        if (roomNameInput.length > maxLength) {
	            $(this).val(roomNameInput.slice(0, maxLength)); // 최대 길이까지만 잘라냄
	            alert('제목은 50자 이내여야 합니다.');
	        }
	    });
	});

	const createForm_onSubmit = function(form){
		form.roomName.value = form.roomName.value.trim();
		
		if(form.roomName.value.length ==0){
			alert('토론방이름을 입력해주세요');
			form.roomName.focus();
			return
		}
		
		form.submit(); 
	}
</script>

		<section class="login border">
	        <h1 class="text-4xl">토론방 만들기</h1>
	        <form action="docreateroom" method="post" onsubmit="createForm_onSubmit(this); return false;">
	            <table class="border">
	                <tr class="border">
	                    <th class="border">토론방 이름</th>
	                    <td><input id="roomName" name="roomName" class="input w-full max-w-xs input-sm" type="text" placeholder="토론방 이름을 입력해주세요"></td>
	                </tr>
	                <tr class="border">
	                    <th colspan="2">
	                        <label>
	                            <input type="radio" name="type" value="1" class="radio radio-success radio-xs" checked/>
	                            채팅 토론
	                        </label>
	                        &nbsp;
	                        <label>
	                            <input type="radio" name="type" value="2" class="radio radio-success radio-xs ">
	                            화상 토론
	                        </label>
	                    </th>
	                </tr>
	                <tr>
	                    <th colspan="2"><button class="btn border m-1">만들기</button></th>
	                </tr>
	            </table>
	        </form>
	    </section>
	</body>
</html>    
