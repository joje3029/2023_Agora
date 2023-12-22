<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<c:set var="pageTitle" value="ARTICLE LIST" />
<!--  여기서 변수 선언헀으니까 haed에서 pageTitle을 title에 넣어서 사용가능.  -->

<%@ include file="../common/head.jsp"%>

<%@ include file="../common/floatingBanner.jsp"%>

<%@ include file="../common/toastUiEditorLib.jsp"%>


<script>
	//여기서 할꺼- 댓글씨
	// 작성 버튼 누르면 보내는 친구. 일 잘함.
	const replyForm_onSubmit = function(form) {
		form.reply.value = form.reply.value.trim();
		
		if (form.reply.value.length === 0) {
			alert('댓글을 작성해주세요.');
			form.reply.focus();
			return;
		}
		
		form.submit();
	}
	
	$(document).ready(function() {
		// check를 확인하고 되어있으면 0 아니면 1
		const check = ${check};
		console.log(check);
		if(check===1){
			$("#subscribeBtn").addClass("btn-active");
		}
		
		
		
		
	    // 여기다가 할짓. 댓글관련 글자수 제한과 글자실시간 보여주기
	    const replyInput = $('#reply'); // textarea 
		const textCount = $('#textCount'); // 숫자 올라갈 부분
		const messageDiv = $('#message'); // 메시지 표시 부분
	    const maxLength = 500;
		
	 // textarea의 글자수 제한
	    replyInput.on('input', function() {
	        const currentLength = $(this).val().length;
	        if (currentLength >= maxLength) {
	            $(this).val($(this).val().substring(0, maxLength));
	            messageDiv.text('댓글은 500자 이내 입력 가능합니다.').addClass('text-red-600');
	        } else {
	            messageDiv.text('').removeClass('error');
	        }
	        textCount.text(currentLength + '/' + maxLength);
	    });
	});

		
</script>

<script>
	// 여기서 할꺼 좋아요씨. 해보고 되면 위랑 합칠꺼임. 합치고 하니까 둘다 조져지더라
	
	// 1. 좋아요 버튼을 눌렀을때 일을 하는지 확인
	const likeCheck = function(el) {
		let likeCheck = $(el).hasClass('btn-active');
		$.ajax({
			url: "/usr/recommendPoint/doRecommendPoint", // ajax 요청 날림.
			method: "get", 
			data: {
					"coulumnId" : ${article.id }, //arteicleid
					"recommendBtn" : likeCheck //버튼 상태 : true / false
				},
			dataType: "json", // 답이 text로옴 -> json으로 바꾸고 조건도 josn중 result가 저거인지로 바꾸고 세는건 뒤에서 세서 json에 담아서 줘야지.
			success: function(data) {
				if (data.result === "좋아요 성공") { //온 답의 결과가 이거면 
				
					$("#recommendBtn").addClass("btn-active");
					$("#likeNum").html(data.recommendPoint.count);
				
				} else if (data.result === "좋아요 취소") {
                    // 좋아요 취소 시의 처리
					$("#recommendBtn").removeClass("btn-active");
					$("#likeNum").html(data.recommendPoint.count);
	            } else {
	            	console.error("예상치 못한 응답 : " + data);
	            }
				
			},
			error: function(xhr, status, error) {
				console.error("ERROR : " + status + " - " + error);
			}
		})
	}
</script>
<script>
	// 여기서 할꺼 좋아요씨. 해보고 되면 위랑 합칠꺼임. 합치고 하니까 둘다 조져지더라
	
	// 1. 좋아요 버튼을 눌렀을때 일을 하는지 확인
	const subscribeCheck = function(el) {
		let subscribeCheck = $(el).hasClass('btn-active');
		
		$.ajax({
			url: "/usr/recommendPoint/doSubscribePoint", // ajax 요청 날림.
			method: "get", 
			data: {
					"writerId" : ${article.colmnWrter }, //arteicle 작성자번호
					"recommendBtn" : subscribeCheck //버튼 상태 : true / false
				},
			dataType: "text", // 답이 text로옴
			success: function(data) {
				if (data === "구독 성공") { //온 답의 결과가 이거면 
					$("#subscribeBtn").addClass("btn-active");
				
				} else if (data === "구독 취소") {
                    // 좋아요 취소 시의 처리
					$("#subscribeBtn").removeClass("btn-active");
	            } else {
	            	console.error("예상치 못한 응답 : " + data);
	            }
				
			},
			error: function(xhr, status, error) {
				console.error("ERROR : " + status + " - " + error);
			}
		})
	}
	
</script>

<section class="listBody">
        <section class="title-section border p-3 font-semibold">
            <div class="title text-3xl">${article.title }</div>
	            <div class="alarm-session w-96">
	              <div class="backColorGreen">좋아요 갯수 : <span id="likeNum">${recommendPoint.count}</span></div><!-- 좋아요 갯수 보일 곳. -->
            <c:if  test="${rq.getLoginedMemberId() != 0 }">
	            <!-- 수정이랑 삭제는 권한인놈만 보이게 수정함. -->
	             <c:if test="${rq.getLoginedMemberId() != null && rq.getLoginedMemberId() != article.colmnWrter }">
	                <div class="btn" id="recommendBtn" onclick="likeCheck(this);" >좋아요 버튼</div>
	                <div class="btn" id="subscribeBtn" onclick="subscribeCheck(this)">구독 버튼</div>
	             </c:if>
	             <c:if test="${rq.getLoginedMemberId() != null && rq.getLoginedMemberId() == article.colmnWrter }">
	            	<div class="btn"><a href="/usr/article/modify?id=${article.id }">수정하기</a></div>
	            	<div class="btn"><a class="" href="doDelete?id=${article.id }" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제하기</a></div>
	        	</c:if>
	        </c:if>
	            </div>
        </section>
        <section class="article-read">
            <div class="toast-ui-viewer">
            <script type="text/x-template">${article.getForPrintBody() }</script>
       </section>
       
        <!-- 댓글과 댓글 형태 -->
	    <section class="comment-session p-1 border">
	        <div>[댓글 갯수] <span>${replyCount.getCount() }</span></div>
	        <hr class="border border-dashed my-2">
        	<c:if  test="${rq.getLoginedMemberId() != 0 }">
	            
	            <div><!-- 댓글 입력 부분 -->
	                <form action="../reply/doWrite" method="post" onsubmit="replyForm_onSubmit(this); return false;">
	                    <input name="columnId" type="hidden" value="${article.id }" /><!-- 이 글 넘버 보냄. -->
	                    <div class="mt-4 border border-gray-400 rounded-lg p-4">
	                        <div class="mb-2"><span>닉네임: ${nickname } </span></div><!-- 여기 지금은 rq에 pk 번호만 저장되서 이거로 박음 . 수정해야함. 닉네임으로 -->
	                        <textarea id="reply" class="textarea textarea-bordered w-full resize-none" name="reply" placeholder="500자 이내로 댓글을 적어주세요."></textarea>
	                        <div class="flex justify-between ">
	                        	<!-- 글자수 보여주는 부분 -->
	                        	<div class="flex justify-between w-full">
	                        		<div class="w-6/12" id="message"></div>
	                        		<div class="count_bar" id="textCount"></div>
	                        	</div>
	                        	<button class="btn btn-sm">작성</button>
	                        </div>
	                </form>
	            </div>
            </c:if>
            <!-- 입력한 댓글 보여주는 부분 -->
            <div>
            	<c:forEach var="reply" items="${replies }">
				<div class="py-2 pl-2 border border-dashed my-2 ">
					<div class="font-semibold">${reply.writerName }</div>
					<div class="my-1 text-lg ml-2">${reply.getForPrintBody() }</div>
					<div class="text-xs text-gray-400">${reply.answerUpdtTime }</div>
            	</div>
            	</c:forEach>
            </div>
      </section>
	</body>
</html>           
            

